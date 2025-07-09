package implementazioniPostgresDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.*;
import database.*;

public class AuthImplementation implements AuthInterface {
    public int logIn(String username, String password, String[] names){
        int results = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT  COUNT(*) AS conto FROM platformAdmin P WHERE P.username = ? AND P.passkey = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results = rs.getInt("conto");
            }
            if(results == 0){
                sql = "SELECT fName, lName FROM plUser P WHERE P.username = ? AND P.passkey = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    names[0] = rs.getString("fName");
                    names[1] = rs.getString("lName");
                    results = 1;
                }
                if(results == 0)
                    return results;
                else{
                    sql = "SELECT COUNT(*) AS conto FROM Organizer O, Hackathon H WHERE O.username = ? AND " +
                            "O.idHack = H.idHack AND " +
                            "H.endDate >= CURRENT_DATE AND (H.startDate - 30) <= CURRENT_DATE";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, username);
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        results = rs.getInt("conto");
                    }
                    if(results == 1)
                        return 2;
                    else{
                        sql = "SELECT COUNT(*) AS conto FROM Judge J, Hackathon H WHERE J.username = ? AND " +
                                "J.idHack = H.idHack AND " +
                                "H.endDate >= CURRENT_DATE AND (H.startDate - 7) <= CURRENT_DATE";
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1, username);
                        rs = stmt.executeQuery();
                        if (rs.next()) {
                            results = rs.getInt("conto");
                        }
                        if(results == 1)
                            return 3;
                        else{
                            sql = "SELECT COUNT(*) AS conto FROM Participant P, Hackathon H, Team T " +
                                    "WHERE P.username = ? AND " +
                                    "P.idTeam = T.idTeam AND " +
                                    "H.endDate >= CURRENT_DATE AND " +
                                    "T.idHack = H.idHack AND (H.startDate - 7) <= CURRENT_DATE";
                            stmt = conn.prepareStatement(sql);
                            stmt.setString(1, username);
                            rs = stmt.executeQuery();
                            if (rs.next()) {
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

    public int signUp(String username, String password, String fName, String lName){
        int results = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "INSERT INTO plUser VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, username);
            stmt.setString(4, password);
            results = stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            if(e.getMessage().contains("passcheck")){
                results = -3;
            } else if (e.getMessage().contains("noadminusernamefunct()") || e.getMessage().contains("pkpluser")) {
                results = -4;
            } else{
                results = 0;
            }
        }
        return results;
    }

    public int changePassword(String username, String password){
        int results = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "UPDATE plUser SET passkey = ? WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, password);
            stmt.setString(2, username);
            results = stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            if(e.getMessage().contains("passcheck")){
                results = -4;
            }
        }
        return results;
    }

    public int changeUsername(String newUsername, String oldUsername) {
        int results = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "UPDATE plUser SET username = ? WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newUsername);
            stmt.setString(2, oldUsername);
            results = stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            if(e.getMessage().contains("noadminusernamefunct()") || e.getMessage().contains("pkpluser")){
                results = -4;
            }
        }
        return results;
    }
}

