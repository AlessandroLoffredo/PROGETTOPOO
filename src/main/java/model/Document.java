package model;

import java.time.LocalDateTime;

/**
 * Classe che contiene gli elementi principali di un documento, elemento principale per dove viene inserita la soluzione del problema dell'Hackathon.
 */
public class Document {
    private String name;
    private String description;
    private LocalDateTime date;
    private String comment;
    private Team docTeam;

    /**
     * Istanzia un nuovo Documento.
     *
     * @param description Descrizione del documento.
     * @param date        Data relativa al caricamento del documento nella lista documenti.
     * @param docTeam     Team a cui appartiene il documento.
     */
//COSTRUTTORE
    public Document(String description, LocalDateTime date, Team docTeam) {
        this.description = description;
        this.date = date;
        this.docTeam = docTeam;
        this.comment = null;
    }

    /**
     * Restituisce la descrizione del documento.
     *
     * @return String: descrizione del documento.
     */
//METODI
    public String getDescription() {
        return description;
    }

    /**
     * Imposta il commento del documento.
     *
     * @param comment Commento del documento.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Restituisci la data di caricamento del documento.
     *
     * @return LocalDateTime: data di caricamento del documento.
     */
    public LocalDateTime getDate() {
        return date;
    }

    public String toString(){
        return date.toString() + ": " + description;
    }
}
