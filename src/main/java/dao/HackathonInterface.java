package dao;

import java.util.List;

public interface HackathonInterface {
    void getHackList (List<List<Object>> data);
    void getJudgesList (List<String> judges, int idHack);
    String getOrganizer (int idHack);
    void getRanking (List<String> ranking, int idHack);
    void removeRequests(String idHacks);
}
