package implementazioniPostgresDAO;


import dao.ParticipantInterface;
import database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ParticipantImplementation implements ParticipantInterface {
    public void getParticipants(List<String> participants, int idHack, String username, int max) {
        PreparedStatement stmt = null;
        try (Connection conn = ConnessioneDatabase.getInstance().connection) {
            String sql = "SELECT P.username, T.idTeam " +
                    "FROM Participant P " +
                    "JOIN Team T ON P.idTeam = T.idTeam " +
                    "WHERE T.idHack = ? " +
                    "AND P.idTeam NOT IN (SELECT P2.idTeam FROM Participant P2 WHERE P2.username = ?) " +
                    "AND P.username <> ? " +
                    "AND T.idTeam IN (" +
                    "   SELECT T2.idTeam " +
                    "    FROM Team T2 " +
                    "    JOIN Participant P3 ON T2.idTeam = P3.idTeam " +
                    "    WHERE T2.idHack = ? " +
                    "    GROUP BY T2.idTeam " +
                    "    HAVING COUNT(P3.username) < ? " +
                    ")";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idHack);
            stmt.setString(2, username);
            stmt.setString(3, username);
            stmt.setInt(4, idHack);
            stmt.setInt(5, max);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                participants.add(rs.getString(1));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void findHack(String username, List<Object> data){
        PreparedStatement stmt = null;
        try (Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT * FROM HACKATHON H, TEAM T, PARTICIPANT P " +
                    "WHERE T.idHack = H.idHack AND T.idTeam = P.idTeam AND P.username = ? " +
                    "AND H.endDate >= CURRENT_DATE ORDER BY H.endDate ASC LIMIT 1";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                data.add(rs.getString("title"));
                data.add(rs.getString("venue"));
                data.add(rs.getDate("startDate"));
                data.add(rs.getDate("endDate"));
                data.add(rs.getInt("maxRegistration"));
                data.add(rs.getInt("maxTeamPar"));
                data.add(rs.getInt("regCounter"));
                data.add(rs.getString("problemDesc"));
                data.add(rs.getDate("startRegDate"));
                data.add(rs.getInt("idHack"));
                data.add(rs.getBytes("photo"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int sendRequests(String sender, int idHack, String receiver, String message){
        int results = 0;
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "INSERT INTO REQUESTS(sender, receiver, idteams, idteamr, rmessage) " +
                    "SELECT ?, ?, T1.idTeam, T2.idTeam, ? " +
                    "FROM Team T1, Team T2, Participant P1, Participant P2 " +
                    "WHERE T1.idTeam <> T2.idTeam AND " +
                    "T1.idTeam = P1.idTeam AND P1.username = ? AND " +
                    "T2.idTeam = P2.idTeam AND P2.username = ? AND " +
                    "T1.idHack = ? AND T2.idHack = ? AND " +
                    "NOT EXISTS (SELECT 1 FROM REQUESTS R WHERE " +
                    "(R.sender = ? AND R.receiver = ? AND R.idTeamS = T2.idTeam AND R.idTeamR = T1.idTeam) OR (" +
                    "R.sender = ? AND R.receiver = ? AND R.idTeamS = T1.idTeam AND R.idTeamR = T2.idTeam))";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sender);
            stmt.setString(2, receiver);
            stmt.setString(3, message);
            stmt.setString(4, sender);
            stmt.setString(5, receiver);
            stmt.setInt(6, idHack);
            stmt.setInt(7, idHack);
            stmt.setString(8, receiver);
            stmt.setString(9, sender);
            stmt.setString(10, sender);
            stmt.setString(11, receiver);
            results = stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            results = -2;
        } catch (Exception e) {
            e.printStackTrace();
            results = -3;
        }finally {
            try{
                if(stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;

    }

    public void getRequests(List<String> requests, String username){
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT R.sender, R.rmessage FROM Requests R WHERE " +
                    "R.receiver = ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                requests.add(rs.getString(1) + ": " + rs.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int acceptRequest(String sender, String receiver, int idHack){
        int results = 0;
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT acceptRequest(?, ? ,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sender);
            stmt.setString(2, receiver);
            stmt.setInt(3, idHack);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                results = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            results = 0;
        }finally {
            try{
                if(stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public int declineRequest(String sender, String receiver){
        int results;
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "DELETE FROM Requests WHERE sender = ? AND receiver = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sender);
            stmt.setString(2, receiver);
            results = stmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            results = 0;
        }finally {
            try{
                if(stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
