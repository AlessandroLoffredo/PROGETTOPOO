package implementazioniPostgresDAO;
//TODO RICORDARE DI CAMBIARE IL NUMERO DI GIORNI IN GETINVITES
import dao.*;
import database.ConnessioneDatabase;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UsersImplementation implements UsersInterface {
    public void getFreeUser(ArrayList<String> freeUsers, LocalDate start, LocalDate end){
        try (Connection conn = ConnessioneDatabase.getInstance().connection) {
            String sql = "SELECT P.username FROM PlUser P WHERE P.username NOT IN (" +
                            "SELECT O.username FROM Organizer O JOIN Hackathon H ON O.idHack = H.idHack " +
                            "WHERE NOT (H.endDate < ? OR (H.startDate - 15) > ?)) AND " +
                            "P.username NOT IN (" +
                            "SELECT J.username FROM Judge J JOIN Hackathon H ON J.idHack = H.idHack " +
                            "WHERE NOT (H.endDate < ? OR (H.startDate - 7) > ?)) AND " +
                            "P.username NOT IN (" +
                            "SELECT Pa.username FROM Participant Pa JOIN Team T ON Pa.idTeam = T.idTeam JOIN Hackathon H ON T.idHack = H.idHack " +
                            "WHERE NOT (H.endDate < ? OR (H.startDate - 7) > ?));";
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 1; i <= 6; i++) {
                if (i % 2 == 1)
                    stmt.setDate(i, Date.valueOf(start.minusDays(7)));
                else
                    stmt.setDate(i, Date.valueOf(end));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                freeUsers.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void getInvites(ArrayList<String> requests, String receiver){
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT I.organizer, H.title FROM Invites I, Hackathon H WHERE I.idHackOrg = H.idHack AND " +
                         "H.startRegDate >= CURRENT_DATE AND " +
                         "CURRENT_DATE >= (H.startDate - 30)  AND " +
                         "I.invitedUser = ?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, receiver);
            ResultSet rs = stmt.executeQuery();
            int i = 0;
            while (rs.next()){
                requests.add("Sei stato invitato come giudice per '" + rs.getString("title") + "' da: " + rs.getString("organizer"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int acceptInvite(String sender, String receiver){
        int results;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "INSERT INTO judge(username, idHack) " +
                         "SELECT ?, O.idHack FROM Organizer O " +
                         "JOIN Hackathon H ON O.idHack = H.idHack " +
                         "WHERE O.username = ? AND H.endDate >= CURRENT_DATE";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, receiver);
            stmt.setString(2, sender);
            results = stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            results = 0;
        }
        return results;
    }

    public int declineInvite(String sender, String receiver){
        int results = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "DELETE FROM Invites I WHERE I.organizer = ? AND I.invitedUser = ? AND I.idHackOrg = (" +
                         "SELECT H.idHack FROM Hackathon H WHERE H.startDate >= CURRENT_DATE AND H.idHack = " +
                         "(SELECT O.idHack FROM Organizer O WHERE O.username = ?))";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, sender);
            stmt.setString(2, receiver);
            stmt.setString(3, sender);
            results = stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            results = 0;
        }
        return results;
    }

    public void lastHack (ArrayList<Object> data){
        try (Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT * " +
                         "FROM Hackathon H " +
                         "WHERE H.startDate > CURRENT_DATE ORDER BY H.startDate ASC LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(sql);
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
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public int veryfingIsFree(String username, LocalDate start, LocalDate end){
        try (Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT COUNT(P.username) AS conto FROM PlUser P WHERE P.username NOT IN (" +
                            "SELECT O.username FROM Organizer O JOIN Hackathon H ON O.idHack = H.idHack " +
                            "WHERE NOT (H.endDate < ? OR (H.startDate - 15) > ?)) AND " +
                            "P.username NOT IN (" +
                            "SELECT J.username FROM Judge J JOIN Hackathon H ON J.idHack = H.idHack " +
                            "WHERE NOT (H.endDate < ? OR (H.startDate - 7) > ?)) AND " +
                            "P.username NOT IN (" +
                            "SELECT Pa.username FROM Participant Pa JOIN Team T ON Pa.idTeam = T.idTeam JOIN Hackathon H ON T.idHack = H.idHack " +
                            "WHERE NOT (H.endDate < ? OR (H.startDate - 7) > ?)) AND " +
                         "P.username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 1; i <= 6; i++) {
                if (i % 2 == 1)
                    stmt.setDate(i, Date.valueOf(start.minusDays(7)));
                else
                    stmt.setDate(i, Date.valueOf(end));
            }
            stmt.setString(7, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("conto"); // Restituisce 1 se libero, 0 se occupato
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -3;
    }

}
