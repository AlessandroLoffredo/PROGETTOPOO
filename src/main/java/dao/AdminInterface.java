package dao;

import java.time.LocalDate;

/**
 * Interfaccia che dichiara tutti i metodi che eseguono delle operazioni in lettura/scrittura sul DB e che vengono eseguite dall'/sull' admin
 */
public interface AdminInterface {
    /**
     * Firma del metodo che permette all'admin di creare un nuovo hackathon e di salvarlo sul DB, mediante i valori dell'hackathon passati come parametri
     *
     * @param title      il titolo
     * @param venue      la location
     * @param startDate  la data di inizio
     * @param endDate    la data di fine
     * @param maxReg     il numero massimo di utenti che possono iscriversi all'evento
     * @param maxPerTeam il numero massimo di partecipanti che possono far parte dello stesso team
     * @param username   l'username dell'utente scelto come organizzatore
     * @param photoData  la foto locandina dell'evento
     * @return codice che permette di sapere se l'evento è stato memorizzato correttamente nel DB
     */
    int newHack(String title, String venue, LocalDate startDate, LocalDate endDate, int maxReg, int maxPerTeam, String username, byte[] photoData);
}
