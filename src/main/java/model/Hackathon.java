package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class Hackathon {
    private final String title;
    private String venue;
    private LocalDate startDate;
    private LocalDate endDate;
    private final int maxRegistration;
    private final int maxTeamParticipant;
    private ArrayList<Team> ranking;
    private String problemDescription;
    private LocalDate startRegDate;
    private LocalDate endRegDate;
    private int regCounter;
    private Organizer hackOrganaizer;

    //BUILDER
    public Hackathon(String title, String venue, LocalDate startDate, LocalDate endDate, int maxRegistration, int maxTeamParticipant, Organizer hackOrganaizer) {
        this.title = title;
        this.venue = venue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxRegistration = maxRegistration;
        this.maxTeamParticipant = maxTeamParticipant;
        this.ranking = new ArrayList<Team>();
        this.problemDescription = "Problema da definire";
        this.startRegDate = null;
        this.endRegDate = null;
        this.regCounter = 0;
        this.hackOrganaizer = hackOrganaizer;
    }

    //METHODS
    public void addTeam(Team name){ ranking.add(name); }

    public void publishRanking() {
        ranking.sort(Comparator.comparing(Team::mark));
        for (Team team : ranking) {
            System.out.println(team.getNickName);
        }
    }

    public void addRegistration(){ this.regCounter+=1; }

    public int getRegCounter() { return regCounter; }

    public String getProblemDescription(){ return problemDescription; }

    public int getMaxRegistration(){ return maxRegistration; }

    public int getMaxTeamParticipant(){ return maxTeamParticipant; }

    public String getTitle(){ return title; }

    public void setStartEndRegDate(LocalDate date){
        this.startRegDate = date;
        this.endRegDate = endDate.minusDays(2);
    }

    public void setProblemDescription (String problem){
            this.problemDescription = problem;
    }

}
