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
        private ArrayList<String> judges;

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
            judges = new ArrayList<>();
        }


        /**
         * Restituisce la homepage
         *
         * @return homepage home
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
         * @return hackathon a cui si fa riferimento durante l'esecuzione
         */
        public Hackathon getHackathon() {
            return hackathon;
        }

        /**
         * Gestisce l'azione di LogIn dell'utente e ne determina il ruolo
         *
         * @param username username dell'utente
         * @param password password dell'utente
         * @return codice che permette di sapere se il LogIn è andato a buon fine
         */
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
         * Gestisce la registrazione di un utente
         *
         * @param username il nuovo username con cui ci si vuole registrare
         * @param password la nuova password con cui ci si vuoke registrare
         * @param fName    nome
         * @param lName    cognome
         * @return codice che permette di sapere se la SignUp è andata a buon fine
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
         * Gestisce l'azione che permette all'utente di cambiare password
         *
         * @param oldPassword   la vecchia password dell'utente
         * @param newPassword   la nuova password dell'utente
         * @param confirmedPass la nuova password confermata
         * @return codice che permette di determinare se il cambio password è andato a buon fine
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
         * Gestisce l'azione che permette all'utente di cambiare username
         *
         * @param newUsername il nuovo username dell'utente
         * @param password    la password, usata come campo di conferma
         * @return codice che permette di sapere se il cambio username è andato a buon fine
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
         * Gestisce l'azione di LogOut dell'utente
         */
        public void logout(){
        this.user = null;
        this.plAdmin = null;
        this.hackathon = null;
        this.currIdHack = -1;
    }

        /**
         * Gestisce l'azione che permette ad un partecipante di inviare un messaggio motivazione ad un altro, chiedendogli di unirsi al suo team
         *
         * @param message  il messaggio inviato dall'utente
         * @param username l'username dell'utente a cui ci vogliamo unire
         * @return codice che permette di sapere se la richiesta è stata inviata correttamente o meno
         */
        public int sendRequest(String message, String username){
            ParticipantImplementation parI = new ParticipantImplementation();
            return parI.sendRequests(this.user.getUsername(), this.currIdHack, username, message);
    }

        /**
         * Gestisce la possibilità che l'organizzatore ha di invitare utenti chiedendogli di diventare giudici di un hackathon
         *
         * @param username l'username dell'utente invitato
         * @return codice che permette di sapere se l'invito è stato invitato correttamente o meno
         */
        public int sendRequestOrganizer(String username){
        OrgImplementation orgI = new OrgImplementation();
        return orgI.inviteUser(this.user.getUsername(), username, this.currIdHack);
    }

        /**
         * Gestisce il caso in cui l'utente invitato come giudice accetti l'invito
         *
         * @param sender l'username dell'utente che ha inviato l'invito
         * @return codice che permette di sapere se il sistema è riuscito a gestire l'accettazione dell'invito correttamente
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
         * Gestisce il caso in cui l'utente che riceve la richiesta, la accetta
         *
         * @param sender l'username dell'utente che ha inviato la richiesta
         * @return codice che permette di sapere se il sistema è riuscito a gestire l'accettazione della richiesta correttamente
         */
        public int handleAccRequest(String sender){
        ParticipantImplementation parI = new ParticipantImplementation();
        return parI.acceptRequest(sender, this.user.getUsername(), this.currIdHack);
    }

        /**
         * Gestisce il caso in cui l'utente invitato come giudice rifiuta l'invito
         *
         * @param sender l'username dell'utente che ha inviato l'invito
         * @return codice che permette di sapere se il sistema è riuscito a gestire il rifiuto dell'invito correttamente
         */
        public int handleDecInvite(String sender){
        UsersImplementation userI = new UsersImplementation();
        return userI.declineInvite(sender, this.user.getUsername());
    }

        /**
         * Gestisce il caso in cui l'utente che riceve la richiesta, la rifiuta
         *
         * @param sender l'username dell'utente che ha inviato la richiesta
         * @return codice che permette di sapere se il sistema è riuscito a gestire il rifiuto della richiesta correttamente
         */
        public int handleDecRequest(String sender){
        ParticipantImplementation parI = new ParticipantImplementation();
        return parI.declineRequest(sender, this.user.getUsername());
    }

        /**
         * Gestisce la definizione della descrizione del problema da parte di un giudice
         *
         * @param description la descrizione inviata dal giudice
         * @return codice che permette di sapere se la descrizione è stata memorizzata correttamente
         */
        public int handleProblemDescription(String description){
        JudgeImplementation judgeI = new JudgeImplementation();
        return judgeI.updateDescription(description, this.user.getUsername());
    }

        /**
         * Gestisce la volontà del giudice di conoscere tutti i documenti di un team
         *
         * @param team     il nickname del team di cui si richiedono i documenti
         * @param files    la lista dei documenti
         * @param names    la lista dei nomi dei documenti
         * @param comments la lista dei commenti caricati dai giudici per i documenti
         */
        public void handleLoadFile(String team, List<byte[]> files, List<String> names, List<String> comments){
        JudgeImplementation judgeI = new JudgeImplementation();
        judgeI.getDocuments(team, this.currIdHack, files, names, comments);
    }

        /**
         * Gestisce il commento inserito da un giudici per un documento
         *
         * @param comment il commento inserito dal giudice
         * @param doc     il nome del documento che il giudice vuole commentare
         * @param team    il nickname del team che ha caricato il documento commentato
         * @return codice che permette di sapere se il commento è stato inserito corrente
         */
        public int handleComment(String comment, String doc, String team){
        JudgeImplementation judgeI = new JudgeImplementation();
        return judgeI.setComment(comment, this.user.getUsername(), this.currIdHack, doc, team);
    }

        /**
         * Gestisce l'inserimento di un voto da parte di un giudice ad un team
         *
         * @param team il nickname del team votato
         * @param mark il voto inserito dal giudice
         * @return codice che permette di sapere di sapere se il voto è stato inserito correttamente
         */
        public int handleAssignMark(String team, int mark){
        JudgeImplementation judgeI = new JudgeImplementation();
        return judgeI.assignMark(team, mark, this.user.getUsername(), this.currIdHack);
    }

        /**
         * Gestisce l'inserimento della data di apertura registrazioni da parte dell'organizzatore
         *
         * @param date la data inserita dall'organizzatore
         * @return codice che permette di sapere se la data è stata inserita correttamente
         */
        public int handleStartSignUp(LocalDate date){
        OrgImplementation orgI = new OrgImplementation();
        return orgI.setupDate(date, this.currIdHack);
    }

        /**
         * Riempie un vettore con le date di riferimento dell'hackathon a cui facciamo riferimento
         *
         * @param dates l'array di date da riempire
         */
        public void getDates(LocalDate[] dates){
        OrgImplementation orgI = new OrgImplementation();
        orgI.getDates(this.user.getUsername(), dates);
    }

        /**
         * Gestisce le richieste che necessitano sapere se è stata inserita la data di apertura delle iscrizioni
         *
         * @return un valore vero/falso per sapere se è stata inserita la data di apertura delle iscrizioni
         */
        public boolean isSignUpInserted(){
            OrgImplementation orgI = new OrgImplementation();
            return orgI.isStarted(this.user.getUsername(), this.currIdHack);
    }

        /**
         * Gestisce le richieste che necessitano sapere se un evento è cominciato
         *
         * @return un valore vero/falso per sapere se un evento è cominciato
         */
        public boolean isHackStarted(){
        return (new Date()).after(this.hackathon.getStartDate());
    }


        /**
         * Gestisce le richieste di iscrizione ad un hackahthon da parte di un utente
         *
         * @param start la data di inizio dell'evento a cui l'utente vuole iscriversi
         * @param end   la data di fine dell'evento a cui l'utente vuole iscriversi
         * @return codice che permette di sapere se l'utente è riuscito ad iscriversi all'evento correttamente
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
            if(userI.verifyingIsFree(this.user.getUsername(), start, end) == 1){
                int code = userI.subscribe(this.user.getUsername(), this.idHack);
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
         * Gestisce la creazione dell'area personale di ogni utente loggato che intende vedere i propri dati, a seconda del tipo di utente
         *
         * @param frame necessario quando si vuole uscire dall'area personale per spostarsi verso un'altra pagina
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
         * Gestisce le richieste che richiedono il riferimento all'admin per effettuare dei controlli
         *
         * @return il riferimento all'admin
         */
        public PlatformAdmin getPlAdmin() {
        return plAdmin;
    }

        /**
         * Gestisce le richieste che richiedono una lista piena di utenti liberi in un certo intervallo di tempo
         *
         * @param freeUsers la lista di utenti da riempire
         * @param start     la data di inizio dell'intervallo
         * @param end       la data di fine dell'intervallo
         */
        public void getFreeUser(List<String> freeUsers, LocalDate start, LocalDate end){
            UsersImplementation usersI = new UsersImplementation();
            usersI.getFreeUser(freeUsers, start, end);
        }

        /**
         * Restituisce la lista inviti ricevuti da un utente da parte di organizzatori
         *
         * @param requests la lista da riempire con gli inviti
         */
        public void getInvites(List<String> requests){
            UsersImplementation usersI = new UsersImplementation();
            usersI.getInvites(requests, this.user.getUsername());
        }

        /**
         * Gestisce l'evento di creazione di un hackathon da parte di un admin
         *
         * @param title      il titolo del nuovo hackathon
         * @param venue      la location del nuovo hackathon
         * @param startDate  il giorno in cui comincerà il nuovo hackathon
         * @param endDate    il giorno in cui terminerà il nuovo hackathon
         * @param maxReg     il numero massimo di persone che potranno registrarsi al nuovo hackathon
         * @param maxPerTeam il numero massimo di persone che potranno far parte dello stesso team durante il nuovo hackathon
         * @param username   l'username dell'utente scelto dall'admin che ricoprirà il ruolo di organizzatore durante il nuovo hackathon
         * @param file       la foto locandina del nuovo hackathon
         * @return codice che permette di sapere se il nuovo hackathon è stato creato correttamente
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
         * Gestisce le richieste che necessitano di sapere la classe di cui fa parte l'istanza dell'utente loggato
         *
         * @return un valore vero/falso che determina se un utente è istanza della classe plUser o di una sua sottoclasse
         */
        public boolean getUserClass(){
        return !(this.user instanceof Participant || this.user instanceof Judge || this.user instanceof Organizer);
    }

        /**
         * Memorizza tutti i valori del'hackathon a cui l'utente partecipa, qualsiasi sia il suo ruolo
         */
        public void findHack() {
        ArrayList<Object> data = new ArrayList<>();
        if (this.user instanceof Organizer || this.user instanceof Judge) {
            OrgImplementation orgI = new OrgImplementation();
            orgI.findHack(this.user.getUsername(), data, this.user.getClass().getSimpleName());
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
         * Setta nuovi valori all'hackathon memorizzato
         *
         * @param currentTitleArea      titolo dell'hackathon a cui si fa riferimento in quel momento dell'esecuzione
         * @param currentVenueArea      location dell'hackathon a cui si fa riferimento in quel momento dell'esecuzione
         * @param currentStartArea      data di inizio dell'hackathon a cui si fa riferimento in quel momento dell'esecuzione
         * @param currentEndArea        data di fine dell'hackathon a cui si fa riferimento in quel momento dell'esecuzione
         * @param currentStartRegArea   data di apertura delle registrazioni dell'hackathon a cui si fa riferimento in quel momento dell'esecuzione
         * @param currentMaxRegArea     il numero massimo di utenti che possono registrarso all'hackathon a cui si fa riferimento in quel momento dell'esecuzione
         * @param currentCounterArea    il numero attuale di iscritti all'hackathon a cui si fa riferimento in quel momento dell'esecuzione
         * @param currentMaxTeamParArea il numero massimo di iscritti ad un solo team dell'hackathon a cui si fa riferimento in quel momento dell'esecuzione
         * @param currentProbDescArea   la descrizione del problema da risolvere dell'hackathon a cui si fa riferimento in quel momento dell'esecuzione
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
         * Trova l'ultimo hackathon terminato e ne memorizza i valori
         *
         * @return codice che permette di determinare se l'ultimo hackathon è stato trovato con successo
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
         * Riempi una lista di hackathon e di tutti i loro dati, inoltre chiama un metodo per cancellare le richieste scadute
         *
         * @param data la lista degli ultimi 15 hackathon e di tutti i loro dati
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
         * Restituisce l'id dell'hackathon aperto in un certo momento dell'esecuzione
         *
         * @return il codice identificativo dell'hackathon
         */
        public int getIdHack() {
        return idHack;
    }

        /**
         * Imposta il codice identificativo che si sta per aprire in un certo momento dell'esecuzione
         *
         * @param idHack il codice che voglio settare
         */
        public void setIdHack(int idHack) {
        this.idHack = idHack;
    }

        /**
         * Imposta i valori dell'hackathon che si sta per aprire durante l'esecuzione
         *
         * @param title        il titolo
         * @param venue        la location
         * @param startDate    la data di inizio
         * @param endDate      la data di fine
         * @param maxReg       il numero massimo di utenti che possono iscriversi
         * @param maxTeamPar   il numero massimo di utenti che possono far parte dello stesso team
         * @param problemDesc  la descrizione del problema
         * @param startRegDate la data in cui si aprono le iscrizioni
         * @param regCounter   il numero attuale di iscritti
         */
        public void setHackathon(String title, String venue, Date startDate, Date endDate, int maxReg, int maxTeamPar, String problemDesc, Date startRegDate, int regCounter) {
            this.hackathon = new Hackathon(title, venue, startDate, endDate, maxReg, maxTeamPar, problemDesc, startRegDate, regCounter);
    }

        /**
         * Setta i nuovi valori dell'hackathon memorizzato
         *
         * @param hackathon l'hackathon da memorizzare
         */
        public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

        /**
         * Restituisce la foto locandina dell'ultimo hackathon aperto durante l'esecuzione
         *
         * @return la foto locandina
         */
        public byte[] getPhoto() {
        return photo;
    }

        /**
         * Imposta la foto locandina memorizzata come quella dell'hackathon da aprire
         *
         * @param photo la foto locandina da memorizzare
         */
        public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

        /**
         * Memorizza la lista dei giudici dell'hackathon da aprire
         */
        public void getJudgesList(){
            judges.clear();
            HackathonImplementation hackI = new HackathonImplementation();
            hackI.getJudgesList(judges, this.idHack);
        }

        /**
         * Memorizza la lista dei giudici dell'hackathon a cui l'utente partecipa, qualsiasi sia il suo ruolo
         */
        public void getActJudgesList(){
            judges.clear();
            HackathonImplementation hackI = new HackathonImplementation();
            hackI.getJudgesList(judges, this.currIdHack);
        }

        /**
         * Restituisce il nickname dell'organizzatore dell'hackathon che vogliamo aprire
         *
         * @return il nickname dell'organizzatore
         */
        public String getOrganizer(){
            HackathonImplementation hackI = new HackathonImplementation();
            return hackI.getOrganizer(this.idHack);
        }

        /**
         * Restituisce la classifica dell'hackathon aperto
         *
         * @param ranking   lista dei nickname dei team messi in ordine di media voti
         * @param idHack    id dell'hackathon di cui vogliamo conoscere la classifica
         */
        public void getRanking(List<String> ranking, int idHack) {
        HackathonImplementation hackI = new HackathonImplementation();
        hackI.getRanking(ranking, idHack);
    }

        /**
         * Riempie una lista di tutti i partecipanti ad uno stesso hackathon,
         * a patto che non siano nello stesso team dell'utente in esecuzione, e che il team di cui fanno partenon sia pieno
         *
         * @param participants la lista da riempire di partecipanti
         */
        public void getHackParticipants(List<String> participants){
        ParticipantImplementation parI = new ParticipantImplementation();
        parI.getParticipants(participants, this.currIdHack, this.user.getUsername(), this.hackathon.getMaxTeamParticipant());
    }

        /**
         * Riempie una lista con i team che partecipano all'hackathon il cui giudice accede all'area personale
         *
         * @param teams la lista da riempire con i nomi dei team
         */
        public void getTeams(List<String> teams){
        JudgeImplementation judgeI = new JudgeImplementation();
        judgeI.getTeams(teams, this.currIdHack);
    }

        /**
         * Riempie una lista con tutti gli hackathon a cui un utente ha partecipato, qualsiasi sia stato il suo ruolo. Degli hackathon memorizza ogni informazione
         *
         * @param hackathon la lista da riempire con gli hackathon
         */
        public void getLastsUserHack(List<ArrayList<Object>> hackathon){
        UsersImplementation userI = new UsersImplementation();
        userI.getLastUserHacks(hackathon, this.user.getUsername());
    }

        /**
         * Restituisce il voto di un team su richiesta del giudice
         *
         * @param team il nickname del team di cui il giudice vuole vedere il voto
         * @return il voto del team selezionato, se inserito, altrimenti un codice che indica che il voto non è stato inserito
         */
        public int getMark(String team){
        JudgeImplementation judgeI = new JudgeImplementation();
        return judgeI.getMark(team, this.user.getUsername(), this.currIdHack);
    }

        /**
         * Riempie una lista con le richieste che un partecipante riceve
         *
         * @param requests la lista da riempire con le richieste
         */
        public void getRequests(List<String> requests){
            ParticipantImplementation parI = new ParticipantImplementation();
            parI.getRequests(requests, this.user.getUsername());
        }

        /**
         * Memorizza l'id del team del partecipante loggato
         */
        public void getTeam(){
            TeamImplementation teamI = new TeamImplementation();
            this.idTeam = teamI.getTeam(this.user.getUsername(), this.currIdHack);
        }

        /**
         * Memorizza il nickname del team del partecipante loggato
         *
         * @return il nickname del team
         */
        public String getNickname(){
            TeamImplementation teamI = new TeamImplementation();
            return teamI.getNickname(this.idTeam);
        }

        /**
         * Gestisce la richiesta del team di cambiare nickname
         *
         * @param nickname il nuovo nickname del team
         * @return codice che permette di sapere se il nickname è stato cambiato correttamente
         */
        public int changeNickname(String nickname){
            TeamImplementation teamI = new TeamImplementation();
            return teamI.changeNickname(nickname, this.idTeam);
        }

        /**
         * Riempie una lista con tutti i partecipanti allo stesso team dell'utente loggato
         *
         * @param teammates la lista da riempire con i partecipanti al team
         */
        public void findTeammates(List<String> teammates){
        TeamImplementation teamI = new TeamImplementation();
        teamI.findTeammates(teammates, this.idTeam);
    }

        /**
         * Gestisce l'invio di un file di aggiornamento da parte di un team
         *
         * @param file il file da inviare
         * @param name il nome del file da inviare
         * @return the codice che permette di sapere se il file è stato memorizzato correttamente
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
         * Riempie tre liste con i file, i nomi e i commenti dei documenti caricati dal team dell'utente loggato
         *
         * @param docs     la lista da riempire con i nomi dei documenti
         * @param files    la lista da riempire con i file veri e propri
         * @param comments la lista dei commenti di ogni file (se inseriti)
         */
        public void getDocuments(List<String> docs, List<byte[]> files, List<String> comments){
            TeamImplementation teamI = new TeamImplementation();
            teamI.getDocuments(docs, files, comments, this.idTeam);
        }

        /**
         * Restituisce l'id dell'hackathon a cui l'utente loggato partecipa
         *
         * @return l'id dell'hackhaton
         */
        public int getCurrIdHack() {
            return currIdHack;
        }

        /**
         * Riempie una lista con i nomi dei team ordinati per ordine di media voti crescente, generando quindi la classifica
         *
         * @param teams la lista da riempire con i nickname dei team
         */
        public void fillRanking(List<String> teams){
    if(this.hackathon.getEndDate().before(new Date())){
            HackathonImplementation hackI = new HackathonImplementation();
            hackI.getRanking(teams, this.idHack);
        }
    }

        /**
         * Restituisce la liste dei giudici dell'hackathon aperto
         *
         * @return la lista dei giudici
         */
        public List<String> getJudges() {
            return judges;
        }
    }
