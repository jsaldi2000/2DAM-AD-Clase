package practica22.main;

import java.util.Scanner;

import com.db4o.ObjectSet;

import practica22.db.AccesoStreamersDB;
import practica22.javabeans.Sesion;
import practica22.javabeans.Streamer;

public class GestionStreamers {
	
	private static final AccesoStreamersDB accesoDB = new AccesoStreamersDB();
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String opcion = "";
		while (!opcion.equals("S")) {
			System.out.println("\nMenú de Gestión de Streamers:");
			System.out.println("-----------------------------------------");
			System.out.println("IS: Insertar Streamer y Sesión");
			System.out.println("CT: Consultar todos los streamers");
			System.out.println("CP: Consultar streamers por plataforma");
			System.out.println("CS: Consultar streamers con más de X suscriptores");
			System.out.println("S:  Salir de la aplicación");
			System.out.println("-----------------------------------------");
			System.out.println("Elige una opción");
			
			opcion = sc.nextLine().toUpperCase().trim();
			
			switch (opcion) {
			case "IS": insertaStreamerYSesion();
				break;

			default:
				System.out.println("opción incorrecta, escoja de nuevo por favor");
				break;
			}
			
		}
		
		accesoDB.cerrarDB();
		sc.close();

	}

	private static void insertaStreamerYSesion() {
		System.out.println("Nombre del streamer");
		String nombre = sc.nextLine();
		
		// Busco el streamer por nombre en la bbdd
		ObjectSet<Streamer> resultados = accesoDB.buscarStreamerPorNombre(nombre);
		Streamer streamer;
		
		// Si el streamer no existe, solicitamos sus datos y lo creamos
		if (!resultados.hasNext()) {
			System.out.println("Plataforma: ");
			String plataforma = sc.nextLine();
			System.out.println("Categoria: ");
			String categoria = sc.nextLine();
			System.out.println("Seguidores: ");
			int seguidores = Integer.parseInt(sc.nextLine());
			System.out.println("Suscriptores: ");
			int suscriptores = Integer.parseInt(sc.nextLine());
			
			// Calculamos el ID
			int id = accesoDB.obtenerSiguienteId();
			
			// Creamos nuevo streamer y lo guardamos en la bbdd
			streamer = new Streamer(id, nombre, plataforma, categoria, seguidores, suscriptores);
			accesoDB.insertarStreamer(streamer);
			System.out.println("Streamer insertado correctamente en la bbdd");
		} else {
			//Si ya existe, obtenemos el streamer existente
			streamer = resultados.next();
			System.out.println("El streamer ya existe, se agregará una nueva sesión");
		}
		
		// Solicitar los datos de la sesión
		System.out.println("Fecha de la sesión");
		String fecha = sc.nextLine();
		System.out.println("Duración de la sesión (en minutos): ");
		int duracion = Integer.parseInt(sc.nextLine());
		System.out.println("Espectadores de la sesión: ");
		int espectadores = Integer.parseInt(sc.nextLine());
		
		// Creamos la sesión
		Sesion sesion = new Sesion(fecha, duracion, espectadores);
		
		// Agreagar la sesión al streamer
		if (accesoDB.agregarSesion(nombre, sesion)) {
			System.out.println("Sesión añadida con éxito");
		} else {
			System.out.println("Error al añadir la sesión");
		}
	}

}
