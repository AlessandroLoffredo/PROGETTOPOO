package dao;

import java.util.List;

public interface ParticipantInterface {
    void getParticipants(List<String> participants, int idHack, String username, int max);
    void findHack(String username, List<Object> data);
    int sendRequests(String sender, int idHack, String receiver, String message);
    void getRequests(List<String> requests, String username);
    int acceptRequest(String sender, String receiver, int idHack);
    int declineRequest(String sender, String receiver);
}
