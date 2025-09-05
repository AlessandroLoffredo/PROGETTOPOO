package model;


/**
 * La classe che contiene i metodi e gli attributi utili a definire un platformAdmin
 */
public class PlatformAdmin {
    private String username;
    private String password;


    /**
     * Istanzia un nuovo platformAdmin
     *
     * @param username l'username
     * @param password la password
     */
    public PlatformAdmin(String username, String password){
        this.username = username;
        this.password = password;
    }


    /**
     * Restituisce l'username utilizzato dal platformAdmin
     *
     * @return l'username utilizzato dal platformAdmin
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Restituisce la password utilizzata dal platformAdmin
     *
     * @return la password utilizzata dal platformAdmin
     */
    public String getPassword(){
        return this.password;
    }


}
