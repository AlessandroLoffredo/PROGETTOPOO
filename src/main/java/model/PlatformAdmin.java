package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

/**
 * Classe che contiene gli attributi principali di un PlatformAdmin, colui che crea gli Hackathon.
 */
public class PlatformAdmin {
    private String username;
    private String password;


    /**
     * Istanzia un nuovo PlatformAdmin.
     *
     * @param username Username
     * @param password Password
     */
    public PlatformAdmin(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Permette di impostare un nuovo username.
     */
    public void resetUsername(){
        //DA GESTIRE CON DB PER VERIFICARE ESISTENZA ALTRO USERNAME UGUALE//
        Scanner in = new Scanner(System.in);
        System.out.println("Inserisci l'username (min 3 caratteri, max 15: ");
        username = in.nextLine();
        while (username.length()<3 || username.length()>15) {
            System.out.println("L'username non rispetta i requisiti (min 3 caratteri, max 15), riprova");
        }
        this.username = username;
        //AGGIORNAMENTO DATI NEL DB
        in.close();
    }

    /**
     * Permette di impostare una nuova password.
     */
    public void resetPassword(){
        Scanner in = new Scanner(System.in);
        System.out.println("Inserisci una password di almeno 8 caratteri e non superiore a 16: ");
        String s = in.nextLine();
        while (s.length() < 8 || s.length() > 16){
            System.out.println("La password non rispetta i requisiti (min 8 caratteri, max 16), riprova");
            s = in.nextLine();
        }
        this.password = s;
        in.close();
    }

    /**
     * Restituisce l'username dell'admin.
     *
     * @return String: username dell'admin
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Restituisce la password dell'admin.
     *
     * @return String: password dell'admin
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Permette di creare un nuovo Hackathon.
     *
     * @param title              Titolo dlel'Hackathon
     * @param venue              Luogo dove si svolgerà l'Hackathon.
     * @param startDate          Data di inzio.
     * @param endDate            Data di fine.
     * @param maxRegistration    Numero massimo di partecipanti all'Hackathon.
     * @param maxTeamParticipant Numero massimo di partecipanti all'interno di un team.
     * @param hackOrganizer      Organizzatore dell'Hackathon
     */
    public void createHackathon(String title, String venue, Date startDate, Date endDate, int maxRegistration, int maxTeamParticipant, Organizer hackOrganizer){
        //Hackathon hack = new Hackathon(title, venue, startDate, endDate, maxRegistration, maxTeamParticipant, hackOrganizer);
        //ANDRà FATTA UNA QUERY PER AGGIUNGERE UN NUOVO HACKATHON AL DB
    }
}
