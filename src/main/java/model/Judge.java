package model;
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
        this.judged.setProblemDescription(problem);
    }

    public void assignMark(Team t){
        System.out.printf("Inserire il voto da assegnare al team "+t.nickname+"> ");
        int mark = in.nextInt();
        t.setMark(mark);
    }

    public void examineDoc(Team t){
        System.out.println("Il team "+t.nickname+" ha pubblicato il seguente documento:");
        System.out.println((t.listDoc.get((t.listDoc.size)-1).description);
        System.out.println("Ora, aggiungi un commento al documento (non utilizzare enter/a capo fino alla fine del commento> ");
        String comment = in.nextLine();
        t.listDoc.get((t.listDoc.size)-1).setComment(comment);
    }





}