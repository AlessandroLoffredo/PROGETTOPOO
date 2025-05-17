package controller;

import model.*;

public class Controller {
    //IN QUESTA CLASSE SARà DEFINITO UN RIFERIMENTO AD UN OGGETTO UTENTE
    //SETTATO A NULL OGNI VOLTA CHE L'UTENTE DI DISCONNETTE O ACCEDE PER LA PRIMA VOLTA
    User user;
    public Controller() {
        user = null;
    }

    public User getUser() {
        return user;
    }

    //SE SI LOGGA CON SUCCESSO ISTANZIARE L'OGGETTO USER
    public int handleLogin(String username, char[] password){
        Person person = new Person();
        //return person.logIn(username, password);
        return 0;
    }

    public int handleSignUp(String username, char[] password, String fName, String lName){
        Person person = new Person();
        //return person.signUp(username,password,fName, lName);
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
