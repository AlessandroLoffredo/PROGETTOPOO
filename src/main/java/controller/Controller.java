package controller;

import model.*;

import javax.swing.*;

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
    public int handleLogin(String username, char[] password) throws Exception{
        Person person = new Person();
        //int risultato = person.logIn(username, password);
        int risultato = 0;
        if(risultato>=0){
            if (risultato == 0) {
                user = new User(null, null, username, new String(password), risultato);
            } else if (risultato == 1) {
                user = new Judge(null, null, username, new String(password), risultato);
            } else if (risultato == 2) {
                user = new Organizer(null, null, username, new String(password), risultato);
            } else if (risultato == 3) {
                user = new Participant(null, null, username, new String(password), risultato);
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

    /*public void sendRequest(String message, String username){
        if(this.user instanceof Participant){
            Participant participant = (Participant) user;
            participant.sendRequest(message, username);
        }
    }*/
}
