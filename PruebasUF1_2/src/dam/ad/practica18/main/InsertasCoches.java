package dam.ad.practica18.main;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import dam.ad.practica18.javabeans.Coche;
import dam.ad.practica18.javabeans.Motor;

public class InsertasCoches {
	
	private static final String FILE_BINARIO = "Ficheros/coches.dat";
	
	public static void main(String[] args) {
		
		List<Coche> coches = new ArrayList<>();
		
		String[] marcas = {"Toyota", "Honda", "Ford", "Tesla", "BMW"};
		String[] modelos = {"Corolla", "Civic", "Focus", "Model 3", "Serie 3"};
		int[] potencias = {120, 150, 130, 300, 180};
		String[] combustibles = {"Gasolina", "Gasolina", "Diésel", "Eléctrico", "Gasolina"};
		double[] consumos = {6.5, 7.0, 5.8, 0.0, 8.0};
		double[] precios = {20000, 25000, 23000, 50000, 40000};
		int[] aniosFabricacion = {2021, 2022, 2020, 2023, 2021};

		for (int i = 0; i < marcas.length; i++) {
			coches.add(new Coche(i + 1, marcas[i], modelos[i], new Motor(potencias[i], combustibles[i], consumos[i]), precios[i], aniosFabricacion[i]));
		}
		
		try (FileOutputStream fos = new FileOutputStream(FILE_BINARIO);
		ObjectOutputStream oos = new ObjectOutputStream(fos)){
			
			// Iteramos sobre cada uno de los coches de la lista coches
			for (Coche coche : coches) {
				oos.writeObject(coche);
			}
			System.out.println("Coches insertados correctamente en el archivo binario " + FILE_BINARIO);
			
		} catch (Exception e) {
			System.out.println("Error al guardar los objetos coche");
		}
		
	}
}
