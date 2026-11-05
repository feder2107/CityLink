package modelo;
public class PedidoFragil extends Pedido {
    public PedidoFragil(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm) {
        super(codigo, cliente, destino, pesoKg, distanciaKm);
    }
    @Override
    public double calcularCosto(Vehiculo vehiculo) {
        double costoBase;
        double costoFinal;
        costoBase = 1000 + getDistanciaKm() * vehiculo.getPrecioporkm();
        costoFinal = costoBase + 800;
        return costoFinal;
    }
}
