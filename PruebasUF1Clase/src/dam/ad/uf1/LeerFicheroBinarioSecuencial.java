package dam.ad.uf1;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class LeerFicheroBinarioSecuencial {

	public static void main(String[] args) {
		// Ruta al archivo
		File archivo = new File("Ficheros","ejemplo.dat");
		
		//Verificamos si exuste
		if (!archivo.exists()) {
			System.out.println("El archivo no existe");
			return;
		}
		
		//Leer datos del archivo binario de forma secuencial
		try (FileInputStream fis = new FileInputStream(archivo);
				DataInputStream dis = new DataInputStream(fis)){
			
			//leer datos en el mismo orden que fueron escritos
			int entero = dis.readInt(); //Leer un entero
			double doble = dis.readDouble(); //Leer un double
			String cadena = dis.readUTF(); //Leer una cadena de texto
			
			//Imprimo por consola
			System.out.println("Entero: "+ entero);
			System.out.println("Double: "+ doble);
			System.out.println("Cadena: "+ cadena);
			
		}catch (Exception e) {
			System.out.println("Error al leer el archivo binario.");
		}

	}

}
