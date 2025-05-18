package model;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class User extends Person {
    private final String fName;
    private final String lName;
    private String username;
    private String password;
    private boolean isBusy;
    private Hackathon orgInviting;

    //BUILDER
    public User(String fName, String lName, String username, String password){
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
        this.isBusy = false;
        this.orgInviting = null;
    }

    //METHODS
    public int resetUsername(String newUsername, char[] password, String oldUsername){
        String convertedPass = new String(password);
        if(newUsername.isEmpty() || convertedPass.isEmpty()){
            return -3;
        }
        if(newUsername.length()<3 || newUsername.length()>15) {
            return -1;
        }
        //DA GESTIRE CON DB PER VERIFICARE ESISTENZA ALTRO USERNAME UGUALE//
        /*
            if(controllo col db){
                return -2;
            }
        */
        //DOPO QUESTA GESTIONE...

        //ULTERIORE CONTROLLO DA INSERIRE PER LA PASSOWRD PER RENDERE IL SISTEMA PIù SICURO
        /*
               if(controllo pass){
                    return -4;
                }
         */
        //SE IL NUOVO USERNAME RISPETTA LE REGOLE...
        //AGGIORNAMENTO DATI NEL DB
        this.username = newUsername;
        return 0;
    }

    public int resetPassword(char[] oldPassword, char[] newPassword, char[] confiermedPass,String username) {
        String convertedNewPass = new String(newPassword);
        String convertedOldPass = new String(oldPassword);
        String conConfPass = new String(confiermedPass);
        if(convertedOldPass.isEmpty() || convertedNewPass.isEmpty() || conConfPass.isEmpty()){
            return -3;
        }
        if(!convertedNewPass.equals(conConfPass)){
            return -4;
        }
        if (convertedNewPass.length() < 8 || convertedNewPass.length() > 16){
            return -1;
        }
        //CONTROLLO UGUAGLIANZA VECCHIA PASSWORD CON oldPassword e username
        //CHIEDERE A TRAMONTANA SE è POSSIBILE ESEGUIRE QUESTO CONTROLLO AL DI FUORI DEL DB
        /*
          if(errore di db){
             return -2
          }
        */

        //SE LA NUOVA PASSWORD SUPERA I CONTROLLI...
        //VIENE AGGIORNATO IL DB GRAZIE ALLA STRINGA USERNAME
        this.password = convertedNewPass;
        return 0;
    }

    public void regHackaton(Hackathon hack){
        if(hack.getRegCounter() < hack.getMaxRegistration()){
            hack.addRegistration();
            //probabilemtne il parametro hack andrà sostituito con l'utilizzo in coppia di una gui e del db
            /*
             qui andrà creato un nuovo partecipante nel database con una query
             */
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
            Judge newJudge = new Judge(fName, lName, username, password, hack);
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
