package implementazioniPostgresDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.*;
import Database.*;

public class AuthImplementation implements AuthInterface {
    public int logIn(String username, String password){
        int results = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT COUNT(*) AS conto FROM PlUser P WHERE P.username = ? AND P.passkey = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results = rs.getInt("conto");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
