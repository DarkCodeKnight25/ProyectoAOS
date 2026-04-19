package pe.edu.idat.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.idat.pedidos.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByDni(String dni);
}