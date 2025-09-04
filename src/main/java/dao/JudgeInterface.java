package dao;

import java.util.List;

/**
 * The interface Judge interface.
 */
public interface JudgeInterface {
    /**
     * Update description int.
     *
     * @param description the description
     * @param username    the username
     * @return the int
     */
    int updateDescription(String description, String username);

    /**
     * Gets teams.
     *
     * @param teams  the teams
     * @param idHack the id hack
     */
    void getTeams(List<String> teams, int idHack);

    /**
     * Gets documents.
     *
     * @param team     the team
     * @param idHack   the id hack
     * @param files    the files
     * @param names    the names
     * @param comments the comments
     */
    void getDocuments(String team, int idHack, List<byte[]> files, List<String> names, List<String> comments);

    /**
     * Sets comment.
     *
     * @param comment  the comment
     * @param username the username
     * @param idHack   the id hack
     * @param doc      the doc
     * @param team     the team
     * @return the comment
     */
    int setComment(String comment, String username, int idHack, String doc, String team);

    /**
     * Assign mark int.
     *
     * @param team     the team
     * @param mark     the mark
     * @param username the username
     * @param idHack   the id hack
     * @return the int
     */
    int assignMark(String team, int mark, String username, int idHack);

    /**
     * Gets mark.
     *
     * @param team     the team
     * @param username the username
     * @param idHack   the id hack
     * @return the mark
     */
    int getMark(String team, String username, int idHack);
}
