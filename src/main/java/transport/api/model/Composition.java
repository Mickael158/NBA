package transport.api.model;

import jakarta.persistence.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "composition")
public class Composition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComposition")
    private int idComposition;

    private int idJoueur;
    private int idEquipe;
    private String numero;
    private int idPost;

    public List<Composition> getAllComposition( Connection connection) throws SQLException, ClassNotFoundException {
        List<Composition> compositions = new ArrayList<>();

        if (connection != null) {
            String selectQuery = "select * from composition";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Composition composition = new Composition();
                    composition.setIdComposition(resultSet.getInt("idComposition"));
                    composition.setIdJoueur(resultSet.getInt("idJoueur"));
                    composition.setIdEquipe(resultSet.getInt("idEquipe"));
                    composition.setNumero(resultSet.getString("numero"));
                    composition.setIdPost(resultSet.getInt("idPost"));
                    compositions.add(composition);
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return compositions;
    }
    public List<Composition> getAllCompositionByEquipe(int idEquipe , Connection connection) throws SQLException, ClassNotFoundException {
        List<Composition> compositions = new ArrayList<>();

        if (connection != null) {
            String selectQuery = "select * from composition WHERE idEquipe=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1, idEquipe);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Composition composition = new Composition();
                    composition.setIdComposition(resultSet.getInt("idComposition"));
                    composition.setIdJoueur(resultSet.getInt("idJoueur"));
                    composition.setIdEquipe(resultSet.getInt("idEquipe"));
                    composition.setNumero(resultSet.getString("numero"));
                    composition.setIdPost(resultSet.getInt("idPost"));
                    compositions.add(composition);
                }
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return compositions;
    }


    public int getIdComposition() {
        return idComposition;
    }

    public void setIdComposition(int idComposition) {
        this.idComposition = idComposition;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }
}
