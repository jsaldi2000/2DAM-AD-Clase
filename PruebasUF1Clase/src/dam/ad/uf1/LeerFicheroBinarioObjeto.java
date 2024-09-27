package dam.ad.uf1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import dam.ad.uf1.javabeans.Alumno;

public class LeerFicheroBinarioObjeto {

	public static void main(String[] args) {
		try(ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("Ficheros/alumnos.dat"))){
			Alumno alumno;
			
			//Bucle para leer objetos del archivo binario
			while (true) {
				try {
					alumno = (Alumno) ois.readObject(); //leo el objeto alumnos
					System.out.println(alumno);
				} catch (Exception e) {
					break;
				}
				
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido encontrar el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Se ha producido un error");
			e.printStackTrace();
		}

	}

}
