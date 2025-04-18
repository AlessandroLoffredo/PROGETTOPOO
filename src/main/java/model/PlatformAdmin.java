package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class PlatformAdmin {
    private String username;
    private String password;
    private  Scanner in = new Scanner(System.in);


    public PlatformAdmin(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void resetUsername(){
        //DA GESTIRE CON DB PER VERIFICARE ESISTENZA ALTRO USERNAME UGUALE//
    }

    public void resetPassword(){
        System.out.printf("Inserisci una password di almeno 8 caratteri e non superiore a 16: ");
        String s = in.nextLine();
        while (s.length() < 8 || s.length() > 16){
            System.out.println("La password non rispetta i requisiti, riprova");
            s = in.nextLine();
        }
        this.password = s;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setHackathon(){
        System.out.printf("Inserire il nome del nuovo hackaton da creare");
        String title = in.nextLine();
        System.out.printf("Inserire la sede di svolgimento");
        String venue = in.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("Inserire la data di inizio della competizione (dd/mm/yyyy)");
        String firstDate = in.nextLine();
        LocalDate startDate = LocalDate.parse(firstDate, formatter);
        LocalDate endDate = startDate.plusDays(2);
        System.out.printf("Inserire il numero massimo di partecipanti");
        int maxReg = in.nextInt();
        System.out.printf("Inserire il numero massimo di partecipanti per team");
        int maxTeam = in.nextInt();
        // gestione organizzatore con db
        //Hackathon newestHackaton = new Hackathon(title, venue, startDate, endDate, maxReg, maxTeam, this);
        //return newestHackaton;
    }
}
