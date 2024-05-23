package fr.univrouen.cv24v1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import fr.univrouen.cv24v1.dto.AllResumeDTO;
import fr.univrouen.cv24v1.dto.ResumeDTO;
import fr.univrouen.cv24v1.exceptions.ErrorDetail;
import fr.univrouen.cv24v1.service.ResumeService;

@Controller
@RequestMapping("/cv24")
public class ResumeGetController {

    @Autowired
    private ResumeService resumeService;

    @GetMapping(value = "/resume/xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public List<ResumeDTO> getAllResumesXml() {
        return resumeService.getAllResumes();
    }
    
    @GetMapping(value = "/resume", produces = MediaType.TEXT_HTML_VALUE)
    public String getAllResumesHtml(Model model) {
        List<ResumeDTO> resumes = resumeService.getAllResumes();
        model.addAttribute("resumes", resumes);
        return "resumes";
    }
    
    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getResumeByIdXml(@RequestParam("id") String id) {
        try {
            AllResumeDTO resume = resumeService.getResumeById(id);
            return ResponseEntity.ok(resume);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetail(id, "Erreur", "Cv non trouvé"));
        }
    }
    
    @GetMapping(value = "/html", produces = MediaType.TEXT_HTML_VALUE)
    public String getResumeByIdHtml(@RequestParam("id") String id, Model model) {
        try {
            AllResumeDTO resume = resumeService.getResumeById(id);
            model.addAttribute("resume", resume);
            return "resumesAll"; 
        } catch (Exception e) {
            model.addAttribute("error", new ErrorDetail(id, "Erreur", "Cv non trouvé"));
            return "error"; 
        }
    }
    



}
