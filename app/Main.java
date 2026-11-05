package app;
import java.util.Scanner;
import modelo.*;
public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        CityLink sistema = new CityLink();
        int opcion = 0;

        try {
            cargarDatos(sistema);
        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
        do {
            System.out.println("\n===== CITYLINK =====");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar repartidor");
            System.out.println("3. Registrar pedido");
            System.out.println("4. Asignar pedido");
            System.out.println("5. Cambiar estado de pedido");
            System.out.println("6. Buscar cliente");
            System.out.println("7. Listar pedidos");
            System.out.println("8. Consultar estados");
            System.out.println("9. Ver importes y comisiones");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opcion: ");
            try {
                opcion = Integer.parseInt(teclado.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.print("Codigo: ");
                        int codigoCliente = Integer.parseInt(teclado.nextLine());
                        System.out.print("Nombre: ");
                        String nombre = teclado.nextLine();
                        System.out.print("Telefono: ");
                        String telefono = teclado.nextLine();
                        System.out.print("Zona: ");
                        String zona = teclado.nextLine();
                        Cliente cliente = new Cliente(nombre, telefono, codigoCliente, zona);
                        sistema.registrarCliente(cliente);
                        System.out.println("Cliente registrado correctamente");
                        break;
                    case 2:
                        System.out.print("Legajo: ");
                        int legajo = Integer.parseInt(teclado.nextLine());
                        System.out.print("Nombre: ");
                        String nombreRepartidor = teclado.nextLine();
                        System.out.println("Tipo de vehiculo:");
                        System.out.println("1. Bicicleta");
                        System.out.println("2. Moto");
                        System.out.println("3. Camioneta");
                        int tipoVehiculo = Integer.parseInt(teclado.nextLine());
                        System.out.print("Patente: ");
                        String patente = teclado.nextLine();
                        Vehiculo vehiculo;
                        if (tipoVehiculo == 1) {
                            vehiculo = new Bicicleta(patente, 15, 10, 50);
                        }
                        else if (tipoVehiculo == 2) {
                            vehiculo = new Moto(patente, 40, 30, 120);
                        }
                        else if (tipoVehiculo == 3) {
                            vehiculo = new Camioneta(patente, 30, 500, 300);
                        }
                        else {
                            System.out.println("Tipo de vehiculo incorrecto");
                            break;
                        }
                        Repartidor repartidor = new Repartidor(legajo, nombreRepartidor, vehiculo);
                        sistema.registrarRepartidor(repartidor);
                        System.out.println("Repartidor registrado correctamente");
                        break;
                    case 3:
                        System.out.print("Codigo del pedido: ");
                        int codigoPedido = Integer.parseInt(teclado.nextLine());
                        System.out.print("Codigo del cliente: ");
                        int codigoC = Integer.parseInt(teclado.nextLine());
                        Cliente clientePedido = sistema.buscarCliente(codigoC);
                        System.out.print("Destino: ");
                        String destino = teclado.nextLine();
                        System.out.print("Peso en kg: ");
                        double peso = Double.parseDouble(teclado.nextLine());
                        System.out.print("Distancia en km: ");
                        double distancia = Double.parseDouble(teclado.nextLine());
                        System.out.println("Tipo de pedido:");
                        System.out.println("1. Normal");
                        System.out.println("2. Urgente");
                        System.out.println("3. Fragil");
                        int tipoPedido = Integer.parseInt(teclado.nextLine());
                        Pedido pedido;
                        if (tipoPedido == 1) {
                            pedido = new PedidoNormal(codigoPedido, clientePedido, destino, peso, distancia);
                        }
                        else if (tipoPedido == 2) {
                            pedido = new PedidoUrgente(codigoPedido, clientePedido, destino, peso, distancia);
                        }
                        else if (tipoPedido == 3) {
                            pedido = new PedidoFragil(codigoPedido, clientePedido, destino, peso, distancia);
                        }
                        else {
                            System.out.println("Tipo de pedido incorrecto");
                            break;
                        }
                        sistema.registrarPedido(pedido);
                        System.out.println("Pedido registrado correctamente");
                        break;
                    case 4:
                        System.out.print("Codigo del pedido: ");
                        int pedidoAsignar = Integer.parseInt(teclado.nextLine());
                        System.out.print("Legajo del repartidor: ");
                        int repartidorAsignar = Integer.parseInt(teclado.nextLine());
                        sistema.asignarPedido(pedidoAsignar, repartidorAsignar);
                        System.out.println("Pedido asignado correctamente");
                        break;
                    case 5:
                        System.out.print("Codigo del pedido: ");
                        int pedidoEstado = Integer.parseInt(teclado.nextLine());
                        System.out.println("1. EN_CAMINO");
                        System.out.println("2. ENTREGADO");
                        System.out.println("3. CANCELADO");
                        int nuevoEstado = Integer.parseInt(teclado.nextLine());
                        String estado;
                        if (nuevoEstado == 1) {
                            estado = "EN_CAMINO";
                        }
                        else if (nuevoEstado == 2) {
                            estado = "ENTREGADO";
                        }
                        else if (nuevoEstado == 3) {
                            estado = "CANCELADO";
                        }
                        else {
                            System.out.println("Estado incorrecto");
                            break;
                        }
                        sistema.cambiarEstadoPedido(pedidoEstado, estado);
                        System.out.println("Estado actualizado");
                        break;
                    case 6:
                        System.out.println("1. Buscar por codigo");
                        System.out.println("2. Buscar por nombre");
                        int tipoBusqueda = Integer.parseInt(teclado.nextLine());
                        Cliente encontrado;
                        if (tipoBusqueda == 1) {
                            System.out.print("Codigo: ");
                            int buscarCodigo = Integer.parseInt(teclado.nextLine());
                            encontrado = sistema.buscarCliente(buscarCodigo);
                        }
                        else if (tipoBusqueda == 2) {
                            System.out.print("Nombre: ");
                            String buscarNombre = teclado.nextLine();
                            encontrado = sistema.buscarCliente(buscarNombre);
                        }
                        else {
                            System.out.println("Opcion incorrecta");
                            break;
                        }
                        System.out.println(encontrado);
                        break;
                    case 7:
                        sistema.listarPedidos();
                        break;
                    case 8:
                        System.out.println("1. Estado de pedido");
                        System.out.println("2. Estado de repartidor");
                        int consulta = Integer.parseInt(teclado.nextLine());
                        if (consulta == 1) {
                            System.out.print("Codigo del pedido: ");
                            int codigoConsultar = Integer.parseInt(teclado.nextLine());
                            System.out.println(sistema.consultarEstadoPedido(codigoConsultar));
                        }
                        else if (consulta == 2) {
                            System.out.print("Legajo del repartidor: ");
                            int legajoConsultar = Integer.parseInt(teclado.nextLine());
                            System.out.println(sistema.consultarEstadoRepartidor(legajoConsultar));
                        }
                        else {
                            System.out.println("Opcion incorrecta");
                        }
                        break;
                    case 9:
                        System.out.println("Importe total: $" + sistema.calcularImporteTotal());
                        sistema.mostrarComisiones();
                        break;
                    case 10:
                        System.out.println("Gracias por utilizar CityLink");
                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("------------------------");
            }
        } while (opcion != 10);
        teclado.close();
    }
    public static void cargarDatos(CityLink sistema) throws Exception {
        Cliente c1 = new Cliente("Fede", "5553334392", 101, "Centro");
        Cliente c2 = new Cliente("Juan", "2664123456", 102, "Norte");
        Cliente c3 = new Cliente("Pedro", "2664555444", 103, "Sur");
        Cliente c4 = new Cliente("Ana", "2664333222", 104, "Este");
        Cliente c5 = new Cliente("Maria", "2664111222", 105, "Oeste");
        sistema.registrarCliente(c1);
        sistema.registrarCliente(c2);
        sistema.registrarCliente(c3);
        sistema.registrarCliente(c4);
        sistema.registrarCliente(c5);
        Vehiculo bici = new Bicicleta("BICI01", 15, 10, 50);
        Vehiculo moto = new Moto("ABC123", 40, 30, 120);
        Vehiculo camioneta = new Camioneta("DEF456", 30, 500, 300);
        sistema.registrarRepartidor(new Repartidor(1, "Carlos", bici));
        sistema.registrarRepartidor(new Repartidor(2, "Luis", moto));
        sistema.registrarRepartidor(new Repartidor(3, "Martin", camioneta));
        sistema.registrarPedido(new PedidoNormal(1, c1, "Centro", 3, 5));
        sistema.registrarPedido(new PedidoUrgente(2, c2, "Norte", 8, 10));
        sistema.registrarPedido(new PedidoFragil(3, c3, "Sur", 15, 12));
        sistema.registrarPedido(new PedidoNormal(4, c4, "Este", 4, 7));
        sistema.registrarPedido(new PedidoUrgente(5, c5, "Oeste", 12, 15));
        sistema.registrarPedido(new PedidoFragil(6, c1, "Centro", 2, 8));
        sistema.registrarPedido(new PedidoNormal(7, c2, "Norte", 20, 18));
        sistema.registrarPedido(new PedidoUrgente(8, c3, "Sur", 6, 9));
        sistema.asignarPedido(1, 1);
        sistema.asignarPedido(2, 2);
        sistema.asignarPedido(3, 3);
    }
}
