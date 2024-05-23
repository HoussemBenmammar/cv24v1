package fr.univrouen.cv24v1.service;

import fr.univrouen.cv24v1.models.Resume;
import fr.univrouen.cv24v1.dto.ResumeDTO;
import fr.univrouen.cv24v1.dto.ResumeInsertionResultDTO;
import fr.univrouen.cv24v1.dto.AllResumeDTO;
import fr.univrouen.cv24v1.dto.AllResumeDTO.CertificationDTO;
import fr.univrouen.cv24v1.dto.AllResumeDTO.EducationDTO;
import fr.univrouen.cv24v1.dto.AllResumeDTO.ExperienceDTO;
import fr.univrouen.cv24v1.dto.AllResumeDTO.LanguageDTO;
import fr.univrouen.cv24v1.dto.AllResumeDTO.OtherDTO;
import fr.univrouen.cv24v1.repository.ResumeRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.ctc.wstx.shaded.msv.org_isorelax.verifier.Schema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.StringReader;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    public List<ResumeDTO> getAllResumes() {
        return resumeRepository.findAll().stream().map(resume -> 
            new ResumeDTO(
                resume.getId(),
                resume.getIdentite().getGenre(),
                resume.getIdentite().getPrenom(),
                resume.getIdentite().getNom(),
                resume.getObjectif().getDescription(),
                resume.getObjectif().getStatut(),
                resume.getCompetences().getDiplomes().stream()
                    .max((d1, d2) -> d1.getDate().compareTo(d2.getDate()))
                    .orElse(new Resume.Diplome()).getInstitut() // Assuming that the institute name is used as the highest degree detail.
            )
        ).collect(Collectors.toList());
    }
    
    public AllResumeDTO getResumeById(String id) throws Exception {
        Resume resume = resumeRepository.findById(id)
            .orElseThrow(() -> new Exception("Resume not found"));
        return mapToAllResumeDTO(resume);
    }

    private AllResumeDTO mapToAllResumeDTO(Resume resume) {
        AllResumeDTO dto = new AllResumeDTO();
        dto.setIdentite(new AllResumeDTO.Identite(
            resume.getIdentite().getGenre(),
            resume.getIdentite().getNom(),
            resume.getIdentite().getPrenom(),
            resume.getIdentite().getTel(),
            resume.getIdentite().getMel()
        ));
        dto.setObjectif(new AllResumeDTO.Objectif(
            resume.getObjectif().getStatut(),
            resume.getObjectif().getDescription()
        ));
        dto.setExperiences(resume.getProf().stream()
            .map(p -> new AllResumeDTO.ExperienceDTO(p.getDatedeb(), p.getDatefin(), p.getTitre()))
            .collect(Collectors.toList()));
        dto.setCompetences(new AllResumeDTO.Competences(
            resume.getCompetences().getDiplomes().stream()
                .map(e -> new AllResumeDTO.EducationDTO(e.getDate(), e.getInstitut(), e.getNiveau()))
                .collect(Collectors.toList()),
            resume.getCompetences().getCertifications().stream()
                .map(c -> new AllResumeDTO.CertificationDTO(c.getDatedeb(), c.getDatefin(), c.getTitre()))
                .collect(Collectors.toList())
        ));
        dto.setDivers(new AllResumeDTO.Divers(
            resume.getDivers().getLangues().stream()
                .map(l -> new AllResumeDTO.LanguageDTO(l.getLang(), l.getCert(), l.getNivs()))
                .collect(Collectors.toList()),
            resume.getDivers().getAutres().stream()
                .map(o -> new AllResumeDTO.OtherDTO(o.getTitre(), o.getComment()))
                .collect(Collectors.toList())
        ));
        return dto;
    }
    
    public void validateResume(String xmlContent) throws SAXException, IOException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        try {
            // Attempt to load the XSD file from the resources directory
            StreamSource schemaSource = new StreamSource(new ClassPathResource("/xsd/cv24.xsd").getInputStream());
            javax.xml.validation.Schema schema = factory.newSchema(schemaSource);

            // Create a Validator instance, which can be used to validate an XML file
            Validator validator = schema.newValidator();

            // Validate XML against the loaded schema
            validator.validate(new StreamSource(new java.io.StringReader(xmlContent)));
        } catch (IOException e) {
            // Handle and throw a specific IOException if there's an issue accessing the file
            throw new IOException("Error accessing the XSD file: " + e.getMessage(), e);
        } catch (SAXException e) {
            // Handle schema validation exceptions separately
            throw new SAXException("Error validating XML against the schema: " + e.getMessage(), e);
        }
    }

    
    /* public AllResumeDTO insertResume(String resumeXml) throws JAXBException {
        AllResumeDTO resumeDto = convertXmlToDto(resumeXml);

        if (isDuplicate(resumeDto)) {
            throw new IllegalStateException("CV déjà inséré (DUPLICATED)");
        }
        
        Resume resume = convertDtoToEntity(resumeDto); // Convert DTO to entity before saving
        Resume savedResume = resumeRepository.save(resume);
        return convertEntityToDto(savedResume); // Convert entity back to DTO to return
    }
    */
    
    public ResumeInsertionResultDTO insertResume(String resumeXml) throws JAXBException {
        AllResumeDTO resumeDto = convertXmlToDto(resumeXml);

        if (isDuplicate(resumeDto)) {
            throw new IllegalStateException("CV déjà inséré (DUPLICATED)");
        }
        
        Resume resume = convertDtoToEntity(resumeDto);
        Resume savedResume = resumeRepository.save(resume);
        
        return new ResumeInsertionResultDTO(convertEntityToDto(savedResume), savedResume.getId());
    }
     
    
    private boolean isDuplicate(AllResumeDTO resumeDto) {
        // Implement logic to check if an entry with the same identity details already exists
        return resumeRepository.existsByIdentiteGenreAndIdentiteNomAndIdentitePrenomAndIdentiteTel(
            resumeDto.getIdentite().getGenre(),
            resumeDto.getIdentite().getNom(),
            resumeDto.getIdentite().getPrenom(),
            resumeDto.getIdentite().getTel()
        );
    }

    private AllResumeDTO convertXmlToDto(String xml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(AllResumeDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        //unmarshaller.setProperty("jaxb.noNamespaceSchemaLocation", "/src/main/resources/xsd/cv24.xsd");
        return (AllResumeDTO) unmarshaller.unmarshal(new StringReader(xml));
    }
    
    private Resume convertDtoToEntity(AllResumeDTO dto) {
        Resume resume = new Resume();
        
        // Vérifiez si Identite est null et initialisez-le si nécessaire
        if (dto.getIdentite() != null) {
            Resume.Identite identite = new Resume.Identite();
            identite.setGenre(dto.getIdentite().getGenre());
            identite.setNom(dto.getIdentite().getNom());
            identite.setPrenom(dto.getIdentite().getPrenom());
            identite.setTel(dto.getIdentite().getTel());
            identite.setMel(dto.getIdentite().getMel());
            resume.setIdentite(identite);
        }

        // Vérifiez si Objectif est null et initialisez-le si nécessaire
        if (dto.getObjectif() != null) {
           System.out.print("dkhall hna f objectf");
            Resume.Objectif objectif = new Resume.Objectif();
            objectif.setDescription(dto.getObjectif().getDescription());
            objectif.setStatut(dto.getObjectif().getStatut());
            resume.setObjectif(objectif);
        }

        // Vérifiez si Prof est null et initialisez-le si nécessaire
        if (dto.getExperiences() != null) {
            List<Resume.Prof> profs = new ArrayList<>();
            for (ExperienceDTO experienceDTO : dto.getExperiences()) {
                Resume.Prof prof = new Resume.Prof();
                prof.setDatedeb(experienceDTO.getStartDate());
                prof.setDatefin(experienceDTO.getEndDate());
                prof.setTitre(experienceDTO.getTitle());
                profs.add(prof);
            }
            resume.setProf(profs);
        }

        // Vérifiez si Competences est null et initialisez-le si nécessaire
        if (dto.getCompetences() != null) {
            Resume.Competences competences = new Resume.Competences();
            List<Resume.Diplome> diplomes = new ArrayList<>();
            
            // Vérifier si la liste education n'est pas null et itérer si elle contient des éléments
            if (dto.getCompetences().getEducation() != null) {
                for (EducationDTO educationDTO : dto.getCompetences().getEducation()) {
                    Resume.Diplome diplome = new Resume.Diplome();
                    diplome.setDate(educationDTO.getDate());
                    diplome.setInstitut(educationDTO.getInstitute());
                    diplome.setNiveau(educationDTO.getLevel());
                    diplomes.add(diplome);
                }
                competences.setDiplomes(diplomes);
            } else {
                // Initialiser diplomes à une liste vide si getEducation() est null
                competences.setDiplomes(new ArrayList<>());
            }

            // Vérifier si la liste certifications n'est pas null et itérer si elle contient des éléments
            if (dto.getCompetences().getCertifications() != null) {
                List<Resume.Certification> certifications = new ArrayList<>();
                for (CertificationDTO certificationDTO : dto.getCompetences().getCertifications()) {
                    Resume.Certification certification = new Resume.Certification();
                    certification.setDatedeb(certificationDTO.getStartDate());
                    certification.setDatefin(certificationDTO.getEndDate());
                    certification.setTitre(certificationDTO.getTitle());
                    certifications.add(certification);
                }
                competences.setCertifications(certifications);
            } else {
                // Initialiser certifications à une liste vide si getCertifications() est null
                competences.setCertifications(new ArrayList<>());
            }

            resume.setCompetences(competences);
        }


        // Vérifiez si Divers est null et initialisez-le si nécessaire
        if (dto.getDivers() != null) {
            Resume.Divers divers = new Resume.Divers();
            
            // Vérifier si la liste de langues n'est pas null avant de l'itérer
            List<Resume.Langue> langues = new ArrayList<>();
            if (dto.getDivers().getLanguages() != null) {
                for (LanguageDTO languageDTO : dto.getDivers().getLanguages()) {
                    Resume.Langue langue = new Resume.Langue();
                    langue.setLang(languageDTO.getLanguage());
                    langue.setCert(languageDTO.getCertification());
                    langue.setNivs(languageDTO.getLevel());
                    langues.add(langue);
                }
            }
            divers.setLangues(langues);

            // Vérifier si la liste d'autres informations n'est pas null avant de l'itérer
            List<Resume.Autre> autres = new ArrayList<>();
            if (dto.getDivers().getOthers() != null) {
                for (OtherDTO otherDTO : dto.getDivers().getOthers()) {
                    Resume.Autre autre = new Resume.Autre();
                    autre.setTitre(otherDTO.getTitle());
                    autre.setComment(otherDTO.getComment());
                    autres.add(autre);
                }
            }
            divers.setAutres(autres);

            resume.setDivers(divers);
        }


        // Retourne le Resume complété
        return resume;
    }

    // Method to convert Entity back to DTO (for returning in responses)
    private AllResumeDTO convertEntityToDto(Resume resume) {
        AllResumeDTO dto = new AllResumeDTO();
        AllResumeDTO.Identite identite = new AllResumeDTO.Identite(
        		resume.getIdentite().getGenre(),
        		resume.getIdentite().getNom(),
        		resume.getIdentite().getPrenom(),
        		resume.getIdentite().getTel(),
        		resume.getIdentite().getMel()
        );
        dto.setIdentite(identite);
        // Continue mapping all other fields similarly
        return dto;
    }
    
    public boolean deleteResume(String id) {
        if (resumeRepository.existsById(id)) {
            resumeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    

   

}
