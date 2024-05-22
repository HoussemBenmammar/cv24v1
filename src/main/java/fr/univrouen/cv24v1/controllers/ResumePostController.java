package fr.univrouen.cv24v1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import fr.univrouen.cv24v1.dto.AllResumeDTO;
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
            AllResumeDTO insertedResume = resumeService.insertResume(resumeXml);
            return ResponseEntity.ok(insertedResume);
        } catch (SAXException | IOException e) {
            //return ResponseEntity.badRequest().body("<error><status>ERROR</status><detail>INVALID ddsqdsq</detail></error>");
            return ResponseEntity.badRequest().body("mechy valid" +e);
        } catch (Exception e) {
            //return ResponseEntity.internalServerError().body("<error><status>ERROR</status><detail>UNKNOWN</detail></error>");
        	return ResponseEntity.internalServerError().body("unkonwn"+e);
        }
    }
}
