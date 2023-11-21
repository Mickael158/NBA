package transport.api.model;

import jakarta.persistence.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "joueur")
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJoueur")
    private int idJoueur;

    private String nom;

    private String prenom;

    public List<Joueur> getAllJoueur() throws SQLException, ClassNotFoundException {
        ConnexionBDD base = new ConnexionBDD();
        Connection connection = base.connect();
        List<Joueur>joueurs = new ArrayList<>();

        if (connection != null) {
            String selectQuery = "select * from joueur";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                   Joueur joueur = new Joueur();
                   joueur.setIdJoueur(resultSet.getInt("idJoueur"));
                   joueur.setNom(resultSet.getString("nom"));
                   joueur.setPrenom(resultSet.getString("prenom"));
                   joueurs.add(joueur);
                }
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return joueurs;
    }
    public Joueur getJoueurByJoueur(int idJoueur , Connection connection) throws SQLException, ClassNotFoundException {
        Joueur joueur = new Joueur();

        if (connection != null) {
            String selectQuery = "select * from joueur where idjoueur = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1 , idJoueur);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    joueur.setIdJoueur(resultSet.getInt("idJoueur"));
                    joueur.setNom(resultSet.getString("nom"));
                    joueur.setPrenom(resultSet.getString("prenom"));
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return joueur;
    }
    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
