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
    private ArrayList<Judge> judesList;

    //BUILDER
    public Hackathon(String title, String venue, LocalDate startDate, LocalDate endDate, int maxRegistration, int maxTeamParticipant, Organizer hackOrganaizer) {
        this.title = title;
        this.venue = venue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxRegistration = maxRegistration;
        this.maxTeamParticipant = maxTeamParticipant;
        this.ranking = new ArrayList<>();
        this.problemDescription = "Problema da definire";
        this.startRegDate = null;
        this.endRegDate = null;
        this.regCounter = 0;
        this.hackOrganaizer = hackOrganaizer;
        this.judesList = new ArrayList<>();
    }

    //METHODS
    public void addTeam(Team t){ ranking.add(t); }

    public ArrayList<Team> getTeam(){ return ranking; }

    public void publishRanking() {
        ranking.sort(Comparator.comparing(Team::getMark));
        for (Team team : ranking) {
            System.out.println(team.getNickname());
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

    public void addJudge(Judge j){ judesList.add(j); }

    public void endHackathon(){
        for(Team team : ranking){
            for(Participant participant : team.getParticipant()){
                participant.setIsBusy(false);
            }
        }
        hackOrganaizer.setIsBusy(false);
        for(Judge judge : judesList){
            judge.setIsBusy(false);
        }
    }
}
