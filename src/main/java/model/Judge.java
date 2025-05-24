package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Judge extends User{
    private Hackathon judgedHack;

    public Judge(String fName, String lName, String username, String password){
        super(fName, lName, username, password);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        Date fdata = null;
        try {
            data = dateFormat.parse("23/05/2025"); // Converte la stringa in Data
            fdata = dateFormat.parse("24/05/2025");
        } catch (ParseException e) {
            System.out.println("Errore: Formato della data non valido!");
        }
        this.judgedHack = new Hackathon("hack", "napoli", data, fdata, 50, 2, new Organizer(null, null, "pippo", "pluto"));
    }

    public void describeProblem(String description){
        //CARICARE LA DESCRIZIONE NEL DB
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