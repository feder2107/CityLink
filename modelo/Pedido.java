package modelo;
import java.time.LocalDateTime;
public abstract class Pedido implements Facturable, Rastreable {
    private int codigo;
    private Cliente cliente;
    private String destino;
    private double pesoKg;
    private double distanciaKm;
    private LocalDateTime fechaHora;
    private String estado;
    private Vehiculo vehiculo;
    public Pedido() {
        this.fechaHora = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }
    public Pedido(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm) {

        setCodigo(codigo);
        setCliente(cliente);
        setDestino(destino);
        setPesoKg(pesoKg);
        setDistanciaKm(distanciaKm);

        this.fechaHora = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }
    public int getCodigo() {
        return codigo;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public String getDestino() {
        return destino;
    }
    public double getPesoKg() {
        return pesoKg;
    }
    public double getDistanciaKm() {
        return distanciaKm;
    }
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public String getEstado() {
        return estado;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public void setCodigo(int codigo) {

        if (codigo <= 0) {
            throw new IllegalArgumentException("El codigo del pedido es incorrecto");
        }

        this.codigo = codigo;
    }
    public void setCliente(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede estar vacio");
        }

        this.cliente = cliente;
    }
    public void setDestino(String destino) {

        if (destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("El destino es incorrecto");
        }

        this.destino = destino;
    }
    public void setPesoKg(double pesoKg) {

        if (pesoKg <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero");
        }

        this.pesoKg = pesoKg;
    }
    public void setDistanciaKm(double distanciaKm) {

        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor que cero");
        }

        this.distanciaKm = distanciaKm;
    }
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    public void cambiarEstado(String nuevoEstado) {

        if (estado.equals("PENDIENTE") && nuevoEstado.equals("EN_CAMINO")) {
            estado = nuevoEstado;
        }
        else if (estado.equals("EN_CAMINO") && nuevoEstado.equals("ENTREGADO")) {
            estado = nuevoEstado;
        }
        else if (estado.equals("PENDIENTE") && nuevoEstado.equals("CANCELADO")) {
            estado = nuevoEstado;
        }
        else {
            throw new IllegalArgumentException("No se puede cambiar a ese estado");
        }
    }
    public abstract double calcularCosto(Vehiculo vehiculo);
    @Override
    public String obtenerEstadoActual() {
        return estado;
    }
    @Override
    public double calcularImporte() {

        if (vehiculo == null) {
            throw new IllegalStateException("El pedido no tiene un vehiculo asignado");
        }

        return calcularCosto(vehiculo);
    }
    @Override
    public String toString() {
        return "Codigo: " + codigo
             + "\nCliente: " + cliente.getNombre()
             + "\nDestino: " + destino
             + "\nPeso: " + pesoKg + " kg"
             + "\nDistancia: " + distanciaKm + " km"
             + "\nFecha y hora: " + fechaHora
             + "\nEstado: " + estado;
    }
}
