package dam.ad.practica17.ejer1.javabeans;

import java.io.Serializable;

public class Competidor implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private String vehiculo;
    private double tiempo;

    public Competidor(int id, String nombre, String vehiculo, double tiempo) {
        this.id = id;
        this.nombre = nombre;
        this.vehiculo = vehiculo;
        this.tiempo = tiempo;
    }

    // Getters y setters para los atributos

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Vehículo: " + vehiculo + ", Tiempo: " + tiempo + " minutos";
    }
}
