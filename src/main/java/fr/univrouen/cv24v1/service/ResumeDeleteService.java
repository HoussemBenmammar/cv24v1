package fr.univrouen.cv24v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;

import fr.univrouen.cv24v1.models.Resume;
import fr.univrouen.cv24v1.repository.ResumeRepository;

@Service
public class ResumeDeleteService {
	
	  @Autowired
	   private ResumeService resumeService;
    
    @Autowired
    private ResumeRepository resumeRepository;

    public boolean deleteResume(String id) {
        int idInt = Integer.parseInt(id);
        Optional<Resume> resumeOptional = resumeService.existsByIdResume(idInt);
        if (resumeOptional.isPresent()) {
            Object logger;
			try {
                resumeRepository.deleteByResumeId(idInt);
                System.out.print("Deleted resume with ID: {}"+ idInt);
                return true;
            } catch (Exception e) {
            	System.out.print("Failed to delete resume with ID: {}: {}"+  e.getMessage());
                return false;
            }
        }
        return false;
    }


}
