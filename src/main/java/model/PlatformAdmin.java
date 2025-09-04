package model;


import java.util.Scanner;

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
     * Reset username.
     */
    public void resetUsername(){
        //DA GESTIRE CON DB PER VERIFICARE ESISTENZA ALTRO USERNAME UGUALE//
        Scanner in = new Scanner(System.in);
        System.out.println("Inserisci l'username (min 3 caratteri, max 15: ");
        this.username = in.nextLine();
        while (this.username.length()<3 || this.username.length()>15) {
            System.out.println("L'username non rispetta i requisiti (min 3 caratteri, max 15), riprova");
        }

        in.close();
    }

    /**
     * Reset password.
     */
    public void resetPassword(){
        Scanner in = new Scanner(System.in);
        System.out.println("Inserisci una password di almeno 8 caratteri e non superiore a 16: ");
        String s = in.nextLine();
        while (s.length() < 8 || s.length() > 16){
            System.out.println("La password non rispetta i requisiti (min 8 caratteri, max 16), riprova");
            s = in.nextLine();
        }
        this.password = s;
        in.close();
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
