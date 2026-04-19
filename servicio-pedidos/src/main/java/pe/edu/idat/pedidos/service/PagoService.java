package pe.edu.idat.pedidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.idat.pedidos.model.Pago;
import pe.edu.idat.pedidos.repository.PagoRepository;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    // 🔥 PROCESAR PAGO (REGLA DE NEGOCIO)
    public Pago procesarPago(Pago pago) {

        // Regla 1: monto válido
        if (pago.getMonto() <= 0) {
            throw new RuntimeException("Monto inválido");
        }

        // 🔥 Regla 2: lógica de aprobación
        if (pago.getMonto() <= 1000) {
            pago.setEstado("APROBADO");
        } else {
            pago.setEstado("RECHAZADO");
        }

        return pagoRepository.save(pago);
    }
}