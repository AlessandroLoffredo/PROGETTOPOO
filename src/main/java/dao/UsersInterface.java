package dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The interface Users interface.
 */
public interface UsersInterface {
    /**
     * Gets free user.
     *
     * @param freeUsers the free users
     * @param start     the start
     * @param end       the end
     * @throws SQLException the sql exception
     */
    void getFreeUser(List<String> freeUsers, LocalDate start, LocalDate end) throws SQLException;

    /**
     * Gets invites.
     *
     * @param requests the requests
     * @param reciver  the reciver
     */
    void getInvites(List<String> requests, String reciver);

    /**
     * Veryfing is free int.
     *
     * @param username the username
     * @param start    the start
     * @param end      the end
     * @return the int
     */
    int veryfingIsFree(String username, LocalDate start, LocalDate end);

    /**
     * Accept invite int.
     *
     * @param sender   the sender
     * @param receiver the receiver
     * @return the int
     */
    int acceptInvite(String sender, String receiver);

    /**
     * Decline invite int.
     *
     * @param sender   the sender
     * @param receiver the receiver
     * @return the int
     */
    int declineInvite(String sender, String receiver);

    /**
     * Last hack.
     *
     * @param data the data
     */
    void lastHack (List<Object> data);

    /**
     * Subscribe int.
     *
     * @param username the username
     * @param idHack   the id hack
     * @return the int
     */
    int subscribe(String username, int idHack);

    /**
     * Gets lasts user hack.
     *
     * @param hackathon the hackathon
     * @param username  the username
     */
    void getLastsUserHack(List<ArrayList<Object>> hackathon, String username);
}
