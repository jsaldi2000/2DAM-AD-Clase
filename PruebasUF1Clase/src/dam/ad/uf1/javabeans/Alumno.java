package dam.ad.uf1.javabeans;

import java.io.Serializable;

public class Alumno implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private int edad;
	private String curso;
	
	
public Alumno(String nombre, int edad, String curso) {
		this.nombre = nombre;
		this.edad = edad;
		this.curso = curso;
	}


public String getNombre() {
	return nombre;
}


public void setNombre(String nombre) {
	this.nombre = nombre;
}


public int getEdad() {
	return edad;
}


public void setEdad(int edad) {
	this.edad = edad;
}


public String getCurso() {
	return curso;
}


public void setCurso(String curso) {
	this.curso = curso;
}


@Override
public String toString() {
	return "Alumno [nombre=" + nombre + ", edad=" + edad + ", curso=" + curso + "]";
}
	
	
	

}
