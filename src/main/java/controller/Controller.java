    package controller;

    import gui.*;
    import implementazioniPostgresDAO.*;
    import model.*;
    import javax.swing.*;
    import java.io.File;
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.time.LocalDate;
    import java.time.ZoneId;
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
        private int idHack;
        private byte[] photo;
        private int currIdHack;
        private int idTeam;
        /**
         * Istanzia un nuovo controller.
         *
         * @param home il frame principale della classe Home
         */
        public Controller(Home home) {
            //this.user = new User("Alessandro", "Loffredo", "Alex", "Password");
            //this.user = new Organizer("Alessandro", "Loffredo", "Alex", "Password");
            //this.user = new Judge("Alessandro", "Loffredo", "Alex", "Password");
            //this.user = new Participant("Alessandro", "Loffredo", "Alex", "Password");
            //this.plAdmin = new PlatformAdmin("Alex", "Password");
            this.user = null;
            this.plAdmin = null;
            this.home = home;
            this.hackathon = null;
            this.photo = null;
            this.currIdHack = -1;
            //questa inizializzazioen serve a capire se chi si è loggato è legato a qualche hackathon che si sta svolgendo
        }


        /*public void photo() {
            AuthImplementation authImplementation = new AuthImplementation();
            authImplementation.updateHackathonPhotos();
        }*/
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
                this.findHack();
                System.out.println(this.currIdHack);
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
        ParticipantImplementation parI = new ParticipantImplementation();
        return parI.sendRequests(this.user.getUsername(), this.currIdHack, username, message);
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

    public int handleAccInvite(String sender){
        UsersImplementation userI = new UsersImplementation();
        return userI.acceptInvite(sender, this.user.getUsername());
    }

    public int handleAccRequest(String sender){
        ParticipantImplementation parI = new ParticipantImplementation();
        return parI.acceptRequest(sender, this.user.getUsername(), this.currIdHack);
    }

    public int handleDecInvite(String sender){
        UsersImplementation userI = new UsersImplementation();
        return userI.declineInvite(sender, this.user.getUsername());
    }

    public int handleDecRequest(String sender){
        ParticipantImplementation parI = new ParticipantImplementation();
        return parI.declineRequest(sender, this.user.getUsername());
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
    public void handleLoadFile(String team, ArrayList<byte[]> files, ArrayList<String> names, ArrayList<String> comments){
        JudgeImplementation judgeI = new JudgeImplementation();
        judgeI.getDocuments(team, this.currIdHack, files, names, comments);
        for(String comm : comments)
            System.out.println(comm);
    }

    public int handleComment(String comment, String doc, String team){
        JudgeImplementation judgeI = new JudgeImplementation();
        return judgeI.setComment(comment, this.user.getUsername(), this.currIdHack, doc, team);
    }

    /**
     * Gestisce l'assegnazione di un voto da parte di un giudice ad un team per il lavoro svolto.
     *
     * @param team Il team da giudicare.
     * @param mark Il voto assegnato.
     */
    public int handleAssignMark(String team, int mark){
        JudgeImplementation judgeI = new JudgeImplementation();
        return judgeI.assignMark(team, mark, this.user.getUsername(), this.currIdHack);
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

    public int subscribe(LocalDate start, LocalDate end){
        if (!(this.hackathon.getEndDate().after(new Date()))){
            return -4;
        } else if(this.hackathon.getRegCounter() == this.hackathon.getMaxRegistration()){
            return -1;
        } else if (this.hackathon.getStartRegDate().after(new Date())) {
            return -2;
        }
        else{
            UsersImplementation userI = new UsersImplementation();
            if(userI.veryfingIsFree(this.user.getUsername(), start, end) == 1){
                return userI.subscribe(this.user.getUsername(), this.idHack); //QUI DOVREBBE RESTITUIRE -4 SE GENERE ECCEZIONE, NON CONVIENE VERIFICARE A PRIORI CHE LA DATA DI FINE NON SIA STATA SUPERATA?
            }else{
                return -3;
            }
        }
    }

    public void areaPersonale(JFrame frame){
        if(this.plAdmin != null){
            AdminGui areaPersonalePA = new AdminGui(frame, this);
            areaPersonalePA.getFrame().setVisible(true);
            frame.setVisible(false);
        }else {
            switch (this.user.getClass().getSimpleName()) {
                case "Participant":
                    AreaPersonale areaPersonale = new AreaPersonale(frame, this);
                    areaPersonale.getFrame().setVisible(true);
                    frame.setVisible(false);
                    break;
                case "Organizer":
                    AreaPersonaleOrganizzatore areaPersonaleO = new AreaPersonaleOrganizzatore(frame, this);
                    areaPersonaleO.getFrame().setVisible(true);
                    frame.setVisible(false);
                    break;
                case "Judge":
                    AreaPersonaleGiudice areaPersonaleG = new AreaPersonaleGiudice(frame, this);
                    areaPersonaleG.getFrame().setVisible(true);
                    frame.setVisible(false);
                    break;
                case "User":
                    AreaPersonale areaPersonaleU = new AreaPersonale(frame, this);
                    areaPersonaleU.getFrame().setVisible(true);
                    areaPersonaleU.getMessagePanel().setVisible(false);
                    areaPersonaleU.getTeamPanel().setVisible(false);
                    frame.setVisible(false);
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

    public int handleCreateHackathon(String title, String venue, LocalDate startDate, LocalDate endDate, int maxReg, int maxPerTeam, String username, File file){
        if(title.length() > 50 || venue.length() > 25){
            return -2;
        } else if (startDate.isBefore((LocalDate.now().plusDays(7)))) {
            return -3;
        } else {
            AdminImplementation adminI = new AdminImplementation();
            if(file == null){
                return adminI.newHack(title, venue, startDate, endDate, maxReg, maxPerTeam, username, null);
            }
            Path imagePath = file.toPath();

            if (!Files.exists(imagePath)) {
                System.err.println("File immagine non trovato: " + imagePath);
                return -5;
            }
            byte[] photoData;
            try{
                photoData = Files.readAllBytes(imagePath);
                return adminI.newHack(title, venue, startDate, endDate, maxReg, maxPerTeam, username, photoData);
            } catch (IOException e) {
                e.printStackTrace();
                return -5;
            }
        }
    }

    public boolean getUserClass(){
        return !(this.user instanceof Participant || this.user instanceof Judge || this.user instanceof Organizer);
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
        ArrayList<Object> data = new ArrayList<>();
        if(this.user instanceof Organizer || this.user instanceof Judge){
            OrgImplementation orgI = new OrgImplementation();
            orgI.findHack(this.user.getUsername(), data, this.getUser().toString());
        }else{
            ParticipantImplementation parI = new ParticipantImplementation();
            parI.findHack(this.user.getUsername(), data);
        }
            String title = (String) data.get(0);
            String venue = (String) data.get(1);
            Date startDate = (Date) data.get(2);
            Date endDate = (Date) data.get(3);
            int maxReg = (int) data.get(4);
            int maxTeamPar = (int) data.get(5);
            int regCounter = (int) data.get(6);
            String problemDesc = (String) data.get(7);
            Date startRegDate = (Date) data.get(8);
            this.currIdHack = (int) data.get(9);
            this.photo = (byte[]) data.get(10);
            this.hackathon = new Hackathon(title, venue, startDate, endDate, maxReg, maxTeamPar, problemDesc, startRegDate, regCounter);
    }

    public void setHackValue(JLabel currentTitleArea, JLabel currentVenueArea, JLabel currentStartArea, JLabel currentEndArea, JLabel currentStartRegArea, JLabel currentMaxRegArea, JLabel currentCounterArea, JLabel currentMaxTeamParArea, JTextArea currentProbDescArea){
        currentTitleArea.setText(this.getHackathon().getTitle());
        currentVenueArea.setText(this.getHackathon().getVenue());
        currentStartArea.setText(this.getHackathon().getStartDate().toString());
        currentEndArea.setText(this.getHackathon().getEndDate().toString());
        currentMaxTeamParArea.setText(String.valueOf(getHackathon().getMaxTeamParticipant()));
        if(this.getHackathon().getProblemDescription() == null || this.getHackathon().getProblemDescription().equals("")){
            currentProbDescArea.setText("Descrizione problema ancora non definita");
        } else {
            currentProbDescArea.setText(this.getHackathon().getProblemDescription());
        }
        if(this.getHackathon().getStartRegDate() == null){
            currentStartRegArea.setText("Data ancora non definita");
        } else {
            currentStartRegArea.setText(this.getHackathon().getStartRegDate().toString());
        }
        currentMaxRegArea.setText(String.valueOf(this.getHackathon().getMaxRegistration()));
        currentCounterArea.setText(String.valueOf(this.getHackathon().getRegCounter()));
    }

    public int findLastHack(){
        UsersImplementation userI = new UsersImplementation();
        ArrayList<Object> data = new ArrayList<>();
        userI.lastHack(data);
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
        return (int)data.get(9);
    }

    public void getHackList(ArrayList<ArrayList<Object>> data){
        HackathonImplementation hackI = new HackathonImplementation();
        hackI.getHackList(data);
        if(!data.isEmpty()){
            String idHacks = "0";
            for(ArrayList<Object> list : data){
                if(((Date)list.get(3)).before(new Date())){
                    idHacks = idHacks + "-" + list.get(9);
                    System.out.println(idHacks);
                }
            }
            idHacks = idHacks + "-";
            hackI.removeRequests(idHacks);
        }
    }

    public int getIdHack() {
        return idHack;
    }

    public void setIdHack(int idHack) {
        this.idHack = idHack;
    }

    public void setHackathon(String title, String venue, Date startDate, Date endDate, int maxReg, int maxTeamPar, String problemDesc, Date startRegDate, int regCounter) {
        this.hackathon = new Hackathon(title, venue, startDate, endDate, maxReg, maxTeamPar, problemDesc, startRegDate, regCounter);
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void getJudgesList(ArrayList<String> judges){
        HackathonImplementation hackI = new HackathonImplementation();
        hackI.getJudgesList(judges, this.idHack);
    }

    public void getActJudgesList(ArrayList<String> judges){
        HackathonImplementation hackI = new HackathonImplementation();
        hackI.getJudgesList(judges, this.currIdHack);
    }

    public String getOrganizer(){
        HackathonImplementation hackI = new HackathonImplementation();
        return hackI.getOrganizer(this.idHack);
    }

    public String getActOrganizer(){
        HackathonImplementation hackI = new HackathonImplementation();
        return hackI.getOrganizer(this.currIdHack);
    }

    public void getRanking(ArrayList<String> ranking, int idLastHack) {
        HackathonImplementation hackI = new HackathonImplementation();
        hackI.getRanking(ranking, idLastHack);
    }

    /*public int subrscibeTeam(){   PROBABILMENTE MEGLIO FARE UN TRIGGER O ALTRIMENTI FARE ALTRA QUERY PER PRENDERE IDTEAM APPENA INSERITO E USARLO PER INSERIRE PARTICIPANT
        Teamimplementation teamI = new Teamimplementation();
        return teamI.subscribeTeam(this.user.getUsername(), this.idHack);
    }*/
    public void getHackParticipants(ArrayList<String> participants){
        ParticipantImplementation parI = new ParticipantImplementation();
        parI.getParticipants(participants, this.currIdHack, this.user.getUsername(), this.hackathon.getMaxTeamParticipant());
    }

    public void getTeams(ArrayList<String> teams){
        JudgeImplementation judgeI = new JudgeImplementation();
        judgeI.getTeams(teams, this.currIdHack);
    }

    public void getLastsUserHack(ArrayList<ArrayList<Object>> hackathon){
        UsersImplementation userI = new UsersImplementation();
        userI.getLastsUserHack(hackathon, this.user.getUsername());
    }

    public int getMark(String team){
        JudgeImplementation judgeI = new JudgeImplementation();
        return judgeI.getMark(team, this.user.getUsername(), this.currIdHack);
    }

    public void getRequests(ArrayList<String> requests){
        ParticipantImplementation parI = new ParticipantImplementation();
        parI.getRequests(requests, this.user.getUsername());
    }

    public void getTeam(){
        TeamImplementation teamI = new TeamImplementation();
        this.idTeam = teamI.getTeam(this.user.getUsername(), this.currIdHack);
    }

    public String getNickname(){
        TeamImplementation teamI = new TeamImplementation();
        return teamI.getNickname(this.idTeam);
    }

    public int changeNickname(String nickname){
        TeamImplementation teamI = new TeamImplementation();
        return teamI.changeNickname(nickname, this.idTeam);
    }

    public void findTeammates(ArrayList<String> teammates){
        TeamImplementation teamI = new TeamImplementation();
        teamI.findTeammates(teammates, this.idTeam);
    }

    public int sendFile(File file, String name){
        TeamImplementation teamI = new TeamImplementation();
        int results = 0;
        try{
            results = teamI.sendFile(Files.readAllBytes(file.toPath()),
            name, this.idTeam, new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            System.out.println(results);
        } catch (IOException e) {
            e.printStackTrace();
            results = -2;
        }
        System.out.println(results);
        return results;
    }

    public void getDocuments(ArrayList<String> docs, ArrayList<byte[]> files, ArrayList<String> comments){
        TeamImplementation teamI = new TeamImplementation();
        teamI.getDocuments(docs, files, comments, this.idTeam);
    }

    public int getCurrIdHack() {
        return currIdHack;
    }
}
