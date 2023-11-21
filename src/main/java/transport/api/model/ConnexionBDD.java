package transport.api.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
    private static final String DATABASE_URL = "postgresql://postgres:bbcbCf1aeBg3CAaegaE5ga3C-A5bAbeg@viaduct.proxy.rlwy.net:58154/railway";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "bbcbCf1aeBg3CAaegaE5ga3C-A5bAbeg";

    // Méthode pour établir une connexion à la base de données PostgreSQL
    public Connection connect() throws ClassNotFoundException, SQLException {
        //Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }
}
