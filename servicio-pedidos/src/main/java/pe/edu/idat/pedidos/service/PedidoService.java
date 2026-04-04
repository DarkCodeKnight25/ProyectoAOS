package pe.edu.idat.pedidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.idat.pedidos.model.Pedido;
import pe.edu.idat.pedidos.repository.PedidoRepository;

import java.util.List; // 🔥 IMPORTANTE

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // 🔥 REGISTRAR PEDIDO
    public Pedido registrarPedido(Pedido pedido) {

        if (pedido.getCantidad() <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }

        if (pedido.getCliente() == null || pedido.getCliente().isEmpty()) {
            throw new RuntimeException("El cliente es obligatorio");
        }

        pedido.setEstado("PENDIENTE");

        return pedidoRepository.save(pedido);
    }

    // 🔥 LISTAR PEDIDOS (NUEVO)
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
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