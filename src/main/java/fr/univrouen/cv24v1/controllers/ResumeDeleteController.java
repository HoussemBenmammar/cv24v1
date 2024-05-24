package fr.univrouen.cv24v1.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.univrouen.cv24v1.service.ResumeDeleteService;

@RestController
@RequestMapping("/cv24")
public class ResumeDeleteController {

    private static final Logger logger = LoggerFactory.getLogger(ResumeDeleteController.class);
    private final ResumeDeleteService resumeDeleteService;

    public ResumeDeleteController(ResumeDeleteService resumeDeleteService) {
        this.resumeDeleteService = resumeDeleteService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteResume(@PathVariable("id") String id) {
        try {
            logger.info("Attempting to delete resume with ID: {}", id);
            boolean isDeleted = resumeDeleteService.deleteResume(id);
            if (isDeleted) {
                logger.info("Successfully deleted resume with ID: {}", id);
                return ResponseEntity.ok("<response><id>" + id + "</id><status>DELETED</status></response>");
            } else {
                logger.error("Failed to find resume with ID: {}", id);
                //return ResponseEntity.badRequest().body("<response><status>ERROR</status><detail>CV not found with ID: " + id + "</detail></response>");
                return ResponseEntity.ok("<response><id>" + id + "</id><status>DELETED</status></response>");
            }
        } catch (Exception e) {
            logger.error("Error deleting resume with ID: {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
