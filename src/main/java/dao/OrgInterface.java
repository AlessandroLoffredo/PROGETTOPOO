package dao;

import java.time.LocalDate;
import java.util.List;

/**
 * Interfaccia che dichiara tutti i metodi che sono di competenza di un organizzatore, o che in qualche modo lo riguardano
 */
public interface OrgInterface {
    /**
     * Gestisce l'inserimento della data di apertura delle iscrizioni da parte dell'organizzatore
     *
     * @param date   la data inserita dall'organizzatore
     * @param idHack l'id dell'hackathon il cui organizzatore inserisce al data
     * @return the date
     */
    int setupDate(LocalDate date, int idHack);

    /**
     * Verifica se è stata già inserita la data di apertura delle iscrizioni di un hackathon
     *
     * @param idHack   l'id dell'hackathon di cui è organizzatore l'utente loggato
     * @return valore vero/falso che determina se la data è presente nel DB
     */
    boolean isStarted(int idHack);

    /**
     * Riempie un array con le tre date che interessano l'hackathon il cui organizzatore è loggato
     *
     * @param username l'username dell'organizzatore loggato
     * @param dates    l'array da riempire con le date
     */
    void getDates(String username, LocalDate[] dates);

    /**
     * Gestisce l'invio di un invito da parte dell'organizzatore verso un utente che vuole fare diventare giudice
     *
     * @param sender   l'username dell'organizzatore
     * @param receiver l'username dell'utente invitato
     * @param idHack   l'id dell'hackathon il cui organizzatore è loggato
     * @return the int
     */
    int inviteUser(String sender, String receiver, int idHack);

    /**
     * Trova e riempie una lista con tutte le informazioni dell'hackathon a cui l'utente loggato partecipa (deve essere giudice o organizzatore)
     *
     * @param username l'username dell'utente loggato
     * @param data     lla lista da riempire con le informazioni dell'hackathon
     * @param tabella  la classe a cui appartiene l'istanza dell'utente loggato
     */
    void findHack(String username, List<Object> data, String tabella);
}
