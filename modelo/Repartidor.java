package modelo;
public class Repartidor implements Facturable, Rastreable {
    private int legajo;
    private String nombre;
    private Vehiculo vehiculo;
    private Pedido[] pedidos;
    private int cantidadPedidos;
    public Repartidor(int legajo, String nombre, Vehiculo vehiculo) {
        if (legajo <= 0) {
            throw new IllegalArgumentException("El legajo es incorrecto");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es incorrecto");
        }
        if (vehiculo == null) {
            throw new IllegalArgumentException("Debe tener un vehiculo");
        }
        this.legajo = legajo;
        this.nombre = nombre;
        this.vehiculo = vehiculo;
        this.pedidos = new Pedido[20];
        this.cantidadPedidos = 0;
    }
    public int getLegajo() {
        return legajo;
    }
    public String getNombre() {
        return nombre;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public Pedido[] getPedidos() {
        return pedidos;
    }
    public int getCantidadPedidos() {
        return cantidadPedidos;
    }
    public void asignarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no existe");
        }
        if (cantidadPedidos >= pedidos.length) {
            throw new IllegalArgumentException("El repartidor tiene demasiados pedidos");
        }
        if (!vehiculo.puedeTransportar(pedido)) {
            throw new IllegalArgumentException("El vehiculo no puede transportar este pedido");
        }
        if (!pedido.getEstado().equals("PENDIENTE")) {
            throw new IllegalArgumentException("El pedido no esta pendiente");
        }
        if (pedido.getVehiculo() != null) {
            throw new IllegalArgumentException("El pedido ya tiene un vehiculo asignado");
        }
        pedido.cambiarEstado("EN_CAMINO");
        pedido.setVehiculo(vehiculo);
        pedidos[cantidadPedidos] = pedido;
        cantidadPedidos++;
    }
    public void asignarPedido(Pedido pedido, Vehiculo vehiculo) {
        if (this.vehiculo != vehiculo) {
            throw new IllegalArgumentException("El vehiculo no pertenece al repartidor");
        }
        asignarPedido(pedido);
    }
    public void registrarEntrega(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no existe");
        }
        boolean encontrado = false;
        for (int i = 0; i < cantidadPedidos; i++) {
            if (pedidos[i] == pedido) {
                encontrado = true;
            }
        }
        if (!encontrado) {
            throw new IllegalArgumentException("El pedido no pertenece al repartidor");
        }
        pedido.cambiarEstado("ENTREGADO");
    }
    @Override
    public double calcularImporte() {
        double comision = 0;
        for (int i = 0; i < cantidadPedidos; i++) {
            if (pedidos[i].getEstado().equals("ENTREGADO")) {
                comision = comision + pedidos[i].calcularImporte() * 0.10;
            }
        }
        return comision;
    }
    @Override
    public String obtenerEstadoActual() {
        return "Repartidor activo. Pedidos asignados: " + cantidadPedidos;
    }
    @Override
    public String toString() {

        return "Legajo: " + legajo
             + "\nNombre: " + nombre
             + "\nPatente: " + vehiculo.getPatente()
             + "\nPedidos asignados: " + cantidadPedidos;
    }
}
