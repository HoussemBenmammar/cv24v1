package fr.univrouen.cv24v1.dto;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "cv24", namespace = "http://univ.fr/cv24")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CV24Type", namespace = "http://univ.fr/cv24")
public class AllResumeDTO {
    @XmlElement(name = "identite", namespace = "http://univ.fr/cv24")
    private Identite identite;

    @XmlElement(name = "objectif", namespace = "http://univ.fr/cv24")
    private Objectif objectif;

    @XmlElementWrapper(name = "prof", namespace = "http://univ.fr/cv24")
    @XmlElement(name = "detail", namespace = "http://univ.fr/cv24")
    private List<ExperienceDTO> experiences;

    @XmlElement(name = "competences", namespace = "http://univ.fr/cv24")
    private Competences competences;

    @XmlElement(name = "divers", namespace = "http://univ.fr/cv24")
    private Divers divers;

    // Constructors
    public AllResumeDTO() {}

    public AllResumeDTO(Identite identite, Objectif objectif, List<ExperienceDTO> experiences, Competences competences, Divers divers) {
        this.identite = identite;
        this.objectif = objectif;
        this.experiences = experiences;
        this.competences = competences;
        this.divers = divers;
    }

    // Getters and setters
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

    // Identite nested class
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Identite {
        @XmlElement(name = "genre", namespace = "http://univ.fr/cv24")
        private String genre;
        @XmlElement(name = "nom", namespace = "http://univ.fr/cv24")
        private String nom;
        @XmlElement(name = "prenom", namespace = "http://univ.fr/cv24")
        private String prenom;
        @XmlElement(name = "tel", namespace = "http://univ.fr/cv24")
        private String tel;
        @XmlElement(name = "mel", namespace = "http://univ.fr/cv24")
        private String mel;

        // Constructors
        public Identite() {}

        public Identite(String genre, String nom, String prenom, String tel, String mel) {
            this.genre = genre;
            this.nom = nom;
            this.prenom = prenom;
            this.tel = tel;
            this.mel = mel;
        }

        // Getters and setters
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
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Objectif {
        @XmlAttribute(name = "statut")
        private String statut;

        @XmlValue  // Utilisez XmlValue pour capturer le contenu textuel de l'élément
        private String description;

        // Constructors
        public Objectif() {}

        public Objectif(String statut, String description) {
            this.statut = statut;
            this.description = description;
        }

        // Getters and setters
        public String getStatut() { return statut; }
        public void setStatut(String statut) { this.statut = statut; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }


    // ExperienceDTO nested class
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ExperienceDTO {
        @XmlElement(name = "datedeb", namespace = "http://univ.fr/cv24")
        private String startDate;
        @XmlElement(name = "datefin", namespace = "http://univ.fr/cv24")
        private String endDate;
        @XmlElement(name = "titre", namespace = "http://univ.fr/cv24")
        private String title;

        // Constructors
        public ExperienceDTO() {}

        public ExperienceDTO(String startDate, String endDate, String title) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
        }

        // Getters and setters
        public String getStartDate() { return startDate; }
        public void setStartDate(String startDate) { this.startDate = startDate; }

        public String getEndDate() { return endDate; }
        public void setEndDate(String endDate) { this.endDate = endDate; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
    }

    // Other nested classes (EducationDTO, CertificationDTO, LanguageDTO, and OtherDTO) would follow a similar pattern.

    // Competences nested class
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Competences {
        @XmlElement(name = "diplome", namespace = "http://univ.fr/cv24")
        private List<EducationDTO> education;
        @XmlElement(name = "certif", namespace = "http://univ.fr/cv24")
        private List<CertificationDTO> certifications;

        // Constructors
        public Competences() {}

        public Competences(List<EducationDTO> education, List<CertificationDTO> certifications) {
            this.education = education;
            this.certifications = certifications;
        }

        // Getters and setters
        public List<EducationDTO> getEducation() { return education; }
        public void setEducation(List<EducationDTO> education) { this.education = education; }

        public List<CertificationDTO> getCertifications() { return certifications; }
        public void setCertifications(List<CertificationDTO> certifications) { this.certifications = certifications; }
    }

    // Divers nested class
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Divers {
        // @XmlElementWrapper(name = "divers", namespace = "http://univ.fr/cv24")
        @XmlElement(name = "lv", namespace = "http://univ.fr/cv24")
        private List<LanguageDTO> languages;
        @XmlElement(name = "autre", namespace = "http://univ.fr/cv24")
        private List<OtherDTO> others;

        // Constructors
        public Divers() {}

        public Divers(List<LanguageDTO> languages, List<OtherDTO> others) {
            this.languages = languages;
            this.others = others;
        }

        // Getters and setters
        public List<LanguageDTO> getLanguages() { return languages; }
        public void setLanguages(List<LanguageDTO> languages) { this.languages = languages; }

        public List<OtherDTO> getOthers() { return others; }
        public void setOthers(List<OtherDTO> others) { this.others = others; }
    }

    // EducationDTO nested class
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class EducationDTO {
        @XmlElement(name = "date", namespace = "http://univ.fr/cv24")
        private String date;
        @XmlElement(name = "institut", namespace = "http://univ.fr/cv24")
        private String institute;
        @XmlAttribute(name = "niveau")
        private int level;

        // Constructors
        public EducationDTO() {}

        public EducationDTO(String date, String institute, int level) {
            this.date = date;
            this.institute = institute;
            this.level = level;
        }

        // Getters and setters
        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }

        public String getInstitute() { return institute; }
        public void setInstitute(String institute) { this.institute = institute; }

        public int getLevel() { return level; }
        public void setLevel(int level) { this.level = level; }
    }

    // CertificationDTO nested class
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CertificationDTO {
        @XmlElement(name = "datedeb", namespace = "http://univ.fr/cv24")
        private String startDate;
        @XmlElement(name = "datefin", namespace = "http://univ.fr/cv24")
        private String endDate;
        @XmlElement(name = "titre", namespace = "http://univ.fr/cv24")
        private String title;

        // Constructors
        public CertificationDTO() {}

        public CertificationDTO(String startDate, String endDate, String title) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
        }

        // Getters and setters
        public String getStartDate() { return startDate; }
        public void setStartDate(String startDate) { this.startDate = startDate; }

        public String getEndDate() { return endDate; }
        public void setEndDate(String endDate) { this.endDate = endDate; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
    }

    // LanguageDTO nested class
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class LanguageDTO {
        @XmlAttribute(name = "lang")
        private String language;
        @XmlAttribute(name = "cert")
        private String certification;
        @XmlAttribute(name = "nivs")
        private String level;

        // Constructors
        public LanguageDTO() {}

        public LanguageDTO(String language, String certification, String level) {
            this.language = language;
            this.certification = certification;
            this.level = level;
        }

        // Getters and setters
        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }

        public String getCertification() { return certification; }
        public void setCertification(String certification) { this.certification = certification; }

        public String getLevel() { return level; }
        public void setLevel(String level) { this.level = level; }
    }

    // OtherDTO nested class
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class OtherDTO {
        @XmlAttribute(name = "titre", namespace = "http://univ.fr/cv24")
        private String title;
        @XmlAttribute(name = "comment", namespace = "http://univ.fr/cv24")
        private String comment;

        // Constructors
        public OtherDTO() {}

        public OtherDTO(String title, String comment) {
            this.title = title;
            this.comment = comment;
        }

        // Getters and setters
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getComment() { return comment; }
        public void setComment(String comment) { this.comment = comment; }
    }
}
