package pe.edu.idat.pedidos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

// 🔥 IMPORTS
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import pe.edu.idat.pedidos.model.Pedido;
import pe.edu.idat.pedidos.model.Producto;

@SpringBootApplication
public class ServicioPedidosApplication implements CommandLineRunner {

    // 🔥 SCANNER GLOBAL (SOLUCIÓN)
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(ServicioPedidosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mostrarMenu();
    }

    private void mostrarMenu() {

        int opcion = -1;

        do {
            try {
                System.out.println("\n===== SISTEMA DE PEDIDOS =====");
                System.out.println("1. Crear pedido");
                System.out.println("2. Listar pedidos");
                System.out.println("3. Procesar pedido");
                System.out.println("4. Enviar pedido (JMS)");
                System.out.println("5. Ejecutar flujo ESB");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        crearPedido();
                        break;
                    case 2:
                        listarPedidos();
                        break;
                    case 3:
                        procesarPedido();
                        break;
                    case 4:
                        enviarPedido();
                        break;
                    case 5:
                        ejecutarESB();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }

            } catch (Exception e) {
                System.out.println("⚠️ Entrada inválida");
            }

        } while (opcion != 0);
    }

    // 🔥 CREAR PEDIDO (CON PRODUCTOS)
    private void crearPedido() {

        try {
            RestTemplate restTemplate = new RestTemplate();

            // 🔹 Obtener productos
            String urlProductos = "http://localhost:8080/productos";

            ResponseEntity<Producto[]> response =
                    restTemplate.getForEntity(urlProductos, Producto[].class);

            Producto[] productos = response.getBody();

            if (productos == null || productos.length == 0) {
                System.out.println("⚠️ No hay productos disponibles");
                return;
            }

            // 🔹 Mostrar productos
            System.out.println("\n=== PRODUCTOS DISPONIBLES ===");
            for (Producto p : productos) {
                System.out.println(p.getId() + ". " + p.getNombre() + " - S/" + p.getPrecio());
            }

            // 🔹 Selección
            System.out.print("Seleccione ID del producto: ");
            Long idSeleccionado = Long.parseLong(sc.nextLine());

            Producto productoSeleccionado = null;

            for (Producto p : productos) {
                if (p.getId().equals(idSeleccionado)) {
                    productoSeleccionado = p;
                    break;
                }
            }

            if (productoSeleccionado == null) {
                System.out.println("❌ Producto no válido");
                return;
            }

            // 🔹 Datos pedido
            System.out.print("Ingrese cliente: ");
            String cliente = sc.nextLine();

            System.out.print("Ingrese cantidad: ");
            int cantidad = Integer.parseInt(sc.nextLine());

            // 🔹 Crear objeto
            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setProducto(productoSeleccionado.getNombre());
            pedido.setCantidad(cantidad);

            // 🔥 IMPORTANTE: usar API correcta
            String urlPedido = "http://localhost:8080/api/pedidos";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Pedido> request = new HttpEntity<>(pedido, headers);

            ResponseEntity<Pedido> responsePedido =
                    restTemplate.postForEntity(urlPedido, request, Pedido.class);

            Pedido creado = responsePedido.getBody();

            System.out.println("✅ Pedido creado:");
            System.out.println("ID: " + creado.getId());
            System.out.println("Estado: " + creado.getEstado());

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // 🔥 LISTAR PEDIDOS
    private void listarPedidos() {

        try {
            RestTemplate restTemplate = new RestTemplate();

            String url = "http://localhost:8080/api/pedidos";

            ResponseEntity<Pedido[]> response =
                    restTemplate.getForEntity(url, Pedido[].class);

            Pedido[] pedidos = response.getBody();

            if (pedidos == null || pedidos.length == 0) {
                System.out.println("⚠️ No hay pedidos registrados");
                return;
            }

            System.out.println("\n===== LISTA DE PEDIDOS =====");

            for (Pedido p : pedidos) {
                System.out.println("----------------------------");
                System.out.println("ID: " + p.getId());
                System.out.println("Cliente: " + p.getCliente());
                System.out.println("Producto: " + p.getProducto());
                System.out.println("Cantidad: " + p.getCantidad());
                System.out.println("Estado: " + p.getEstado());
            }

        } catch (Exception e) {
            System.out.println("❌ Error al listar pedidos: " + e.getMessage());
        }
    }

    // 🔥 PROCESAR PEDIDO
    private void procesarPedido() {

        try {
            System.out.print("Ingrese ID del pedido a procesar: ");
            Long id = Long.parseLong(sc.nextLine());

            RestTemplate restTemplate = new RestTemplate();

            String url = "http://localhost:8080/api/pedidos/" + id + "/aprobar";

            restTemplate.put(url, null);

            System.out.println("✅ Pedido procesado (aprobado)");

        } catch (Exception e) {
            System.out.println("❌ Error al procesar pedido: " + e.getMessage());
        }
    }

    // 🔥 JMS (AÚN SIMULADO)
    private void enviarPedido() {
        System.out.println(">>> Enviar pedido a JMS (pendiente implementar)");
    }

    // 🔥 ESB COMPLETO
    private void ejecutarESB() {

        try {
            RestTemplate restTemplate = new RestTemplate();

            System.out.println("=== INICIANDO FLUJO ESB ===");

            Pedido pedido = new Pedido();
            pedido.setCliente("Cliente ESB");
            pedido.setProducto("Producto ESB");
            pedido.setCantidad(5);

            String url = "http://localhost:8080/api/pedidos";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Pedido> request = new HttpEntity<>(pedido, headers);

            ResponseEntity<Pedido> response =
                    restTemplate.postForEntity(url, request, Pedido.class);

            Pedido resultado = response.getBody();

            System.out.println("✅ Pedido procesado por ESB:");
            System.out.println("ID: " + resultado.getId());
            System.out.println("Estado: " + resultado.getEstado());

            System.out.println("=== FIN FLUJO ESB ===");

        } catch (Exception e) {
            System.out.println("❌ Error en ESB: " + e.getMessage());
        }
    }
}