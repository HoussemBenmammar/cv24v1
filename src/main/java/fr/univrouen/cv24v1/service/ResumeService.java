package fr.univrouen.cv24v1.service;

import fr.univrouen.cv24v1.models.Resume;
import fr.univrouen.cv24v1.dto.ResumeDTO;
import fr.univrouen.cv24v1.dto.AllResumeDTO;
import fr.univrouen.cv24v1.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
   

}
