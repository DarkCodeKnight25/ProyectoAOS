package pe.edu.idat.pedidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.edu.idat.pedidos.model.Pedido;
import pe.edu.idat.pedidos.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // 🔹 Crear pedido
    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.registrarPedido(pedido);
    }

    // 🔹 Aprobar pedido
    @PutMapping("/{id}/aprobar")
    public Pedido aprobar(@PathVariable Long id) {
        return pedidoService.aprobarPedido(id);
    }

    // 🔹 Rechazar pedido
    @PutMapping("/{id}/rechazar")
    public Pedido rechazar(@PathVariable Long id) {
        return pedidoService.rechazarPedido(id);
    }
}