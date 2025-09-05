package model;

/**
 * La classe che contiene i metodi e gli attributi utili a definire un partecipante
 */
public class Participant extends User {
    /**
     * Istanzia un nuovo partecipante
     *
     * @param fName    il nome
     * @param lName    il cognome
     * @param username l'username
     * @param password la password
     */
    public Participant(String fName, String lName, String username, String password){
        super(fName, lName, username, password);
    }
}
