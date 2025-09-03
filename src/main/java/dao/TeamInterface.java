package dao;

import java.time.LocalDate;
import java.util.List;

public interface TeamInterface {
    int getTeam(String username, int idHack);
    String getNickname(int idTeam);
    int changeNickname(String nickname, int idTeam);
    void findTeammates(List<String> teammates, int idTeam);
    int sendFile(byte[] file, String name, int idTeam, LocalDate dataUpload);
    void getDocuments(List<String> docs, List<byte[]> files, List<String> comments, int idTeam);
}
