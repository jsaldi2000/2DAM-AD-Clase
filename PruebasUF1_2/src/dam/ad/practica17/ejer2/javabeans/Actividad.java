package dam.ad.practica17.ejer2.javabeans;

import java.io.Serializable;

public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;  // ID único para cada actividad
    private String nombreActividad;
    private String zona;
    private int duracion;

    public Actividad(int id, String nombreActividad, String zona, int duracion) {
        this.id = id;  // Asignar el ID desde fuera
        this.nombreActividad = nombreActividad;
        this.zona = zona;
        this.duracion = duracion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Actividad [id=" + id + ", nombreActividad=" + nombreActividad + ", zona=" + zona + ", duracion=" + duracion + "]";
    }

}
