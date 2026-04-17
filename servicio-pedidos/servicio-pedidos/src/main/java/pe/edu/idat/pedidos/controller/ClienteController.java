package pe.edu.idat.pedidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.edu.idat.pedidos.model.Cliente;
import pe.edu.idat.pedidos.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // 🔹 Registrar cliente
    @PostMapping
    public Cliente registrar(@RequestBody Cliente cliente) {
        return clienteService.registrarCliente(cliente);
    }

    // 🔹 Validar cliente
    @GetMapping("/validar/{dni}")
    public Cliente validar(@PathVariable String dni) {
        return clienteService.validarCliente(dni);
    }
}