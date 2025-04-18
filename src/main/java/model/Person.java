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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner input = new Scanner(System.in);
        System.out.print("Inserisci il nome: ");
        while (true){
            fName = input.nextLine();
            if(fName.length() <= 20){
                break;
            }
            else{
                System.out.println("Il nome non rispetta i requisiti, riprova");
            }
        }
        System.out.print("Inserisci il cognome: ");
        while (true){
            lName = input.nextLine();
            if(lName.length() <= 20){
                break;
            }
            else{
                System.out.println("Il cognome non rispetta i requisiti, riprova");
            }
        }
        System.out.print("Inserisci la tua data di nascita (dd/mm/yyyy): ");
        while (true){
            String s = input.nextLine();
            birthDate = LocalDate.parse(s,formatter);
            LocalDate today = LocalDate.now();
            Period period = Period.between(birthDate, today);
            if(period.getYears()>15){
                break;
            }
            else{
                System.out.println("Il limite di età per iscriversi è di 16 anni");
            }
        }
        //DA GESTIRE CON DB PER VERIFICARE SE L'USERNAME E' GIA' UTILIZZATO
        System.out.println("Inserisci l'username: ");
        while (true) {
            username = input.nextLine();
            if (username.length() >= 3 && username.length() <= 15) {
                break;
            } else {
                System.out.println("La password non rispetta i requisiti, riprova");
            }
        }
        System.out.printf("Inserisci una password di almeno 8 caratteri e non superiore a 16: ");
        while (true){
            password = input.nextLine();
            if(password.length() >= 8 && password.length() <= 16){
                break;
            }
            else {
                System.out.println("La password non rispetta i requisiti, riprova");
            }
        }
        User newUser = new User(fName, lName, birthDate, username, password);
    }
}
