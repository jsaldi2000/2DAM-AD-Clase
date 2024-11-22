package dam.ad.practica18.main;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import dam.ad.practica18.javabeans.Coche;

public class EscribirArchivoTexto {

    private static final String FILE_BINARIO = "Ficheros/coches.dat";
    private static final String FILE_REPORTE = "Ficheros/coches.txt";

	public static void main(String[] args) {
		
		// Cargar los objetos coche desde el binario
		List<Coche> coches = cargarCoches();
		
		exportarCochesATexto(coches);
		
}

    private static void exportarCochesATexto(List<Coche> coches) {
        try (FileWriter writer = new FileWriter(FILE_REPORTE, true)) {
            for (Coche coche : coches) {
                writer.write(coche.toString() + System.lineSeparator()); // Convierte el objeto Coche a cadena y lo escribe en una nueva línea
            }
            System.out.println("Reporte exportado exitosamente a " + FILE_REPORTE);
        } catch (IOException e) {
            System.err.println("Error al exportar los coches a texto");
        }
    }

	private static List<Coche> cargarCoches() {
	        List<Coche> coches = new ArrayList<>();
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_BINARIO))) {
	            while (true) {
	                try {
	                    Coche coche = (Coche) ois.readObject();
	                    coches.add(coche);
	                } catch (EOFException e) {
	                    break;
	                }
	            }
	        } catch (IOException | ClassNotFoundException e) {
	            System.err.println("Error al cargar los coches");
	        }
	        return coches;
	    }
	}


