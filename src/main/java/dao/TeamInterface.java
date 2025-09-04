package dao;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface Team interface.
 */
public interface TeamInterface {
    /**
     * Gets team.
     *
     * @param username the username
     * @param idHack   the id hack
     * @return the team
     */
    int getTeam(String username, int idHack);

    /**
     * Gets nickname.
     *
     * @param idTeam the id team
     * @return the nickname
     */
    String getNickname(int idTeam);

    /**
     * Change nickname int.
     *
     * @param nickname the nickname
     * @param idTeam   the id team
     * @return the int
     */
    int changeNickname(String nickname, int idTeam);

    /**
     * Find teammates.
     *
     * @param teammates the teammates
     * @param idTeam    the id team
     */
    void findTeammates(List<String> teammates, int idTeam);

    /**
     * Send file int.
     *
     * @param file       the file
     * @param name       the name
     * @param idTeam     the id team
     * @param dataUpload the data upload
     * @return the int
     */
    int sendFile(byte[] file, String name, int idTeam, LocalDate dataUpload);

    /**
     * Gets documents.
     *
     * @param docs     the docs
     * @param files    the files
     * @param comments the comments
     * @param idTeam   the id team
     */
    void getDocuments(List<String> docs, List<byte[]> files, List<String> comments, int idTeam);
}
