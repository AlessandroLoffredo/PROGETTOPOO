package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Participant extends User {
    private Team parTeam;
    private ArrayList<String> invRecived;
    private String invSent;
    private Hackathon parHackathon;

    //Costruttore
    public Participant (String fName, String lName, LocalDate birthDate, String username, String password, Hackathon parHackathon) {
        super (fName,lName, birthDate, username, password);
        this.parTeam = null;
        this.invRecived = new ArrayList<>();
        this.invSent = null;
        this.parHackathon = parHackathon;
    }
    //Metodi
    public Hackathon getParHackathon() {
        return parHackathon;
    }

    public void setParHackathon(Hackathon parHackathon) {
        this.parHackathon = parHackathon;
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

    public void addInvRecived(String user, String inv){
        String s = user + " " + inv;
        invRecived.add(s);
    }

    public void manageDoc(){
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci Descrizione (Non andare a capo): ");
        String s = input.nextLine();
        LocalDateTime oggi = LocalDateTime.now();
        Document doc = new Document(s, oggi, this.parTeam);
        this.parTeam.addDoc(doc);
        input.close();
    }


    public void sendRequest (Participant p) {
        Scanner input = new Scanner(System.in);
        String message = null;
        //IF --> Il primo if serve a controllare se i partecipanti partecipano allo stesso hackathon,
        if(p.getParHackathon().equals(this.parHackathon)){
            if(p.getParTeam().getParList().size() == this.parHackathon.getMaxTeamParticipant()){
                System.out.println("Il Team è al completo.");
            }
            else{
                System.out.println("Inserisci il messaggio motivazionale per la partecipazione.");
                message = input.nextLine();
                p.addInvRecived(this.getUsername(), message); //stiamo aggiungendo la richiesta di invito alla lista degli inviti ricevuti
                this.invSent = p.getUsername() + " " + message;

            }
        }
        else {
            System.out.println("Non partecipa al tuo stesso Hackathon.");
        }
        input.close();
    }

    public void answerInvRecived () {
        for(String s : invRecived){
            if(this.parTeam.getParList().size() == this.parHackathon.getMaxTeamParticipant()){
                System.out.println("Il Team è al completo!");
                this.invRecived.remove(0);
            }
            else{
                String[] parts = s.split(" ",2);
                System.out.println("Hai ricevuto una richiesta da: " + parts[0] + "\nTi ha scritto: " + parts[1]);
                Scanner input = new Scanner(System.in);
                System.out.println("Accetti? (si/no)");
                String risposta = input.nextLine().trim().toLowerCase(Locale.ITALIAN);
                while(!risposta.equals("si") && !risposta.equals("no")){
                    System.out.println("Puoi inserire solo si/no!");
                    risposta = input.nextLine().trim().toLowerCase(Locale.ITALIAN);
                }
                if(risposta.equals("si")){
                    if(this.parTeam != null){
                        this.parTeam.addParticipant(/*Partecipante*/);
                        //Non possiamo aggiungere l'oggetto participant perchè manca il db.
                    }
                    else{ /*Verifica del nickname giàà esistente del team tramite db*/
                        System.out.println("Non fai ancora parte di un Team! Inserisci il nome: ");
                        String nome = input.nextLine().trim();
                        Team newestTeam = new Team(nome, this.parHackathon);
                        this.parTeam = newestTeam;
                        this.parTeam.addParticipant(/*Partecipante*/);
                        /*
                        in questa sezione verrano effettuati diversi controlli sulla validità del nome del team, tra cui team esistenti
                        e nomi di altri partecipanti
                        */
                        this.parHackathon.addTeam(newestTeam);
                    }
                    this.invRecived.remove(0);
                }
                else if(risposta.equals("no")){
                    this.invRecived.remove(0);
                }
                input.close();
            }
        }
    }

    public void createTeam(){
        if(this.parHackathon.getEndRegDate().equals(LocalDate.now()) && this.parTeam == null){
            this.parTeam = new Team(this.getUsername(), this.parHackathon);
            this.parHackathon.addTeam(this.parTeam);
        }
    }
}
