package model;
import java.util.*;

public class Organizer extends User{
    private Scanner in = new Scanner(System.in);

    public Organizer(String fName, String lName, LocalDate birthDate, String hackatonTitle){
        super(fName, lName, birthDate);
        this.hackatonOrg = hackatonTitle;
    }

    public void inviteJudge(){
        String fiName;
        String laName;
        System.out.printf("Inserire il nome del giudice da invitare> ");
        fiName = in.nextLine();
        System.out.printf("Inserire il cognome del giudice da invitare> ");
        laName = in.nextLine();
        /*
        In questa zona verranno inseriti i controlli utili a determinare l'esistenza di un utente che potrà essere invitato come giudice
        */
    }

    public Hacktaton setHackaton(){
        System.out.printf("Inserire il nome del nuovo hackaton da creare");
        String title = in.nextLine();
        System.out.printf("Inserire la sede di svolgimento");
        String venue = in.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("Inserire la data di inizio della competizione (dd/mm/yyyy)");
        String firstDate = in.NextLine();
        LocalDate startDate = LocalDate.parse(startDate, formatter);
        LocalDate endDate = startDate.plusDays(2);
        System.out.printf("Inserire il numero massimo di partecipanti");
        int maxReg = in.nextInt();
        System.out.printf("Inserire il numero massimo di partecipanti per team");
        int maxTeam = in.nextInt();
        Hackaton newestHackaton = new Hackaton(title, venue, startDate, endDate, maxReg, maxTeam);
        return newestHackaton;
    }

    public void setRegDate(Hackaton hack){
        /*
            Qui andranno inseriti controlli sull'esistenza dell'Hackaton con db con opportuna gestione delle eccezioni
         */
        /*
            Qui andrà aggiornata la data di inizio e fine registrazione nell'db con gestione eccezioni
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("Inserire la data di inizio registrazione (dd/mm/yyyy)");
        String regDate = in.NextLine();
        LocalDate startRegDate = LocalDate.parse(regDate, formatter);
        hack.setStartEndRegDate(regDate);
    }
}