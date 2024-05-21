/* package fr.univrouen.cv24v1.seeder;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fr.univrouen.cv24v1.models.Resume;
import fr.univrouen.cv24v1.repository.ResumeRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    @Autowired
    private ResumeRepository resumeRepository;

    
    @Override
    public void run(String... args) throws Exception {
      
Resume resume = new Resume();
        
        Resume.Identite identite = new Resume.Identite();
        identite.setNom("hamitou");
        identite.setPrenom("yacine");
        identite.setGenre("Male");
        resume.setIdentite(identite);
        
        Resume.Objectif objectif = new Resume.Objectif();
        objectif.setDescription("Description de l'objectif");
        objectif.setStatut("Statut de l'objectif");
        resume.setObjectif(objectif);
        
        Resume.Prof prof = new Resume.Prof();
        prof.setTitre("Software Engineer");
        prof.setDatedeb("2021-01");
        prof.setDatefin("2022-12");
        resume.setProf(Arrays.asList(prof));
        
        Resume.Competences competences = new Resume.Competences();
        Resume.Diplome diplome = new Resume.Diplome();
        diplome.setDate("2020");
        diplome.setInstitut("University of Example");
        diplome.setNiveau(1);
        competences.setDiplomes(Arrays.asList(diplome));
        resume.setCompetences(competences);
        
        Resume.Divers divers = new Resume.Divers();
        Resume.Langue langue = new Resume.Langue();
        langue.setLang("English");
        langue.setCert("Certified");
        langue.setNivs("Advanced");
        divers.setLangues(Arrays.asList(langue));
        resume.setDivers(divers);
 

        // Save the resume to MongoDB
        resumeRepository.save(resume);
    }
}
*/

