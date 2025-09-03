package model;


/**
 * Classe che contiene gli attributi principali di un Judge, colui che imposta la descrizione del problema e valuta i documenti prodotti dai team assegnandogli dei voti.
 * Estende la classe User.
 */
public class Judge extends User{
    private Hackathon judgedHack;

    /**
     * Istanzia un nuovo Judge.
     *
     * @param fName    Nome del giudice
     * @param lName    Cognome del giudice
     * @param username Username
     * @param password Password
     */
    public Judge(String fName, String lName, String username, String password) {
        super(fName, lName, username, password);
    }
}