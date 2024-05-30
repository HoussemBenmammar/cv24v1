package fr.univrouen.cv24v1.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "resumes")
public class Resume {
    @Id
    private String id; 
    private int idResume;
    private static int compteur = 1;
    private Identite identite;
    private Objectif objectif;
    private List<Prof> prof;
    private Competences competences;
    private Divers divers;

    // Constructors, Getters and Setters
    
    public Resume() {
   	 this.idResume= compteur++; 
   }
  
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Identite getIdentite() {
        return identite;
    }

    public void setIdentite(Identite identite) {
        this.identite = identite;
    }

    public Objectif getObjectif() {
        return objectif;
    }

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
    }

    public List<Prof> getProf() {
        return prof;
    }

    public void setProf(List<Prof> prof) {
        this.prof = prof;
    }

    public Competences getCompetences() {
        return competences;
    }

    public void setCompetences(Competences competences) {
        this.competences = competences;
    }

    public Divers getDivers() {
        return divers;
    }

    public void setDivers(Divers divers) {
        this.divers = divers;
    }

    public int getIdResume() {
		return idResume;
	}

	public void setIdResume(int idResume) {
		this.idResume = idResume;
	}

	// Nested Classes
    public static class Identite {
        private String genre;
        private String nom;
        private String prenom;
        private String tel;
        private String mel;
		public String getGenre() {
			return genre;
		}
		public void setGenre(String genre) {
			this.genre = genre;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getMel() {
			return mel;
		}
		public void setMel(String mel) {
			this.mel = mel;
		}

        // Getters and Setters
    }

    public static class Objectif {
        private String description;
        private String statut;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getStatut() {
			return statut;
		}
		public void setStatut(String statut) {
			this.statut = statut;
		}

        // Getters and Setters
    }

    public static class Prof {
        private String datedeb;
        private String datefin;
        private String titre;
		public String getDatedeb() {
			return datedeb;
		}
		public void setDatedeb(String datedeb) {
			this.datedeb = datedeb;
		}
		public String getDatefin() {
			return datefin;
		}
		public void setDatefin(String datefin) {
			this.datefin = datefin;
		}
		public String getTitre() {
			return titre;
		}
		public void setTitre(String titre) {
			this.titre = titre;
		}

        // Getters and Setters
    }

    public static class Competences {
        private List<Diplome> diplomes;
        private List<Certification> certifications;
		public List<Diplome> getDiplomes() {
			return diplomes;
		}
		public void setDiplomes(List<Diplome> diplomes) {
			this.diplomes = diplomes;
		}
		public List<Certification> getCertifications() {
			return certifications;
		}
		public void setCertifications(List<Certification> certifications) {
			this.certifications = certifications;
		}

        // Getters and Setters
    }

    public static class Diplome {
        private String date;
        private String institut;
        private int niveau;
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getInstitut() {
			return institut;
		}
		public void setInstitut(String institut) {
			this.institut = institut;
		}
		public int getNiveau() {
			return niveau;
		}
		public void setNiveau(int niveau) {
			this.niveau = niveau;
		}

        // Getters and Setters
    }

    public static class Certification {
        private String datedeb;
        private String datefin;
        private String titre;
		public String getDatedeb() {
			return datedeb;
		}
		public void setDatedeb(String datedeb) {
			this.datedeb = datedeb;
		}
		public String getDatefin() {
			return datefin;
		}
		public void setDatefin(String datefin) {
			this.datefin = datefin;
		}
		public String getTitre() {
			return titre;
		}
		public void setTitre(String titre) {
			this.titre = titre;
		}

        // Getters and Setters
    }

    public static class Divers {
        private List<Langue> langues;
        private List<Autre> autres;
		public List<Langue> getLangues() {
			return langues;
		}
		public void setLangues(List<Langue> langues) {
			this.langues = langues;
		}
		public List<Autre> getAutres() {
			return autres;
		}
		public void setAutres(List<Autre> autres) {
			this.autres = autres;
		}

        // Getters and Setters
    }

    public static class Langue {
        private String lang;
        private String cert;
        private String nivs;
		public String getLang() {
			return lang;
		}
		public void setLang(String lang) {
			this.lang = lang;
		}
		public String getCert() {
			return cert;
		}
		public void setCert(String cert) {
			this.cert = cert;
		}
		public String getNivs() {
			return nivs;
		}
		public void setNivs(String nivs) {
			this.nivs = nivs;
		}

        // Getters and Setters
    }

    public static class Autre {
        private String titre;
        private String comment;
		public String getTitre() {
			return titre;
		}
		public void setTitre(String titre) {
			this.titre = titre;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}

        // Getters and Setters
    }
}