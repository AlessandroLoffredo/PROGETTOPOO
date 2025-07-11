package implementazioniPostgresDAO;

import database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HackathonImplementation {
    //TODO DOBBIAMO VALUTARE SE NE SONO TROPPI QUANDO FERMARCI NEL CARICARLI
    public void getHackList (ArrayList<ArrayList<Object>> data){
        try (Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT * " +
                    "FROM Hackathon H " +
                    "ORDER BY H.startDate ASC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                ArrayList<Object> hack = new ArrayList<>();
                hack.add(rs.getString("title"));
                hack.add(rs.getString("venue"));
                hack.add(rs.getDate("startDate"));
                hack.add(rs.getDate("endDate"));
                hack.add(rs.getInt("maxRegistration"));
                hack.add(rs.getInt("maxTeamPar"));
                hack.add(rs.getInt("regCounter"));
                hack.add(rs.getString("problemDesc"));
                hack.add(rs.getDate("startRegDate"));
                hack.add(rs.getInt("idHack"));
                data.add(hack);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getJudgesList (ArrayList<String> judges, int idHack){
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT J.username " +
                         "FROM Judge J " +
                         "WHERE J.idHack = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idHack);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                judges.add(rs.getString("username"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String getOrganizer (int idHack){
        String result = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT O.username " +
                         "FROM Organizer O " +
                         "WHERE O.idHack = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idHack);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                result = rs.getString("username");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
