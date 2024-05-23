package fr.univrouen.cv24v1.dto;

public class ResumeInsertionResultDTO {
    private AllResumeDTO resumeDTO;
    private int id;

    public ResumeInsertionResultDTO(AllResumeDTO resumeDTO, int i) {
        this.resumeDTO = resumeDTO;
        this.id = i;
    }

    // Getters
    public AllResumeDTO getResumeDTO() {
        return resumeDTO;
    }

    public int getId() {
        return id;
    }
}