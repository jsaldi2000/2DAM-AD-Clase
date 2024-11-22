package dam.ad.practica18.javabeans;

import java.io.Serializable;

public class Coche implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idCoche;
	private String marca;
	private String modelo;
	private Motor motor;
	private double precio;
	private int anioFabricacion;
	
	public Coche(int idCoche, String marca, String modelo, Motor motor, double precio, int anioFabricacion) {
		this.idCoche = idCoche;
		this.marca = marca;
		this.modelo = modelo;
		this.motor = motor;
		this.precio = precio;
		this.anioFabricacion = anioFabricacion;
	}

	public int getIdCoche() {
		return idCoche;
	}

	public void setIdCoche(int idCoche) {
		this.idCoche = idCoche;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getAnioFabricacion() {
		return anioFabricacion;
	}

	public void setAnioFabricacion(int anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}

    @Override
    public String toString() {
        return "Coche [idCoche=" + idCoche + ", marca=" + marca + ", modelo=" + modelo + ", motor=" + motor
                + ", precio=" + precio + ", añoFabricacion=" + anioFabricacion + "]";
    }
	
}
