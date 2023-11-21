package transport.api.model;

import javax.swing.text.Style;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Affichage affichage = new Affichage();
        ConnexionBDD connexionBDD = new ConnexionBDD();
        Connection connection = connexionBDD.connect();
        List<Affichage> affichages = affichage.getAllAffichage(connection);
        for (Affichage affichage1 : affichages){
            System.out.println("Nom :"+affichage1.getNomJoueur()+" Prenom :"+affichage1.getPrenoomJoueur()+" Equipe :"+affichage1.getNomEquipe()+" Lancer Franc :"+affichage1.getLancerFranc()+" 2 Points :"+affichage1.getPanierDeuxPoints()+" 3 Points :"+affichage1.getPanierTroisPoints());
        }
    }
}
