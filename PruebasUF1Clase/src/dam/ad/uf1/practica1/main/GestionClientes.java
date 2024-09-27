package dam.ad.uf1.practica1.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dam.ad.uf1.practica1.javabeans.Cliente;

public class GestionClientes {
	// Declaro variables
	private static ArrayList<Cliente> listaClientes = new ArrayList<>();
	private static Scanner scanner = new Scanner (System.in);

	
	public static void main(String[] args) {
	boolean salir = false; //Variable salir para poder controlar la ejecución más tarde
	cargarClientesTexto(); //Cargar clientes al inicio
	
	//Creo el menú para el usuario.

	while (!salir) {
		System.out.println("\nELIJA LA OPCIÓN QUE DESEE:");
		System.out.println("1. Añadir cliente");
		System.out.println("2. Ver clientes");
		System.out.println("3. Salir");
		int opcion = scanner.nextInt();
		scanner.nextLine();
		
		//Switch para controlar la ejecución deseada por el usuario
		switch (opcion) {
		case 1:
			aniadirCliente();
			break;
		case 2:
			verClientes();
			break;
		case 3:
			salir = true;
			System.out.println("SALIENDO DEL PROGRAMA");
			break;
		default:
			System.out.println("Opción no válida, introduzca un valor de 1 a 3");
		}
		
	}


	}


	private static void aniadirCliente() {
		// Método para añadir nuevos clientes por consola
		System.out.println("Nombre: ");
		String nombre = scanner.nextLine();

		System.out.println("Apellido: ");
		String apellido = scanner.nextLine();

		System.out.println("Email: ");
		String email = scanner.nextLine();

		System.out.println("Dirección: ");
		String direccion = scanner.nextLine();

		System.out.println("Fecha alta: ");
		String fechaAlta = scanner.nextLine();

		System.out.println("Provincia: ");
		String provincia = scanner.nextLine();

		System.out.println("Ciudad: ");
		String ciudad = scanner.nextLine();
		
		//Crea el objeto cliente y lo añado al array listaCliente
		Cliente cliente = new Cliente(nombre, apellido, email, direccion, fechaAlta, provincia, ciudad);
		listaClientes.add(cliente);
		System.out.println("Cliente se ha añadido");
		
		//Creo el directorio "Ficheros" si no existe
		File directorio = new File ("Ficheros");
		if (!directorio.exists()) {
			directorio.mkdir();
		}
		
		//Archivo dentro del directorio Ficheros
		File file = new File(directorio, "clientes.txt");
		
		//Guardar el cliente en el archivo de texto inmediantamente después de añadirlo
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true))) {
	        writer.write(cliente.getNombre() + "," + cliente.getApellido() + "," + cliente.getEmail() + "," +
	                     cliente.getDireccion() + "," + cliente.getFechaAlta() + "," + cliente.getProvincia() + "," +
	                     cliente.getCiudad());
	        writer.newLine();
	        System.out.println("Cliente guardado en archivo de texto.");
	    } catch (IOException e) {
	        System.out.println("Error al guardar el cliente en el archivo de texto.");
	        e.printStackTrace();
	    }	

	}


	//Método para cargar los clientes y obtener persistencia
	private static void cargarClientesTexto() {
		// Crear el directorio "Ficheros" si no existe
		File directorio = new File("Ficheros");
		if (!directorio.exists()) {
			System.out.println(
					"No existe el directorio Ficheros, no existe el archivo clientes.txt. No se cargarán clientes previos."); // No existe el directorio ni el archivo clientes.txt
		}

		// Archivo dentro del directorio Ficheros
		File file = new File(directorio, "clientes.txt");

		if (!file.exists()) {
			System.out.println("El archivo clientes.txt no existe en el directorio Ficheros. No se cargarán clientes.");
			return; // Evitar seguir con el resto del código si no existe el archivo
		}

		//Si el fichero existe, continúo cargando el fichero en el array para mostrarlos en caso de que el usuario lo requiera, o que el cliente añada nuevos clientes
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				String[] datos = linea.split(",");
				Cliente cliente = new Cliente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
				listaClientes.add(cliente);
			}
			System.out.println("Carga inicial realizada desde archivo de texto.");
		} catch (IOException e) {
			System.out.println("Error al cargar clientes.");
			e.printStackTrace();
		}
	}
	
	//Opción para leer los clientes que hemos guardado en el fichero
	private static void verClientes() {

		System.out.println("\nLista de clientes guardados en la app");
		System.out.println("--------------------------------------");

		for (Cliente cliente : listaClientes) {
			System.out.println(cliente);
		}
	}

}
