package pe.edu.idat.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.idat.pedidos.model.Distribucion;

public interface DistribucionRepository extends JpaRepository<Distribucion, Long> {
}