package fr.univrouen.cv24v1.exceptions;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorDetail {
    private int id;
    private String status;
    private String message;



    public ErrorDetail(int id2, String status, String message) {
        this.id = id2;
        this.status = status;
        this.message = message;
    }
	// Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}