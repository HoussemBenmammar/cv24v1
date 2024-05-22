package fr.univrouen.cv24v1.service;

import fr.univrouen.cv24v1.models.Resume;
import fr.univrouen.cv24v1.dto.ResumeDTO;
import fr.univrouen.cv24v1.dto.AllResumeDTO;
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

    
    public AllResumeDTO insertResume(String resumeXml) throws JAXBException {
        AllResumeDTO resumeDto = convertXmlToDto(resumeXml);

        if (isDuplicate(resumeDto)) {
            throw new IllegalStateException("Duplicate resume found");
        }
        

        Resume resume = convertDtoToEntity(resumeDto); // Convert DTO to entity before saving
        Resume savedResume = resumeRepository.save(resume);
        return convertEntityToDto(savedResume); // Convert entity back to DTO to return
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
    
 // Method to convert DTO to Entity
    private Resume convertDtoToEntity(AllResumeDTO dto) {
    	System.out.println("dkhall m dto l entiti");
        Resume resume = new Resume();
        resume.getIdentite().setGenre(dto.getIdentite().getGenre());
        resume.getIdentite().setNom(dto.getIdentite().getNom());
        resume.getIdentite().setPrenom(dto.getIdentite().getPrenom());
        resume.getIdentite().setTel(dto.getIdentite().getTel());
        resume.getIdentite().setMel(dto.getIdentite().getMel());
        // Continue mapping all other fields similarly
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

    

   

}
