package dam.ad.pruebasXStream.javabeans;

import java.io.Serializable;

public class Objeto implements Serializable{


	private String nombre;
	private int valor;
	
	public Objeto(String nombre, int valor) {
		this.nombre = nombre;
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
