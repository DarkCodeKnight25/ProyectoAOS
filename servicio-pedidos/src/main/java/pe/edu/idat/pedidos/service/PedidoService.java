package pe.edu.idat.pedidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.idat.pedidos.model.Pedido;
import pe.edu.idat.pedidos.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // 🔥 LÓGICA DE NEGOCIO REAL
    public Pedido registrarPedido(Pedido pedido) {

        // Regla 1: cantidad no puede ser 0 o negativa
        if (pedido.getCantidad() <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }

        // Regla 2: cliente obligatorio
        if (pedido.getCliente() == null || pedido.getCliente().isEmpty()) {
            throw new RuntimeException("El cliente es obligatorio");
        }

        // Regla 3: estado inicial automático
        pedido.setEstado("PENDIENTE");

        return pedidoRepository.save(pedido);
    }
}