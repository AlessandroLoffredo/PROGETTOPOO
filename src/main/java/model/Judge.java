package model;


/**
 * The type Judge.
 */
public class Judge extends User{
    private Hackathon judgedHack;

    /**
     * Instantiates a new Judge.
     *
     * @param fName    the f name
     * @param lName    the l name
     * @param username the username
     * @param password the password
     */
    public Judge(String fName, String lName, String username, String password) {
        super(fName, lName, username, password);
    }
}