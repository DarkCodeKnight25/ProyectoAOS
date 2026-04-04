package pe.edu.idat.pedidos.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import pe.edu.idat.pedidos.model.Pedido;

@Component
public class PedidoConsumer {

    @JmsListener(destination = "cola.pedidos")
    public void recibirPedido(Pedido pedido) {
        System.out.println("📥 Pedido recibido desde JMS:");
        System.out.println("ID: " + pedido.getId());
        System.out.println("Cliente: " + pedido.getCliente());
        System.out.println("Estado: " + pedido.getEstado());
    }
}