package model;


import java.util.ArrayList;

/**
 * Classe che contiene gli attributi principali di un User, colui che naviga nella piattaforma e che può specializzarsi in figure diverse in diversi Hackathon (Participant, Judje, Organizer).
 * Superclasse di Participant, Judje e Organizer.
 * Estende la classe Person.
 */
public class User extends Person {
    private final String fName;
    private final String lName;
    private String username;
    private String password;
    private int userType; //QUANDO IL DB SARà CONNESSO, POTREMO SAPERE DI CHE TIPO DI UTENTE SI PARLA
    private ArrayList<Request> requestsJudge;

    /**
     * Istanzia un nuovo User.
     *
     * @param fName    Nome dell'utente
     * @param lName    Cognome dell'utente
     * @param username Username
     * @param password Password
     */
//BUILDER
    public User(String fName, String lName, String username, String password){
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
        this.requestsJudge = null;
    }

    //METHODS


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Restituisce l'username dell'utente.
     *
     * @return String: username dell'utente.
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Restituisci il tipo dell'utente.
     *
     * @return int: codice per tipo dell'utente.
     */
    public int getUserType() {
        return this.userType;
    }

    /**
     * Restituisce la lista di richieste come giudice.
     *
     * @return ArrayList: lista di richieste come giudice.
     */
    public ArrayList<Request> getRequestsJudge() {
        return this.requestsJudge;
    }

    /**
     * Aggiunge una Request alla lista di richieste come giudice.
     *
     * @param request richiesta come giudice
     */
    public void addRequest(Request request){
        this.requestsJudge.add(request);
    }

    /**
     * Permette di impostare il tipo di utente.
     *
     * @param userType tipo di utente
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }

    /**
     * Permette di trovare un utente tramite il suo username.
     *
     * @param username username dell'utente da cercare
     * @return User: utente cercato.
     */
    public static User findUser(String username){
        //QUERY CHE TROVA L'UTENTE ASSOCIATO ALLA STRINGA
        User user = new Participant(null, null, "Cacca", "pluto");
        return user;
    }

    public int subscribe(String nameHack){
        Hackathon hack = Hackathon.findHackathon(nameHack);
        if(hack.getRegCounter() < hack.getMaxRegistration()){
            hack.addRegistration();
            return 0;
        }else{
            return -1;
        }
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
        return "PlUser";
    }
}
