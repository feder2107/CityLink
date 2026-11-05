package modelo;
public class Camioneta extends Vehiculo {
    public Camioneta(String patente, double velocidadkmh, double capacidadkg, double precioporkm){
        super(patente, velocidadkmh, capacidadkg, precioporkm);
    }
    @Override
    public double calcularTiempoEntrega(double km){
        double tiempo;
        tiempo=km/getVelocidadkmh()*60+10;
        return tiempo;
    }
}
