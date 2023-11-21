package transport.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transport.api.model.Affichage;
import transport.api.model.Vehicule;
import transport.api.service.VehiculeService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/serice")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    @Autowired
    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @PostMapping
    public ResponseEntity<Vehicule> createVehicule(@RequestBody Vehicule vehicule) {
        Vehicule createdVehicule = vehiculeService.createVehicule(vehicule);
        return new ResponseEntity<>(createdVehicule, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable int id) {
        return vehiculeService.getVehiculeById(id)
                .map(vehicule -> new ResponseEntity<>(vehicule, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Vehicule>> getAllVehicules() {
        List<Vehicule> vehicules = vehiculeService.getAllVehicules();
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> updateVehicule(@PathVariable int id, @RequestBody Vehicule vehicule) {
        vehicule.setId(id);
        Vehicule updatedVehicule = vehiculeService.updateVehicule(vehicule);
        return new ResponseEntity<>(updatedVehicule, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculeById(@PathVariable int id) {
        vehiculeService.deleteVehiculeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



