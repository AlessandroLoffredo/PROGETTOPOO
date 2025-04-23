package model;

import java.time.LocalDate;
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
    }

    public void resetPassword(){
        Scanner input = new Scanner(System.in);
        System.out.printf("Inserisci una password di almeno 8 caratteri e non superiore a 16: ");
        while (true){
            String s = input.nextLine();
            if(s.length() >= 8 && s.length() <= 16){
                this.password = s;
                break;
            }
            else {
                System.out.println("La password non rispetta i requisiti, riprova");
            }
        }
        input.close();
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
        this.orgInviting = orgInviting;
    }

    public void answerInvitation(Hackathon hack){
        Scanner input = new Scanner(System.in);
        System.out.println("Accetti l'invito come giudice per l'Hackathon" + hack.getTitle() + "?");
        String s = input.nextLine();
        if (s.equals("si")){
            Judge newJudge = new Judge(fName, lName, birthDate, username, password, hack);
            this.isBusy = true;
            hack.addJudge(newJudge);
        }
        else{
            this.orgInviting = null;
        }
        input.close();
    }

    public boolean getIsBusy(){ return isBusy; }

    public void setIsBusy(boolean state){ this.isBusy = state; }

}
