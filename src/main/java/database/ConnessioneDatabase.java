package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestisce le richieste di connessione al database, che avvengono quando è necessaria una manipolazione dei dati
 */
public class ConnessioneDatabase {

    private static ConnessioneDatabase instance;
    /**
     * La connessione che verrà istanziata con il DB e richiesta quando necessaria
     */
    public Connection connection = null;
    private String nome = "postgres";
    private String password = "domenico05";
    private String url = "jdbc:postgresql://localhost:5432/HckMng";
    private String driver = "org.postgresql.Driver";

    private ConnessioneDatabase() throws SQLException {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, nome, password);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }


    /**
     * Restituisce l'unica connessione con il DB che la classe istanzia
     *
     * @return l'istanza della connessione con il DB
     * @throws SQLException un'eccezione SQL che il metodo può lanciare quando non riesce a connettersi al DB
     */
    public static ConnessioneDatabase getInstance() throws SQLException {
        if (instance == null || instance.connection.isClosed()) {
            instance = new ConnessioneDatabase();
        }
        return instance;
    }
}