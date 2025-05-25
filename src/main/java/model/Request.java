package model;

/**
 * Classe che contiene gli elementi principali di una Request, elemento essenziale per identificare una richiesta come giudice o per accettare un altro partecipante nel team.
 */
public class Request {
    private String message;
    private User sender;

    /**
     * Istanzia una nuova Request.
     */
    public Request() {
        this.message = null;
        this.sender = null;
    }

    /**
     * Istanzia una nuova Request specificando messaggio e utente che l'ha inviata.
     *
     * @param message messaggio motivazionale
     * @param user    utente che invia la richiesta
     */
    public Request(String message, User user) {
        this.message = message;
        this.sender = user;
    }

    /**
     * Restituisce l'utente che ha inviato la richiesta.
     *
     * @return User: utente che ha inviato la richiesta
     */
    public User getSender() {
        return this.sender;
    }

    /**
     * Imposta l'utente che ha inviato la richiesta.
     *
     * @param sender utente che ha inviato la richiesta.
     */
    public void setSender(User sender) {
        this.sender = sender;
    }

    /**
     * Restituisce il messaggio motivazionale della richiesta.
     *
     * @return String: messaggio motivazionale della richiesta.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Imposta il messaggio motivazionale della richiesta.
     *
     * @param message messaggio motivazionale della richiesta.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.sender.getUsername() + ": " +this.message;
    }
}
