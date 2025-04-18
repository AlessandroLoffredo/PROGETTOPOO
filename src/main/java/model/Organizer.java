package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Organizer extends User{
    private Scanner in = new Scanner(System.in);
    private ArrayList<Hackathon> organizedHackathon;
    public Organizer(String fName, String lName, LocalDate birthDate, String username, String password, Hackathon mngHackathon){
        super(fName, lName, birthDate, username, password);
        organizedHackathon = new ArrayList<>();
        organizedHackathon.add(mngHackathon);
    }

    public void inviteJudge(){
        String username;
        System.out.printf("Inserisci l'username dell'utente da invitare come giudice> ");
        username = in.nextLine();
        /*
        In questa zona verranno inseriti i controlli utili a determinare l'esistenza di un utente che potrà essere invitato come giudice
        */
        /*

        */
    }

    public Hackathon setHackaton(){
        System.out.printf("Inserire il nome del nuovo hackaton da creare");
        String title = in.nextLine();
        System.out.printf("Inserire la sede di svolgimento");
        String venue = in.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("Inserire la data di inizio della competizione (dd/mm/yyyy)");
        String firstDate = in.nextLine();
        LocalDate startDate = LocalDate.parse(firstDate, formatter);
        LocalDate endDate = startDate.plusDays(2);
        System.out.printf("Inserire il numero massimo di partecipanti");
        int maxReg = in.nextInt();
        System.out.printf("Inserire il numero massimo di partecipanti per team");
        int maxTeam = in.nextInt();
        Hackathon newestHackaton = new Hackathon(title, venue, startDate, endDate, maxReg, maxTeam, this);
        return newestHackaton;
    }

    public void setRegDate(Hackathon hack){
        /*
            Qui andranno inseriti controlli sull'esistenza dell'Hackaton con db con opportuna gestione delle eccezioni
         */
        /*
            Qui andrà aggiornata la data di inizio e fine registrazione nell'db con gestione eccezioni
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("Inserire la data di inizio registrazione (dd/mm/yyyy)");
        String regDate = in.nextLine();
        LocalDate startRegDate = LocalDate.parse(regDate, formatter);
        hack.setStartEndRegDate(startRegDate);
    }

    public void setNewHackaton(Hackathon h){
        this.organizedHackathon.add(h);
    }
}