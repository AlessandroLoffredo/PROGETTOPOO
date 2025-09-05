package model;

import java.time.LocalDateTime;

/**
 * La classe che contiene i metodi e gli attributi utili a definire un documento
 */
public class Document {
    private String name;
    private byte[] description;
    private LocalDateTime date;
    private String comment;
    private Team docTeam;

    /**
     * Istanzia un nuovo Document
     *
     * @param name        il nome
     * @param description file vero e proprio
     * @param date        la data in cui viene caricato
     * @param docTeam     il team che carica il documento
     */
    public Document(String name, byte[] description, LocalDateTime date, Team docTeam) {
        this.description = description;
        this.name = name;
        this.date = date;
        this.docTeam = docTeam;
        this.comment = null;
    }
}
