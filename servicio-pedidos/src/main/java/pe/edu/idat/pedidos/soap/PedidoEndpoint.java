package pe.edu.idat.pedidos.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PedidoEndpoint {

    private static final String NAMESPACE_URI = "http://idat.edu.pe/pedidos";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "EstadoPedidoRequest")
    @ResponsePayload
    public EstadoPedidoResponse getEstadoPedido(@RequestPayload EstadoPedidoRequest request) {
        // Valor dummy para pruebas
        EstadoPedidoResponse response = new EstadoPedidoResponse();
        response.setEstado("PEDIDO EN PROCESO"); 
        return response;
    }
}