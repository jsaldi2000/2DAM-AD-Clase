package dam.ad.uf1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirFicheroSecuencialClase {

	public static void main(String[] args) {
		// Ruta del fichero ya existente
		String rutaArchivo = "Ficheros/ejemplo.txt";

		// Escribir la frase "Hello World" en el fichero de forma secuencial (añadir al
		// final del archivo)
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo, true))) { // true para modo append es decir que escribirá cada vez una línea/registro nueva
			escritor.write("Axel León, 21525425, axel@loquesea.com, 20/01/2000");
			escritor.newLine(); // Añadir un salto de línea
			System.out.println("Escritura completada");
		} catch (IOException e) {
			System.out.println("Ocurrió un error al escribir en el archivo.");
			e.printStackTrace();
		}
	}
}