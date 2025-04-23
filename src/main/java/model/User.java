package model;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class User extends Person {
    private final String fName;
    private final String lName;
    private final LocalDate birthDate;
    private String username;
    private String password;
    private Hackathon hackathonReg;
    private boolean isBusy;
    private Hackathon orgInviting;

    //BUILDER
    public User(String fName, String lName, LocalDate birthDate, String username, String password){
        this.fName = fName;
        this.lName = lName;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
        this.hackathonReg = null;
        this.isBusy = false;
        this.orgInviting = null;
    }

    //METHODS
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

    public void regHackaton(Hackathon hack){
        if(hack.getRegCounter() < hack.getMaxRegistration()){
            hack.addRegistration();
            this.hackathonReg = hack;
            this.isBusy = true;
        }
        else{
            System.out.println("Numero massimo di iscritti raggiunto");
        }
    }

    public String getUsername(){ return username; }

    public void setOrgInviting(Hackathon orgInviting) {
        /*
        questo metodo viene invocato quando un organizzatore invita un utente della piattaforma ad essere giudice di un hackathon.
        tale hackathon viene passato come parametro
        */
        this.orgInviting = orgInviting;
    }

    public void answerInvitation(Hackathon hack){
        Scanner input = new Scanner(System.in);
        System.out.println("Accetti l'invito come giudice per l'Hackathon " + hack.getTitle() + " da parte di " +hack.getHackOrganizer().getUsername()+ " (si/no)?");
        String s = input.nextLine().trim().toLowerCase(Locale.ITALIAN);
        while(!s.equals("si") && !s.equals("no")){
            System.out.println("Puoi inserire solo si/no!");
            s = input.nextLine().trim().toLowerCase(Locale.ITALIAN);
        }
        if (s.equals("si")){
            Judge newJudge = new Judge(fName, lName, birthDate, username, password, hack);
            //anche qui verrà fatta una query per aggiungere un nuovo giudice
            this.isBusy = true;
            hack.addJudge(newJudge);
        }
        else{
            //quest'istruzione serve a liberare lo spazio per un nuovo invito, visto che un utente può riceverne uno solo
            this.orgInviting = null;
        }
        input.close();
    }

    public boolean getIsBusy(){ return isBusy; }

    public void setIsBusy(boolean state){ this.isBusy = state; }

    public Hackathon getOrgInviting(){
        return this.orgInviting;
    }

}
