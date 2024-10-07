package dam.ad.uf1.practica6.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import dam.ad.uf1.practica6.javabeans.LineaPedido;

public class LeerLineasPedidosObj {

	public static void main(String[] args) {
		
		// Leer los objetos LineaPedido desde el fichero "lineasPedidos.dat"
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Ficheros/lineasPedidos.dat"))){
			
            while (true) {
                try {
                    LineaPedido lineaPedido = (LineaPedido) ois.readObject(); // Hacer casting explícito y leer cada objeto LineaPedido
                    System.out.println(lineaPedido); // Mostrar por pantalla
                } catch (IOException | ClassNotFoundException e) {
                    break; // Salir del bucle cuando se termine de leer el archivo
                }
            }

	} catch (FileNotFoundException e1) {
		System.out.println("Fichero no encontrado");
		e1.printStackTrace();
	} catch (IOException e1) {
		System.out.println("Ha ocurrido un error inesperado");
		e1.printStackTrace();
	}

}
	
}
