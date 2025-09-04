package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The type Participant.
 */
public class Participant extends User {
    private Team parTeam;
    private ArrayList<Request> invRecived;
    private String invSent;
    private Hackathon parHackathon;

    /**
     * Instantiates a new Participant.
     *
     * @param fName    the f name
     * @param lName    the l name
     * @param username the username
     * @param password the password
     */
//Costruttore
    public Participant(String fName, String lName, String username, String password){
        super(fName, lName, username, password);
        /*this.parHackathon = risultato della query di cercamento dell'hackathon*/
    }

    /**
     * Gets par hackathon.
     *
     * @return the par hackathon
     */
//Metodi
    public Hackathon getParHackathon() {
        return parHackathon;
    }

    /**
     * Sets par hackathon.
     *
     * @param parHackathon the par hackathon
     */
    public void setParHackathon(Hackathon parHackathon) {
        this.parHackathon = parHackathon;
        //SCRIVERE NEL DB
    }

    /**
     * Gets par team.
     *
     * @return the par team
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
     * Set par team.
     *
     * @param t the t
     */
    public void setParTeam(Team t){
       this.parTeam = t;
    }

    /**
     * Add inv recived.
     *
     * @param request the request
     */
    public void addInvRecived(Request request){
        this.invRecived.add(request);
        //QUI CI SARà LA QUERY DI INSERIMENTO DELLA RICHIESTA NEL DB
    }

    /**
     * Manage doc.
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
     * Send request int.
     *
     * @param message  the message
     * @param receiver the receiver
     * @return the int
     */
    public int sendRequest (String message, String receiver) {
        //QUERY PER CONTROLLARE L'ESISTENZA DEL RICEVENTE
        //PARTECIPANTE DI TEST, IL VERO RICEVENTE VERRà INIZIALIZZATO CON UNA QUERY
        Participant parReceiver = new Participant(null, null, "pippo", "pluto");

        if(parReceiver.getParTeam().getParList().size() == this.parHackathon.getMaxTeamParticipant()){
            return -1;
        }else{
            parReceiver.addInvRecived(new Request(message, this.getUsername())); //stiamo aggiungendo la richiesta di invito alla lista degli inviti ricevuti
            return 0;
        }
    }

    /**
     * Gets inv recived.
     *
     * @return the inv recived
     */
    public List<Request> getInvRecived() {
        return invRecived;
    }

    @Override
    public String toString(){
        return "Participant";
    }
}
