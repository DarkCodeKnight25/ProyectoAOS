package pe.edu.idat.pedidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.idat.pedidos.PedidoNoValidoException;
import pe.edu.idat.pedidos.model.Pedido;
import pe.edu.idat.pedidos.repository.PedidoRepository;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // 🔹 Registrar pedido
    public Pedido registrarPedido(Pedido pedido) {
        // Validar cantidad mayor a 0
        if (pedido.getCantidad() <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }

        // Validar que el cliente esté presente
        if (pedido.getCliente() == null || pedido.getCliente().isEmpty()) {
            throw new RuntimeException("El cliente es obligatorio");
        }

        // Establecer el estado del pedido
        pedido.setEstado("PENDIENTE");
        return pedidoRepository.save(pedido);
    }

    // 🔹 Listar pedidos
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    // 🔹 Obtener pedido por ID (nuevo)
    public Pedido obtenerPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    // 🔹 Aprobar pedido
    public Pedido aprobarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        // Validar que el estado sea "PENDIENTE"
        if (!pedido.getEstado().equals("PENDIENTE")) {
            throw new RuntimeException("Solo pedidos pendientes pueden aprobarse");
        }

        pedido.setEstado("APROBADO");
        return pedidoRepository.save(pedido);
    }

    // 🔹 Rechazar pedido
    public Pedido rechazarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setEstado("RECHAZADO");
        return pedidoRepository.save(pedido);
    }

    // 🔹 Procesar pedido con validación de estado
    public void procesarPedido(Long pedidoId) throws PedidoNoValidoException {
        // Obtener el pedido
        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new PedidoNoValidoException("El pedido no existe"));

        // Validar que el estado del pedido sea "Creado"
        if (!"Creado".equals(pedido.getEstado())) {
            throw new PedidoNoValidoException("El estado del pedido no es válido para ser procesado.");
        }

        // Aquí continuarías con el procesamiento del pedido (ej. actualización del estado, etc.)
    }
}