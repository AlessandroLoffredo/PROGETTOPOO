package model;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

public class Judge extends User{
    private Hackathon judgedHack;
    private Scanner in = new Scanner(System.in);
    public Judge(String fName, String lName, LocalDate birthDate, String username, String password, Hackathon jdgHackathon){
        super(fName, lName, birthDate, username, password);
        this.judgedHack = jdgHackathon;
    }

    public void describeProblem(){
        System.out.println("Inserire la descrizione del problema dell'hackatho (non utilizzare enter/a capo fino alla terminazione della descrizione)> ");
        String problem = in.nextLine();
        this.judgedHack.setProblemDescription(problem);
    }

    public void assignMark(){
        for(Team team : this.judgedHack.getTeam()){
            System.out.printf("Inserire il voto da assegnare al team "+team.nickname+"> ");
            int mark = in.nextInt();
            team.setMark(mark);
        }
    }

    public void examineDoc(){
        for(Team team : this.judgedHack.getTeam()){
            System.out.println("Il team "+team.nickname+" ha pubblicato il seguente documento:");
            System.out.println((team.listDoc.get((team.listDoc.size)-1).description);
            System.out.println("Ora, aggiungi un commento al documento (non utilizzare enter/a capo fino alla fine del commento> ");
            String comment = in.nextLine();
            team.listDoc.get((team.listDoc.size)-1).setComment(comment);
        }

    }
}