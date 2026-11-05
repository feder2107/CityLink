package modelo;
public class PedidoNormal extends Pedido {
    public PedidoNormal(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm) {
        super(codigo, cliente, destino, pesoKg, distanciaKm);
    }
    @Override
    public double calcularCosto(Vehiculo vehiculo) {
        double costo;
        costo = 1000 + getDistanciaKm() * vehiculo.getPrecioporkm();
        return costo;
    }
}
