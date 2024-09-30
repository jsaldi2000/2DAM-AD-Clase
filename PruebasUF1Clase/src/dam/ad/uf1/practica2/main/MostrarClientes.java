package dam.ad.uf1.practica2.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import dam.ad.uf1.practica2.javabeans.Cliente;

public class MostrarClientes {

	public static void main(String[] args) {
		
		//Leer los clientes desde el archivo binario
		try(ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("Ficheros/clientes.dat"))){
			
			//Leer cada cliente y lo muestro por consola
			while (true) {
				try {
					Cliente cliente = (Cliente) ois.readObject();
					System.out.println(cliente);
					
				}catch (Exception e) {
					break; //Fin del archivo
					// TODO: handle exception
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ha ocurrido un error inesperado");
			e.printStackTrace();
		}

	}

}
