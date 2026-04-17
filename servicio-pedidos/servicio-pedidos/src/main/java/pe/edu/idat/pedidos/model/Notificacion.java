package pe.edu.idat.pedidos.model;

import jakarta.persistence.*;

@Entity
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;
    private String destinatario;
    private String estado; // ENVIADO / ERROR

    public Notificacion() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}