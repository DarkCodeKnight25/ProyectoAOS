package pe.edu.idat.pedidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import pe.edu.idat.pedidos.model.Pedido;

@Service
public class PedidoProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    private static final String COLA = "cola.pedidos";

    public void enviarPedido(Pedido pedido) {
        jmsTemplate.convertAndSend(COLA, pedido);
        System.out.println("📨 Pedido enviado a JMS: " + pedido.getId());
    }
}