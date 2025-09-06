package model;

/**
 * La classe che contiene i metodi e gli attributi utili a definire una richiesta
 */
public class Request {
    private String message;
    private String sender;
    private String receiver;

    /**
     * Istanzia una nuova richiesta con messaggio e mittente
     *
     * @param message il messaggio scritto dal mittente
     * @param user    il mittente
     */
    public Request(String message, String user, String receiver) {
        this.message = message;
        this.sender = user;
        this.receiver = receiver;
    }
}
