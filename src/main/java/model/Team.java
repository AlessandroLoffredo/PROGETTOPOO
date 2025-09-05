package model;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe che contiene i metodi e gli attributi utili a definire un team
 */
public class Team {
    private short finalMark;
    private ArrayList<Integer> judgesMark;
    private final String nickname;
    private ArrayList<Participant> parList;
    private ArrayList<Document> docList;

    /**
     * Istanzia un nuovo team
     *
     * @param nickname  il nickname utilizzato dal team
     * @param hackathon l'Hackathon a cui il team partecipa
     */
    public Team(String nickname, Hackathon hackathon) {
        this.nickname = nickname;
        this.judgesMark = new ArrayList<>();
        this.finalMark = 0;
        this.parList = new ArrayList<>();
        this.docList = new ArrayList<>();
    }
}
