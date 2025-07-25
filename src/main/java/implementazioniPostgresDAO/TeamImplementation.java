package implementazioniPostgresDAO;

import database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamImplementation {
    public int getTeam(String username, int idHack){
        int idTeam = 0;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT P.idTeam FROM Participant P, Team T " +
                         "WHERE P.idTeam = T.idTeam AND P.username = ? AND T.idHack = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setInt(2, idHack);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                idTeam = rs.getInt("idTeam");
            }
        } catch (SQLException e){
            e.printStackTrace();
            idTeam = -1;
        }
        return idTeam;
    }

    public String getNickname(int idTeam){
        String nick = null;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT nickname " +
                         "FROM Team " +
                         "WHERE idTeam = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idTeam);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                nick = rs.getString("nickname");
            }
        } catch (SQLException e){
            e.printStackTrace();
            nick = null;
        }
        return nick;
    }

    public int changeNickname(String nickname, int idTeam){
        int results;
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "UPDATE Team " +
                         "SET nickname = ? " +
                         "WHERE idTeam = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nickname);
            stmt.setInt(2, idTeam);
            results = stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            results = -1;
        }
        return results;
    }

    public void findTeammates(ArrayList<String> teammates, int idTeam){
        try(Connection conn = ConnessioneDatabase.getInstance().connection){
            String sql = "SELECT username " +
                         "FROM Participant " +
                         "WHERE idTeam = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idTeam);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                teammates.add(rs.getString("username"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
