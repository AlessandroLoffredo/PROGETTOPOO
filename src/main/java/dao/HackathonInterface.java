package dao;

import java.util.ArrayList;

public interface HackathonInterface {
    void getHackList (ArrayList<ArrayList<Object>> data);
    void getJudgesList (ArrayList<String> judges, int idHack);
    String getOrganizer (int idHack);
    void getRanking (ArrayList<String> ranking, int idHack);
}
