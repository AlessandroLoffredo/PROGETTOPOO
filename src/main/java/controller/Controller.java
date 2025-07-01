package controller;

import gui.*;
import implementazioniPostgresDAO.*;
import model.*;
import javax.swing.*;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Date;

/**
 * Gestisce tutte le interazioni che hanno le classi del package gui con quelle del package model.
 */
public class Controller {
    //IN QUESTA CLASSE SARà DEFINITO UN RIFERIMENTO AD UN OGGETTO UTENTE
    //SETTATO A NULL OGNI VOLTA CHE L'UTENTE DI DISCONNETTE O ACCEDE PER LA PRIMA VOLTA
    private User user;
    private Home home;

    /**
     * Istanzia un nuovo controller.
     *
     * @param home il frame principale della classe Home
     */
    public Controller(Home home) {
        this.user = new User(null, null, "pippo", "pureio");
        this.home = home;
    }

    /**
     * Restituisce l'attributo home.
     *
     * @return Home : Il frame della classe Home.
     */
    public Home getHome() {
        return home;
    }
    /**
     * Restituisce l'attributo utente.
     *
     * @return User : utente che naviga nella pagina.
     */
    public User getUser() {
        return user;
    }

    /**
     * Gestisce l'azione di login di un utente già registrato.
     *
     * @param username L'username dell'utente che intende accedere
     * @param password La password dell'utente che intende accedere
     * @return int : Codice che indentifica le diverse situazioni di un accesso.
     * @throws Exception Gestione della situazione in cui non accada nessuna delle opzioni previste.
     */
    //SE SI LOGGA CON SUCCESSO ISTANZIARE L'OGGETTO USER
    public int handleLogin(String username, char[] password) throws Exception{

        Person person = new Person();
        //this.user = person.logIn(username, password);
        if(username.isEmpty() || (new String(password)).isEmpty()){
            return -1;
        }else{
            AuthImplementation authI = new AuthImplementation();
            int log = authI.logIn(username, new String(password));
            if(log == 0){
                this.user = new User(username, new String(password), null, null);
                return 0;
            }else{
                return -2;
            }
        }
    }

    /**
     * Gestisce l'azione di registrazione di un nuovo utente.
     *
     * @param username L'username del nuovo utente
     * @param password La password del nuovo utente
     * @param fName    Il nome del nuovo utente
     * @param lName    Il cognome del nuovo utente
     * @return int : codice che identifica le diverse situazioni di una registrazione.
     */
    public int handleSignUp(String username, char[] password, String fName, String lName){
        Person person = new Person();
        //int risultato person.signUp(username,password,fName, lName);
        return 0;
    }

    /**
     * Permette di modificare la password di un utente esistente.
     *
     * @param oldPassword   La vecchia password dell'utente, utilizzata per una questione di sicurezza
     * @param newPassword   La nuova password dell'utente
     * @param confirmedPass La conferma della nuova password dell'utente
     * @return int : Codice che identifica le diverse situazioni di un cambio password.
     */
    public int changePassword(char[] oldPassword, char[] newPassword, char[] confirmedPass){
        //return this.user.resetPassword(oldPassword, newPassword, confirmedPass, this.user.getUsername());
        return 0;
    }

    /**
     * Permette di cambiare l'username di un utente esistente.
     *
     * @param newUsername Il nuovo username dell'utente.
     * @param password    La password dell'utente, utilizzata per una questione di sicurezza.
     * @return int : Codice che identifica le varie situazioni di un cambio username.
     */
    public int changeUsername(String newUsername, char[] password){
        //return this.user.resetUsername(newUsername, password, this.user.getUsername());
        return 0;
    }

    /**
     * Metodo che disconnette l'utente dalla piattaforma.
     */
    public void logout(){
        this.user = null;
    }

    /**
     * Permette ad un partecipante di chiedere ad un altro partecipante dello stesso hackathon di unirsi al suo team.
     *
     * @param message  Messaggio motivazione che l'utente invia insieme alla richiesta.
     * @param username L'username dell'utente che invia la richiesta.
     * @return int : Codice che identifica le diverse situazioni che possono accadere.
     */
    public int sendRequest(String message, String username){
        if(this.user instanceof Participant){
            Participant participant = (Participant) user;
            int risultato = participant.sendRequest(message, username);
            return risultato;
        }else{
            return -2;
        }
    }

    /**
     * Permette ad un organizzatore di inviare ad utente la richiesta di diventare giudice di un hackathon.
     *
     * @param username Username dell'utente da invitare.
     * @return int : Un codice che identifica le varie situazioni che possono accadere.
     */
    public int sendRequestOrganizer(User username){
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

    /**
     * Restituisce gli inviti di unione al team che un partecipante riceve.
     *
     * @return ArrayList : Lista di invita della classe Participant.
     */
    public ArrayList<Request> getRequests(){
        if(this.user instanceof Participant){
            return ((Participant) user).getInvRecived();
        }else{
            return null;
        }
    }

    /**
     * Gestisce ciò che avviene quando un partecipante permette ad un altro di unirsi al suo team tramite richiesta.
     *
     * @param username       L'utente che invia la richiesta.
     * @param frameChiamante Il frame da dove viene chiamato il metodo, da questa dipende l'azione successiva.
     * @return int : Codice che identifica le varie situazioni che possono accadere.
     */
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

    /**
     * Gestisce l'aggiunta della descrizione del problema di un Hackathon da parte di un giudice.
     *
     * @param description La descrizione del problema.
     */
    public void handleProblemDescription(String description){
        ((Judge)user).describeProblem(description);
    }

    /**
     * Gestisce la lista dei documenti di un team, che unn giudice osserva e commenta.
     *
     * @param team Il team di cui il giudice vuole vedere la lista.
     * @return ArrayList : La lista dei documenti di un team.
     */
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

    /**
     * Gestisce l'assegnazione di un voto da parte di un giudice ad un team per il lavoro svolto.
     *
     * @param team Il team da giudicare.
     * @param mark Il voto assegnato.
     */
    public void handleAssignMark(Team team, int mark){
        //SI DEVE MODIFICARE IL PARAMETRO IN INGRESSO DA TEAM A STRING, USATO PER SEMPLICTA' E PER EVITARE ERRORI
        //SI DOVRA' CERCARE NEL DB IL TEAM CORRISPONDENTE AL NICKNAME
        team.addMark((short) mark);
    }

    /**
     * Gestisce l'inserimento da parte dell'organizzatore della data di inizio delle registrazioni all'Hackathon.
     *
     * @param date La data di inizio registrazioni.
     */
    public void handleStartSignUp(Date date){
        System.out.println(date);
    }

    /**
     * Controlla se, per l'Hackathon gestito da un organizzatore, è già stata gestita la data di apertura delle registrazioni.
     *
     * @return boolean : per sapere se sono state aperte.
     */
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

    /**
     * Verifica se l'Hackathon gestito da un organizzatore è già cominciato.
     *
     * @return boolean : per sapere se è cominciato.
     */
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

    /**
     * Create team int.
     *
     * @param nickname the nickname
     * @return the int
     */
    public int createTeam(String nickname){
        return Team.create(nickname, ((Participant) this.user));
    }

    public int subscribe(JFrame frame, String nameHack){
        if(this.user != null && (!(this.user instanceof User))){
            return -2;
        }else{
            if(this.user == null){
                // Pulsanti personalizzati
                Object[] options = {"Accedi", "Registrati"};
                int scelta = JOptionPane.showOptionDialog(
                        frame,
                        "Non hai eseguito l'accesso",
                        "AUTHENTICATION",
                        JOptionPane.YES_NO_OPTION, // Tipo di opzioni (può essere ignorato)
                        JOptionPane.QUESTION_MESSAGE,
                        null, // Nessuna icona personalizzata
                        options, // Pulsanti personalizzati
                        options[0] // Pulsante di default (Se l'utente preme invio senza selezionare nulla)
                );
                if(scelta == 0){
                    Login login = new Login(frame, this, this.home);
                } else if (scelta == 1) {
                    SignUp signUp = new SignUp(frame, this);
                }
            }
            if(this.user != null){
                int result = this.user.subscribe(nameHack);
                if (result == 0) {
                    this.user = new Participant(this.user.getfName(), this.user.getlName(), this.user.getUsername(), this.user.getPassword());
                    ((Participant) this.user).setParHackathon(Hackathon.findHackathon(nameHack));
                }
                return result;
            }
        }
        return -4;
    }

    public void areaPersonale(JFrame frame){
        switch (this.user.getClass().getSimpleName()) {
            case "Participant":
                AreaPersonale areaPersonale = new AreaPersonale(frame, this);
                areaPersonale.getFrame().setVisible(true);
                break;
            case "Organizer":
                AreaPersonaleOrganizzatore areaPersonaleO = new AreaPersonaleOrganizzatore(frame, this);
                areaPersonaleO.getFrame().setVisible(true);
                break;
            case "Judge":
                AreaPersonaleGiudice areaPersonaleG = new AreaPersonaleGiudice(frame, this);
                areaPersonaleG.getFrame().setVisible(true);
                break;
            case "User":
                AreaPersonale areaPersonaleU = new AreaPersonale(frame, this);
                areaPersonaleU.getFrame().setVisible(true);
                areaPersonaleU.getMessagePanel().setVisible(false);
                areaPersonaleU.getParticipantPanel().setVisible(false);
                areaPersonaleU.getTeamPanel().setVisible(false);
                break;
        }
        if(frame.equals(this.home.getFrame())){
            frame.setVisible(false);
        }else{
            frame.dispose();
        }
    }
}

