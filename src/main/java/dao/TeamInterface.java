package dao;

import java.time.LocalDate;
import java.util.List;

/**
 * Interfaccia che dichiara tutti i metodi che sono di competenza di un team, o che in qualche modo lo riguardano
 */
public interface TeamInterface {
    /**
     * Restituisce l'id del team di cui l'utente loggato fa parte
     *
     * @param username l'username dell'utente loggato
     * @param idHack   l'id dell'hackathon a cui l'utente loggato partecipa
     * @return l'identificato del team
     */
    int getTeam(String username, int idHack);

    /**
     * Restituisce il nome del team di cui l'utente fa parte
     *
     * @param idTeam l'id del team di cui l'utente loggato fa parte
     * @return il nome del team
     */
    String getNickname(int idTeam);

    /**
     * Gestisce la richiesta del team di cambiare nickname, a patto che il nuovo nickname non sia già utilizzato in quell'hackathon
     *
     * @param nickname il nuovo nickname del team
     * @param idTeam   l'id del team che vuole cambiare nickname
     * @return codice che permette di sapere se il nickname è stato cambiato, e quindi se è stato aggiornato correttamente il DB
     */
    int changeNickname(String nickname, int idTeam);

    /**
     * Trova e riempie una lista con gli username dei partecipanti che fanno parte dello stesso team del partecipante loggato
     *
     * @param teammates la lista da riempire con gli username
     * @param idTeam    l'id del team di cui il partecipante fa parte
     */
    void findTeammates(List<String> teammates, int idTeam);

    /**
     * Memorizza nel DB tutte le informazioni del documento che il team vorrebbe caricare
     *
     * @param file       il file vero e proprio
     * @param name       il nome del documento
     * @param idTeam     l'id del team di cui l'utente loggato fa parte
     * @param dateUpload la data in cui viene caricato il file
     * @return codice che permette di sapere se il documento è stato caricato correttamente, e quindi memorizzato nel DB
     */
    int sendFile(byte[] file, String name, int idTeam, LocalDate dateUpload);

    /**
     * Recupera e riempie tre liste con i file, i nomi e i commenti dei documenti caricati dal team dell'utente loggato
     *
     * @param docs     la lista da riempire con i nomi
     * @param files    la lista da riempire con i file
     * @param comments la lista da riempire con i commenti dei file (se inseriti dai giudici)
     * @param idTeam   l'id del team di cui l'utente loggato fa parte
     */
    void getDocuments(List<String> docs, List<byte[]> files, List<String> comments, int idTeam);
}
