package implementazioniPostgresDAO;

import dao.AdminInterface;
import database.ConnessioneDatabase;

import java.sql.*;
import java.time.LocalDate;

public class AdminImplementation implements AdminInterface {
    public int newHack(String title, String venue, LocalDate startDate, LocalDate endDate, int maxReg, int maxPerTeam, String username, byte[] photoData){
        int results = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT doubleInsHackOrg(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, venue);
            stmt.setDate(3, Date.valueOf(startDate));
            stmt.setDate(4, Date.valueOf(endDate));
            stmt.setInt(5, maxReg);
            stmt.setInt(6, maxPerTeam);
            stmt.setBytes(7, photoData);
            stmt.setString(8, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                results = rs.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
            if (e.getMessage().contains("duration")) {
                results = -4;
            }else if (e.getMessage().contains("checkdate")) {
                results = -3;
            }else{
                results = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            results = -1;
        }
        return results;
    }
}
