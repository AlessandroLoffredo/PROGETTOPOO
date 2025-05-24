package model;

import java.time.LocalDateTime;

public class Document {
    private String description;
    private LocalDateTime date;
    private String comment;
    private Team docTeam;

    //COSTRUTTORE
    public Document(String description, LocalDateTime date, Team docTeam) {
        this.description = description;
        this.date = date;
        this.docTeam = docTeam;
        this.comment = null;
    }

    //METODI
    public String getDescription() {
        return description;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String toString(){
        return date.toString() + ": " + description;
    }
}
