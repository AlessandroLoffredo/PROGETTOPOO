package implementazioniPostgresDAO;

import dao.*;
import database.ConnessioneDatabase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UsersImplementation implements UsersInterface {
    public void getOrganizers(ArrayList<String> organizers, LocalDate start, LocalDate end) throws SQLException{
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT P.username FROM PlUser P WHERE P.username NOT IN (" +
                    "SELECT O.username FROM Organizer O, Hackathon H " +
                    "WHERE O.idHack = H.idHack AND (((H.startDate - 7) <= ? AND H.endDate <= ?) OR ((H.startDate - 7) >= ? AND (H.startDate - 7) <= ?) OR ((H.startDate - 7) >= ? AND H.endDate <= ?))) AND " +
                    "P.Username NOT IN (" +
                    "SELECT J.username FROM Judge J, Hackathon H " +
                    "WHERE J.idHack = H.idHack AND (((H.startDate - 7) <= ? AND H.endDate <= ?) OR ((H.startDate - 7) >= ? AND (H.startDate - 7) <= ?) OR ((H.startDate - 7) >= ? AND H.endDate <= ?))) AND " +
                    "P.username NOT IN (" +
                    "SELECT Pa.username FROM Participant Pa, Hackathon H, Team T " +
                    "WHERE Pa.idTeam = T.idTeam AND T.idHack = H.idHack AND " +
                    "(((H.startDate - 7) <= ? AND H.endDate <= ?) OR ((H.startDate - 7) >= ? AND (H.startDate - 7) <= ?) OR ((H.startDate - 7) >= ? AND H.endDate <= ?)))";
            PreparedStatement stmt = conn.prepareStatement(sql);
            for(int i = 1; i <= 18; i++)
                if(i%2 == 0)
                    stmt.setDate(i, Date.valueOf(end));
                else
                    stmt.setDate(i, Date.valueOf(start.minusDays(7)));

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                organizers.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int newHack(String title, String venue, LocalDate startDate, LocalDate endDate, int maxReg, int maxPerTeam, String username){
        int results = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT doubleInsHackOrg(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, venue);
            stmt.setDate(3, Date.valueOf(startDate));
            stmt.setDate(4, Date.valueOf(endDate));
            stmt.setInt(5, maxReg);
            stmt.setInt(6, maxPerTeam);
            stmt.setString(7, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                results = rs.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
            if(e.getMessage().contains("checkdatefunct()")) {
                results = -4;
            }else if (e.getMessage().contains("duration")) {
                results = -5;
            }else if (e.getMessage().contains("checkstart")) {
                results = -3;
            }
        }
        return results;
    }
}
