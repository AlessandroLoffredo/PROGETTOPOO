package model;

import java.time.LocalDateTime;

/**
 * The type Document.
 */
public class Document {
    private String name;
    private String description;
    private LocalDateTime date;
    private String comment;
    private Team docTeam;

    /**
     * Instantiates a new Document.
     *
     * @param description the description
     * @param date        the date
     * @param docTeam     the doc team
     */
//COSTRUTTORE
    public Document(String description, LocalDateTime date, Team docTeam) {
        this.description = description;
        this.date = date;
        this.docTeam = docTeam;
        this.comment = null;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
//METODI
    public String getDescription() {
        return description;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    public String toString(){
        return date.toString() + ": " + description;
    }
}
