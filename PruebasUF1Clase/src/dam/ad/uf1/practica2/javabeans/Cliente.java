package dam.ad.uf1.practica2.javabeans;

import java.io.Serializable;

public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String apellido;
	private String email;
	private String direccion;
	private String fechaAltaString;
	private String provincia;
	private String ciudad;
	
	
	public Cliente(String nombre, String apellido, String email, String direccion, String fechaAltaString,
			String provincia, String ciudad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
		this.fechaAltaString = fechaAltaString;
		this.provincia = provincia;
		this.ciudad = ciudad;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getFechaAltaString() {
		return fechaAltaString;
	}


	public void setFechaAltaString(String fechaAltaString) {
		this.fechaAltaString = fechaAltaString;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", direccion=" + direccion
				+ ", fechaAltaString=" + fechaAltaString + ", provincia=" + provincia + ", ciudad=" + ciudad + "]";
	}
	
}
