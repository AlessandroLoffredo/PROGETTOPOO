package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Interfaccia che dichiara tutti i metodi che sono di competenza di un utente, o che in qualche modo lo riguardano
 */
public interface UsersInterface {
    /**
     * Trova e riempie una lista con gli username degli utente liberi in un periodo di tempo
     *
     * @param freeUsers lista da riempire con gli username
     * @param start     la data in cui inizia l'intervallo
     * @param end       la data in cui termina l'intervallo
     */
    void getFreeUser(List<String> freeUsers, LocalDate start, LocalDate end);

    /**
     * Trova e riempie una lista con tutti gli inviti per diventare giudici che l'utente loggato (libero) ha ricevuto
     *
     * @param requests  la lista da riempire con gli inviti
     * @param receiver  l'username dell'utente che ha ricevuto gli inviti
     */
    void getInvites(List<String> requests, String receiver);

    /**
     * Controlla attraverso una query se l'utente loggato è libero in un periodo di tempo
     *
     * @param username l'username dell'utente loggato
     * @param start    la data di inizio dell'intervallo
     * @param end      la fine dell'intervallo
     * @return codice che permette di sapere se l'utente è libero nel periodo di tempo selezionato
     */
    int verifyingIsFree(String username, LocalDate start, LocalDate end);

    /**
     * Gestisce il caso in cui l'utente loggato abbia accettato l'invito,
     * cancellandolo dal DB, e rendendo l'utente che accettato un giudice
     *
     * @param sender   l'username di chi ha inviato l'invito
     * @param receiver l'username di chi ha ricevuto l'invito, e quindi dell'utente loggato
     * @return codice che permette di sapere se l'invito è stata accettato correttamente,
     * e quindi se le operazioni da eseguire sul DB eseguite correttamente
     */
    int acceptInvite(String sender, String receiver);

    /**
     * Gestisce il caso in cui l'utente loggato abbia rifiutato l'invito, cancellandolo dal DB
     *
     * @param sender   l'username di chi ha inviato l'invito
     * @param receiver l'username di chi ha ricevuto l'invito, e quindi dell'utente loggato
     * @return codice che permette di sapere se l'invito è stata rifiutato correttamente,
     * e l'invito cancellato dal DB
     */
    int declineInvite(String sender, String receiver);

    /**
     * Recupera dal DB tutti i dati dell'ultimo hackathon terminato, e li mette in una lista
     *
     * @param data la lista da riempire con le informazioni dell'ultimo hackathon terminato
     */
    void lastHack (List<Object> data);

    /**
     * Gestisce ciò che succede quando l'utente si iscrive ad un hackthon,
     * mettendolo in un team con il suo nome e aggiornando il suo ruolo a partecipante
     *
     * @param username l'username dell'utente loggato che si iscrive
     * @param idHack   l'id dell'hackathon a cui l'utente vuole iscriversi
     * @return codice che permette di sapere se l'iscrizione è stata effettuata correttamente,
     * e quindi le operazioni da effettuare nel DB sono terminate tutte correttamente
     */
    int subscribe(String username, int idHack);

    /**
     * Riempie una lista con tutti gli hackathon a cui un utente ha partecipato da quando si è registrato alla piattaforma,
     * con tutte le loro informazioni
     *
     * @param hackathon la lista da riempire con gli hackathon
     * @param username  l'username dell'utente registrato
     */
    void getLastUserHacks(List<ArrayList<Object>> hackathon, String username);
}
