package pe.edu.idat.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.idat.pedidos.model.Producto;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findAllByOrderByIdAsc(); // 🔥 ordena por ID
}