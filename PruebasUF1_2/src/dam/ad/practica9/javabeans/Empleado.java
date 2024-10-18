package dam.ad.practica9.javabeans;

public class Empleado {
	
	private int id;
	private String nombre;
	private String apellido;
	private Direccion direccion;
	
	public Empleado(int id, String nombre, String apellido, Direccion direccion) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Direccion getDireccion() {
		return direccion;
	}
	
}
