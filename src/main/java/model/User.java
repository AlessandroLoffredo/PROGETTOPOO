package model;

/**
 * La classe che contiene i metodi e gli attributi utili a definire un utente
 */
public class User {
    private final String fName;
    private final String lName;
    private String username;
    private String password;

    /**
     * Istanzia un nuovo utente
     *
     * @param fName    il nome
     * @param lName    il cognome
     * @param username l'username
     * @param password la password
     */
    public User(String fName, String lName, String username, String password){
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
    }


    /**
     * Imposta l'username di un utente
     *
     * @param username l'username che viene utilizzato
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Imposta la password di un utente
     *
     * @param password la password che viene utilizzata
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Restituisce l'username utilizzato da un utente
     *
     * @return l'username utilizzato da un utente
     */
    public String getUsername(){
        return this.username;
    }


    /**
     * Restituisce il nome di un utente
     *
     * @return il nome di un utente
     */
    public String getfName() {
        return fName;
    }

    /**
     * Restituisce il cognome di un utente
     *
     * @return il cognome di un utente
     */
    public String getlName() {
        return lName;
    }

    /**
     * Restituisce la password utilizzata da un utente
     *
     * @return la password utilizzata da un utente
     */
    public String getPassword() {
        return password;
    }

}
