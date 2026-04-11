package pe.edu.idat.pedidos.service;

import org.springframework.stereotype.Component;

import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import pe.edu.idat.pedidos.model.Pedido;

@Component
public class PedidoListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            Pedido pedido = (Pedido) ((ObjectMessage) message).getObject();
            System.out.println("\n📩 Pedido recibido desde JMS:");
            System.out.println("ID: " + pedido.getId());
            System.out.println("Cliente: " + pedido.getCliente());
            System.out.println("Producto: " + pedido.getProducto());
            System.out.println("Cantidad: " + pedido.getCantidad());
            System.out.println("Estado: " + pedido.getEstado());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al recibir el pedido: " + e.getMessage());
        }
    }
}