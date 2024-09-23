package dam.ad.uf1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerFicheroSecuencial {

	public static void main(String[] args) {
		// Ruta del fichero ya existente
		String rutaArchivo = "Ficheros/ejemplo.txt";

		// Leer el archivo secuencialmente línea por línea
		try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				System.out.println(linea); // Imprime cada línea del archivo
			}
		} catch (IOException e) {
			System.out.println("Ocurrió un error al leer el archivo.");
			e.printStackTrace();
		}
	}
}

