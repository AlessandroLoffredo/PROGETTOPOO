package implementazioniPostgresDAO;

import dao.OrgInterface;
import database.ConnessioneDatabase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrgImplementation implements OrgInterface {
    @Override
    public int setupDate(LocalDate date, String username) {
        int resultsUp = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "UPDATE Hackathon " +
                    "SET startRegDate = ? " +
                    "WHERE idHack = (SELECT O.idHack FROM Organizer O WHERE O.username = ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(date));
            stmt.setString(2, username);
            resultsUp = stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            if(e.getMessage().contains("registration")){
                resultsUp = -1;
            }
        }
        return resultsUp;
    }

    public boolean verifyDate(String username){
        int results = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT COUNT(H.startRegDate) as conto FROM Hackathon H, Organizer O WHERE O.idHack = H.idHack AND " +
                    "O.username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                results = rs.getInt("conto");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(results == 1)
            return true;
        else
            return false;
    }

    public boolean isStarted(String username){
        int results = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT COUNT(H.startRegDate) as conto FROM Hackathon H, Organizer O WHERE O.idHack = H.idHack AND " +
                         "O.username = ? AND H.startRegDate IS NOT NULL";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                results = rs.getInt("conto");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(results == 1)
            return true;
        else
            return false;
    }

    public void getDates(String username, LocalDate dates[]){
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT H.startDate, H.endDate, H.startRegDate FROM Hackathon H, Organizer O " +
                    "WHERE H.idHack = O.idHack AND O.username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                dates[0] = rs.getDate("startDate").toLocalDate();
                dates[1] = rs.getDate("endDate").toLocalDate();
                dates[2] = rs.getDate("startRegDate").toLocalDate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int inviteUser(String sender, String receiver){
        int inserted = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "INSERT INTO Invites (organizer, idhackorg, inviteduser) " +
                    "SELECT ?, H.idHack, ? FROM Hackathon H, Organizer O " +
                    "WHERE H.idHack = O.idHack AND O.username = ? AND ? NOT IN ( " +
                    "SELECT I.invitedUser FROM Invites I WHERE I.idHackOrg = H.idHack)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, sender);
            stmt.setString(2, receiver);
            stmt.setString(3, sender);
            stmt.setString(4, receiver);
            inserted = stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            if(e.getMessage().contains("userisntbusyfunct()")){
                inserted = -1;
            }else if(e.getMessage().contains("differentusers")){
                inserted = -2;
            }
        }
        return inserted;
    }

    public void findHack(String username, ArrayList<Object> datas){
        try (Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT * " +
                         "FROM Hackathon H, Organizer O " +
                         "WHERE H.idHack = O.idHack AND O.username = ? AND H.startDate-30 >= CURRENT_DATE AND H.endDate > CURRENT_DATE";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                datas.add(rs.getString("title"));
                datas.add(rs.getString("venue"));
                datas.add(rs.getInt("regCounter"));
                datas.add(rs.getDate("startDate"));
                datas.add(rs.getDate("endDate"));
                datas.add(rs.getInt("maxRegistration"));
                datas.add(rs.getInt("maxTeamPar"));
                datas.add(rs.getString("problemDesc"));
                datas.add(rs.getDate("startRegDate"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
