package controller;

import gui.CreaTeam;
import model.*;
import javax.swing.*;
import java.util.ArrayList;

public class Controller {
    //IN QUESTA CLASSE SARà DEFINITO UN RIFERIMENTO AD UN OGGETTO UTENTE
    //SETTATO A NULL OGNI VOLTA CHE L'UTENTE DI DISCONNETTE O ACCEDE PER LA PRIMA VOLTA
    private User user;
    private JFrame home;
    public Controller(JFrame frame) {
        user = null;
        home = frame;
    }

    public JFrame getHome() {
        return home;
    }

    public User getUser() {
        return user;
    }

    //SE SI LOGGA CON SUCCESSO ISTANZIARE L'OGGETTO USER
    public Integer handleLogin(String username, char[] password) throws Exception{
        Person person = new Person();
        //int risultato = person.logIn(username, password);
        int risultato = 3;
        if(risultato>=0){
            if (risultato == 0) {
                user = new User(null, null, username, new String(password));
            } else if (risultato == 1) {
                user = new Judge(null, null, username, new String(password));
            } else if (risultato == 2) {
                user = new Organizer(null, null, username, new String(password));
            } else if (risultato == 3) {
                user = new Participant(null, null, username, new String(password));
            }else{
                    throw new Exception("Errore durante l'accesso");
            }
        }
        return risultato;
    }

    public int handleSignUp(String username, char[] password, String fName, String lName){
        Person person = new Person();
        //int risultato person.signUp(username,password,fName, lName);
        return 0;
    }

    public int changePassword(char[] oldPassword, char[] newPassword, char[] confirmedPass){
        //return this.user.resetPassword(oldPassword, newPassword, confirmedPass, this.user.getUsername());
        return 0;
    }

    public int changeUsername(String newUsername, char[] password){
        //return this.user.resetUsername(newUsername, password, this.user.getUsername());
        return 0;
    }

    public void logout(){
        this.user = null;
    }

    public int sendRequest(String message, String username){
        if(this.user instanceof Participant){
            Participant participant = (Participant) user;
            int risultato = participant.sendRequest(message, username);
            return risultato;
        }else{
            return -2;
        }
    }

    public ArrayList<Request> getRequests(){
        if(this.user instanceof Participant){
            return ((Participant) user).getInvRecived();
        }else{
            return null;
        }
    }

    //GESTIONE RICHIESTE ACCETTATE DALL'UTENTE
    //MOLTO DA RIVEDERE UTILIZZATO, è STATO SCRITTO PER IL TESTING
    public int handleAccRequest(String username, JFrame frameChiamante){
        User sender = User.findUser(username);
        Participant p = (Participant) sender;
        p.setParTeam(null);
        Participant p2 = new Participant(null, null, "pippo", "pluto");
        p2.setParTeam(null);
        if(p.getParTeam() == null){
            if(p2.getParTeam() == null){
                CreaTeam creaTeam = new CreaTeam(frameChiamante, this);
                creaTeam.getFrame().setVisible(true);
                frameChiamante.setEnabled(false);
            }
        }
        return 0;
    }
}
