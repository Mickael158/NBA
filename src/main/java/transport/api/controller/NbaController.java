package transport.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transport.api.model.Affichage;
import transport.api.model.ConnexionBDD;
import transport.api.model.Equipe;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/nba")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class NbaController {



    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    @GetMapping
    public ResponseEntity<List<Affichage>> getAllAffichage() throws SQLException, ClassNotFoundException {
        Affichage affichage = new Affichage();
        ConnexionBDD connexionBDD = new ConnexionBDD();
        Connection connection = connexionBDD.connect();
        List<Affichage> affichages = affichage.getAllAffichage(connection);
        connection.close();
        return new ResponseEntity<>(affichages, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    @GetMapping("ListeEquipe")
    public ResponseEntity<List<Equipe>> getAllEquipe() throws SQLException, ClassNotFoundException {
        Equipe equipe = new Equipe();
        ConnexionBDD connexionBDD = new ConnexionBDD();
        Connection connection = connexionBDD.connect();
        List<Equipe> equipes = equipe.getAllEquipe(connection);
        connection.close();
        return new ResponseEntity<>(equipes, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    @GetMapping("StateByEquipe/{idEquipe}")
    public ResponseEntity<List<Affichage>> getAllAffichageByEquipe(@PathVariable int idEquipe) throws SQLException, ClassNotFoundException {
        Affichage affichage = new Affichage();
        ConnexionBDD connexionBDD = new ConnexionBDD();
        Connection connection = connexionBDD.connect();
        List<Affichage> affichages = affichage.getAllAffichageByEquipe(idEquipe , connection);
        connection.close();
        return new ResponseEntity<>(affichages, HttpStatus.OK);
    }
}
