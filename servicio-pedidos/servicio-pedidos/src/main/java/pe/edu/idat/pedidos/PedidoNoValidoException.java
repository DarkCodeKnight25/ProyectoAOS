package pe.edu.idat.pedidos;



public class PedidoNoValidoException extends RuntimeException {
    public PedidoNoValidoException(String message) {
        super(message);
    }
}
