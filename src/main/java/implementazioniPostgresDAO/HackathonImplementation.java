package implementazioniPostgresDAO;

import dao.HackathonInterface;
import database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Hackathon implementation.
 */
public class HackathonImplementation implements HackathonInterface {
    public void getHackList (List<List<Object>> data){
        PreparedStatement stmt = null;
        try (Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT * " +
                         "FROM Hackathon H " +
                         "ORDER BY H.startDate DESC " +
                         "LIMIT 15";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                ArrayList<Object> hack = new ArrayList<>();
                hack.add(rs.getString("title"));
                hack.add(rs.getString("venue"));
                hack.add(rs.getDate("startDate"));
                hack.add(rs.getDate("endDate"));
                hack.add(rs.getInt("maxRegistration"));
                hack.add(rs.getInt("maxTeamPar"));
                hack.add(rs.getInt("regCounter"));
                hack.add(rs.getString("problemDesc"));
                hack.add(rs.getDate("startRegDate"));
                hack.add(rs.getInt("idHack"));
                byte[] imageBytes = rs.getBytes("photo");
                hack.add(imageBytes);
                data.add(hack);
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

    public void getJudgesList (List<String> judges, int idHack){
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT J.username " +
                         "FROM Judge J " +
                         "WHERE J.idHack = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idHack);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                judges.add(rs.getString("username"));
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

    public String getOrganizer (int idHack){
        String result = null;
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT O.username " +
                         "FROM Organizer O " +
                         "WHERE O.idHack = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idHack);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                result = rs.getString("username");
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
        return result;
    }

    public void getRanking (List<String> ranking, int idHack){
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT * " +
                         "FROM Ranking " +
                         "WHERE idHack = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idHack);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                ranking.add(rs.getString("nickname"));
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

    public void removeRequests(String idHacks){
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT removeExpRequests(?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idHacks);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                rs.getInt(1);
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
