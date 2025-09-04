package dao;

/**
 * The interface Auth interface.
 */
public interface AuthInterface {
    /**
     * Log in int.
     *
     * @param username the username
     * @param password the password
     * @param names    the names
     * @return the int
     */
    int logIn(String username, String password, String[] names );

    /**
     * Sign up int.
     *
     * @param username the username
     * @param password the password
     * @param fName    the f name
     * @param lName    the l name
     * @return the int
     */
    int signUp(String username, String password, String fName, String lName);

    /**
     * Change password int.
     *
     * @param username the username
     * @param password the password
     * @return the int
     */
    int changePassword(String username, String password);

    /**
     * Change username int.
     *
     * @param newUsername the new username
     * @param oldUsername the old username
     * @return the int
     */
    int changeUsername(String newUsername, String oldUsername);
}
