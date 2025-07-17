package implementazioniPostgresDAO;


import database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParticipantImplementation {
    public void getParticipants(ArrayList<String> participants, int idHack, String username) {
        try (Connection conn = ConnessioneDatabase.getInstance().connection) {
            // Query corretta che:
            // 1. Trova tutti i partecipanti allo stesso hackathon
            // 2. Esclude quelli del team corrente dell'utente
            String sql = "SELECT P.username FROM Participant P " +
                    "JOIN Team T ON P.idTeam = T.idTeam " +
                    "WHERE T.idHack = ? " +
                    "AND P.idTeam <> (SELECT P2.idTeam FROM Participant P2 WHERE P2.username = ?) " +
                    "AND P.username <> ?"; // Esclude se stesso

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idHack);
            stmt.setString(2, username);
            stmt.setString(3, username);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                participants.add(rs.getString(1));
            }

            if (participants.isEmpty()) {
                System.out.println("Nessun partecipante trovato per l'hackathon " + idHack);
            }
        } catch (SQLException e) {
            System.err.println("Errore SQL durante il recupero dei partecipanti:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Errore generico durante il recupero dei partecipanti:");
            e.printStackTrace();
        }
    }
}
