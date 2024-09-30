package dam.ad.uf1.practica3.javabeans;

import java.io.Serializable;

public class Asignatura implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private String codigo;
    private String nombre;
    private String profesor;
    private int numeroHoras;
    
	public Asignatura(String codigo, String nombre, String profesor, int numeroHoras) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.profesor = profesor;
		this.numeroHoras = numeroHoras;
	}

	@Override
	public String toString() {
		return "Asignatura [codigo=" + codigo + ", nombre=" + nombre + ", profesor=" + profesor + ", numeroHoras="
				+ numeroHoras + "]";
	}
}
