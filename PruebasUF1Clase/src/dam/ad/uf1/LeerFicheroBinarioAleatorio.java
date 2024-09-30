package dam.ad.uf1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LeerFicheroBinarioAleatorio {

	public static void main(String[] args) {
		// Ruta del archivo 
		String rutaArchivo = "Ficheros/ejemplo.bin";
		
		try (RandomAccessFile archivo = new RandomAccessFile(rutaArchivo, "r")) {
			
			//Mueve el puntero al inicio del archivo para comenzar a leer
			archivo.seek(0);
			
			//Leer la cadena escrita antes con writeUTF()
			String textoLeido = archivo.readUTF();
			System.out.println("Texto leído: " + textoLeido);
			
			// Leer el valor entero
			int numeroEntero = archivo.readInt();
			System.out.println("Entero leído: " + numeroEntero);
			
			// Leer booleano
			boolean valorBooleano = archivo.readBoolean();
			System.out.println("Booleano leído: " + valorBooleano);
			
			// Leer caracter
			char caracterLeido = archivo.readChar();
			System.out.println("Caracter leído: " + caracterLeido);
			
			// Leer double
			double numeroDoubleLeido = archivo.readDouble();
			System.out.println("Double leído: " + numeroDoubleLeido);
			
			// Leer el booleano de nuevo
			archivo.seek(30*2+1*4);
			
			// Leer booleano
			System.out.println("Nuevo Booleano leído: " + valorBooleano);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
