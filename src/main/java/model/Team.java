package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Team.
 */
public class Team {
    private short finalMark;
    private ArrayList<Integer> judgesMark;
    private final String nickname;
    private ArrayList<Participant> parList;
    private ArrayList<Document> docList;


    /**
     * Instantiates a new Team.
     *
     * @param nickname  the nickname
     * @param hackathon the hackathon
     */
//COSTRUTTORE
    public Team(String nickname, Hackathon hackathon){
        this.nickname = nickname;
        this.judgesMark = new ArrayList<>();
        this.finalMark = 0;
        this.parList = new ArrayList<>();
        this.docList = new ArrayList<>();
    }

    /**
     * Get final mark short.
     *
     * @return the short
     */
//METODI
    public short getFinalMark(){
        return finalMark;
    }

    /**
     * Add mark.
     *
     * @param mark the mark
     */
    public void addMark(short mark) {
        this.judgesMark.add((int)mark);
    }

    /**
     * Get nickname string.
     *
     * @return the string
     */
    public String getNickname(){
        return nickname;
    }

    /**
     * Add participant.
     *
     * @param participant the participant
     */
    public void addParticipant(Participant participant){
        this.parList.add(participant);
        //AGGIUNTA PARTECIPANTE AL DB
    }

    /**
     * Get par list list.
     *
     * @return the list
     */
    public List<Participant> getParList(){
        return parList;
    }

    /**
     * Get doc list list.
     *
     * @return the list
     */
    public List<Document> getDocList(){
        return docList;
    }

    /**
     * Add doc.
     *
     * @param doc the doc
     */
    public void addDoc(Document doc){
        this.docList.add(doc);
    }

}
