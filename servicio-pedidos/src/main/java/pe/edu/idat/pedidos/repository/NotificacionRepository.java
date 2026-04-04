package pe.edu.idat.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.idat.pedidos.model.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}