package dam.ad.uf1.practica3.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import dam.ad.uf1.practica3.javabeans.Asignatura;

public class AlmacenarAsignaturas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Crear un array de 5 asignaturas
        Asignatura[] asignaturas = new Asignatura[5];

        // Pedir datos al usuario y crear los objetos Asignatura
        for (int i = 0; i < asignaturas.length; i++) {

            System.out.print("Código de la asignatura: ");
            String codigo = sc.nextLine();

            System.out.print("Nombre de la asignatura: ");
            String nombre = sc.nextLine();

            System.out.print("Profesor de la asignatura: ");
            String profesor = sc.nextLine();

            System.out.print("Número de horas: ");
            int numeroHoras = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            // Crear la asignatura con los datos introducidos
            asignaturas[i] = new Asignatura(codigo, nombre, profesor, numeroHoras);
        }
		
		//Guardar los objetos a un archivo binario
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("Ficheros/asignaturas.dat"))) {
			
			for (Asignatura asignatura : asignaturas) {
				oos.writeObject(asignatura);
			}
			System.out.println("Asignaturas guardadas correctamente en el archivo asignaturas.dat");
			
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no se encuentra");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ha ocurrido un error inesperado");
			e.printStackTrace();
		}

	}

}
