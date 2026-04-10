package pe.edu.idat.pedidos.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import pe.edu.idat.pedidos.model.Pedido;

@Component
public class PedidoListener {

    // 🔹 Escucha la cola "pedidos-queue"
    @JmsListener(destination = "pedidos-queue")
    public void recibirPedido(Pedido pedido) {
        System.out.println("\n📩 Pedido recibido desde JMS:");
        System.out.println("ID: " + pedido.getId());
        System.out.println("Cliente: " + pedido.getCliente());
        System.out.println("Producto: " + pedido.getProducto());
        System.out.println("Cantidad: " + pedido.getCantidad());
        System.out.println("Estado: " + pedido.getEstado());
    }
}