package implementazioniPostgresDAO;

import dao.JudgeInterface;
import database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
