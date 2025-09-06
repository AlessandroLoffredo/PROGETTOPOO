package implementazioniPostgresDAO;

import dao.TeamInterface;
import database.ConnessioneDatabase;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe che implementa i metodi nell'interfaccia TeamInterface
 */
public class TeamImplementation implements TeamInterface {
    public int getTeam(String username, int idHack){
        PreparedStatement stmt = null;
        int idTeam = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT P.idTeam FROM Participant P, Team T " +
                         "WHERE P.idTeam = T.idTeam AND P.username = ? AND T.idHack = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setInt(2, idHack);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                idTeam = rs.getInt("idTeam");
            }
        } catch (SQLException e){
            e.printStackTrace();
            idTeam = -1;
        }finally {
            try{
                if(stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idTeam;
    }

    public String getNickname(int idTeam){
        String nick = null;
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT nickname " +
                         "FROM Team " +
                         "WHERE idTeam = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idTeam);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                nick = rs.getString("nickname");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nick;
    }

    public int changeNickname(String nickname, int idTeam){
        int results;
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "UPDATE Team " +
                         "SET nickname = ? " +
                         "WHERE idTeam = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nickname);
            stmt.setInt(2, idTeam);
            results = stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            results = -1;
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

    public void findTeammates(List<String> teammates, int idTeam){
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT username " +
                         "FROM Participant " +
                         "WHERE idTeam = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idTeam);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                teammates.add(rs.getString("username"));
            }
        } catch (SQLException e){
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

    public int sendFile(byte[] file, String name, int idTeam, LocalDate dateUpload){
        int results = 0;
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "INSERT INTO doc (dname, loaddate, description, idTeam) " +
                    "VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDate(2, Date.valueOf(dateUpload));
            stmt.setBytes(3, file);
            stmt.setInt(4, idTeam);
            results = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            if(e.getMessage().contains("pkdoc")){
                results = -1;
            }
        } catch (Exception e){
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

    public void getDocuments(List<String> docs, List<byte[]> files, List<String> comments, int idTeam){
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT D.dname, D.description, D.dComment FROM Doc D WHERE " +
                    "D.idTeam  = ? ORDER BY D.loadDate DESC";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idTeam);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                docs.add(rs.getString(1));
                files.add(rs.getBytes(2));
                comments.add(rs.getString(3));
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
}
