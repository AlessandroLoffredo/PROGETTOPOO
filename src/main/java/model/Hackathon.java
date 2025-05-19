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
    private Organizer hackOrganizer;
    private ArrayList<Judge> judesList;

    //BUILDER
    public Hackathon(String title, String venue, LocalDate startDate, LocalDate endDate, int maxRegistration, int maxTeamParticipant, Organizer hackOrganizer) {
        this.title = title;
        this.venue = venue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxRegistration = maxRegistration;
        this.maxTeamParticipant = maxTeamParticipant;
        this.ranking = new ArrayList<>();
        this.problemDescription = "Problema da definire";
        this.startRegDate = null;
        this.endRegDate = startDate.minusDays(2);
        this.regCounter = 0;
        this.hackOrganizer = hackOrganizer;
        this.judesList = new ArrayList<>();
    }

    //METHODS
    public void addTeam(Team t){ ranking.add(t); }

    public ArrayList<Team> getTeam(){ return ranking; }

    public void publishRanking() {
        for (Team team : ranking) {
            team.avgMark();
        }
        ranking.sort(Comparator.comparing(Team::getFinalMark));
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

    public void setStartRegDate(LocalDate date){
        this.startRegDate = date;
    }

    public void setProblemDescription (String problem){
            this.problemDescription = problem;
    }

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

    public Organizer getHackOrganizer(){
        return this.hackOrganizer;
    }

    public LocalDate getEndRegDate(){
        return this.endRegDate;
    }
}
