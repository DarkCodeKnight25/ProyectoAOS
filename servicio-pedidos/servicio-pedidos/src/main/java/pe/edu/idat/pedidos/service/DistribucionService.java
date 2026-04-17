package pe.edu.idat.pedidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.idat.pedidos.model.Distribucion;
import pe.edu.idat.pedidos.repository.DistribucionRepository;

@Service
public class DistribucionService {

    @Autowired
    private DistribucionRepository distribucionRepository;

    // 🔥 ASIGNAR INSTALACIÓN
    public Distribucion asignarInstalacion(Distribucion distribucion) {

        if (distribucion.getPedidoId() == null) {
            throw new RuntimeException("Pedido requerido");
        }

        // Regla: asignación automática de técnico
        distribucion.setTecnico("TECNICO-01");

        // Estado inicial
        distribucion.setEstado("PROGRAMADO");

        return distribucionRepository.save(distribucion);
    }

    // 🔥 INICIAR INSTALACIÓN
    public Distribucion iniciarInstalacion(Long id) {

        Distribucion d = distribucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));

        d.setEstado("EN_PROCESO");
        return distribucionRepository.save(d);
    }

    // 🔥 FINALIZAR INSTALACIÓN
    public Distribucion finalizarInstalacion(Long id) {

        Distribucion d = distribucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));

        d.setEstado("FINALIZADO");
        return distribucionRepository.save(d);
    }
}