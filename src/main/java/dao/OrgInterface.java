package dao;

import java.time.LocalDate;

public interface OrgInterface {
    int setupDate(LocalDate date, int idHack);
    boolean verifyDate(String username);
    boolean isStarted(String username);
    void getDates(String username, LocalDate dates[]);
    int inviteUser(String sender, String receiver, int idHack);
}
