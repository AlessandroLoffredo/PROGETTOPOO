package dao;

import java.util.List;

/**
 * Interfaccia che dichiara i metodi utilizzati per le operazioni di lettura dal DB, che recuperano le informazioni degli hackathon
 */
public interface HackathonInterface {
    /**
     * Riempie una lista con gli ultimi 15 hackathon e con tutte le loro informazione
     *
     * @param data la lista da riempire
     */
    void getHackList (List<List<Object>> data);

    /**
     * Riempie una lista con tutti i giudici di un determinato hackathon
     *
     * @param judges la lista da riempire con i giudici della competizione
     * @param idHack l'id dell'evento di cui si vogliono conoscere i giudici
     */
    void getJudgesList (List<String> judges, int idHack);

    /**
     * Restituisce l'username dell'organizzatore di un determinato hackathon
     *
     * @param idHack l'id dell'hackathon di cui si vuole sapere l'organizzatore
     * @return l'username dell'organizzatore dell'hackathon di cui si è inviato l'id
     */
    String getOrganizer (int idHack);

    /**
     * Rispetto ad un identificativo di un hackathon,
     * riempie una lista con i nickname dei team partecipanti a quell'hackathon in ordine di media voti crescente, in modo da avere una classifica
     *
     * @param ranking la lista da riempire con i nickname dei team
     * @param idHack  l'id dell'hackathon di cui vogliamo conoscere la classifica
     */
    void getRanking (List<String> ranking, int idHack);

    /**
     * Si occupa di chiamare una funzione del DBMS che elimina le richieste il cui admin di riferimento è terminato
     *
     * @param idHacks la lista degli hackathon terminati di cui si vogliono cancellare le richieste
     */
    void removeRequests(String idHacks);
}
