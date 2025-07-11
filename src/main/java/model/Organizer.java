package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        /*this.organizedHackathon = risultato della query di cercamento dell'hackathon*/
    }

    /*public void inviteJudge(User u){
        /*
        In questa zona verranno inseriti i controlli utili a determinare l'esistenza di un utente che potrà essere invitato come giudice
        */
        /*questo serve a controllare se un utente non è gia occupato con un'altra attività, qualsiasi essa sia. inoltre si controlla se non ha gia ricevuto un altro invito
        se non lo è, e non ha ricevuto altri inviti, può ricevere uno nuovo
         */
      //  if(!u.getIsBusy() && u.getOrgInviting() == null)
        //    u.setOrgInviting(this.organizedHackathon);
    //}*/


    /**
     * Permette di impostare la data di inizio delle registrazioni all'Hackathon di cui è Organizzatore.
     *
     * @param hack Hackathon di cui è Organizzatore.
     */
    public void openRegDate(Hackathon hack){
        Scanner in = new Scanner(System.in);
        /*
            Qui andranno inseriti controlli sull'esistenza dell'Hackaton con db con opportuna gestione delle eccezioni
         */
        /*
            Qui andrà aggiornata la data di inizio e fine registrazione nell'db con gestione eccezioni
         */
        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println("Inserire la data di inizio registrazione (yyyy/mm/dd)> ");
        String regDate = in.nextLine();
        LocalDate startRegDate = LocalDate.parse(regDate, formatter);
        hack.setStartRegDate(startRegDate);

         */
    }

    /*public void openRegDate(Date date) {
        organizedHackathon.setStartRegDate(date);
    }
        OSCURATA PER FARE PROVE
     */

    /**
     * Restituisce l'Hackathon di cui è Organizzatore.
     *
     * @return Hackathon: hackathon di cui è Organizzatore.
     */
    public Hackathon getOrganizedHackathon() {
        return organizedHackathon;
    }

    @Override
    public String toString(){
        return "Organizer";
    }
}