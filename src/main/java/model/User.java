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

    /**
     * Permette di impostare nuovo username.
     *
     * @param newUsername Nuovo username
     * @param password    Password
     * @param oldUsername Vechhio username
     * @return int: codice per verificare stato cambio username.
     */
//METHODS
    public int resetUsername(String newUsername, char[] password, String oldUsername){
        String convertedPass = new String(password);
        if(newUsername.isEmpty() || convertedPass.isEmpty()){
            return -3;
        }
        if(newUsername.length()<3 || newUsername.length()>15) {
            return -1;
        }
        //DA GESTIRE CON DB PER VERIFICARE ESISTENZA ALTRO USERNAME UGUALE//
        /*
            if(controllo col db){
                return -2;
            }
        */
        //DOPO QUESTA GESTIONE...

        //ULTERIORE CONTROLLO DA INSERIRE PER LA PASSOWRD PER RENDERE IL SISTEMA PIù SICURO
        /*
               if(controllo pass){
                    return -4;
                }
         */
        //SE IL NUOVO USERNAME RISPETTA LE REGOLE...
        //AGGIORNAMENTO DATI NEL DB
        this.username = newUsername;
        return 0;
    }

    /**
     * Permette di impostare una nuova password
     *
     * @param oldPassword    Vecchia password
     * @param newPassword    Nuova password
     * @param confiermedPass Password confermata
     * @param username       Username
     * @return int: codice per verificare stato cambio password.
     */
    public int resetPassword(char[] oldPassword, char[] newPassword, char[] confiermedPass, String username) {
        String convertedNewPass = new String(newPassword);
        String convertedOldPass = new String(oldPassword);
        String conConfPass = new String(confiermedPass);
        if(convertedOldPass.isEmpty() || convertedNewPass.isEmpty() || conConfPass.isEmpty()){
            return -3;
        }
        if(!convertedNewPass.equals(conConfPass)){
            return -4;
        }
        if (convertedNewPass.length() < 8 || convertedNewPass.length() > 16){
            return -1;
        }
        //CONTROLLO UGUAGLIANZA VECCHIA PASSWORD CON oldPassword e username
        //CHIEDERE A TRAMONTANA SE è POSSIBILE ESEGUIRE QUESTO CONTROLLO AL DI FUORI DEL DB
        /*
          if(errore di db){
             return -2
          }
        */

        //SE LA NUOVA PASSWORD SUPERA I CONTROLLI...
        //VIENE AGGIORNATO IL DB GRAZIE ALLA STRINGA USERNAME
        this.password = convertedNewPass;
        return 0;
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
}
