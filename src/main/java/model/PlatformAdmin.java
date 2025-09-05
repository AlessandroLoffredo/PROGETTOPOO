package model;


/**
 * The type Platform admin.
 */
public class PlatformAdmin {
    private String username;
    private String password;


    /**
     * Instantiates a new Platform admin.
     *
     * @param username the username
     * @param password the password
     */
    public PlatformAdmin(String username, String password){
        this.username = username;
        this.password = password;
    }


    /**
     * Get username string.
     *
     * @return the string
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Get password string.
     *
     * @return the string
     */
    public String getPassword(){
        return this.password;
    }


}
