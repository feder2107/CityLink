package modelo;
public class Cliente {
    private String nombre;
    private String numerotelefono;
    private int codigo;
    private String zona;

public Cliente(){

}
public Cliente(String nombre, String numerotelefono, int codigo, String zona) {
    setNombre(nombre);
    setNumerotelefono(numerotelefono);
    setCodigo(codigo);
    setZona(zona);
}
public String getNombre() {
    return nombre;
}
public String getNumerotelefono(){
    return numerotelefono;
}
public String getZona(){
    return zona;
}
public int getCodigo(){
    return codigo;
}
public void setNombre(String nombre){
    if(nombre==null || nombre.trim().isEmpty()){
        throw new IllegalArgumentException("el nombre que introdujo es incorrecto");
    }   
    this.nombre=nombre;
}
public void setNumerotelefono(String numerotelefono){
    if(numerotelefono==null || numerotelefono.trim().isEmpty()){
        throw new IllegalArgumentException("el numero de telefono ingresado es incorrecto o no ingreso ninguno");
    }
    for(int i=0; i<numerotelefono.length(); i++){
        char caracter = numerotelefono.charAt(i);
    if( caracter < '0' || caracter > '9'){
        throw new IllegalArgumentException("el numero de telefono ingresado es incorrecto o no ingreso ninguno");
    }
}
    this.numerotelefono=numerotelefono;
}
public void setCodigo(int codigo){
    if (codigo<=0){
        throw new IllegalArgumentException("el codigo que introdujo es incorrecto");
    }
    this.codigo=codigo;
}
public void setZona(String zona){
    if(zona==null || zona.trim().isEmpty()){
        throw new IllegalArgumentException("la zona ingresada es incorrecta o no ingreso ninguna");
    }
    this.zona=zona;
}
@Override 
public String toString(){
    return "nombre: " + nombre + "\n telefono: " + numerotelefono + "\n codigo: " + codigo + "\n  zona: " + zona;
}
}