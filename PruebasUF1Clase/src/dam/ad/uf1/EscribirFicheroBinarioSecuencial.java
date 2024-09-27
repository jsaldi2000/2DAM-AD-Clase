package dam.ad.uf1;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class EscribirFicheroBinarioSecuencial {

	public static void main(String[] args) {
		// Ruta del directorio donde queremos crear el archivo
		String nombreDirectorio = "Ficheros";
		
		//Creamos un objeto File para crear el fichero "ejemplo.dat" en binario
		File archivo = new File(nombreDirectorio, "ejemplo.dat");
		
		//Verificamos si el archivo existe
		if(archivo.exists()) {
			System.out.println("El archivo ya existe, no se ha creado ningún fichero nuevo");
		} else {
			try {
				archivo.createNewFile();
				System.out.println("Archivo creado con éxito");
				
			} catch (Exception e) {
				System.out.println("El fichero no se ha podido crear");
			}
		}
		
		//Escribir datos en el archivo binario de forma secuencial
		try (FileOutputStream fos = new FileOutputStream(archivo);
				DataOutputStream dos = new DataOutputStream(fos)) {
			
			//Escribir datos en el archivo binario
			dos.writeInt(100); // Escribo un enter0
			dos.writeDouble(3.14159); //Escribo un double
			dos.writeUTF("Hola Mundo en archivo binario");
			
			System.out.println("Datos escritos en el archivo binario de forma secuencial");
					
				}catch (Exception e) {
					System.out.println("Error al escribir en el archivo binario");
				}

	}

}
