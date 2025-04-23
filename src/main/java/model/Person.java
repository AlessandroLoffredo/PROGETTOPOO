package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Person {
    //METHODS
    public void regPlatform(){
        String username = null;
        String password = null;
        String fName = null;
        String lName = null;
        LocalDate birthDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Scanner input = new Scanner(System.in);
        System.out.print("Inserisci il nome, max 20 caratteri: ");
        fName = input.nextLine();
        while (fName.length()>20){
            System.out.println("Il nome non rispetta i requisiti, inserisci un nome di max 20 caratteri: ");
            fName = input.nextLine();
        }
        System.out.print("Inserisci il cognome, max 20 caratteri: ");
        lName = input.nextLine();
        while (lName.length()>20){
            System.out.println("Il cognome non rispetta i requisiti, inseirisci un cognome di max 20 caratteri");
        }
        System.out.print("Inserisci la tua data di nascita (dd/mm/yyyy): ");
        String s = input.nextLine();
        birthDate = LocalDate.parse(s,formatter);
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthDate, today);

        while (period.getYears()<15){
            System.out.println("L'età non supera il limite minimo per iscriversi (16 anni), inserire un'altra data (yyyy/mm/dd): ");
            s = input.nextLine();
            birthDate = LocalDate.parse(s,formatter);
            today = LocalDate.now();
            period = Period.between(birthDate, today);
        }

        //DA GESTIRE CON DB PER VERIFICARE SE L'USERNAME E' GIA' UTILIZZATO
        System.out.println("Inserisci l'username (min 3 caratteri, max 15: ");
        username = input.nextLine();
        while (username.length()<3 || username.length()>15) {
            System.out.println("L'username non rispetta i requisiti (min 3 caratteri, max 15), riprova");
        }

        System.out.print("Inserisci una password di almeno 8 caratteri e non superiore a 16: ");
        password = input.nextLine();
        while (password.length()<8 || password.length()>16){
            System.out.println("La password non rispetta i requisiti (min 8 caratteri, max 16), riprova");
            password = input.nextLine();
        }
        User newUser = new User(fName, lName, birthDate, username, password);
        /*
        Il nuovo utente verrà inserito nel database con i suoi dati
        */
        input.close();
    }
}
