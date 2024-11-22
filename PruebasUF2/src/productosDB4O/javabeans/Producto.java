package productosDB4O.javabeans;

import java.io.Serializable;

public class Producto implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private double precio;
	private int cantidad;
	private double peso;
	private String tamanio;
	private String EAN;
	private double rendimientoDeVentas;
	
	// Constructor parametrizado
	public Producto(String nombre, double precio, int cantidad, double peso, String tamanio, String eAN,
			double rendimientoDeVentas) {
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
		this.peso = peso;
		this.tamanio = tamanio;
		EAN = eAN;
		this.rendimientoDeVentas = rendimientoDeVentas;
	}

	// Constructor vacío para crear producto de referencia y hacer consultas
	public Producto() {
		super();
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public String getEAN() {
		return EAN;
	}

	public void setEAN(String eAN) {
		EAN = eAN;
	}

	public double getRendimientoDeVentas() {
		return rendimientoDeVentas;
	}

	public void setRendimientoDeVentas(double rendimientoDeVentas) {
		this.rendimientoDeVentas = rendimientoDeVentas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// toString
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad + ", peso=" + peso
				+ ", tamanio=" + tamanio + ", EAN=" + EAN + ", rendimientoDeVentas=" + rendimientoDeVentas + "]";
	}
	
	
	

}
