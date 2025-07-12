package implementazioniPostgresDAO;

import database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Teamimplementation {
    /*public int subscribeTeam (String username, int idHack){
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "INSERT INTO Team(nickname, idHack) " +
                         "VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setInt(2, idHack);
            return stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }*/
}
