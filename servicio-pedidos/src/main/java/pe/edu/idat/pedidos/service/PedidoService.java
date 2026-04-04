package pe.edu.idat.pedidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.idat.pedidos.model.Pedido;
import pe.edu.idat.pedidos.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // 🔥 REGISTRAR PEDIDO (LÓGICA DE NEGOCIO)
    public Pedido registrarPedido(Pedido pedido) {

        // Regla 1: cantidad válida
        if (pedido.getCantidad() <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }

        // Regla 2: cliente obligatorio
        if (pedido.getCliente() == null || pedido.getCliente().isEmpty()) {
            throw new RuntimeException("El cliente es obligatorio");
        }

        // Regla 3: estado inicial
        pedido.setEstado("PENDIENTE");

        return pedidoRepository.save(pedido);
    }

    // 🔥 APROBAR PEDIDO
    public Pedido aprobarPedido(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (!pedido.getEstado().equals("PENDIENTE")) {
            throw new RuntimeException("Solo pedidos pendientes pueden aprobarse");
        }

        pedido.setEstado("APROBADO");
        return pedidoRepository.save(pedido);
    }

    // 🔥 RECHAZAR PEDIDO
    public Pedido rechazarPedido(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setEstado("RECHAZADO");
        return pedidoRepository.save(pedido);
    }
}