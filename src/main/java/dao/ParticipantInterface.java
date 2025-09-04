package dao;

import java.util.List;

/**
 * The interface Participant interface.
 */
public interface ParticipantInterface {
    /**
     * Gets participants.
     *
     * @param participants the participants
     * @param idHack       the id hack
     * @param username     the username
     * @param max          the max
     */
    void getParticipants(List<String> participants, int idHack, String username, int max);

    /**
     * Find hack.
     *
     * @param username the username
     * @param data     the data
     */
    void findHack(String username, List<Object> data);

    /**
     * Send requests int.
     *
     * @param sender   the sender
     * @param idHack   the id hack
     * @param receiver the receiver
     * @param message  the message
     * @return the int
     */
    int sendRequests(String sender, int idHack, String receiver, String message);

    /**
     * Gets requests.
     *
     * @param requests the requests
     * @param username the username
     */
    void getRequests(List<String> requests, String username);

    /**
     * Accept request int.
     *
     * @param sender   the sender
     * @param receiver the receiver
     * @param idHack   the id hack
     * @return the int
     */
    int acceptRequest(String sender, String receiver, int idHack);

    /**
     * Decline request int.
     *
     * @param sender   the sender
     * @param receiver the receiver
     * @return the int
     */
    int declineRequest(String sender, String receiver);
}
