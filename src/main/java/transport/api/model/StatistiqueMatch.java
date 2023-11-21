package transport.api.model;

import jakarta.persistence.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "statistiquematch")
public class StatistiqueMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStatistiqueMatch")
    private int idStatistiqueMatch;
    private int idMatch;
    private int idJoueur;
    private String idAction;
    private Timestamp dates;


    public double getCountAction(String action , int idjoueur , Connection connection) throws SQLException, ClassNotFoundException {
        double count = 0;

        if (connection != null) {
            String selectQuery = "select Count(*) as Lancerfranc from statistiquematch where idAction=? AND idJoueur=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setString(1 , action);
                preparedStatement.setInt(2 , idjoueur);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    count = resultSet.getDouble("Lancerfranc");
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return count;
    }
    public double getCountMatchByJoueur(int idjoueur) throws SQLException, ClassNotFoundException {
        ConnexionBDD base = new ConnexionBDD();
        Connection connection = base.connect();
        double count = 0;

        if (connection != null) {
            String selectQuery = "SELECT  SUM(EXTRACT(EPOCH FROM (sm2.dates - sm1.dates)) / 3600) AS difference_hours FROM statistiquematch sm1 JOIN statistiquematch sm2 ON sm1.idMatch = sm2.idMatch WHERE sm1.idAction = '1' AND sm2.idAction = '2' AND sm1.idJoueur=?; ";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1 , idjoueur);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    count = resultSet.getDouble("difference_hours");
                }
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return count;
    }
    public double getMoyenAction(String action , int idJoueur) throws SQLException, ClassNotFoundException {
        ConnexionBDD base = new ConnexionBDD();
        Connection connection = base.connect();
        double count = 0;

        if (connection != null) {
            String selectQuery = "SELECT AVG(nb_actions) AS moyenne_actions FROM ( SELECT idMatch, COUNT(*) AS nb_actions FROM statistiquematch WHERE idAction = ? AND idJoueur=? GROUP BY idMatch ) AS sous_requete;";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setString(1 , action);
                preparedStatement.setInt(2, idJoueur);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    count = resultSet.getDouble("Lancerfranc");
                }
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return count;
    }
    public double getPourcentageAction(String actionNum , String actionDem , int idJoueur ,Connection connection) throws SQLException, ClassNotFoundException {
        double count = 0;
        double num = getCountAction(actionNum , idJoueur , connection) * 100;
        double dem = getCountAction(actionNum , idJoueur , connection) + getCountAction(actionDem , idJoueur , connection);
        count = num / dem;
        return count;
    }


    public int getIdStatistiqueMatch() {
        return idStatistiqueMatch;
    }

    public void setIdStatistiqueMatch(int idStatistiqueMatch) {
        this.idStatistiqueMatch = idStatistiqueMatch;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getIdAction() {
        return idAction;
    }

    public void setIdAction(String idAction) {
        this.idAction = idAction;
    }

    public Timestamp getDates() {
        return dates;
    }

    public void setDates(Timestamp dates) {
        this.dates = dates;
    }
}
