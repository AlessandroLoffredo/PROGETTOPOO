package dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface UsersInterface {
    void getFreeUser(List<String> freeUsers, LocalDate start, LocalDate end) throws SQLException;
    void getInvites(List<String> requests, String reciver);
    int veryfingIsFree(String username, LocalDate start, LocalDate end);
    int acceptInvite(String sender, String receiver);
    int declineInvite(String sender, String receiver);
    void lastHack (List<Object> data);
    int subscribe(String username, int idHack);
    void getLastsUserHack(List<ArrayList<Object>> hackathon, String username);
}
