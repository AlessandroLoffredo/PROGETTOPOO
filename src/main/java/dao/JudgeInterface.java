package dao;

import java.util.List;

public interface JudgeInterface {
    int updateDescription(String description, String username);
    void getTeams(List<String> teams, int idHack);
    void getDocuments(String team, int idHack, List<byte[]> files, List<String> names, List<String> comments);
    int setComment(String comment, String username, int idHack, String doc, String team);
    int assignMark(String team, int mark, String username, int idHack);
    int getMark(String team, String username, int idHack);
}
