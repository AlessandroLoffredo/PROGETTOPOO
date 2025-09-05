package model;


/**
 * La classe che contiene i metodi e gli attributi utili a definire un giudice
 */
public class Judge extends User{
    /**
     * Istanzia un nuovo giudice
     *
     * @param fName    il nome
     * @param lName    il cognome
     * @param username l'username
     * @param password la password
     */
    public Judge(String fName, String lName, String username, String password) {
        super(fName, lName, username, password);
    }
}