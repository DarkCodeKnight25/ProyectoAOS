package pe.edu.idat.pedidos.soap;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EstadoPedidoResponse", namespace = "http://idat.edu.pe/pedidos")
public class EstadoPedidoResponse {

    private String estado;

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}