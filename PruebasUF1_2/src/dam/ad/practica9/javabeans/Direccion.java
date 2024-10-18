package dam.ad.practica9.javabeans;

public class Direccion {
	
	private String calle;
	private String ciudad;
	private String provincia;
	private String codigoPostal;
	
	public Direccion(String calle, String ciudad, String provincia, String codigoPostal) {
		this.calle = calle;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.codigoPostal = codigoPostal;
	}

	public String getCalle() {
		return calle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

}
