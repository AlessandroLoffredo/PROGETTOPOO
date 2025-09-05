package dao;

/**
 * Interfaccia che dichiara tutti i metodi relativi all'autenticazione di un utente e la modifica delle credenziali
 */
public interface AuthInterface {
    /**
     * Gestisce il LogIn di un utente, controllando le credenziali e il ruolo che assume in quel momento l'utente che si sta loggando
     *
     * @param username l'username dell'utente che intende loggarsi
     * @param password la password dell'utente che vuole loggarsi
     * @param names    array che conterrà nome e cognome dell'utente loggato
     * @return codice che permette di sapere se l'utente è riuscito a loggarsi, e quale ruolo svolge
     */
    int logIn(String username, String password, String[] names );

    /**
     * Gestisce l'azione di registrazione da parte di un nuovo utente
     *
     * @param username l'username che l'utente intende avere
     * @param password la password con cui effettuerà il logIn in futuro
     * @param fName    il nome
     * @param lName    il cognome
     * @return codice che permette di sapere se l'utente è riuscito a registrarsi, e quindi se le sue informazioni sono state memorizzate correttamente ne DB
     */
    int signUp(String username, String password, String fName, String lName);

    /**
     * Gestisce la volontà dell'utente di modificare la password
     *
     * @param username l'username di chi vuole modificare la propria password
     * @param password la nuova password che intende avere
     * @return codice che permette di sapere se l'utente è riuscito a modificare la propria password, ovvero se è stato aggiornato correttamente il DB
     */
    int changePassword(String username, String password);

    /**
     * Gestisce la volontà dell'utente di modificare il proprio username, a patto che il nuovo username non sia già stato utilizzato
     *
     * @param newUsername il nuovo username
     * @param oldUsername il vecchio username
     * @return codice che permette di sapere se l'utente è riuscito a modificare il proprio username, ovvero se è stato aggiornato correttamente il DB
     */
    int changeUsername(String newUsername, String oldUsername);
}
