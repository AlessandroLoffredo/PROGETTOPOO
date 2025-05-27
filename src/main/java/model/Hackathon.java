package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

/**
 * Classe che contiene gli elementi principali di un Hackathon, evento su cui si basa tutto il programma.
 */
public class Hackathon {
    private final String title;
    private String venue;
    private Date startDate;
    private Date endDate;
    private final int maxRegistration;
    private final int maxTeamParticipant;
    private ArrayList<Team> ranking;
    private String problemDescription;
    private Date startRegDate;
    private Date endRegDate;
    private int regCounter;
    private Organizer hackOrganizer;
    private ArrayList<Judge> judesList;


    /**
     * Instanzia un nuovo Hackathon.
     *
     * @param title              Titolo dell'Hackathon.
     * @param venue              Luogo dove si svolgerà l'Hackathon.
     * @param startDate          Data di inzio.
     * @param endDate            Data di fine.
     * @param maxRegistration    Numero massimo di partecipanti all'Hackathon.
     * @param maxTeamParticipant Numero massimo di partecipanti all'interno di un team.
     * @param hackOrganizer      Organizzatore dell'Hackathon
     */
//BUILDER
    public Hackathon(String title, String venue, Date startDate, Date endDate, int maxRegistration, int maxTeamParticipant, Organizer hackOrganizer) {
        this.title = title;
        this.venue = venue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxRegistration = maxRegistration;
        this.maxTeamParticipant = maxTeamParticipant;
        this.ranking = new ArrayList<>();
        this.problemDescription = "";
        this.startRegDate = null;
        Calendar giorno = Calendar.getInstance();
        giorno.setTime(startDate);
        giorno.add(Calendar.DAY_OF_MONTH, -2);
        this.endRegDate = giorno.getTime();
        this.regCounter = 0;
        this.hackOrganizer = hackOrganizer;
        this.judesList = new ArrayList<>();
    }

    /**
     * Inserisce un nuovo team nella lista dei team di un Hackathon.
     *
     * @param t Team che partecipa all'Hackathon.
     */
//METHODS
    public void addTeam(Team t){ ranking.add(t); }

    /**
     * Restituisce la lista dei team che partecipano all'Hackathon.
     *
     * @return ArrayList: lista dei team che partecipano all'Hackathon.
     */
    public ArrayList<Team> getTeam(){ return ranking; }

    /**
     * Ordina i team partecipanti all'Hackathon e pubblica le classifiche.
     */
    public void publishRanking() {
        for (Team team : ranking) {
            team.avgMark();
        }
        ranking.sort(Comparator.comparing(Team::getFinalMark));
        for (Team team : ranking) {
            System.out.println(team.getNickname());
        }
    }

    /**
     * Aggiorna il counter che tiene traccia di quanti partecipanti ci sono in un Hackathon.
     */
    public void addRegistration(){ this.regCounter+=1; }

    /**
     * Restituisce il counter che tiene traccia di quanti partecipanti ci sono in un Hackathon.
     *
     * @return int: counter che tiene traccia di quanti partecipanti ci sono in un Hackathon.
     */
    public int getRegCounter() { return regCounter; }

    /**
     * Restituisce la descrizione del problema.
     *
     * @return String: descrizione del problema.
     */
    public String getProblemDescription(){ return problemDescription; }

    /**
     * Restituisce il numero massimo di partecipanti che possono partecipare ad un Hackathon.
     *
     * @return int: numero massimo di partecipanti che possono partecipare ad un Hackathon.
     */
    public int getMaxRegistration(){ return maxRegistration; }

    /**
     * Restituisci il numero massimo di partecipanti che possono essere in un team.
     *
     * @return int: numero massimo di partecipanti che possono essere in un team.
     */
    public int getMaxTeamParticipant(){ return maxTeamParticipant; }

    /**
     * Restituisce il titolo dell'Hackathon.
     *
     * @return String: titolo dell'Hackathon.
     */
    public String getTitle(){ return title; }

    /**
     * Imposta il giorno in cui inziano le iscrizioni all'Hackaton.
     *
     * @param date giorno in cui inziano le iscrizioni all'Hackaton.
     */
    public void setStartRegDate(Date date){
        this.startRegDate = date;
    }

    /**
     * Imposta la descrizione del problema.
     *
     * @param problem descrizione del problema.
     */
    public void setProblemDescription (String problem){
            this.problemDescription = problem;
    }

    /**
     * Aggiunge un giudice alla lista giudici dell'Hackathon.
     *
     * @param j giudice che giudicherà l'Hackathon.
     */
    public void addJudge(Judge j){ judesList.add(j); }

    /*public void endHackathon(){
        for(Team team : ranking){
            for(Participant participant : team.getParList()){
                participant.setIsBusy(false);
            }
        }
        hackOrganizer.setIsBusy(false);
        for(Judge judge : judesList){
            judge.setIsBusy(false);
        }
    }*/

    /**
     * Restituisce l'organizzatore dell'Hackathon.
     *
     * @return Organizer: organizzatore dell'Hackathon.
     */
    public Organizer getHackOrganizer(){
        return this.hackOrganizer;
    }

    /**
     * Restituisce il giorno in cui terminano le iscrizioni all'Hackathon.
     *
     * @return Date: giorno in cui terminano le iscrizioni all'Hackathon.
     */
    public Date getEndRegDate(){
        return this.endRegDate;
    }

    /**
     * Restituisce il giorno in cui inziano le iscrizioni all'Hackathon.
     *
     * @return Date: giorno in cui inziano le iscrizioni all'Hackathon.
     */
    public Date getStartRegDate() {
        return startRegDate;
    }

    /**
     * Restituisce il giorno in cui inizia l'Hackathon.
     *
     * @return Date giorno in cui inizia l'Hackathon.
     */
    public Date getStartDate() {
        return startDate;
    }

    public static Hackathon findHackathon(String name){
        //QUERY PER TROVARE L'HACKATHON
        //return fine a se stessa inserita per testing
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        Date fdata = null;
        try {
            data = dateFormat.parse("23/05/2025"); // Converte la stringa in Data
            fdata = dateFormat.parse("24/05/2025");
        } catch (ParseException e) {
            System.out.println("Errore: Formato della data non valido!");
        }
        Hackathon hack = new Hackathon("hack", "napoli", data, fdata, 50, 2, new Organizer(null, null, "pippo", "pluto"));
        return hack;
    }
}
