package model;

import java.util.ArrayList;

public class Team {
    private short finalMark;
    private ArrayList<Integer> judgesMark;
    private final String nickname;
    private ArrayList<Participant> parList;
    private Hackathon hackathon;
    private ArrayList<Document> docList;


    //COSTRUTTORE
    public Team(String nickname, Hackathon hackathon){
        this.nickname = nickname;
        this.judgesMark = new ArrayList<>();
        this.finalMark = 0;
        this.parList = new ArrayList<>();
        this.hackathon = hackathon;
        this.docList = new ArrayList<>();
    }

    //METODI
    public short getFinalMark(){
        return finalMark;
    }

    public void addMark(short mark) {
        this.judgesMark.add((int)mark);
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

    public void avgMark(){
        for (int mark : this.judgesMark) {
            this.finalMark += (short) mark;
        }
        if (!this.judgesMark.isEmpty()) {
            this.finalMark /= this.judgesMark.size();
            this.finalMark = (short) Math.round(this.finalMark);
        } else {
            System.out.println("La lista dei punteggi è vuota. Impossibile calcolare il finalMark.");
        }
    }
}
