package app;
import modelo.*;
import excepciones.*;
public class CityLink {
    private static final int MAX_CLIENTES = 50;
    private static final int MAX_PEDIDOS = 100;
    private static final int MAX_REPARTIDORES = 10;
    private Cliente[] clientes;
    private Pedido[] pedidos;
    private Repartidor[] repartidores;
    private int cantidadClientes;
    private int cantidadPedidos;
    private int cantidadRepartidores;
    public CityLink() {
        clientes = new Cliente[MAX_CLIENTES];
        pedidos = new Pedido[MAX_PEDIDOS];
        repartidores = new Repartidor[MAX_REPARTIDORES];
        cantidadClientes = 0;
        cantidadPedidos = 0;
        cantidadRepartidores = 0;
    }
    public void registrarCliente(Cliente cliente) throws AlmacenamientoLlenoException {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no existe");
        }
        if (cantidadClientes >= MAX_CLIENTES) {
            throw new AlmacenamientoLlenoException("No hay espacio para mas clientes");
        }
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getCodigo() == cliente.getCodigo()) {
                throw new IllegalArgumentException("Ya existe un cliente con ese codigo");
            }
        }
        clientes[cantidadClientes] = cliente;
        cantidadClientes++;
    }
    public Cliente buscarCliente(int codigo) throws ClienteNoEncontradoException {
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getCodigo() == codigo) {
                return clientes[i];
            }
        }
        throw new ClienteNoEncontradoException("No se encontro el cliente");
    }
    public Cliente buscarCliente(String nombre) throws ClienteNoEncontradoException {
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getNombre().equalsIgnoreCase(nombre)) {
                return clientes[i];
            }
        }
        throw new ClienteNoEncontradoException("No se encontro el cliente");
    }
    public void registrarRepartidor(Repartidor repartidor) throws AlmacenamientoLlenoException {
        if (repartidor == null) {
            throw new IllegalArgumentException("El repartidor no existe");
        }
        if (cantidadRepartidores >= MAX_REPARTIDORES) {
            throw new AlmacenamientoLlenoException("No hay espacio para mas repartidores");
        }
        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i].getLegajo() == repartidor.getLegajo()) {
                throw new IllegalArgumentException("Ya existe un repartidor con ese legajo");
            }
        }
        repartidores[cantidadRepartidores] = repartidor;
        cantidadRepartidores++;
    }
    public Repartidor buscarRepartidor(int legajo) {
        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i].getLegajo() == legajo) {
                return repartidores[i];
            }
        }
        throw new IllegalArgumentException("No se encontro el repartidor");
    }
    public void registrarPedido(Pedido pedido) throws AlmacenamientoLlenoException, PedidoInvalidoException {
        if (pedido == null) {
            throw new PedidoInvalidoException("El pedido no existe");
        }
        if (cantidadPedidos >= MAX_PEDIDOS) {
            throw new AlmacenamientoLlenoException("No hay espacio para mas pedidos");
        }
        for (int i = 0; i < cantidadPedidos; i++) {
            if (pedidos[i].getCodigo() == pedido.getCodigo()) {
                throw new PedidoInvalidoException("Ya existe un pedido con ese codigo");
            }
        }
        boolean clienteExiste = false;
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getCodigo() == pedido.getCliente().getCodigo()) {
                clienteExiste = true;
            }
        }
        if (!clienteExiste) {
            throw new PedidoInvalidoException("El cliente no esta registrado");
        }
        pedidos[cantidadPedidos] = pedido;
        cantidadPedidos++;
    }
    public Pedido buscarPedido(int codigo) {
        for (int i = 0; i < cantidadPedidos; i++) {
            if (pedidos[i].getCodigo() == codigo) {
                return pedidos[i];
            }
        }
        throw new IllegalArgumentException("No se encontro el pedido");
    }
    public void asignarPedido(int codigoPedido, int legajo) throws CapacidadExcedidaException {
        Pedido pedido = buscarPedido(codigoPedido);
        Repartidor repartidor = buscarRepartidor(legajo);
        if (!repartidor.getVehiculo().puedeTransportar(pedido)) {
            throw new CapacidadExcedidaException("El vehiculo no puede transportar este pedido");
        }
        repartidor.asignarPedido(pedido);
    }
    public void cambiarEstadoPedido(int codigo, String estado) {
        Pedido pedido = buscarPedido(codigo);
        pedido.cambiarEstado(estado);
    }
    public void listarPedidos() {
        if (cantidadPedidos == 0) {
            System.out.println("No hay pedidos registrados");
            return;
        }
        for (int i = 0; i < cantidadPedidos; i++) {
            System.out.println("------------------------");
            System.out.println(pedidos[i]);
            if (pedidos[i].getVehiculo() != null) {
                Vehiculo vehiculo = pedidos[i].getVehiculo();
                System.out.println("Costo: $" + pedidos[i].calcularCosto(vehiculo));
                System.out.println("Tiempo: " +
                    vehiculo.calcularTiempoEntrega(pedidos[i].getDistanciaKm()) + " minutos");
            }
            else {
                System.out.println("Todavia no tiene vehiculo asignado");
            }
        }
    }
    public String consultarEstadoPedido(int codigo) {
        Pedido pedido = buscarPedido(codigo);
        return pedido.obtenerEstadoActual();
    }
    public String consultarEstadoRepartidor(int legajo) {
        Repartidor repartidor = buscarRepartidor(legajo);
        return repartidor.obtenerEstadoActual();
    }
    public double calcularImporteTotal() {
        double total = 0;
        for (int i = 0; i < cantidadPedidos; i++) {
            if (pedidos[i].getVehiculo() != null) {
                total = total + pedidos[i].calcularImporte();
            }
        }
        return total;
    }
    public void mostrarComisiones() {
        for (int i = 0; i < cantidadRepartidores; i++) {
            System.out.println("Repartidor: " + repartidores[i].getNombre());
            System.out.println("Comision: $" + repartidores[i].calcularImporte());
            System.out.println("------------------------");
        }
    }
    public int getCantidadClientes() {
        return cantidadClientes;
    }
    public int getCantidadPedidos() {
        return cantidadPedidos;
    }
    public int getCantidadRepartidores() {
        return cantidadRepartidores;
    }
    public void eliminarCliente(int codigo) throws ClienteNoEncontradoException {
        int posicion = -1;
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getCodigo() == codigo) {
                posicion = i;
                break;
            }
        }
        if (posicion == -1) {
            throw new ClienteNoEncontradoException("No se encontro el cliente");
        }
        for (int i = posicion; i < cantidadClientes - 1; i++) {
            clientes[i] = clientes[i + 1];
        }
        clientes[cantidadClientes - 1] = null;
        cantidadClientes--;
    }
}
