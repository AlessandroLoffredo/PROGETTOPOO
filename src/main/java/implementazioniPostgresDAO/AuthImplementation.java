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
            String sql = "SELECT COUNT(*) AS conto FROM platformAdmin P WHERE P.username = ? AND P.passkey = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results = rs.getInt("conto");
            }
            if(results == 0){
                sql = "SELECT COUNT(*) AS conto FROM plUser P WHERE P.username = ? AND P.passkey = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    results = rs.getInt("conto");
                }
                if(results == 0)
                    return results;
                else{
                    sql = "SELECT COUNT(*) AS conto FROM Organizer O, Hackathon H WHERE O.username = ? AND " +
                            "O.idHack = H.idHack AND " +
                            "H.endDate >= CURRENT_DATE";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, username);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        results = rs.getInt("conto");
                    }
                    if(results == 1)
                        return 2;
                    else{
                        sql = "SELECT COUNT(*) AS conto FROM Judge J, Hackathon H WHERE J.username = ? AND " +
                                "J.idHack = H.idHack AND " +
                                "H.endDate >= CURRENT_DATE";
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1, username);
                        rs = stmt.executeQuery();
                        while (rs.next()) {
                            results = rs.getInt("conto");
                        }
                        if(results == 1)
                            return 3;
                        else{
                            sql = "SELECT COUNT(*) AS conto FROM Participant P, Hackathon H, Team T " +
                                    "WHERE P.username = ? AND " +
                                    "P.idTeam = T.idTeam AND " +
                                    "H.endDate >= CURRENT_DATE AND " +
                                    "T.idHack = H.idHack";
                            stmt = conn.prepareStatement(sql);
                            stmt.setString(1, username);
                            rs = stmt.executeQuery();
                            while (rs.next()) {
                                results = rs.getInt("conto");
                            }
                            if(results == 1)
                                return 4;
                            else
                                return 5;
                        }
                    }
                }
            }else
                return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }


}

