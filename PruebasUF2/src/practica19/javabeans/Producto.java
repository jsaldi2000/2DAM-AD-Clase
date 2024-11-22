package practica19.javabeans;

import java.io.Serializable;

public class Producto implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private String nombre;
    private double precio;
    private int cantidad;
    private double peso;
    private double tamanio;
    private String EAN;
    private double rendimiento;
    
	public Producto(String nombre, double precio, int cantidad, double peso, double tamanio, String EAN,
			double rendimiento) {
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
		this.peso = peso;
		this.tamanio = tamanio;
		this.EAN = EAN;
		this.rendimiento = rendimiento;
	}

	public Producto() {
		super();
	}

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

	public double getTamanio() {
		return tamanio;
	}

	public void setTamanio(double tamanio) {
		this.tamanio = tamanio;
	}

	public String getEAN() {
		return EAN;
	}

	public void setEAN(String EAN) {
		EAN = EAN;
	}

	public double getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(double rendimiento) {
		this.rendimiento = rendimiento;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad + ", peso=" + peso
				+ ", tamanio=" + tamanio + ", EAN=" + EAN + ", rendimiento=" + rendimiento + "]";
	}
	
}
