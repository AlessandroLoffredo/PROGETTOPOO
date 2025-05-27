package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Classe che contiene gli attributi principali di un Participant, colui che effettivamente partecipa all'Hackathon all'interno di un team.
 * Estende la classe User.
 */

public class Participant extends User {
    private Team parTeam;
    private ArrayList<Request> invRecived;
    private String invSent;
    private Hackathon parHackathon;

    /**
     * Istanzia un nuovo Participant.
     *
     * @param fName    Nome del partecipante
     * @param lName    Cognome del partecipante
     * @param username Username
     * @param password Password
     */
//Costruttore
    public Participant(String fName, String lName, String username, String password){
        super(fName, lName, username, password);
        /*this.parHackathon = risultato della query di cercamento dell'hackathon*/
    }

    /**
     * Restituisci l'Hackathon a cui il partecipante è iscritto.
     *
     * @return Hackathon: Hackathon a cui il partecipante è iscritto.
     */
//Metodi
    public Hackathon getParHackathon() {
        return parHackathon;
    }

    /**
     * Imposta l'Hackathon a cui il partecipante è iscritto.
     *
     * @param parHackathon Hackathon a cui il partecipante è iscritto.
     */
    public void setParHackathon(Hackathon parHackathon) {
        this.parHackathon = parHackathon;
        //SCRIVERE NEL DB
    }

    /**
     * Restituisce il team nel quale si trova il partecipante.
     *
     * @return Team: team nel quale si trova il partecipante.
     */
    public Team getParTeam() {
        if (parTeam == null) {
            System.out.println("Il partecipante non fa parte di nessun Team.");
            return null;
        }
        else {
            return parTeam;
        }
    }

    /**
     * Imposta il team nel quale si trova il partecipante.
     *
     * @param t team nel quale si trova il partecipante.
     */
    public void setParTeam(Team t){
       this.parTeam = t;
    }

    /**
     * Aggiunge una richiesta nella lista delle richieste ricevute dal partecipante.
     *
     * @param request richiesta ricevuta dal partecipante.
     */
    public void addInvRecived(Request request){
        this.invRecived.add(request);
        //QUI CI SARà LA QUERY DI INSERIMENTO DELLA RICHIESTA NEL DB
    }

    /**
     * Permette di creare un documento e aggiungerlo alla lista dpcumenti del team.
     */
    public void manageDoc(){
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci Descrizione (Non andare a capo): ");
        String s = input.nextLine();
        LocalDateTime oggi = LocalDateTime.now();
        Document doc = new Document(s, oggi, this.parTeam);
        this.parTeam.addDoc(doc);
        input.close();
    }


    /**
     * Permette di inviare le richieste per partecipare ad un team esistente.
     *
     * @param message  messaggio motivazionale per entrare nel team
     * @param receiver username dell'utente a cui inviare la richiesta
     * @return int: codice per verificare stato invio.
     */
    public int sendRequest (String message, String receiver) {
        //QUERY PER CONTROLLARE L'ESISTENZA DEL RICEVENTE
        //PARTECIPANTE DI TEST, IL VERO RICEVENTE VERRà INIZIALIZZATO CON UNA QUERY
        Participant parReceiver = new Participant(null, null, "pippo", "pluto");

        if(parReceiver.getParTeam().getParList().size() == this.parHackathon.getMaxTeamParticipant()){
            return -1;
        }else{
            parReceiver.addInvRecived(new Request(message, this)); //stiamo aggiungendo la richiesta di invito alla lista degli inviti ricevuti
            return 0;
        }
    }



    /*public int answerInvRecived () {
        for(Request r : invRecived){
            if(this.parTeam.getParList().size() == this.parHackathon.getMaxTeamParticipant()){
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
                        //this.parTeam.addParticipant(/*Partecipante*///);
                        //Non possiamo aggiungere l'oggetto participant perchè manca il db.
                    //}*/
                    //else{ /*Verifica del nickname giàà esistente del team tramite db*/
                        /*System.out.println("Non fai ancora parte di un Team! Inserisci il nome: ");
                        String nome = input.nextLine().trim();
                        Team newestTeam = new Team(nome, this.parHackathon);
                        this.parTeam = newestTeam;*/
                       // this.parTeam.addParticipant(/*Partecipante*/);
                        /*
                        in questa sezione verrano effettuati diversi controlli sulla validità del nome del team, tra cui team esistenti
                        e nomi di altri partecipanti
                        */
                        /*this.parHackathon.addTeam(newestTeam);
                    }
                    this.invRecived.remove(0);
                }
                else if(risposta.equals("no")){
                    this.invRecived.remove(0);
                }
                input.close();
            }
        }
    }*/

    /**
     * Permette di creare un team.
     */
    public void createTeam(){
        if(this.parHackathon.getEndRegDate().equals(LocalDate.now()) && this.parTeam == null){
            this.parTeam = new Team(this.getUsername(), this.parHackathon);
            this.parHackathon.addTeam(this.parTeam);
        }
    }

    /**
     * Restituisce la lista di richieste ricevute.
     *
     * @return ArrayList: richieste ricevuta.
     */
    public ArrayList<Request> getInvRecived() {
        return invRecived;
    }
}
