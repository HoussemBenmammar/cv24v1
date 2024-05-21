package fr.univrouen.cv24v1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import fr.univrouen.cv24v1.dto.AllResumeDTO;
import fr.univrouen.cv24v1.dto.ResumeDTO;
import fr.univrouen.cv24v1.service.ResumeService;

@Controller
@RequestMapping("/cv24/resume")
public class ResumeGetController {

    @Autowired
    private ResumeService resumeService;

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public List<ResumeDTO> getAllResumesXml() {
        return resumeService.getAllResumes();
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getAllResumesHtml(Model model) {
        List<ResumeDTO> resumes = resumeService.getAllResumes();
        model.addAttribute("resumes", resumes);
        return "resumes";
    }
    
    @GetMapping(value = "/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getResumeByIdXml(@PathVariable String id) {
        try {
            AllResumeDTO resume = resumeService.getResumeById(id);
            
            return ResponseEntity.ok(resume);
        } catch (Exception e) {
            String errorResponse = String.format("<error><id>%s</id><status>ERROR</status><message>Resume not found</message></error>", id);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }


}
