package dao;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface Org interface.
 */
public interface OrgInterface {
    /**
     * Sets date.
     *
     * @param date   the date
     * @param idHack the id hack
     * @return the date
     */
    int setupDate(LocalDate date, int idHack);

    /**
     * Verify date boolean.
     *
     * @param username the username
     * @return the boolean
     */
    boolean verifyDate(String username);

    /**
     * Is started boolean.
     *
     * @param username the username
     * @return the boolean
     */
    boolean isStarted(String username, int idHack);

    /**
     * Gets dates.
     *
     * @param username the username
     * @param dates    the dates
     */
    void getDates(String username, LocalDate[] dates);

    /**
     * Invite user int.
     *
     * @param sender   the sender
     * @param receiver the receiver
     * @param idHack   the id hack
     * @return the int
     */
    int inviteUser(String sender, String receiver, int idHack);

    /**
     * Find hack.
     *
     * @param username the username
     * @param data     the data
     * @param tabella  the tabella
     */
    void findHack(String username, List<Object> data, String tabella);
}
