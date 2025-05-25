package model;

/**
 * Classe che contiene gli attributi principali di una Person, visitatore generale esterno.
 * Superclasse di User.
 */
public class Person {
    /**
     * Permette ad una Person di iscriversi alla piattaforma.
     *
     * @param username Username
     * @param password Password
     * @param fName    Nome della persona
     * @param lName    Cognome della persona
     * @return int: codice per verificare stato registrazione.
     */
//METHODS
    public int signUp(String username, char[] password, String fName, String lName){

        String convertedPass = new String(password);

        if(fName.isEmpty() || lName.isEmpty() || convertedPass.isEmpty() || username.isEmpty()){
            return -3;
        }

        if(fName.length()>20 || lName.length()>20){
            return -1;
        }

        if(username.length()<3 || username.length()>15 || convertedPass.length()<8 || convertedPass.length()>16) {
            return -2;
        }

        //DA GESTIRE CON DB PER VERIFICARE SE L'USERNAME E' GIA' UTILIZZATO
        /*
           In questa sezione di codice l'intero restituito sarà diverso visto che gestioamo dati da db
           return y
        */

        //SE TUTTO è ANDATO A BUON FINE E L'UTENTE VIENE INSERITO CORRETTAMENTE
        return 0;


        /*
        Il nuovo utente verrà inserito nel database con i suoi dati
        */
    }

    /**
     * Permette ad una Person di effettuare il login.
     *
     * @param username Username
     * @param password Password
     * @return User: utente associato all'username inserito
     */
    public User logIn(String username, char[] password){
        /*//Inserimento query al database, in modo da poter controllare la poteziale esistenza di un utente
        String convertedPass = new String(password);

        //IL CONTROLLO è SUL NUMERO DI TUPLE RESTITUITO DALLA QUERY
        if(username o password errati ){
           return null  ;
        }else if(potenziale risultato della query di logIn == 1){
            QUERY PER CONTROLLARE IL TIPO DI UTENTE
            Codice per la gestione dei vari casi
            User user = new User/Participant/Judge/Organizer (risultato query); //SULLA BASE DEI VALORI RESTITUITI DALLA QUERY
        }*/
        Judge judge = new Judge(null, null, "plutissimo", "forzanapoli");
        return judge;
    }
}
