package pe.edu.idat.pedidos.model;

import jakarta.persistence.*;

@Entity
public class Distribucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;
    private String tecnico;
    private String estado; // PROGRAMADO / EN_PROCESO / FINALIZADO

    public Distribucion() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }

    public String getTecnico() { return tecnico; }
    public void setTecnico(String tecnico) { this.tecnico = tecnico; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}