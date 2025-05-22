package model;

public class Person {
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

    public int logIn(String username, char[] password){
        /*//Inserimento query al database, in modo da poter controllare la poteziale esistenza di un utente
        String convertedPass = new String(password);
        if(username.isEmpty() || password.isEmpty())
            return -1;

        //IL CONTROLLO è SUL NUMERO DI TUPLE RESTITUITO DALLA QUERY
        if(username o password errati ){
            return -2;
        }else if(potenziale risultato della query di logIn == 1){
            return 0;
        }*/
        //IL RETURN 0 SERVE SOLO A SCOPO FUNZIONALE E DI TESTING
        return 0;
    }
}
