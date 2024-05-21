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
        // Mapping logic, map all fields
        AllResumeDTO dto = new AllResumeDTO(/* parameters mapping fields from resume to dto */);
        // Implement field mapping here, similar to previous example
        return dto;
    }
    
   

}
