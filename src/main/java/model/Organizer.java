package model;


/**
 * La classe che contiene i metodi e gli attributi utili a definire un organizzatore
 */
public class Organizer extends User{
    /**
     * Istanzia un nuovo organizzatore
     *
     * @param fName    il nome
     * @param lName    il cognome
     * @param username l'username
     * @param password la password
     */
    public Organizer(String fName, String lName, String username, String password){
        super(fName, lName, username, password);
    }
}