package fr.univrouen.cv24v1.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import fr.univrouen.cv24v1.dto.AllResumeDTO;
import fr.univrouen.cv24v1.dto.AllResumeDTO.CertificationDTO;
import fr.univrouen.cv24v1.dto.AllResumeDTO.Competences;
import fr.univrouen.cv24v1.dto.AllResumeDTO.Divers;
import fr.univrouen.cv24v1.dto.AllResumeDTO.EducationDTO;
import fr.univrouen.cv24v1.dto.AllResumeDTO.ExperienceDTO;
import fr.univrouen.cv24v1.dto.AllResumeDTO.Identite;
import fr.univrouen.cv24v1.dto.AllResumeDTO.LanguageDTO;
import fr.univrouen.cv24v1.dto.AllResumeDTO.Objectif;
import fr.univrouen.cv24v1.dto.AllResumeDTO.OtherDTO;
import fr.univrouen.cv24v1.dto.ResumeDTO;
import fr.univrouen.cv24v1.exceptions.ErrorDetail;
import fr.univrouen.cv24v1.models.Resume;
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
    public ResponseEntity<?> getResumeByIdXml(@RequestParam("id") String idString) {
        try {
            int id = Integer.parseInt(idString);
            Optional<Resume> resumeOptional = resumeService.existsByIdResume(id);
            if (resumeOptional.isPresent()) {
            	System.out.print(resumeOptional.get().getCompetences());
                AllResumeDTO resumeDto = convertToDto(resumeOptional.get());
                return ResponseEntity.ok(resumeDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorDetail(id, "Erreur", "Cv non trouvé"));
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorDetail(0, "Invalid ID", "L'ID fourni doit être un entier numérique."));
        }
    }

    private AllResumeDTO convertToDto(Resume resume) {
        AllResumeDTO dto = new AllResumeDTO();
        if (resume.getIdentite() != null) {
            Identite identiteDto = new Identite(
                resume.getIdentite().getGenre(),
                resume.getIdentite().getNom(),
                resume.getIdentite().getPrenom(),
                resume.getIdentite().getTel(),
                resume.getIdentite().getMel());
            dto.setIdentite(identiteDto);
        }

        if (resume.getObjectif() != null) {
            Objectif objectifDto = new Objectif(
                resume.getObjectif().getStatut(),
                resume.getObjectif().getDescription());
            dto.setObjectif(objectifDto);
        }

        if (resume.getProf() != null && !resume.getProf().isEmpty()) {
            List<ExperienceDTO> experienceDTOs = resume.getProf().stream()
                .map(prof -> new ExperienceDTO(
                    prof.getDatedeb(),
                    prof.getDatefin(),
                    prof.getTitre()))
                .collect(Collectors.toList());
            dto.setExperiences(experienceDTOs);
        }

        if (resume.getCompetences() != null) {
            Competences competencesDto = new Competences(
                resume.getCompetences().getDiplomes().stream()
                    .map(d -> new EducationDTO(d.getDate(), d.getInstitut(), d.getNiveau()))
                    .collect(Collectors.toList()),
                resume.getCompetences().getCertifications().stream()
                    .map(c -> new CertificationDTO(c.getDatedeb(), c.getDatefin(), c.getTitre()))
                    .collect(Collectors.toList()));
            dto.setCompetences(competencesDto);
        }

        if (resume.getDivers() != null) {
            Divers diversDto = new Divers(
                resume.getDivers().getLangues().stream()
                    .map(lang -> new LanguageDTO(lang.getLang(), lang.getCert(), lang.getNivs()))
                    .collect(Collectors.toList()),
                resume.getDivers().getAutres().stream()
                    .map(autre -> new OtherDTO(autre.getTitre(), autre.getComment()))
                    .collect(Collectors.toList()));
            dto.setDivers(diversDto);
        }

        return dto;
    }

    @GetMapping(value = "/html", produces = MediaType.TEXT_HTML_VALUE)
    public String getResumeByIdHtml(@RequestParam("id") String idString, Model model) {
        int id = 0;  // Initialize id outside try block to ensure it's available in catch blocks
        try {
            id = Integer.parseInt(idString);  // Attempt to convert the ID from String to int
            Optional<Resume> resumeOptional = resumeService.existsByIdResume(id);
            if (resumeOptional.isPresent()) {
                AllResumeDTO resumeDto = convertToDto(resumeOptional.get());
                model.addAttribute("resume", resumeDto);
                return "resumesAll";  // Thymeleaf view name for displaying resume details
            } else {
                model.addAttribute("error", new ErrorDetail(id, "Erreur", "Cv non trouvé"));
                return "error";  // Thymeleaf error view
            }
        } catch (NumberFormatException e) {
            model.addAttribute("error", new ErrorDetail(0, "Invalid ID", "L'ID fourni doit être un entier numérique."));
            return "error";  // Thymeleaf error view for number format exception
        } catch (Exception e) {
            model.addAttribute("error", new ErrorDetail(id, "Erreur", e.getMessage()));
            return "error";  // Thymeleaf error view for general exceptions
        }
    }


    
    @GetMapping("/checkByName")
    public ResponseEntity<String> checkResumeExistsByName(@RequestParam String name) {
        boolean exists = resumeService.existsByName(name);
        if (exists) {
            return ResponseEntity.ok("Un CV avec le nom '" + name + "' existe.");
        } else {
            return ResponseEntity.ok("Aucun CV trouvé avec le nom '" + name + "'.");
        }
    }
    
    /*
    @GetMapping("/checkById")
    public ResponseEntity<String> checkResumeExistsByIdResume(@RequestParam int id) {
        List<Resume> exists = resumeService.existsByIdResume(id);
        if (!exists.isEmpty()) {
            return ResponseEntity.ok("Un CV avec le nom '" + id + "' existe.");
        } else {
            return ResponseEntity.ok("Aucun CV trouvé avec le nom '" + id + "'.");
        }
    }
    */

}
    
   
