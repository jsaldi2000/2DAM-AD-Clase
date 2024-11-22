package dam.ad.practica18.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import dam.ad.practica18.javabeans.Coche;

public class LeerCoches {

	private static final String FILE_BINARIO = "Ficheros/coches.dat";

	public static void main(String[] args) {

		List<Coche> coches = cargarCoches();

		mostrarCoches(coches);

	}

	private static void mostrarCoches(List<Coche> coches) {
		if (coches.isEmpty()) {
			System.out.println("no se han encontrado coches en el archivo");
		} else {
			for (Coche coche : coches) {
				System.out.println(coche);
			}
		}

	}

	private static List<Coche> cargarCoches() {

		List<Coche> coches = new ArrayList<>();

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_BINARIO))) {

			while (true) {
				try {
					Coche coche = (Coche) ois.readObject();
					coches.add(coche);
				} catch (Exception e) {
					// TODO: handle exception
					break;
				}

			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return coches;
	}

}
