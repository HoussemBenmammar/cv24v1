package fr.univrouen.cv24v1.dto;

public class ResumeDTO {
    private String id;
    private String genre;
    private String prenom;
    private String nom;
    private String objectif;
    private String statusObjectif;
    private String diplomeLePlusEleve;

    // Constructors, getters, and setters
    public ResumeDTO(String id, String genre, String prenom, String nom, String objectif, String statusObjectif, String diplomeLePlusEleve) {
        this.setId(id);
        this.setGenre(genre);
        this.setPrenom(prenom);
        this.setNom(nom);
        this.setObjectif(objectif);
        this.setStatusObjectif(statusObjectif);
        this.setDiplomeLePlusEleve(diplomeLePlusEleve);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public String getStatusObjectif() {
		return statusObjectif;
	}

	public void setStatusObjectif(String statusObjectif) {
		this.statusObjectif = statusObjectif;
	}

	public String getDiplomeLePlusEleve() {
		return diplomeLePlusEleve;
	}

	public void setDiplomeLePlusEleve(String diplomeLePlusEleve) {
		this.diplomeLePlusEleve = diplomeLePlusEleve;
	}

    // Getters and setters
    // Add all necessary getters and setters here
}
