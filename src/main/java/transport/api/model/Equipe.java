package transport.api.model;

import jakarta.persistence.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipe")
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipe")
    private int idEquipe;
    private String nom;

    public List<Equipe> getAllEquipe(Connection connection) throws SQLException, ClassNotFoundException {
        List<Equipe> equipes = new ArrayList<>();

        if (connection != null) {
            String selectQuery = "select * from equipe";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Equipe equipe = new Equipe();
                    equipe.setIdEquipe(resultSet.getInt("idEquipe"));
                    equipe.setNom(resultSet.getString("nom"));
                    equipes.add(equipe);
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return equipes;
    }
    public Equipe getEquipeById(int idEquipe , Connection connection) throws SQLException, ClassNotFoundException {
        Equipe equipe = new Equipe();

        if (connection != null) {
            String selectQuery = "select * from equipe where idEquipe=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1 , idEquipe);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    equipe.setIdEquipe(resultSet.getInt("idEquipe"));
                    equipe.setNom(resultSet.getString("nom"));
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return equipe;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
