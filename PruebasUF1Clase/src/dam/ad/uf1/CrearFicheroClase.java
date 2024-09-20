package dam.ad.uf1;

import java.io.File;

public class CrearFicheroClase {

	public static void main(String[] args) {
		// Establecemos la ruta del directorio
		File file = new File ("Ficheros", "ejemplo.txt");
		
		//Verificamos si el fichero o directorio existe
		if (file.exists()) {
			System.out.println("El fichero o directorio ya existe");
			
			//Verificamos si es fichero o directorio
			if (file.isDirectory()) {
				System.out.println("es un directorio");
			} else if (file.isFile()) {
				System.out.println("Es un fichero");
			} else {
				System.out.println("El elemento no existe en la ruta seleccionada");
			}
		}

	}

}
