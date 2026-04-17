package pe.edu.idat.pedidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.edu.idat.pedidos.model.Pedido;

@Service
public class PedidoProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    private static final String COLA = "pedidos-queue";

    public void enviarPedido(Pedido pedido) throws JsonProcessingException {
        // Serializamos el objeto Pedido a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String pedidoJson = objectMapper.writeValueAsString(pedido);

        // Enviamos el JSON como un TextMessage
        jmsTemplate.convertAndSend(COLA, pedidoJson);
        System.out.println("📨 Pedido enviado a JMS: " + pedido.getId());
    }
}