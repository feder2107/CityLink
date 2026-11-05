package modelo;
public class Moto extends Vehiculo {
    public Moto(String patente, double velocidadkmh, double capacidadkg, double precioporkm){
        super(patente, velocidadkmh, capacidadkg, precioporkm);
    }
    @Override
    public double calcularTiempoEntrega(double km){
        double tiempo;
        tiempo=km/getVelocidadkmh()*60+5;
        return tiempo;
    }
}
    