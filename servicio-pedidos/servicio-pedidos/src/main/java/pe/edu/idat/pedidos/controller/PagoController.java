package pe.edu.idat.pedidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.edu.idat.pedidos.model.Pago;
import pe.edu.idat.pedidos.service.PagoService;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public Pago procesar(@RequestBody Pago pago) {
        return pagoService.procesarPago(pago);
    }
}