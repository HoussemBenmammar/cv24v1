package fr.univrouen.cv24v1.dto;

public class ResumeInsertionResultDTO {
    private AllResumeDTO resumeDTO;
    private String id;

    public ResumeInsertionResultDTO(AllResumeDTO resumeDTO, String string) {
        this.resumeDTO = resumeDTO;
        this.id = string;
    }

    // Getters
    public AllResumeDTO getResumeDTO() {
        return resumeDTO;
    }

    public String getId() {
        return id;
    }
}