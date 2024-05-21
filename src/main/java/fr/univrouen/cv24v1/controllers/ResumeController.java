/* package fr.univrouen.cv24v1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;

import fr.univrouen.cv24v1.dto.ResumeDTO;
import fr.univrouen.cv24v1.service.ResumeService;

import java.util.List;

@Controller  // Changed to Controller to support view returns
@RequestMapping("/cv24/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    // This method returns XML, so we specify @ResponseBody to make it clear that it is part of the REST API.
    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public List<ResumeDTO> getAllResumesXml() {
        return resumeService.getAllResumes();
    }

    // New endpoint to return HTML page
    @GetMapping
    public String getAllResumesHtml(Model model) {
        List<ResumeDTO> resumes = resumeService.getAllResumes();
        model.addAttribute("resumes", resumes);
        return "resumes"; // Return the name of the Thymeleaf template
    }
}
*/