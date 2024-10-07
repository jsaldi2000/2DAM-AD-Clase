package dam.ad.uf1.practica4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LecturaEmpleadosFicheroBinario {

	static final int TAM_NOMBRE = 10; // Tamaño máximo del nombre
	static final int TAM_REG = TAM_NOMBRE * 2 + 4 + 8; //Tamaño del registro
	
	public static void main(String[] args) {
		
		try (RandomAccessFile raf = new RandomAccessFile("Ficheros/empleados.dat", "r")){
			
			// Posicionar el puntero al inicio del archivo
			raf.seek(0);
			
			// Variables para almacenar los datos leíods
			String nombre;
			char[] cNombre = new char[TAM_NOMBRE];
			int depto;
			double salario;
			
			// Leer el archivo y mostrar el contenido de cada elemento
			while (raf.getFilePointer() < raf.length()) { //Siempre que existan datos y no se haya llegado al final,seguiré leyendo
				
				// Lectura del nombre
				//Caracter a caracter
				for (int i = 0; i < cNombre.length; i++) {
					cNombre[i] = raf.readChar(); //Leer cada caracter del nombre hasta 10 caracteres
				}
				nombre = new String(cNombre);
				nombre = nombre.trim(); 
				
				// Lectura del departamento
				depto = raf.readInt();
				
				// Lectura del salario
				salario = raf.readDouble();
				
				// Muestro el registro leído completamente
                System.out.println("Nombre: " + nombre + " - Departamento: " + depto + " - Salario: " + salario);

			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
