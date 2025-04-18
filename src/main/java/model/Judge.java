package model;
import java.time.LocalDate;
import java.util.*;

public class Judge extends User{
    private Hackathon judged;
    private Scanner in = new Scanner(System.in);
    public Judge(String fName, String lName, LocalDate birthDate, String username, String password, Hackathon jdgHackathon){
        super(fName, lName, birthDate, username, password);
        this.judged = jdgHackathon;
    }

    public void describeProblem(){
        System.out.println("Inserire la descrizione del problema dell'hackatho (non utilizzare enter/a capo fino alla terminazione della descrizione)> ");
        String problem = in.nextLine();
        this.judged.setProblemDescription(problem);
    }

    public void assignMark(Team t){
        System.out.printf("Inserire il voto da assegnare al team "+t.nickname+"> ");
        t.mark = in.nextInt();
    }






}