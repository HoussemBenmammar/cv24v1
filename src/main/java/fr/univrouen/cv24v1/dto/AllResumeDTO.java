package fr.univrouen.cv24v1.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "cv24", namespace = "http://univ.fr/cv24")
@XmlAccessorType(XmlAccessType.FIELD)
public class AllResumeDTO {
    @XmlElement(name = "identite")
    private Identite identite;
    @XmlElement(name = "objectif")
    private Objectif objectif;
    @XmlElementWrapper(name = "prof")
    @XmlElement(name = "detail")
    private List<ExperienceDTO> experiences;
    @XmlElement(name = "competences")
    private Competences competences;
    @XmlElement(name = "divers")
    private Divers divers;
    
    // Getters and Setters for AllResumeDTO
    public Identite getIdentite() { return identite; }
    public void setIdentite(Identite identite) { this.identite = identite; }

    public Objectif getObjectif() { return objectif; }
    public void setObjectif(Objectif objectif) { this.objectif = objectif; }

    public List<ExperienceDTO> getExperiences() { return experiences; }
    public void setExperiences(List<ExperienceDTO> experiences) { this.experiences = experiences; }

    public Competences getCompetences() { return competences; }
    public void setCompetences(Competences competences) { this.competences = competences; }

    public Divers getDivers() { return divers; }
    public void setDivers(Divers divers) { this.divers = divers; }

    // Nested classes with corresponding XML annotations
    public static class Identite {
        @XmlElement(name = "genre")
        private String genre;
        @XmlElement(name = "nom")
        private String nom;
        @XmlElement(name = "prenom")
        private String prenom;
        @XmlElement(name = "tel")
        private String tel;
        @XmlElement(name = "mel")
        private String mel;
        
        public Identite(String genre, String nom, String prenom, String tel, String mel) {
            this.genre = genre;
            this.nom = nom;
            this.prenom = prenom;
            this.tel = tel;
            this.mel = mel;
        }
        
        // Getters and Setters for Identite
        public String getGenre() { return genre; }
        public void setGenre(String genre) { this.genre = genre; }
        public String getNom() { return nom; }
        public void setNom(String nom) { this.nom = nom; }
        public String getPrenom() { return prenom; }
        public void setPrenom(String prenom) { this.prenom = prenom; }
        public String getTel() { return tel; }
        public void setTel(String tel) { this.tel = tel; }
        public String getMel() { return mel; }
        public void setMel(String mel) { this.mel = mel; }

        // getters and setters
    }

    public static class Objectif {
        @XmlAttribute(name = "statut")
        private String statut;
        @XmlValue
        private String description;
        
        public Objectif(String statut, String description) {
            this.statut = statut;
            this.description = description;
        }

        // Getters and Setters
        public String getStatut() { return statut; }
        public void setStatut(String statut) { this.statut = statut; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public static class Competences {
        @XmlElementWrapper(name = "diplomes")
        @XmlElement(name = "diplome")
        private List<EducationDTO> diplomes;
        @XmlElementWrapper(name = "certifications")
        @XmlElement(name = "certif")
        private List<CertificationDTO> certifications;
        
        public Competences(List<EducationDTO> diplomes, List<CertificationDTO> certifications) {
            this.diplomes = diplomes;
            this.certifications = certifications;
        }

        // Getters and setters
        public List<EducationDTO> getDiplomes() { return diplomes; }
        public void setDiplomes(List<EducationDTO> diplomes) { this.diplomes = diplomes; }
        public List<CertificationDTO> getCertifications() { return certifications; }
        public void setCertifications(List<CertificationDTO> certifications) { this.certifications = certifications; }
    }

    public static class Divers {
        @XmlElementWrapper(name = "langues")
        @XmlElement(name = "lv")
        private List<LanguageDTO> langues;
        @XmlElementWrapper(name = "autres")
        @XmlElement(name = "autre")
        private List<OtherDTO> autres;
        
        public Divers(List<LanguageDTO> langues, List<OtherDTO> autres) {
            this.langues = langues;
            this.autres = autres;
        }

        // Getters and setters
        public List<LanguageDTO> getLangues() { return langues; }
        public void setLangues(List<LanguageDTO> langues) { this.langues = langues; }
        public List<OtherDTO> getAutres() { return autres; }
        public void setAutres(List<OtherDTO> autres) { this.autres = autres; }
    }

    public static class ExperienceDTO {
        @XmlElement(name = "datedeb")
        private String datedeb;
        @XmlElement(name = "datefin")
        private String datefin;
        @XmlElement(name = "titre")
        private String titre;
        
        public ExperienceDTO(String datedeb, String datefin, String titre) {
            this.datedeb = datedeb;
            this.datefin = datefin;
            this.titre = titre;
        }

        // Getters and setters
        public String getDatedeb() { return datedeb; }
        public void setDatedeb(String datedeb) { this.datedeb = datedeb; }
        public String getDatefin() { return datefin; }
        public void setDatefin(String datefin) { this.datefin = datefin; }
        public String getTitre() { return titre; }
        public void setTitre(String titre) { this.titre = titre; }
    }

    public static class EducationDTO {
        @XmlElement(name = "date")
        private String date;
        @XmlElement(name = "institut")
        private String institut;
        @XmlAttribute(name = "niveau")
        private int niveau;
        
        public EducationDTO(String date, String institut, int niveau) {
            this.date = date;
            this.institut = institut;
            this.niveau = niveau;
        }

        // Getters and setters
        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
        public String getInstitut() { return institut; }
        public void setInstitut(String institut) { this.institut = institut; }
        public int getNiveau() { return niveau; }
        public void setNiveau(int niveau) { this.niveau = niveau; }
    }

    public static class CertificationDTO {
        @XmlElement(name = "datedeb")
        private String datedeb;
        @XmlElement(name = "datefin")
        private String datefin;
        @XmlElement(name = "titre")
        private String titre;
        
        public CertificationDTO(String datedeb, String datefin, String titre) {
            this.datedeb = datedeb;
            this.datefin = datefin;
            this.titre = titre;
        }

        // Getters and setters
        public String getDatedeb() { return datedeb; }
        public void setDatedeb(String datedeb) { this.datedeb = datedeb; }
        public String getDatefin() { return datefin; }
        public void setDatefin(String datefin) { this.datefin = datefin; }
        public String getTitre() { return titre; }
        public void setTitre(String titre) { this.titre = titre; }
    }

    public static class LanguageDTO {
        @XmlAttribute(name = "lang")
        private String lang;
        @XmlAttribute(name = "cert")
        private String cert;
        @XmlAttribute(name = "nivs")
        private String nivs;
        
        public LanguageDTO(String lang, String cert, String nivs) {
            this.lang = lang;
            this.cert = cert;
            this.nivs = nivs;
        }

        // Getters and setters
        public String getLang() { return lang; }
        public void setLang(String lang) { this.lang = lang; }
        public String getCert() { return cert; }
        public void setCert(String cert) { this.cert = cert; }
        public String getNivs() { return nivs; }
        public void setNivs(String nivs) { this.nivs = nivs; }
    }

    public static class OtherDTO {
        @XmlAttribute(name = "titre")
        private String titre;
        @XmlAttribute(name = "comment")
        private String comment;
        
        public OtherDTO(String titre, String comment) {
            this.titre = titre;
            this.comment = comment;
        }

        // Getters and setters
        public String getTitre() { return titre; }
        public void setTitre(String titre) { this.titre = titre; }
        public String getComment() { return comment; }
        public void setComment(String comment) { this.comment = comment; }
    }
}
