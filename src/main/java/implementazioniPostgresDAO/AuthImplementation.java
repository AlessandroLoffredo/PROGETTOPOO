package implementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.*;
import database.*;


public class AuthImplementation implements AuthInterface {
    public int  logIn(String username, String password, String[] names){
        int results = 0;
        PreparedStatement stmt = null;
        String conto = "conto";
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT COUNT(*) AS conto FROM platformAdmin P WHERE P.username = ? AND P.passkey = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results = rs.getInt(conto);
            }
            return finalLog(results, conn, username, password, conto, names);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public int signUp(String username, String password, String fName, String lName){
        int results = 0;
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "INSERT INTO plUser VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
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
        }finally {
            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public int changePassword(String username, String password){
        int results = 0;
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "UPDATE plUser SET passkey = ? WHERE username = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, password);
            stmt.setString(2, username);
            results = stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            if(e.getMessage().contains("passcheck")){
                results = -4;
            }
        }finally {
            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public int changeUsername(String newUsername, String oldUsername) {
        int results = 0;
        PreparedStatement stmt = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "UPDATE plUser SET username = ? WHERE username = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newUsername);
            stmt.setString(2, oldUsername);
            results = stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            if(e.getMessage().contains("noadminusernamefunct()") || e.getMessage().contains("pkpluser")){
                results = -4;
            }
        }finally {
            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    private int logPlUser(Connection conn, String username, String password, String[] names){
        PreparedStatement stmt = null;
        int results = 0;
        try{
            String sql = "SELECT fName, lName FROM plUser P WHERE P.username = ? AND P.passkey = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                names[0] = rs.getString("fName");
                names[1] = rs.getString("lName");
                results = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt != null)
                    stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    private int logOrganizer(Connection conn, String username, String conto){
        PreparedStatement stmt = null;
        int results = 0;
        try{
            String sql = "SELECT COUNT(*) AS conto FROM Organizer O, Hackathon H WHERE O.username = ? AND " +
                    "O.idHack = H.idHack AND " +
                    "H.endDate >= CURRENT_DATE AND (H.startDate - 7) <= CURRENT_DATE";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                results = rs.getInt(conto);
            }
        } catch (Exception e) {
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

    private int logJudge(Connection conn, String username, String conto){
        PreparedStatement stmt = null;
        int results = 0;
        try{
            String sql = "SELECT COUNT(*) AS conto FROM Judge J, Hackathon H WHERE J.username = ? AND " +
                    "J.idHack = H.idHack AND " +
                    "H.endDate >= CURRENT_DATE AND (H.startDate - 7) <= CURRENT_DATE";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                results = rs.getInt(conto);
            }
        } catch (Exception e) {
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

    private int logParticipant(Connection conn, String username, String conto){
        PreparedStatement stmt = null;
        int results = 0;
        try{
            String sql = "SELECT COUNT(*) AS conto FROM Participant P, Hackathon H, Team T " +
                    "WHERE P.username = ? AND " +
                    "P.idTeam = T.idTeam AND " +
                    "H.endDate >= CURRENT_DATE AND " +
                    "T.idHack = H.idHack AND (H.startDate - 7) <= CURRENT_DATE";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                results = rs.getInt(conto);
            }
        } catch (Exception e) {
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

    private int finalLog(int results, Connection conn, String username, String password, String conto, String[] names){
        if(results != 0)
            return results;

        results = logPlUser(conn, username, password, names);
        if(results == 0)
            return results;

        results = logOrganizer(conn, username, conto);
        if(results == 1)
            return 2;

        results = logJudge(conn, username, conto);
        if(results == 1)
            return 3;

        results = logParticipant(conn, username, conto);
        if(results == 1)
            return 4;
        else
            return 5;
     }

}

