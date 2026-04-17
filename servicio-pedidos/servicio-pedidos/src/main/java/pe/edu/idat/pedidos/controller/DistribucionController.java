package pe.edu.idat.pedidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.edu.idat.pedidos.model.Distribucion;
import pe.edu.idat.pedidos.service.DistribucionService;

@RestController
@RequestMapping("/distribucion")
public class DistribucionController {

    @Autowired
    private DistribucionService distribucionService;

    @PostMapping
    public Distribucion asignar(@RequestBody Distribucion distribucion) {
        return distribucionService.asignarInstalacion(distribucion);
    }

    @PutMapping("/{id}/iniciar")
    public Distribucion iniciar(@PathVariable Long id) {
        return distribucionService.iniciarInstalacion(id);
    }

    @PutMapping("/{id}/finalizar")
    public Distribucion finalizar(@PathVariable Long id) {
        return distribucionService.finalizarInstalacion(id);
    }
}