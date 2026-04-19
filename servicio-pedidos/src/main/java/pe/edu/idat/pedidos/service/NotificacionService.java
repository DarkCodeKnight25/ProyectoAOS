package pe.edu.idat.pedidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.idat.pedidos.model.Notificacion;
import pe.edu.idat.pedidos.repository.NotificacionRepository;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    // 🔥 ENVIAR NOTIFICACIÓN
    public Notificacion enviar(Notificacion notificacion) {

        if (notificacion.getDestinatario() == null || notificacion.getDestinatario().isEmpty()) {
            throw new RuntimeException("Destinatario requerido");
        }

        // Simulación de envío exitoso
        notificacion.setEstado("ENVIADO");

        return notificacionRepository.save(notificacion);
    }
}