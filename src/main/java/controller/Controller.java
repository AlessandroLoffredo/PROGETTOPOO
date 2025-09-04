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
    import java.time.chrono.IsoChronology;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;

    /**
     * Classe che gestisce le interazioni che le classi di package diversi hanno tra loro.
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
         * Istanzia un nuovo oggetto controller.
         *
         * @param home homepage dell'applicazione
         */
        public Controller(Home home) {
            this.user = null;
            this.plAdmin = null;
            this.home = home;
            this.hackathon = null;
            this.photo = null;
            this.currIdHack = -1;
            //questa inizializzazioen serve a capire se chi si è loggato è legato a qualche hackathon che si sta svolgendo
        }


        /**
         * Restituisce la homepage
         *
         * @return homepage
         */
        public Home getHome() {
            return home;
        }

        /**
         * Restituisce l'utente collegato
         *
         * @return utente collegato
         */
        public User getUser() {
            return user;
        }

        /**
         * Restituisce l'hackathon
         *
         * @return the hackathon
         */
        public Hackathon getHackathon() {
            return hackathon;
        }

        /**
         * Handle login int.
         *
         * @param username the username
         * @param password the password
         * @return the int
         */
//SE SI LOGGA CON SUCCESSO ISTANZIARE L'OGGETTO USER
        public int handleLogin(String username, char[] password) {
            String[] names = new String[2];
            AuthImplementation authI = new AuthImplementation();
            int log = authI.logIn(username, new String(password), names);
            switch (log) {
                case 1: {
                    this.plAdmin = new PlatformAdmin(username, new String(password));
                    break;
                }
                case 2: {
                    this.user = new Organizer(names[0], names[1], username, new String(password));
                    this.findHack();
                    break;
                }
                case 3: {
                    this.user = new Judge(names[0], names[1], username, new String(password));
                    this.findHack();
                    break;
                }
                case 4: {
                    this.user = new Participant(names[0], names[1], username, new String(password));
                    this.findHack();
                    break;
                }
                case 5: {
                    this.user = new User(names[0], names[1], username, new String(password));
                    break;
                }
                default: {
                    return -1;
                }
            }
            return log;
        }


        /**
         * Handle sign up int.
         *
         * @param username the username
         * @param password the password
         * @param fName    the f name
         * @param lName    the l name
         * @return the int
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
         * Change password int.
         *
         * @param oldPassword   the old password
         * @param newPassword   the new password
         * @param confirmedPass the confirmed pass
         * @return the int
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
         * Change username int.
         *
         * @param newUsername the new username
         * @param password    the password
         * @return the int
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
         * Logout.
         */
        public void logout(){
        this.user = null;
        this.plAdmin = null;
        this.hackathon = null;
        this.currIdHack = -1;
    }

        /**
         * Send request int.
         *
         * @param message  the message
         * @param username the username
         * @return the int
         */
        public int sendRequest(String message, String username){
        ParticipantImplementation parI = new ParticipantImplementation();
        return parI.sendRequests(this.user.getUsername(), this.currIdHack, username, message);
    }

        /**
         * Send request organizer int.
         *
         * @param username the username
         * @return the int
         */
        public int sendRequestOrganizer(String username){
        OrgImplementation orgI = new OrgImplementation();
        return orgI.inviteUser(this.user.getUsername(), username, this.currIdHack);
    }

        /**
         * Handle acc invite int.
         *
         * @param sender the sender
         * @return the int
         */
        public int handleAccInvite(String sender){
        UsersImplementation userI = new UsersImplementation();
        int code = userI.acceptInvite(sender, this.user.getUsername());
        if(code == 1){
            this.user = new Judge(this.user.getfName(), this.user.getlName(), this.user.getUsername(), this.user.getPassword());
        }
        return code;
    }

        /**
         * Handle acc request int.
         *
         * @param sender the sender
         * @return the int
         */
        public int handleAccRequest(String sender){
        ParticipantImplementation parI = new ParticipantImplementation();
        return parI.acceptRequest(sender, this.user.getUsername(), this.currIdHack);
    }

        /**
         * Handle dec invite int.
         *
         * @param sender the sender
         * @return the int
         */
        public int handleDecInvite(String sender){
        UsersImplementation userI = new UsersImplementation();
        return userI.declineInvite(sender, this.user.getUsername());
    }

        /**
         * Handle dec request int.
         *
         * @param sender the sender
         * @return the int
         */
        public int handleDecRequest(String sender){
        ParticipantImplementation parI = new ParticipantImplementation();
        return parI.declineRequest(sender, this.user.getUsername());
    }

        /**
         * Handle problem description int.
         *
         * @param description the description
         * @return the int
         */
        public int handleProblemDescription(String description){
        JudgeImplementation judgeI = new JudgeImplementation();
        return judgeI.updateDescription(description, this.user.getUsername());
    }

        /**
         * Handle load file.
         *
         * @param team     the team
         * @param files    the files
         * @param names    the names
         * @param comments the comments
         */
        public void handleLoadFile(String team, List<byte[]> files, List<String> names, List<String> comments){
        JudgeImplementation judgeI = new JudgeImplementation();
        judgeI.getDocuments(team, this.currIdHack, files, names, comments);
    }

        /**
         * Handle comment int.
         *
         * @param comment the comment
         * @param doc     the doc
         * @param team    the team
         * @return the int
         */
        public int handleComment(String comment, String doc, String team){
        JudgeImplementation judgeI = new JudgeImplementation();
        return judgeI.setComment(comment, this.user.getUsername(), this.currIdHack, doc, team);
    }

        /**
         * Handle assign mark int.
         *
         * @param team the team
         * @param mark the mark
         * @return the int
         */
        public int handleAssignMark(String team, int mark){
        JudgeImplementation judgeI = new JudgeImplementation();
        return judgeI.assignMark(team, mark, this.user.getUsername(), this.currIdHack);
    }

        /**
         * Handle start sign up int.
         *
         * @param date the date
         * @return the int
         */
        public int handleStartSignUp(LocalDate date){
        OrgImplementation orgI = new OrgImplementation();
        return orgI.setupDate(date, this.currIdHack);
    }

        /**
         * Get dates.
         *
         * @param dates the dates
         */
        public void getDates(LocalDate[] dates){
        OrgImplementation orgI = new OrgImplementation();
        orgI.getDates(this.user.getUsername(), dates);
    }

        /**
         * Verifying start reg date boolean.
         *
         * @return the boolean
         */
        public boolean verifyingStartRegDate() {
        OrgImplementation orgI = new OrgImplementation();
        return orgI.verifyDate(this.user.getUsername());
    }

        /**
         * Is started boolean.
         *
         * @return the boolean
         */
        public boolean isStarted(){
        OrgImplementation orgI = new OrgImplementation();
        return orgI.isStarted(this.user.getUsername());
    }

        /**
         * Is hack started boolean.
         *
         * @return the boolean
         */
        public boolean isHackStarted(){
        return !this.hackathon.getStartDate().after(new Date());
    }


        /**
         * Subscribe int.
         *
         * @param start the start
         * @param end   the end
         * @return the int
         */
        public int subscribe(LocalDate start, LocalDate end){
        if (!(this.hackathon.getEndDate().after(new Date()))){
            return -4;
        } else if(this.hackathon.getRegCounter() == this.hackathon.getMaxRegistration()){
            return -1;
        } else if (this.hackathon.getStartRegDate() == null || this.hackathon.getStartRegDate().after(new Date())) {
            return -2;
        }
        else{
            UsersImplementation userI = new UsersImplementation();
            if(userI.veryfingIsFree(this.user.getUsername(), start, end) == 1){
                int code = userI.subscribe(this.user.getUsername(), this.idHack); //QUI DOVREBBE RESTITUIRE -4 SE GENERE ECCEZIONE, NON CONVIENE VERIFICARE A PRIORI CHE LA DATA DI FINE NON SIA STATA SUPERATA?
                if(code == 1){
                    this.user = new Participant(this.user.getfName(), this.user.getlName(), this.user.getUsername(), this.user.getPassword());
                    this.currIdHack = this.idHack;
                }
                return code;
            }else{
                return -3;
            }
        }
    }

        /**
         * Area personale.
         *
         * @param frame the frame
         */
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
                default:
                    JOptionPane.showMessageDialog(null, "Errore durante il caricamento dell'area personale");
                    break;
            }
        }
        if(frame.equals(this.home.getFrame())){
            frame.setVisible(false);
        }else{
            frame.dispose();
        }
    }

        /**
         * Gets pl admin.
         *
         * @return the pl admin
         */
        public PlatformAdmin getPlAdmin() {
        return plAdmin;
    }

        /**
         * Get free user.
         *
         * @param freeUsers the free users
         * @param start     the start
         * @param end       the end
         */
        public void getFreeUser(List<String> freeUsers, LocalDate start, LocalDate end){
        UsersImplementation usersI = new UsersImplementation();
        usersI.getFreeUser(freeUsers, start, end);
    }

        /**
         * Get invites.
         *
         * @param requests the requests
         */
        public void getInvites(List<String> requests){
        UsersImplementation usersI = new UsersImplementation();
        usersI.getInvites(requests, this.user.getUsername());
    }

        /**
         * Handle create hackathon int.
         *
         * @param title      the title
         * @param venue      the venue
         * @param startDate  the start date
         * @param endDate    the end date
         * @param maxReg     the max reg
         * @param maxPerTeam the max per team
         * @param username   the username
         * @param file       the file
         * @return the int
         */
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

        /**
         * Get user class boolean.
         *
         * @return the boolean
         */
        public boolean getUserClass(){
        return !(this.user instanceof Participant || this.user instanceof Judge || this.user instanceof Organizer);
    }

        /**
         * Find hack.
         */
        public void findHack() {
        ArrayList<Object> data = new ArrayList<>();
        if (this.user instanceof Organizer || this.user instanceof Judge) {
            OrgImplementation orgI = new OrgImplementation();
            orgI.findHack(this.user.getUsername(), data, this.getUser().getClass().getSimpleName());
        } else if (this.user instanceof Participant){
            ParticipantImplementation parI = new ParticipantImplementation();
            parI.findHack(this.user.getUsername(), data);
        } else {
            return;
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

        /**
         * Set hack value.
         *
         * @param currentTitleArea      the current title area
         * @param currentVenueArea      the current venue area
         * @param currentStartArea      the current start area
         * @param currentEndArea        the current end area
         * @param currentStartRegArea   the current start reg area
         * @param currentMaxRegArea     the current max reg area
         * @param currentCounterArea    the current counter area
         * @param currentMaxTeamParArea the current max team par area
         * @param currentProbDescArea   the current prob desc area
         */
        public void setHackValue(JLabel currentTitleArea, JLabel currentVenueArea, JLabel currentStartArea, JLabel currentEndArea, JLabel currentStartRegArea, JLabel currentMaxRegArea, JLabel currentCounterArea, JLabel currentMaxTeamParArea, JTextArea currentProbDescArea){
        currentTitleArea.setText(this.getHackathon().getTitle());
        currentVenueArea.setText(this.getHackathon().getVenue());
        currentStartArea.setText(this.getHackathon().getStartDate().toString());
        currentEndArea.setText(this.getHackathon().getEndDate().toString());
        currentMaxTeamParArea.setText(String.valueOf(getHackathon().getMaxTeamParticipant()));
        if(this.getHackathon().getProblemDescription() == null || this.getHackathon().getProblemDescription().isEmpty()){
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

        /**
         * Find last hack int.
         *
         * @return the int
         */
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

        /**
         * Get hack list.
         *
         * @param data the data
         */
        public void getHackList(List<List<Object>> data){
        HackathonImplementation hackI = new HackathonImplementation();
        hackI.getHackList(data);
        if(!data.isEmpty()){
            String idHacks = "0";
            for(List<Object> list : data){
                if(((Date)list.get(3)).before(new Date())){
                    StringBuilder sb = new StringBuilder(idHacks);
                    sb.append("-").append(list.get(9));
                    idHacks = sb.toString();
                }
            }
            idHacks = idHacks + "-";
            hackI.removeRequests(idHacks);
        }
    }

        /**
         * Gets id hack.
         *
         * @return the id hack
         */
        public int getIdHack() {
        return idHack;
    }

        /**
         * Sets id hack.
         *
         * @param idHack the id hack
         */
        public void setIdHack(int idHack) {
        this.idHack = idHack;
    }

        /**
         * Sets hackathon.
         *
         * @param title        the title
         * @param venue        the venue
         * @param startDate    the start date
         * @param endDate      the end date
         * @param maxReg       the max reg
         * @param maxTeamPar   the max team par
         * @param problemDesc  the problem desc
         * @param startRegDate the start reg date
         * @param regCounter   the reg counter
         */
        public void setHackathon(String title, String venue, Date startDate, Date endDate, int maxReg, int maxTeamPar, String problemDesc, Date startRegDate, int regCounter) {
        this.hackathon = new Hackathon(title, venue, startDate, endDate, maxReg, maxTeamPar, problemDesc, startRegDate, regCounter);
    }

        /**
         * Sets hackathon.
         *
         * @param hackathon the hackathon
         */
        public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

        /**
         * Get photo byte [ ].
         *
         * @return the byte [ ]
         */
        public byte[] getPhoto() {
        return photo;
    }

        /**
         * Sets photo.
         *
         * @param photo the photo
         */
        public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

        /**
         * Get judges list.
         *
         * @param judges the judges
         */
        public void getJudgesList(List<String> judges){
        HackathonImplementation hackI = new HackathonImplementation();
        hackI.getJudgesList(judges, this.idHack);
    }

        /**
         * Get act judges list.
         *
         * @param judges the judges
         */
        public void getActJudgesList(List<String> judges){
        HackathonImplementation hackI = new HackathonImplementation();
        hackI.getJudgesList(judges, this.currIdHack);
    }

        /**
         * Get organizer string.
         *
         * @return the string
         */
        public String getOrganizer(){
        HackathonImplementation hackI = new HackathonImplementation();
        return hackI.getOrganizer(this.idHack);
    }

        /**
         * Get act organizer string.
         *
         * @return the string
         */
        public String getActOrganizer(){
        HackathonImplementation hackI = new HackathonImplementation();
        return hackI.getOrganizer(this.currIdHack);
    }

        /**
         * Gets ranking.
         *
         * @param ranking    the ranking
         * @param idLastHack the id last hack
         */
        public void getRanking(List<String> ranking, int idLastHack) {
        HackathonImplementation hackI = new HackathonImplementation();
        hackI.getRanking(ranking, idLastHack);
    }

        /**
         * Get hack participants.
         *
         * @param participants the participants
         */
        public void getHackParticipants(List<String> participants){
        ParticipantImplementation parI = new ParticipantImplementation();
        parI.getParticipants(participants, this.currIdHack, this.user.getUsername(), this.hackathon.getMaxTeamParticipant());
    }

        /**
         * Get teams.
         *
         * @param teams the teams
         */
        public void getTeams(List<String> teams){
        JudgeImplementation judgeI = new JudgeImplementation();
        judgeI.getTeams(teams, this.currIdHack);
    }

        /**
         * Get lasts user hack.
         *
         * @param hackathon the hackathon
         */
        public void getLastsUserHack(List<ArrayList<Object>> hackathon){
        UsersImplementation userI = new UsersImplementation();
        userI.getLastsUserHack(hackathon, this.user.getUsername());
    }

        /**
         * Get mark int.
         *
         * @param team the team
         * @return the int
         */
        public int getMark(String team){
        JudgeImplementation judgeI = new JudgeImplementation();
        return judgeI.getMark(team, this.user.getUsername(), this.currIdHack);
    }

        /**
         * Get requests.
         *
         * @param requests the requests
         */
        public void getRequests(List<String> requests){
        ParticipantImplementation parI = new ParticipantImplementation();
        parI.getRequests(requests, this.user.getUsername());
    }

        /**
         * Get team.
         */
        public void getTeam(){
        TeamImplementation teamI = new TeamImplementation();
        this.idTeam = teamI.getTeam(this.user.getUsername(), this.currIdHack);
    }

        /**
         * Get nickname string.
         *
         * @return the string
         */
        public String getNickname(){
        TeamImplementation teamI = new TeamImplementation();
        return teamI.getNickname(this.idTeam);
    }

        /**
         * Change nickname int.
         *
         * @param nickname the nickname
         * @return the int
         */
        public int changeNickname(String nickname){
        TeamImplementation teamI = new TeamImplementation();
        return teamI.changeNickname(nickname, this.idTeam);
    }

        /**
         * Find teammates.
         *
         * @param teammates the teammates
         */
        public void findTeammates(List<String> teammates){
        TeamImplementation teamI = new TeamImplementation();
        teamI.findTeammates(teammates, this.idTeam);
    }

        /**
         * Send file int.
         *
         * @param file the file
         * @param name the name
         * @return the int
         */
        public int sendFile(File file, String name){
        TeamImplementation teamI = new TeamImplementation();
        int results = 0;
        try{
            results = teamI.sendFile(Files.readAllBytes(file.toPath()),
            name, this.idTeam, new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } catch (IOException e) {
            e.printStackTrace();
            results = -2;
        }
        return results;
    }

        /**
         * Get documents.
         *
         * @param docs     the docs
         * @param files    the files
         * @param comments the comments
         */
        public void getDocuments(List<String> docs, List<byte[]> files, List<String> comments){
        TeamImplementation teamI = new TeamImplementation();
        teamI.getDocuments(docs, files, comments, this.idTeam);
    }

        /**
         * Gets curr id hack.
         *
         * @return the curr id hack
         */
        public int getCurrIdHack() {
        return currIdHack;
    }

        /**
         * Fill ranking.
         *
         * @param teams the teams
         */
        public void fillRanking(List<String> teams){
    if(this.hackathon.getEndDate().before(new Date())){
            HackathonImplementation hackI = new HackathonImplementation();
            hackI.getRanking(teams, this.idHack);
        }
    }
}
