package practica22.javabeans;

import java.io.Serializable;

public class Sesion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String fecha;
	private int duracion;
	private int espectadores;
	
	// Constructor parametrizado
	public Sesion(String fecha, int duracion, int espectadores) {
		this.fecha = fecha;
		this.duracion = duracion;
		this.espectadores = espectadores;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getEspectadores() {
		return espectadores;
	}

	public void setEspectadores(int espectadores) {
		this.espectadores = espectadores;
	}

	@Override
	public String toString() {
		return "Sesion [fecha=" + fecha + ", duracion=" + duracion + ", espectadores=" + espectadores + "]";
	}

}
