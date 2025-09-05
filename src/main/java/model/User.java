package model;

/**
 * The type User.
 */
public class User {
    private final String fName;
    private final String lName;
    private String username;
    private String password;

    /**
     * Instantiates a new User.
     *
     * @param fName    the f name
     * @param lName    the l name
     * @param username the username
     * @param password the password
     */
    public User(String fName, String lName, String username, String password){
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
    }


    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
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
     * Gets name.
     *
     * @return the name
     */
    public String getfName() {
        return fName;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getlName() {
        return lName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

}
