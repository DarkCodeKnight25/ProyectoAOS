package pe.edu.idat.pedidos.soap;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EstadoPedidoRequest", namespace = "http://idat.edu.pe/pedidos")
public class EstadoPedidoRequest {

    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}