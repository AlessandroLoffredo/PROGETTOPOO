package model;

import java.util.ArrayList;

/**
 * Classe che contiene gli elementi principali di un Team, elemento che partecipa all'HAckathon e che effettivamente entra in gara per vincere.
 */
public class Team {
    private short finalMark;
    private ArrayList<Integer> judgesMark;
    private final String nickname;
    private ArrayList<Participant> parList;
    private Hackathon hackathon;
    private ArrayList<Document> docList;


    /**
     * Istanzia un nuvo Team.
     *
     * @param nickname  Nickname del team
     * @param hackathon Hackathon a cui partecipa il team
     */
//COSTRUTTORE
    public Team(String nickname, Hackathon hackathon){
        this.nickname = nickname;
        this.judgesMark = new ArrayList<>();
        this.finalMark = 0;
        this.parList = new ArrayList<>();
        this.hackathon = hackathon;
        this.docList = new ArrayList<>();
    }

    /**
     * Restituisci il voto finale che il team ha ricevuto dai giudici.
     *
     * @return short: voto finale che il team ha ricevuto dai giudici.
     */
//METODI
    public short getFinalMark(){
        return finalMark;
    }

    /**
     * Aggiunge un voto alla lista dei voti ricevuti dai giudici.
     *
     * @param mark voto ricevuto da un giudice.
     */
    public void addMark(short mark) {
        this.judgesMark.add((int)mark);
    }

    /**
     * Restituisce il nickname del team.
     *
     * @return String: nickname del team.
     */
    public String getNickname(){
        return nickname;
    }

    /**
     * Aggiunge un partecipante alla lista partecipanti del team.
     *
     * @param participant partecipante del team
     */
    public void addParticipant(Participant participant){
        this.parList.add(participant);
        //AGGIUNTA PARTECIPANTE AL DB
    }

    /**
     * Restituisce la lista dei partecipanti che fanno parte del team.
     *
     * @return ArrayList: lista dei partecipanti che fanno parte del team.
     */
    public ArrayList<Participant> getParList(){
        return parList;
    }

    /**
     * Restituisce la lista dei documenti del team.
     *
     * @return ArrayList: lista dei documenti del team.
     */
    public ArrayList<Document> getDocList(){
        return docList;
    }

    /**
     * Aggiunge un c alla lista di documenti del team.
     *
     * @param doc documento del team
     */
    public void addDoc(Document doc){
        this.docList.add(doc);
    }

    /**
     * Permette di calcolare il voto finale del team facendo una media dei voti ricevuti dai giudici.
     */
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

    public static int create(String nickname, Participant creator){
        //QUERY PER CONTROLLARE L'ESISTENZA DELLA COPPIA NICKNAME-HACKATHON (creator ha un metodo per sapere a quale hackathon partecipa)
        int result = 0;
        if(result == 0){
            Team team = new Team(nickname, creator.getParHackathon());
            team.addParticipant(creator);
            return 0;
        }else{
            return -1;
        }
    }
}
