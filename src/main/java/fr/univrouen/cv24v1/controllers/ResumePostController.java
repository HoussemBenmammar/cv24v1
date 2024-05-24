package fr.univrouen.cv24v1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import fr.univrouen.cv24v1.dto.AllResumeDTO;
import fr.univrouen.cv24v1.dto.ResumeInsertionResultDTO;
import fr.univrouen.cv24v1.service.ResumeService;

import java.io.IOException;

@RestController
@RequestMapping("/cv24")
public class ResumePostController {

	private final ResumeService resumeService;

    public ResumePostController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    
    @PostMapping(value = "/insert", consumes = "application/xml", produces = "application/xml")
    public ResponseEntity<?> insertResume(@RequestBody String resumeXml) {
        try {
        	resumeService.validateResume(resumeXml);
            ResumeInsertionResultDTO result = resumeService.insertResume(resumeXml);
            String responseXml = "<insertResponse><status>INSÉRÉ avec succès</status></insertResponse>";
            return ResponseEntity.ok(responseXml);
        } catch (SAXException | IOException e) {
            return ResponseEntity.badRequest().body("<error><status>ERROR</status><detail> XML INVALIDE " +  e.getMessage() +"</detail></error>");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("<error><status>ERROR</status><detail>" + e.getMessage() + "</detail></error>");
        }
    }

}
