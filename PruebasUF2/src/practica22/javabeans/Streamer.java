package practica22.javabeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Streamer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nombre;
	private String plataforma;
	private String categoria;
	private int seguidores;
	private int suscriptores;
	private List<Sesion> sesiones; // Atributo compuesto, que es una lista
	
	// Constructor parametrizado
	public Streamer(int id, String nombre, String plataforma, String categoria, int seguidores, int suscriptores) {
		this.id = id;
		this.nombre = nombre;
		this.plataforma = plataforma;
		this.categoria = categoria;
		this.seguidores = seguidores;
		this.suscriptores = suscriptores;
		this.sesiones = new ArrayList<>(); // Inicializamos la lista, evitar errores de NullPointerException
	}
	
	// Constructor vacío
	public Streamer() {
		this.sesiones = new ArrayList<>(); // Inicializamos la lista, evitar errores de NullPointerException
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

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(int seguidores) {
		this.seguidores = seguidores;
	}

	public int getSuscriptores() {
		return suscriptores;
	}

	public void setSuscriptores(int suscriptores) {
		this.suscriptores = suscriptores;
	}

	public List<Sesion> getSesiones() {
		return sesiones;
	}

	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}
	
	// Método para añadir una nueva sesión a la lista de sesiones
	// Después añadiremos al streamer cada una de las sesiones que nos pase el usuario por consola
	// streamer.agregarSesion(sesionCreada);
	public void agregarSesion(Sesion sesion) {
		this.sesiones.add(sesion);
		
	}
	
    @Override
    public String toString() {
        return "Streamer{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", categoria='" + categoria + '\'' +
                ", seguidores=" + seguidores +
                ", suscriptores=" + suscriptores +
                ", sesiones=" + sesiones +
                '}';
    }

}
