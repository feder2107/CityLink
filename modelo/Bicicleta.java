package modelo;
public class Bicicleta extends Vehiculo {
public Bicicleta(String patente, double velocidadkmh, double capacidadkg, double precioporkm){
    super(patente, velocidadkmh, capacidadkg, precioporkm);
}
@Override 
public double calcularTiempoEntrega(double km){
    double tiempo;
    tiempo=km/getVelocidadkmh()*60;
    return tiempo;
}
@Override
public boolean puedeTransportar(Pedido pedido) {
    if (pedido instanceof PedidoFragil) {
        return false;
    }
    return super.puedeTransportar(pedido);
}
}