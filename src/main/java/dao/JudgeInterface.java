package dao;

import java.util.List;

/**
 * Interfaccia che dichiara tutti i metodi che sono di competenza di un giudice, o che in qualche modo lo riguardano
 */
public interface JudgeInterface {
    /**
     * Aggiorna nel DB la descrizione del problema, inserita dal giudice
     *
     * @param description la descrizione che il giudice intende inserire
     * @param username    l'username del giudice che sta modificando la descrizione
     * @return codice che permette di sapere se la descrizione è stata modificata, e quindi se è stato aggiornato il DB
     */
    int updateDescription(String description, String username);

    /**
     * Riempie una lista con tutti i nickname dei team che partecipano all'hackathon di cui l'utente loggato è giudice
     *
     * @param teams  la lista da riempire con i nickname dei team
     * @param idHack l'id dell'hackathon di cui l'utente loggato è giudice
     */
    void getTeams(List<String> teams, int idHack);

    /**
     * Riempie tre liste con i file, i nomi e i commenti dei documenti caricati da un team scelto dal giudice
     *
     * @param team     il nickname del team scelto dal giudice
     * @param idHack   l'id dell'hackathon il cui utente loggato è giudice
     * @param files    la lista da riempire con i file caricati dal team scelto dal giudice
     * @param names    la lista da caricare con i nomi dei documenti caricati dal team scelto
     * @param comments i commenti inseriti dai giudici per ogni documento caricato del team scelto
     */
    void getDocuments(String team, int idHack, List<byte[]> files, List<String> names, List<String> comments);

    /**
     * Aggiunge il commento ad un documento selezionato dal giudice loggato
     *
     * @param comment  il commento che il giudice vuole inserire per il documento selezionato
     * @param username l'username del giudice che carica il commento
     * @param idHack   l'id dell'hackathon di cui uno dei giudici è loggato
     * @param doc      il nome del documento da commentare
     * @param team     il nickname del team che ha caricato il documento
     * @return codice che permette di sapere se il commento è stato caricato correttamente, quindi se il DB è stato aggiornato
     */
    int setComment(String comment, String username, int idHack, String doc, String team);

    /**
     * Aggiunge un voto al team che il giudice loggato seleziona
     *
     * @param team     il nickname del team selezionato
     * @param mark     il voto che il giudice assegna a quel team
     * @param username il nome utente del giudice loggato
     * @param idHack   l'id dell'hackathon di cui uno dei giudici è loggato
     * @return codice che permette di sapere se il voto è stato caricato correttamente, quindi se è stato inserito nel DB
     */
    int assignMark(String team, int mark, String username, int idHack);

    /**
     * Restituisce, se esiste, il voto che il giudice ha inserito per il team selezionato
     *
     * @param team     il nickname del team selezionato
     * @param username l'username del giudice loggato
     * @param idHack   l'id dell'hackathon di cui uno dei giudici è loggato
     * @return the mark
     */
    int getMark(String team, String username, int idHack);
}
