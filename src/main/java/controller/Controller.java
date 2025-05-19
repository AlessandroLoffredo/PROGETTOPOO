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
    public int handleLogin(String username, char[] password){
        Person person = new Person();
        //int risultato = person.logIn(username, password);
        /*if(risultato == 0){
            user = new User(null, null, username, new String(password));
        }*/
        user = new User(null, null, "username", "password");
        return 0;
    }

    public int handleSignUp(String username, char[] password, String fName, String lName){
        Person person = new Person();
        //int risultato person.signUp(username,password,fName, lName);
        /*if(risultato == 0){
            user = new User(fName, lName, username, new String(password));
        }*/
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
}
