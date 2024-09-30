package dam.ad.uf1.practica2.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import dam.ad.uf1.practica2.javabeans.Cliente;

public class AlmacenarClientes {

	public static void main(String[] args) {
		// Crear un array de clientes
		Cliente[] clientes = {
	            new Cliente("Ana", "García", "ana.garcia@email.com", "Calle Mayor 5", "2023-05-20", "Madrid", "Madrid"),
	            new Cliente("Luis", "Pérez", "luis.perez@email.com", "Calle Sol 10", "2023-06-15", "Barcelona", "Barcelona"),
	            new Cliente("Laura", "Sánchez", "laura.sanchez@email.com", "Calle Luna 7", "2023-07-01", "Valencia", "Valencia"),
	            new Cliente("Carlos", "Romero", "carlos.romero@email.com", "Calle Estrella 22", "2023-08-20", "Sevilla", "Sevilla")
	        };

		//Guardamos los clinetes en un archivo binario llamado clientes.dat
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("Ficheros/clientes.dat"))) {
			
			for (Cliente cliente : clientes) {
				oos.writeObject(cliente); //Guardar cada cliente en el archivo binario
			}
			System.out.println("Clientes guardados en el archivo clientes.dat");
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al guardar el archivo");
			e.printStackTrace();
		}
	

	}

}
