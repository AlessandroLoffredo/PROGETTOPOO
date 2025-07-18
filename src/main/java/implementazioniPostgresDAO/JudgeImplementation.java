package implementazioniPostgresDAO;

import dao.JudgeInterface;
import database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JudgeImplementation implements JudgeInterface {
    public int updateDescription(String description, String username){
        int results = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "UPDATE Hackathon SET problemDesc = ? " +
                    "WHERE idHack = (SELECT J.idHack FROM Judge J, Hackathon H WHERE J.username = ? AND H.idHack = J.idHack AND " +
                    "H.endDate >= CURRENT_DATE ORDER BY H.endDate ASC LIMIT 1) AND " +
                    "startDate >= CURRENT_DATE";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, description);
            stmt.setString(2, username);
            results = stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return results;
    }

    public void getTeams(ArrayList<String> teams, int idHack){
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT T.nickname FROM TEAM T WHERE T.idHack = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idHack);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                teams.add(rs.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
