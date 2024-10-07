package dam.ad.uf1.practica6.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import dam.ad.uf1.practica6.javabeans.LineaPedido;
import dam.ad.uf1.practica6.javabeans.Producto;

public class InsertLineasPedidosObj {

	public static void main(String[] args) {


		// Crear algunos objetos Producto
		Producto producto1 = new Producto(1, "Ordenador", 799.99);
		Producto producto2 = new Producto(2, "Smartphone", 499.99);
		Producto producto3 = new Producto(3, "Tablet", 799.99);
		Producto producto4 = new Producto(4, "Monitor", 199.99);
		Producto producto5 = new Producto(5, "Teclado", 29.99);		
		
		
		// Inicializar un array de 5 objetos LineaPedido
		LineaPedido[] lineasPedidos = new LineaPedido[5];
		
		
		// Crear las líneas de pedidos con diferentes productos
		lineasPedidos[0] = new LineaPedido(1, producto1, 2, 1599.98, "07-10-2024");
		lineasPedidos[1] = new LineaPedido(2, producto3, 2, 1599.98, "07-10-2024");
		lineasPedidos[2] = new LineaPedido(3, producto2, 1, 499.99, "15-10-2024");
		lineasPedidos[3] = new LineaPedido(4, producto4, 3, 599.97, "17-10-2024");		
		
		// Escribir los objetos LineaPedido en el fichero binario "lineasPedidos.dat"
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Ficheros/lineasPedidos.dat"))){
			
			for (LineaPedido lineaPedido : lineasPedidos) {
				oos.writeObject(lineaPedido); //Escribo cada objeto en el fichero binario
			}
			System.out.println("Datos de las líneas de pedidos introducidos correctamente en el fichero");
			
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error inesperado");
			e.printStackTrace();
		}

	}

}
