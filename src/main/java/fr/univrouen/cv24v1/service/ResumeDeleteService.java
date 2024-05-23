package fr.univrouen.cv24v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.univrouen.cv24v1.repository.ResumeRepository;

@Service
public class ResumeDeleteService {
    
    @Autowired
    private ResumeRepository resumeRepository;

    public boolean deleteResume(String id) {
    	System.out.print("result " + id);
        if (resumeRepository.existsById(id)) {
            resumeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
