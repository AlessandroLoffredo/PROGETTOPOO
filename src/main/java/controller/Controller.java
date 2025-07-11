    package controller;

    import gui.*;
    import implementazioniPostgresDAO.*;
    import model.*;
    import javax.swing.*;
    import java.time.LocalDate;
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
        private PlatformAdmin plAdmin;
        private Hackathon hackathon;
        private String sender;

        /**
         * Istanzia un nuovo controller.
         *
         * @param home il frame principale della classe Home
         */
        public Controller(Home home) {
            this.user = null;
            //this.user = new User("Alessandro", "Loffredo", "Alex", "Password");
            //this.user = new Organizer("Alessandro", "Loffredo", "Alex", "Password");
            //this.user = new Judge("Alessandro", "Loffredo", "Alex", "Password");
            //this.user = new Participant("Alessandro", "Loffredo", "Alex", "Password");
            //this.plAdmin = new PlatformAdmin("Alex", "Password");
            this.plAdmin = null;
            this.home = home;
            this.hackathon = null;
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

        public Hackathon getHackathon() {
            return hackathon;
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
            String[] names = new String[2];
            AuthImplementation authI = new AuthImplementation();
            int log = authI.logIn(username, new String(password), names);
            if(log == 1){
                this.plAdmin = new PlatformAdmin(username, new String(password));
            }else if(log == 2){
                this.user = new Organizer(names[0], names[1], username, new String(password));
                this.findHack();
            } else if(log == 3) {
                this.user = new Judge(names[0], names[1], username, new String(password));
                this.findHack();
            } else if (log == 4) {
                this.user = new Participant(names[0], names[1], username, new String(password));
            } else if (log == 5) {
                this.user = new User(names[0], names[1], username, new String(password));
            }else{
                return -1;
            }
            return log;
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
    public int handleSignUp(String username, String password, String fName, String lName){
        if(fName.length() > 20 || lName.length() > 20) {
            return -1;
        }else if(username.length() > 16 || username.length() < 3 || password.length() > 16 || password.length() < 8) {
            return -2;
        }else{
            AuthImplementation authI = new AuthImplementation();
            int signUp = authI.signUp(username, password, fName, lName);
            if(signUp == 1)
                this.user = new User(fName, lName, username, password);
            return signUp;
        }
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
        if (!this.user.getPassword().equals(new String(oldPassword))) {
            return -1;
        } else if(new String(newPassword).length() < 8 || new String(newPassword).length() > 16){
            return -2;
        } else if (!(new String(newPassword).equals(new String(confirmedPass)))) {
            return -3;
        } else {
            AuthImplementation authI = new AuthImplementation();
            int changePass = authI.changePassword(this.user.getUsername(), new String(newPassword));
            if(changePass == 1)
                this.user.setPassword(new String(newPassword));
            return changePass;
        }
    }

    /**
     * Permette di cambiare l'username di un utente esistente.
     *
     * @param newUsername Il nuovo username dell'utente.
     * @param password    La password dell'utente, utilizzata per una questione di sicurezza.
     * @return int : Codice che identifica le varie situazioni di un cambio username.
     */
    public int changeUsername(String newUsername, char[] password){
        if(newUsername.length() < 3 || newUsername.length() > 16){
            return -1;
        } else if (!this.user.getPassword().equals(new String(password))) {
            return -2;
        } else if(newUsername.equals(this.user.getUsername())){
            return -3;
        } else {
            AuthImplementation authI = new AuthImplementation();
            int changeUser = authI.changeUsername(newUsername, this.user.getUsername());
            if(changeUser == 1)
                this.user.setUsername(newUsername);
            return changeUser;
        }
    }

    /**
     * Metodo che disconnette l'utente dalla piattaforma.
     */
    public void logout(){
        this.user = null;
        this.plAdmin = null;
        this.hackathon = null;
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
    public int sendRequestOrganizer(String username){
        OrgImplementation orgI = new OrgImplementation();
        return orgI.inviteUser(this.user.getUsername(), username);
    }

    /**
     * Restituisce gli inviti di unione al team che un partecipante riceve.
     *
     * @return ArrayList : Lista di invita della classe Participant.
     */
    /*public ArrayList<String> getRequests(){
        if(this.user instanceof Participant){
            return ((Participant) user).getInvRecived();
        }else{
            return null;
        }
    }*/

    /**
     * Gestisce ciò che avviene quando un partecipante permette ad un altro di unirsi al suo team tramite richiesta.
     *
     * @param sender       L'utente che invia la richiesta.
     * @return int : Codice che identifica le varie situazioni che possono accadere.
     */

    public int handleAccRequest(String sender){
        UsersImplementation userI = new UsersImplementation();
        return userI.acceptInvite(sender, this.user.getUsername()); //TODO CHIEDERE AD ALEXXX SE QUESTA INTERFACE è GIUSTA
    }

    public int handleDecRequest(String sender){
        UsersImplementation userI = new UsersImplementation();
        return userI.declineInvite(sender, this.user.getUsername());
    }
    /**
     * Gestisce l'aggiunta della descrizione del problema di un Hackathon da parte di un giudice.
     *
     * @param description La descrizione del problema.
     */
    public int handleProblemDescription(String description){
        JudgeImplementation judgeI = new JudgeImplementation();
        return judgeI.updateDescription(description, this.user.getUsername());
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
    public int handleStartSignUp(LocalDate date){
        OrgImplementation orgI = new OrgImplementation();
        return orgI.setupDate(date, this.user.getUsername());
    }

    public void getDates(LocalDate[] dates){
        OrgImplementation orgI = new OrgImplementation();
        orgI.getDates(this.user.getUsername(), dates);
    }
    /**
     * Controlla se, per l'Hackathon gestito da un organizzatore, è già stata gestita la data di apertura delle registrazioni.
     *
     * @return boolean : per sapere se sono state aperte.
     */
    public boolean verifyingStartRegDate() {
        OrgImplementation orgI = new OrgImplementation();
        return orgI.verifyDate(this.user.getUsername());
    }

    public boolean isStarted(){
        OrgImplementation orgI = new OrgImplementation();
        return orgI.isStarted(this.user.getUsername());
    }

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
                    Login login = new Login(frame, this);
                } else if (scelta == 1) {
                    SignUp signUp = new SignUp(frame, this);
                }
            }
            if(this.user != null){
                int result = this.user.subscribe(nameHack);
                if (result == 0) {
                    this.user = new Participant(this.user.getfName(), this.user.getlName(), this.user.getUsername(), this.user.getPassword());
                    //((Participant) this.user).setParHackathon(Hackathon.findHackathon(nameHack));
                }
                return result;
            }
        }
        return -4;
    }

    public void areaPersonale(JFrame frame){
        if(this.plAdmin != null){
            AdminGui areaPersonalePA = new AdminGui(frame, this);
            areaPersonalePA.getFrame().setVisible(true);
        }else {
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
                    areaPersonaleU.getTeamPanel().setVisible(false);
                    break;
            }
        }
        if(frame.equals(this.home.getFrame())){
            frame.setVisible(false);
        }else{
            frame.dispose();
        }
    }

    public PlatformAdmin getPlAdmin() {
        return plAdmin;
    }

    public void getFreeUser(ArrayList<String> freeUsers, LocalDate start, LocalDate end){
        UsersImplementation usersI = new UsersImplementation();
        usersI.getFreeUser(freeUsers, start, end);
    }

    public void getInvites(ArrayList<String> requests){
        UsersImplementation usersI = new UsersImplementation();
        usersI.getInvites(requests, this.user.getUsername());
    }

    public int handleCreateHackathon(String title, String venue, LocalDate startDate, LocalDate endDate, int maxReg, int maxPerTeam, String username){
        if(title.length() > 50 || venue.length() > 25){
            return -2;
        } else if (startDate.isBefore((LocalDate.now().plusDays(7)))) {
            return -3;
        } else {
            AdminImplementation adminI = new AdminImplementation();
            return adminI.newHack(title, venue, startDate, endDate, maxReg, maxPerTeam, username);
        }
    }

    public boolean getUserClass(){
        return this.user instanceof User;
    }

    public String getDescription(ArrayList<Object> data){
        OrgImplementation orgI = new OrgImplementation();
        orgI.findHack(this.user.getUsername(), data, this.getUser().toString());
        System.out.println(data.size());
        return ((String) data.get(data.size()-2));
    }

    public boolean isRegStarted(){    //CONTROLLA SE LE REGISTRAZIONI SONO COMINCIATE
        //DOBBIAMO SALVARE LE INFO DELL'HACKATHON
        return !(this.hackathon.getStartRegDate()).before(new Date());
    }

    public void findHack(){
        OrgImplementation orgI = new OrgImplementation();
        ArrayList<Object> data = new ArrayList<>();
        orgI.findHack(this.user.getUsername(), data, this.getUser().toString());
        String title = (String)data.get(0);
        String venue = (String)data.get(1);
        Date startDate = (Date)data.get(2);
        Date endDate = (Date)data.get(3);
        int maxReg = (int)data.get(4);
        int maxTeamPar = (int)data.get(5);
        String problemDesc = (String)data.get(7);
        Date startRegDate = (Date)data.get(8);
        int regCounter = (int)data.get(6);
        this.hackathon = new Hackathon(title, venue, startDate, endDate, maxReg, maxTeamPar, problemDesc, startRegDate, regCounter);
    }

    public void setHackValue(JLabel currentTitleArea, JLabel currentVenueArea, JLabel currentStartArea, JLabel currentEndArea, JLabel currentStartRegArea, JLabel currentMaxRegArea, JLabel currentCounterArea, JTextArea currentProbDescArea){
        currentTitleArea.setText(this.getHackathon().getTitle());
        currentVenueArea.setText(this.getHackathon().getVenue());
        currentStartArea.setText(this.getHackathon().getStartDate().toString());
        currentEndArea.setText(this.getHackathon().getEndDate().toString());
        if(this.getHackathon().getProblemDescription().equals(null) || this.getHackathon().getProblemDescription().equals("")){
            currentProbDescArea.setText("Descrizione problema ancora non definita");
        } else {
            currentProbDescArea.setText(this.getHackathon().getProblemDescription());
        }
        if(this.getHackathon().getStartRegDate().toString().equals(null)){
            currentStartRegArea.setText("Data ancora non definita");
        } else {
            currentStartRegArea.setText(this.getHackathon().getStartRegDate().toString());
        }
        currentMaxRegArea.setText(String.valueOf(this.getHackathon().getMaxRegistration()));
        currentCounterArea.setText(String.valueOf(this.getHackathon().getRegCounter()));
    }

}
