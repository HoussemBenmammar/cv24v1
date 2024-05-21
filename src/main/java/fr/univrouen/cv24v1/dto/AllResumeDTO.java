package fr.univrouen.cv24v1.dto;

import java.util.List;

public class AllResumeDTO {
    private String id;
    private String genre;
    private String prenom;
    private String nom;
    private String objectif;
    private String statusObjectif;
    private String diplomeLePlusEleve;
    private List<EducationDTO> education;
    private List<ExperienceDTO> experiences;
    private List<SkillDTO> skills;
    private List<CertificationDTO> certifications;

    // Additional fields and possibly constructors, getters, and setters

    public static class EducationDTO {
        private String degree;
        private String university;
        private int year;
        // Constructors, getters, and setters
		public String getDegree() {
			return degree;
		}
		public void setDegree(String degree) {
			this.degree = degree;
		}
		public String getUniversity() {
			return university;
		}
		public void setUniversity(String university) {
			this.university = university;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
    }

    public static class ExperienceDTO {
        private String company;
        private String position;
        private String startDate;
        private String endDate;
        // Constructors, getters, and setters
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
    }

    public static class SkillDTO {
        private String skillName;
        // Constructors, getters, and setters

		public String getSkillName() {
			return skillName;
		}

		public void setSkillName(String skillName) {
			this.skillName = skillName;
		}
    }

    public static class CertificationDTO {
        private String certificationName;
        private String issuedBy;
        private String issueDate;
        // Constructors, getters, and setters
		public String getCertificationName() {
			return certificationName;
		}
		public void setCertificationName(String certificationName) {
			this.certificationName = certificationName;
		}
		public String getIssuedBy() {
			return issuedBy;
		}
		public void setIssuedBy(String issuedBy) {
			this.issuedBy = issuedBy;
		}
		public String getIssueDate() {
			return issueDate;
		}
		public void setIssueDate(String issueDate) {
			this.issueDate = issueDate;
		}
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

	public List<EducationDTO> getEducation() {
		return education;
	}

	public void setEducation(List<EducationDTO> education) {
		this.education = education;
	}

	public List<ExperienceDTO> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<ExperienceDTO> experiences) {
		this.experiences = experiences;
	}

	public List<SkillDTO> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillDTO> skills) {
		this.skills = skills;
	}

	public List<CertificationDTO> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<CertificationDTO> certifications) {
		this.certifications = certifications;
	}

    // Constructors, getters, and setters for ResumeDTO
}
