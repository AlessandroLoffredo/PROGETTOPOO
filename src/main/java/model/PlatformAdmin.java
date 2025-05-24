package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class PlatformAdmin {
    private String username;
    private String password;
    


    public PlatformAdmin(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void resetUsername(){
        //DA GESTIRE CON DB PER VERIFICARE ESISTENZA ALTRO USERNAME UGUALE//
        Scanner in = new Scanner(System.in);
        System.out.println("Inserisci l'username (min 3 caratteri, max 15: ");
        username = in.nextLine();
        while (username.length()<3 || username.length()>15) {
            System.out.println("L'username non rispetta i requisiti (min 3 caratteri, max 15), riprova");
        }
        this.username = username;
        //AGGIORNAMENTO DATI NEL DB
        in.close();
    }

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

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setHackathon(){
        Scanner in = new Scanner(System.in);
        System.out.println("Inserire il nome del nuovo hackaton da creare");
        String title = in.nextLine();
        System.out.println("Inserire la sede di svolgimento");
        String venue = in.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println("Inserire la data di inizio della competizione (yyyy/mm/dd)");
        String firstDate = in.nextLine();
        LocalDate startDate = LocalDate.parse(firstDate, formatter);
        System.out.println("Inserire la data di fine della competizione (yyyy/mm/dd)");
        String lastDate = in.nextLine();
        LocalDate endDate = LocalDate.parse(lastDate, formatter);
        System.out.println("Inserire il numero massimo di partecipanti");
        int maxReg = in.nextInt();
        System.out.println("Inserire il numero massimo di partecipanti per team");
        int maxTeam = in.nextInt();
        /*  gestione organizzatore con db
            l'admin avrà una visione di tutti gli utenti tra i quali sceglierà l'organizzatore, che verrà recuperato con una query
            e i cui attributi verrano modificati opportunamente
        */
        //Hackathon newestHackaton = new Hackathon(title, venue, startDate, endDate, maxReg, maxTeam, this);
        /*
            tutti gli attirbuti serviranno per effettura una query e aggiungere un nuovo hackathon al db
        */
        //L'INTERO METODO POTREBBE ESSERE SVOLTO CON LA GUI E QUINDI LE ISTRUZIONI FATTE IN MAIN CHE PASSERà I VALORI COME PARAMETRI DEL METODO
        in.close();
    }

    public void createHackathon(String title, String venue, Date stratDate, Date endDate, int maxRegistration, int maxTeamParticipant, Organizer hackOrganizer){
        Hackathon hack = new Hackathon(title, venue, stratDate, endDate, maxRegistration, maxTeamParticipant, hackOrganizer);
    }
}
