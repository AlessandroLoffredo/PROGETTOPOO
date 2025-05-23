package model;


import java.util.ArrayList;

public class User extends Person {
    private final String fName;
    private final String lName;
    private String username;
    private String password;
    private int userType; //QUANDO IL DB SARà CONNESSO, POTREMO SAPERE DI CHE TIPO DI UTENTE SI PARLA
    private ArrayList<Request> requestsJudge;

    //BUILDER
    public User(String fName, String lName, String username, String password){
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
        this.requestsJudge = null;
    }

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

    public String getUsername(){
        return this.username;
    }

    public int getUserType() {
        return this.userType;
    }

    public ArrayList<Request> getRequestsJudge() {
        return this.requestsJudge;
    }

    public void addRequest(Request request){
        this.requestsJudge.add(request);
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public static User findUser(String username){
        //QUERY CHE TROVA L'UTENTE ASSOCIATO ALLA STRINGA
        User user = new Participant(null, null, "Cacca", "pluto");
        return user;
    }
}
