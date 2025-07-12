package dao;

import model.Request;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface UsersInterface {
    void getFreeUser(ArrayList<String> freeUsers, LocalDate start, LocalDate end) throws SQLException;
    void getInvites(ArrayList<String> requests, String reciver);
    int veryfingIsFree(String username, LocalDate start, LocalDate end);
}
