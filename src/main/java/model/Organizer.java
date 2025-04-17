package model;
import java.util.*;

public class Organizer extends User{
    Scanner in = new Scanner(System.in);

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

    public void openReg(){

    }

}