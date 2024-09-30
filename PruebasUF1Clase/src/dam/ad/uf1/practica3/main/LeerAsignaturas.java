package dam.ad.uf1.practica3.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import dam.ad.uf1.practica3.javabeans.Asignatura;

public class LeerAsignaturas {

	public static void main(String[] args) {
		//Leer las asignaturas desde el archivo binario
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("Ficheros/asignaturas.dat"))) {
			
			//Bucle para leer y mostrar las asignaturas almacenadas en el archivo
			while (true) {
				try {
					Asignatura asignatura = (Asignatura) ois.readObject(); //Leer el objeto Asignatura
					System.out.println(asignatura);
				} catch (Exception e) {
					// TODO: handle exception
					break;
				}
			}
			System.out.println("Todas las asignaturas han sido leídas del archivo 'asignaturas.dat'");
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error inesperado");
			e.printStackTrace();
		}

	}

}
