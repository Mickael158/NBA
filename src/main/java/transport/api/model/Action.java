package transport.api.model;

import jakarta.persistence.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "action")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAction")
    private String idAction;
    private String nom;

    public String getIdAction() {
        return idAction;
    }
    public List<Action> getAllAction() throws SQLException, ClassNotFoundException {
        ConnexionBDD base = new ConnexionBDD();
        Connection connection = base.connect();
        List<Action> actions = new ArrayList<>();

        if (connection != null) {
            String selectQuery = "select * from action";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Action action = new Action();
                    action.setIdAction(resultSet.getString("idAction"));
                    action.setNom(resultSet.getString("nom"));
                    actions.add(action);
                }
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exécution de la requête SELECT : " + e.getMessage());
            }
        }
        return actions;
    }
    public void setIdAction(String idAction) {
        this.idAction = idAction;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
