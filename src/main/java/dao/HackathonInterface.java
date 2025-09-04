package dao;

import java.util.List;

/**
 * The interface Hackathon interface.
 */
public interface HackathonInterface {
    /**
     * Gets hack list.
     *
     * @param data the data
     */
    void getHackList (List<List<Object>> data);

    /**
     * Gets judges list.
     *
     * @param judges the judges
     * @param idHack the id hack
     */
    void getJudgesList (List<String> judges, int idHack);

    /**
     * Gets organizer.
     *
     * @param idHack the id hack
     * @return the organizer
     */
    String getOrganizer (int idHack);

    /**
     * Gets ranking.
     *
     * @param ranking the ranking
     * @param idHack  the id hack
     */
    void getRanking (List<String> ranking, int idHack);

    /**
     * Remove requests.
     *
     * @param idHacks the id hacks
     */
    void removeRequests(String idHacks);
}
