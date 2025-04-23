package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Organizer extends User{
    private Hackathon organizedHackathon;

    public Organizer(String fName, String lName, LocalDate birthDate, String username, String password, Hackathon mngHackathon){
        super(fName, lName, birthDate, username, password);
        organizedHackathon = mngHackathon;
    }

    public void inviteJudge(User u){
        /*
        In questa zona verranno inseriti i controlli utili a determinare l'esistenza di un utente che potrà essere invitato come giudice
        */
        /*questo serve a controllare se un utente non è gia occupato con un'altra attività, qualsiasi essa sia. inoltre si controlla se non ha gia ricevuto un altro invito
        se non lo è, e non ha ricevuto altri inviti, può ricevere uno nuovo
         */
        if(!u.getIsBusy() && u.getOrgInviting() == null)
            u.setOrgInviting(this.organizedHackathon);
    }


    public void setRegDate(Hackathon hack){
        Scanner in = new Scanner(System.in);
        /*
            Qui andranno inseriti controlli sull'esistenza dell'Hackaton con db con opportuna gestione delle eccezioni
         */
        /*
            Qui andrà aggiornata la data di inizio e fine registrazione nell'db con gestione eccezioni
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println("Inserire la data di inizio registrazione (yyyy/mm/dd)> ");
        String regDate = in.nextLine();
        LocalDate startRegDate = LocalDate.parse(regDate, formatter);
        hack.setStartRegDate(startRegDate);
    }


}