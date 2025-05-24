package model;

import java.util.*;

public class Judge extends User{
    private Hackathon judgedHack;


    public Judge(String fName, String lName, String username, String password){
        super(fName, lName, username, password);
        /*this.judgedHack = risultato della query di cercamento dell'hackathon*/
    }

    public void describeProblem(String description){
        this.judgedHack.setProblemDescription(description);
    }

    public Hackathon getJudgedHack() {
        return judgedHack;
    }

    public void assignMark(){
        Scanner in = new Scanner(System.in);
        for(Team team : this.judgedHack.getTeam()){
            System.out.printf("Inserire il voto da assegnare al team "+team.getNickname()+"> ");
            short mark = in.nextShort();
            team.addMark(mark);
        }
        in.close();
    }

    public void examineDoc(){
        Scanner in = new Scanner(System.in);
        for(Team team : this.judgedHack.getTeam()){
            System.out.println("Il team "+team.getNickname()+" ha pubblicato il seguente documento il :" + team.getDocList().getLast().getDate());
            System.out.println((team.getDocList().getLast().getDescription()));
            System.out.println("Ora, aggiungi un commento al documento (non utilizzare enter/a capo fino alla fine del commento> ");
            String comment = in.nextLine();
            team.getDocList().getLast().setComment(comment);
        }
        in.close();
    }
}