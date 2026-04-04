package pe.edu.idat.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.idat.pedidos.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}