package dao;

import model.Request;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface UsersInterface {
    void getFreeUser(ArrayList<String> freeUsers, LocalDate start, LocalDate end) throws SQLException;
    int newHack(String title, String venue, LocalDate startDate, LocalDate endDate, int maxReg, int maxPerTeam, String username);
    void getInvites(ArrayList<Request> requests, String reciver);
}
