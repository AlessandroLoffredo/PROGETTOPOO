package model;

import java.util.ArrayList;

public class Team {
    private short mark;
    private final String nickname;
    private ArrayList<Participant> parList;
    private Hackathon hackathon;
    private ArrayList<Document> docList;


    //COSTRUTTORE
    public Team(String nickname, Hackathon hackathon){
        this.nickname = nickname;
        this.parList = new ArrayList<>();
        this.hackathon = hackathon;
        this.docList = new ArrayList<>();
    }

    //METODI
    public short getMark(){
        return mark;
    }

    public void setMark(short mark) {
        this.mark = mark;
    }

    public String getNickname(){
        return nickname;
    }

    public void addParticipant(Participant participant){
        this.parList.add(participant);
    }

    public ArrayList<Participant> getParList(){
        return parList;
    }

    public ArrayList<Document> getDocList(){
        return docList;
    }

    public void addDoc(Document doc){
        this.docList.add(doc);
    }

}
