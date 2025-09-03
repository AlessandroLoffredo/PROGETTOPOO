package model;


/**
 * Classe che contiene gli attributi principali di un Organizer, colui che gestisce quando possono partire le iscrizioni all'Hackathon.
 * Estende la classe User.
 */

public class Organizer extends User{
    private Hackathon organizedHackathon;

    /**
     * Istanzia un nuovo Organizer.
     *
     * @param fName    Nome dell'organizzatore
     * @param lName    Cognome dell'organizzatore
     * @param username Username
     * @param password Password
     */
    public Organizer(String fName, String lName, String username, String password){
        super(fName, lName, username, password);
    }
}