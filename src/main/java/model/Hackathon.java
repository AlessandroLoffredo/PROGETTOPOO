package model;
import java.util.*;

/**
 * The type Hackathon.
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

    private ArrayList<Judge> judesList;


    /**
     * Instantiates a new Hackathon.
     *
     * @param title              the title
     * @param venue              the venue
     * @param startDate          the start date
     * @param endDate            the end date
     * @param maxRegistration    the max registration
     * @param maxTeamParticipant the max team participant
     * @param problemDescription the problem description
     * @param startRegDate       the start reg date
     * @param regCounter         the reg counter
     */
//BUILDER
    public Hackathon(String title, String venue, Date startDate, Date endDate, int maxRegistration, int maxTeamParticipant, String problemDescription, Date startRegDate, int regCounter/*, Organizer hackOrganizer*/) {
        this.title = title;
        this.venue = venue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxRegistration = maxRegistration;
        this.maxTeamParticipant = maxTeamParticipant;
        this.ranking = new ArrayList<>();
        this.problemDescription = problemDescription;
        this.startRegDate = startRegDate;
        Calendar giorno = Calendar.getInstance();
        giorno.setTime(startDate);
        giorno.add(Calendar.DAY_OF_MONTH, -2);
        this.endRegDate = giorno.getTime();
        this.regCounter = regCounter;
        this.judesList = new ArrayList<>();
    }

    /**
     * Add team.
     *
     * @param t the t
     */
//METHODS
    public void addTeam(Team t){ ranking.add(t); }

    /**
     * Get team list.
     *
     * @return the list
     */
    public List<Team> getTeam(){ return ranking; }


    /**
     * Add registration.
     */
    public void addRegistration(){ this.regCounter+=1; }

    /**
     * Gets reg counter.
     *
     * @return the reg counter
     */
    public int getRegCounter() { return regCounter; }

    /**
     * Get problem description string.
     *
     * @return the string
     */
    public String getProblemDescription(){ return problemDescription; }

    /**
     * Get max registration int.
     *
     * @return the int
     */
    public int getMaxRegistration(){ return maxRegistration; }

    /**
     * Get max team participant int.
     *
     * @return the int
     */
    public int getMaxTeamParticipant(){ return maxTeamParticipant; }

    /**
     * Get title string.
     *
     * @return the string
     */
    public String getTitle(){ return title; }

    /**
     * Set start reg date.
     *
     * @param date the date
     */
    public void setStartRegDate(Date date){
        this.startRegDate = date;
    }

    /**
     * Set problem description.
     *
     * @param problem the problem
     */
    public void setProblemDescription (String problem){
            this.problemDescription = problem;
    }

    /**
     * Add judge.
     *
     * @param j the j
     */
    public void addJudge(Judge j){ judesList.add(j); }


    /**
     * Get end reg date date.
     *
     * @return the date
     */
    public Date getEndRegDate(){
        return this.endRegDate;
    }

    /**
     * Gets start reg date.
     *
     * @return the start reg date
     */
    public Date getStartRegDate() {
        return startRegDate;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Gets venue.
     *
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

}
