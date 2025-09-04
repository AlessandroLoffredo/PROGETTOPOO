package dao;

import java.time.LocalDate;

/**
 * The interface Admin interface.
 */
public interface AdminInterface {
    /**
     * New hack int.
     *
     * @param title      the title
     * @param venue      the venue
     * @param startDate  the start date
     * @param endDate    the end date
     * @param maxReg     the max reg
     * @param maxPerTeam the max per team
     * @param username   the username
     * @param photoData  the photo data
     * @return the int
     */
    int newHack(String title, String venue, LocalDate startDate, LocalDate endDate, int maxReg, int maxPerTeam, String username, byte[] photoData);
}
