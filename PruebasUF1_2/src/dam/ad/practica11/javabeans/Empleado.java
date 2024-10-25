package dam.ad.practica11.javabeans;

import java.io.Serializable;

public class Empleado implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String nombre;
	private int dep;
	private double salario;
	
	public Empleado(int id, String nombre, int dep, double salario) {
		this.id = id;
		this.nombre = nombre;
		this.dep = dep;
		this.salario = salario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDep() {
		return dep;
	}

	public void setDep(int dep) {
		this.dep = dep;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
		
}
