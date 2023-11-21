package transport.api.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Affichage {

    private String nomJoueur;
    private String prenoomJoueur;
    private String nomEquipe;
    private double LancerFranc;
    private double PanierDeuxPoints;
    private double PanierTroisPoints;

    public List<Affichage> getAllAffichage(Connection connection) throws SQLException, ClassNotFoundException {
        List<Affichage> affichages = new ArrayList<>();
        Joueur joueur = new Joueur();
        Equipe equipe = new Equipe();
        Composition composition = new Composition();
        StatistiqueMatch statistiqueMatch = new StatistiqueMatch();
        List<Composition> compositions = composition.getAllComposition(connection);
        for (Composition composition1 : compositions){
            Affichage affichage = new Affichage();
            affichage.setPrenoomJoueur(joueur.getJoueurByJoueur(composition1.getIdJoueur() , connection).getPrenom());
            affichage.setNomJoueur(joueur.getJoueurByJoueur(composition1.getIdJoueur() , connection).getNom());
            affichage.setNomEquipe(equipe.getEquipeById(composition1.getIdEquipe() , connection).getNom());
            affichage.setLancerFranc(statistiqueMatch.getCountAction("1PT" , composition1.getIdJoueur() , connection));
            affichage.setPanierDeuxPoints(statistiqueMatch.getCountAction("2PT" , composition1.getIdJoueur() , connection));
            affichage.setPanierTroisPoints(statistiqueMatch.getCountAction("3PT" , composition1.getIdJoueur() , connection));
            affichages.add(affichage);
        }
        return affichages;
    }

    public List<Affichage> getAllAffichageByEquipe(int  idEquipe,Connection connection) throws SQLException, ClassNotFoundException {
        List<Affichage> affichages = new ArrayList<>();
        Joueur joueur = new Joueur();
        Equipe equipe = new Equipe();
        Composition composition = new Composition();
        StatistiqueMatch statistiqueMatch = new StatistiqueMatch();
        List<Composition> compositions = composition.getAllCompositionByEquipe(idEquipe , connection);
        for (Composition composition1 : compositions){
            Affichage affichage = new Affichage();
            affichage.setPrenoomJoueur(joueur.getJoueurByJoueur(composition1.getIdJoueur() , connection).getPrenom());
            affichage.setNomJoueur(joueur.getJoueurByJoueur(composition1.getIdJoueur() , connection).getNom());
            affichage.setNomEquipe(equipe.getEquipeById(composition1.getIdEquipe() , connection).getNom());
            affichage.setLancerFranc(statistiqueMatch.getCountAction("1PT" , composition1.getIdJoueur() , connection));
            affichage.setPanierDeuxPoints(statistiqueMatch.getCountAction("2PT" , composition1.getIdJoueur() , connection));
            affichage.setPanierTroisPoints(statistiqueMatch.getCountAction("3PT" , composition1.getIdJoueur() , connection));
            affichages.add(affichage);
        }
        return affichages;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public String getPrenoomJoueur() {
        return prenoomJoueur;
    }

    public void setPrenoomJoueur(String prenoomJoueur) {
        this.prenoomJoueur = prenoomJoueur;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public double getLancerFranc() {
        return LancerFranc;
    }

    public void setLancerFranc(double lancerFranc) {
        LancerFranc = lancerFranc;
    }

    public double getPanierDeuxPoints() {
        return PanierDeuxPoints;
    }

    public void setPanierDeuxPoints(double panierDeuxPoints) {
        PanierDeuxPoints = panierDeuxPoints;
    }

    public double getPanierTroisPoints() {
        return PanierTroisPoints;
    }

    public void setPanierTroisPoints(double panierTroisPoints) {
        PanierTroisPoints = panierTroisPoints;
    }
}
