package pe.edu.idat.pedidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.edu.idat.pedidos.model.Notificacion;
import pe.edu.idat.pedidos.service.NotificacionService;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping
    public Notificacion enviar(@RequestBody Notificacion notificacion) {
        return notificacionService.enviar(notificacion);
    }
}