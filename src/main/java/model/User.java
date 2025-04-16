package model;

import java.time.LocalDate;
import java.util.Scanner;

public class User {
    private final String fName;
    private final String lName;
    private String username;
    private String password;
    private final LocalDate birthDate;
    private String hackathonReg;

    //BUILDER
    public User(String fName, String lName, LocalDate birthDate){
        this.fName = fName;
        this.lName = lName;
        this.birthDate = birthDate;
        this.username = null;
        this.password = null;
        this.hackathonReg = null;
    }

    //METHODS
    public void setUsername(){
        Scanner input = new Scanner(System.in);
        System.out.printf("Inserisci il tuo username: ");
        this.username = input.nextLine();
    }

    public void setPassword(){
        Scanner input = new Scanner(System.in);
        System.out.printf("Inserisci una password di almeno 8 caratteri e non superiore a 16: ");
        while (true){
            String s = input.nextLine();
            if(s.length() >= 8 && s.length() <= 16){
                this.password = s;
                break;
            }
            else {
                System.out.println("La password non rispetta i requisiti, riprova");
            }
        }
    }

    public void regHackaton(Hackathon name){
        if(name.getRegCounter() < name.getMaxRegistration()){
            name.addRegistration();
            this.hackathonReg = name.getTitle();
        }
        else{
            System.out.println("Numero massimo di iscritti raggiunto");
        }
    }

    public String getUsername(){ return username; }

}
