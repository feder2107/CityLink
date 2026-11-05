package modelo;
public 	abstract class Vehiculo {
	private String patente;
	private double velocidadkmh;
	private double capacidadkg;
	private double precioporkm;

	public Vehiculo(){

	}
	public Vehiculo(String patente, double velocidadkmh, double capacidadkg, double precioporkm){
		setPatente(patente);
		setVelocidadkmh(velocidadkmh);
		setCapacidadkg(capacidadkg);
		setPrecioporkm(precioporkm);
	}
	public String getPatente(){
		return patente;
	}
	public double getVelocidadkmh(){
		return velocidadkmh;
	}
	public double getCapacidadkg(){
		return capacidadkg;
	}
	public double getPrecioporkm(){
		return precioporkm;
	}
	public void setPatente(String patente){
		if(patente==null || patente.trim().isEmpty()){
			throw new IllegalArgumentException("la patente ingresada es incorrecta o no ingreso ninguna");
		}
		this.patente=patente;
	}
	public void setVelocidadkmh(double velocidadkmh){
		if(velocidadkmh<=0){
			throw new IllegalArgumentException("la velocidad ingresada es incorrecta o no ingreso ninguna");
		}
		this.velocidadkmh=velocidadkmh;
	}
	public void setCapacidadkg(double capacidadkg){
		if(capacidadkg<=0){
			throw new IllegalArgumentException("la capacidad ingresada es incorrecta o no ingreso ninguna");
		}
		this.capacidadkg=capacidadkg;
	}
	public void setPrecioporkm(double precioporkm){
		if(precioporkm<=0){
			throw new IllegalArgumentException("el precio ingresado es incorrecto o no ingreso ninguno");
		}
		this.precioporkm=precioporkm;
	}
	public abstract double calcularTiempoEntrega(double km);

public boolean puedeTransportar(Pedido pedido) {
    if (pedido.getPesoKg() <= getCapacidadkg()) {
        return true;
    }
    else {
        return false;
    }
}
}