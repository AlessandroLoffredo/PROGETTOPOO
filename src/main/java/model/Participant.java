package model;

import java.time.LocalDate;
import java.util.Scanner;

public class Participant extends User {
    private Hackathon parHackathon;
    private Team parTeam;

    //Costruttore
    public Participant (String fName, String lName, LocalDate birthDate, String username, String password, Hackathon parHackathon) {
        super (fName,lName, birthDate, username, password);
        this.parHackathon = parHackathon;
        this.parTeam = null;
    }
    //Metodi
    public Hackathon getParHackathon() {
        return parHackathon;
    }

    public void setParHackathon(Hackathon parHackathon) {
        this.parHackathon = parHackathon;
        //Come faccio a collegarlo con l'hackathon esistente?
    }

    public Team getParTeam() {
        if (parTeam == null) {
            System.out.println("Il partecipante non fa parte di nessun Team.");
            return null;
        }
        else {
            return parTeam;
        }
    }

    public void setParTeam(Team t){
       this.parTeam = t;
    }

    public String sendRequest (Participant p) {
        Scanner input = new Scanner(System.in);
        String message = null;
        if(p.getParTeam() != null){
            if(p.getParTeam().getParList().length() == p.getParHackathon().getMaxTeamParticipant()){
                System.out.println("Il Team è al completo.");
                //deve restituire null? Come gestiamo il messaggio?
            }
            else{
                System.out.println("Inserisci il messaggio motivazionale per la partecipazione.");
                message = input.nextLine();
                return message;
                //come gestiamo la richiesta? A chi la restituisce?
            }
        }
        else {
            System.out.println("Inserisci il messaggio motivazionale.");
        }
    }
}
