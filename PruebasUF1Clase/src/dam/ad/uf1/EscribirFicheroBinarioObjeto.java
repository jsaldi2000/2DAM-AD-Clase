package dam.ad.uf1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import dam.ad.uf1.javabeans.Alumno;

public class EscribirFicheroBinarioObjeto {

	public static void main(String[] args) {
		// Creamos un array de objetos de tipo alumno
		Alumno[] alumnos = {
				new Alumno ("Juan", 20, "2DAM"),
				new Alumno ("Miguel", 19, "2DAM"),
				new Alumno ("Carlos", 22, "2DAM"),
				new Alumno ("Jaime", 28, "2DAM"),
				new Alumno ("Pedro", 19, "2DAM")
		};
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Ficheros/alumnos.dat"))) {
			//Para cada objeto de tipo Alumno en el array alumnos, lo escribimos en el archiv
			for (Alumno alumno : alumnos) {
				oos.writeObject(alumno);
			}
			System.out.println("Alumnos escritos correctamente en el archivo binario");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
