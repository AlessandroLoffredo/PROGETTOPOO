package model;
import java.util.*;

/**
 * La classe che contiene i metodi e gli attributi utili a definire un Hackathon
 */
public class Hackathon {
    private final String title;
    private String venue;
    private Date startDate;
    private Date endDate;
    private final int maxRegistration;
    private final int maxTeamParticipant;
    private String problemDescription;
    private Date startRegDate;
    private int regCounter;

    /**
     * Istanzia un nuovo Hackathon
     *
     * @param title              il titolo
     * @param venue              la location
     * @param startDate          la data in cui comincia
     * @param endDate            la data in cui termina
     * @param maxRegistration    il numero massimo di utenti che possono iscriversi all'evento
     * @param maxTeamParticipant il numero massimo di partecipanti che possono far parte dello stesso team
     * @param problemDescription la descrizione che definisce il problema da risolvere durante l'evento
     * @param startRegDate       la data di apertura delle iscrizioni
     * @param regCounter         il contatore che memorizza il numero attuale di iscritti all'evento
     */

    public Hackathon(String title, String venue, Date startDate, Date endDate, int maxRegistration, int maxTeamParticipant, String problemDescription, Date startRegDate, int regCounter) {
        this.title = title;
        this.venue = venue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxRegistration = maxRegistration;
        this.maxTeamParticipant = maxTeamParticipant;
        this.problemDescription = problemDescription;
        this.startRegDate = startRegDate;
        this.regCounter = regCounter;
    }

    /**
     * Restituisce il numero attuale di iscritti all'evento
     *
     * @return il numero attuale di iscritti
     */
    public int getRegCounter() { return regCounter; }

    /**
     * Restituisce la descrizione che i giudici hanno caricato per l'evento
     *
     * @return la descrizione dell'evento
     */
    public String getProblemDescription(){ return problemDescription; }

    /**
     * Restituisce il numero massimo di utenti che possono iscriversi all'evento
     *
     * @return il numero massimo di utenti che possono iscriversi
     */
    public int getMaxRegistration(){ return maxRegistration; }

    /**
     * Restituisce il numero massimo di partecipanti che possono far parte dello stesso team per quell'evento
     *
     * @return il numero massimo di partecipanti che possono far parte dello stesso team
     */
    public int getMaxTeamParticipant(){ return maxTeamParticipant; }

    /**
     * Restituisce il titolo dell'evento
     *
     * @return il titolo
     */
    public String getTitle(){ return title; }

    /**
     * Restituisce la data in cui si apriranno, o si sono già aperte, le iscrizioni dell'evento
     *
     * @return la data in cui si apriranno, o si sono già aperte, le iscrizioni
     */
    public Date getStartRegDate() {
        return startRegDate;
    }

    /**
     * Restituisce la data in cui comincerà, o è già cominciato, l'evento
     *
     * @return la data in cui comincerà, o è già cominciato
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Restituisce il luogo in cui si svolgerà, si sta svolgendo, o si è già svolto, l'evento
     *
     * @return il luogo in cui si svolgerà, si sta svolgendo, o si è già svolto,
     */
    public String getVenue() {
        return venue;
    }

    /**
     * Restituisce la data in cui terminerà, o è già terminato, l'evento
     *
     * @return la data in cui terminerà, o è già terminato
     */
    public Date getEndDate() {
        return endDate;
    }

}
