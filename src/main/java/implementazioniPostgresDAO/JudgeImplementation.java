package implementazioniPostgresDAO;

import dao.JudgeInterface;
import database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JudgeImplementation implements JudgeInterface {
    public int updateDescription(String description, String username){
        int results = 0;
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "UPDATE Hackathon SET problemDesc = ? " +
                    "WHERE idHack = (SELECT J.idHack FROM Judge J, Hackathon H WHERE J.username = ? AND H.idHack = J.idHack AND " +
                    "H.endDate >= CURRENT_DATE ORDER BY H.endDate ASC LIMIT 1) AND " +
                    "startDate >= CURRENT_DATE";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, description);
            stmt.setString(2, username);
            results = stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
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

    public void getTeams(List<String> teams, int idHack){
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT T.nickname FROM TEAM T WHERE T.idHack = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idHack);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                teams.add(rs.getString(1));
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

    public void getDocuments(String team, int idHack, List<byte[]> files, List<String> names, List<String> comments) {
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT D.dname, d.description, d.dcomment FROM Doc D WHERE " +
                    "D.idTeam = (SELECT T.idTeam FROM Team T WHERE T.nickname = ? AND T.idHack = ?) ORDER BY D.loaddate DESC";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, team);
            stmt.setInt(2, idHack);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                names.add(rs.getString(1));
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

    public int setComment(String comment, String username, int idHack, String doc, String team){
        int results = 0;
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "UPDATE Doc SET dcomment = ?, jusername = ?, idhack = ? WHERE dname = ? AND idTeam = (" +
                    "SELECT T.idTeam FROM TEAM T WHERE T.nickname = ? AND T.idHack = ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, comment);
            stmt.setString(2, username);
            stmt.setInt(3, idHack);
            stmt.setString(4, doc);
            stmt.setString(5, team);
            stmt.setInt(6, idHack);
            results = stmt.executeUpdate();
        }catch (Exception e){
            results = -1;
            e.printStackTrace();
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

    public int assignMark(String team, int mark, String username, int idHack){
        int results = 0;
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection) {
            String sql = "INSERT INTO Marks (jusername, idhack, idteam, grade) " +
                    "SELECT ?, ?, T.idTeam, ? FROM Team T " +
                    "WHERE T.idHack = ? AND T.nickname = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setInt(2, idHack);
            stmt.setInt(3, mark);
            stmt.setInt(4, idHack);
            stmt.setString(5, team);
            results = stmt.executeUpdate();
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
        return results;
    }

    public int getMark(String team, String username, int idHack){
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT M.grade FROM Marks M WHERE M.jusername = ? AND M.idHack = ? AND M.idTeam = (" +
                    "SELECT T.idTeam FROM Team T WHERE T.idHack = ? AND T.nickname = ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setInt(2, idHack);
            stmt.setInt(3, idHack);
            stmt.setString(4, team);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
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
        return -1;
    }
}
