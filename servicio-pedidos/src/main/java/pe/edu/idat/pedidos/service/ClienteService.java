package pe.edu.idat.pedidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.idat.pedidos.model.Cliente;
import pe.edu.idat.pedidos.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // 🔥 REGISTRAR CLIENTE CON VALIDACIONES
    public Cliente registrarCliente(Cliente cliente) {

        // Regla 1: DNI obligatorio
        if (cliente.getDni() == null || cliente.getDni().isEmpty()) {
            throw new RuntimeException("El DNI es obligatorio");
        }

        // Regla 2: DNI único
        if (clienteRepository.findByDni(cliente.getDni()) != null) {
            throw new RuntimeException("El cliente ya existe");
        }

        // Regla 3: estado inicial
        cliente.setEstado("ACTIVO");

        return clienteRepository.save(cliente);
    }

    // 🔥 VALIDAR CLIENTE (para flujo de negocio)
    public Cliente validarCliente(String dni) {

        Cliente cliente = clienteRepository.findByDni(dni);

        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado");
        }

        if (!cliente.getEstado().equals("ACTIVO")) {
            throw new RuntimeException("Cliente bloqueado");
        }

        return cliente;
    }
}