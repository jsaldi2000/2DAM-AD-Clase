package dam.ad.uf1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EscribirFicheroBinarioAleatorio {

	public static void main(String[] args) {
		// Ruta del fichero binario
		String rutaArchivo = "Ficheros/ejemplo.bin";
		
		try (RandomAccessFile archivo = new RandomAccessFile(rutaArchivo, "rw")){
			
			//Borrar el archivo para comenzar de nuevo
			archivo.setLength(0);
			
			//Posicionarme al final de un archivo
			archivo.seek(archivo.length());
			
			// Escribir una cadena como UTF-8
			String texto = "Hello world de forma aleatoria";
			archivo.writeUTF(texto);
			
			// Escribir un entero
			archivo.writeInt(12345);
			
			// Escribir un valor booleano
			archivo.writeBoolean(true);
			
			// Escribir un caracter
			archivo.writeChar('A');
			
			// Escribir un double
			archivo.writeDouble(3.141596);
			
			System.out.println("datos escritos correctamente en el archivo binario");
			
			
		} catch (FileNotFoundException e) {
			System.out.println("no se ha encontrado el archivo");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ha ocurrido un error inesperado");
			e.printStackTrace();
		}

	}

}
