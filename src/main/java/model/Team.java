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
    public Team(String nickname, Hackathon hackathon) {
        this.nickname = nickname;
        this.judgesMark = new ArrayList<>();
        this.finalMark = 0;
        this.parList = new ArrayList<>();
        this.docList = new ArrayList<>();
    }
}
