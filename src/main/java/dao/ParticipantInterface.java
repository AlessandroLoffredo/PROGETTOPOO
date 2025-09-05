package dao;

import java.util.List;

/**
 * Interfaccia che dichiara tutti i metodi che sono di competenza di un partecipante, o che in qualche modo lo riguardano
 */
public interface ParticipantInterface {
    /**
     * Riempie una lista con i nomi dei partecipanti all'hackathon a cui partecipa l'utente loggato,
     * esclusi quelli che fanno parte del suo stesso team, e quelli che fanno parte di team pieni
     *
     * @param participants la lista da riempire con i nomi dei partecipanti
     * @param idHack       l'id dell'hackathon a cui l'utente loggato partecipa
     * @param username     l'username dell'utente loggato
     * @param max          il numero massimo di partecipanti ad uno stesso team definito per quella competizione
     */
    void getParticipants(List<String> participants, int idHack, String username, int max);

    /**
     * Trova e riempie una lista con tutte le informazioni riguardati l'hackathon a cui l'utente loggato partecipa (deve essere partecipante)
     *
     * @param username l'username dell'utente loggato
     * @param data     la lista da riempire con le informazioni dell'hackathon
     */
    void findHack(String username, List<Object> data);

    /**
     * Memorizza nel DB la richiesta che un partecipante invia ad un altro chiedendogli di unirsi al suo team
     *
     * @param sender   l'username dell'utente che chiede di unirsi ad un altro partecipante
     * @param idHack   l'id dell'hackathon a cui l'utente loggato partecipa
     * @param receiver l'username dell'utene a cui il sender invia la richiesta
     * @param message  il messaggio che il sender invia per convincere il receiver ad accettare la sua richiesta
     * @return codice che permette di sapere se la richiesta è stata inviata correttamente, e quindi memorizzata nel DB
     */
    int sendRequests(String sender, int idHack, String receiver, String message);

    /**
     * Riempie una lista con le richieste che l'utente loggato ha ricevuto
     *
     * @param requests la lista da riempire con le richieste
     * @param username l'username dell'utente loggato
     */
    void getRequests(List<String> requests, String username);

    /**
     * Gestisce il caso in cui l'utente loggato abbia accettato la richiesta, aggiornando il team di partecipazione di chi la invia, e cancellando la richiesta nel DB
     *
     * @param sender   l'username di chi ha inviato la richiesta
     * @param receiver l'username di chi ha ricevuto la richiesta, e quindi dell'utente loggato
     * @param idHack   l'id dell'hackathon a cui l'utente loggato partecipa
     * @return codice che permette di sapere se la richiesta è stata accettata correttamente,
     * e quindi se le operazioni da effettuare nel DB sono state effettuate con successo
     */
    int acceptRequest(String sender, String receiver, int idHack);

    /**
     * Gestisce il caso in cui l'utente loggato abbia rifiutato la richiesta, cancellandola dal DB
     *
     * @param sender   l'username di chi ha inviato la richiesta
     * @param receiver l'username di chi ha ricevuto la richiesta, e quindi dell'utente loggato
     * @return codice che permette di sapere se la richiesta è stata rifiutata correttamente e quindi cancellato da DB
     */
    int declineRequest(String sender, String receiver);
}
