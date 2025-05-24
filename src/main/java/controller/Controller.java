package controller;

import gui.CreaTeam;
import model.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

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
        this.user = person.logIn(username, password);
        /*if(username.isEmpty() || (new String(password)).isEmpty()){
            return -1;
        }else{
            this.user = person.logIn(username, password);
            if(user == null){
                return -2;
            }else{
                return 0;
            }
        }*/
        return 0;
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

    public int sendRequest(User username){
        //SI DEVE MODIFICARE IL PARAMETRO IN INGRESSO DA USER A STRING, USATO PER SEMPLICTA' E PER EVITARE ERRORI
        //SI DOVRA' CERCARE NEL DB L'USER CORRISPONDENTE ALL'USERNAME
        /*ArrayList<Request> richieste = username.getRequestsJudge();
        for(Request r : richieste){
            if(r.getSender().equals(username)){
                return 1;
            }
        }
        username.addRequest(new Request("Invito per giudicare l'Hackathon: " + ((Organizer)user).getOrganizedHackathon().getTitle(), user));

         */
        return 0;
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

    public void handleProblemDescription(String description){
        ((Judge)user).describeProblem(description);
    }

    public ArrayList<Document> handleLoadFile(Team team){
        //SI DEVE MODIFICARE IL PARAMETRO IN INGRESSO DA TEAM A STRING, USATO PER SEMPLICTA' E PER EVITARE ERRORI
        //SI DOVRA' CERCARE NEL DB IL TEAM CORRISPONDENTE AL NICKNAME
        return team.getDocList();
    }

    /*public void handleComment(String comment, JFrame frame){
        if(COMMENTO GIA' PRESENTE PER IL DOC)
        COME ARRIVIAMO AL DOC?
        CI SARANNO COMMENTI DA PIU' GIUDICI?
    }*/

    public void handleAssignMark(Team team, int mark){
        //SI DEVE MODIFICARE IL PARAMETRO IN INGRESSO DA TEAM A STRING, USATO PER SEMPLICTA' E PER EVITARE ERRORI
        //SI DOVRA' CERCARE NEL DB IL TEAM CORRISPONDENTE AL NICKNAME
        team.addMark((short) mark);
    }

    public void handleStartSignUp(Date date){
        System.out.println(date);
    }

    public boolean verifyingStartRegDate(){
        /*if(((Organizer)user).getOrganizedHackathon().getStartRegDate() == null){
            return false;
        }
        else {
            return true;
        }
        */
        //INSERITO PER COMODITA' PER TEST
        return true;
    }

    public boolean verifyingStartHack(){
        /*if(((Organizer)user).getOrganizedHackathon().getStartDate().equals(new Date())){
            return true;
        }
        else {
            return false;
        }
         */
        //INSERITO PER COMODITA' PER TEST
        return false;
    }

    /*public int handleCreateHackathon(String title, String venue, Date startHack, Date endHack, int maxPar, int maxParTeam){
        //CONTROLLI CON IL DB PER VERIFICARE CHE NON CI SIANO ALTRI HACKATHON UGUALI...
        //VALUTARE E DETERMINARE QUALI ELEMENTI POSSONO ESSERE UGUALI...
        //E NEL CASO RETSITUIRE 1
        //DOBBIAMO CREARE ATTRIBUTO ADMIN??
        // (admin.createHackathon(title, venue, startHack, endHack, maxPar, maxParTeam);
    }
     */
}
