package practica19.main;

import java.util.Scanner;

import com.db4o.ObjectSet;

import practica19.db.AccesoProductosDB;
import practica19.javabeans.Producto;

public class GestionProductos {

	// Crear una instancia de AccesoProductosDB
	private static final AccesoProductosDB accesoDB = new AccesoProductosDB();
	
	// Inicializamos Scanner
	private static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

		System.out.println("\nMenú de Gestión de Productos");
		System.out.println();
		System.out.println("CONSULTAS SIMPLES");
		System.out.println("-----------------------------------");
		System.out.println("1. Añadir producto");
		System.out.println("2. Consultar productos");
		System.out.println("3. Modificar productos por EAN");
		System.out.println("4. Modificar productos por precio específico");
		System.out.println("5. Eliminar producto");
		System.out.println();
		System.out.println("CONSULTAS AVANZADAS");
		System.out.println("-----------------------------------");
		System.out.println("6. Consultar productos con peso mayor que...");
		System.out.println("7. Ajustar precios de productos dentro de un rango");
		System.out.println("8. Calcular precios con IVA por país");
		System.out.println("S. Salir");
		System.out.println();
		System.out.println("Elige una opción: ");
		String opcion = sc.nextLine().toUpperCase().trim();
		
		switch (opcion) {
		case "1": insertaProducto();
			break;
		case "2": consultaProductos();
			break;
		case "3": modificaProductoPorEAN();
			break;
		case "4": modificaProductoPorPrecio();
			break;
		case "5": eliminaProducto();
			break;
		case "6": consultaProductosPesoMayorQue();
			break;
		case "7": ajustaPrecios();
			break;
		case "8": calculaPreciosConIVA();
			break;
		case "S": System.out.println("Aplicación cerrada");
			break;		
		default: System.out.println("No se ha seleccionado una opción correcta");
			break;
		}
		
		accesoDB.cerrarDB();
		sc.close();

	}

	//8. calcular el iva de los productos por país
	private static void calculaPreciosConIVA() {
		System.out.println("introduce el nombre del país: ");
		String pais = sc.nextLine();
		System.out.println("Introduce la tasa de IVA para " + pais +": ");
		double iva = Double.parseDouble(sc.nextLine());
		
		accesoDB.calcularPreciosConIVA(pais, iva);
		
	}

	//7. ajustar precio de productos dentro de un rango
	private static void ajustaPrecios() {
		System.out.println("Introduce el precio mínimo: ");
		double precioMin = Double.parseDouble(sc.nextLine());
		System.out.println("Introduce el precio máximo: ");
		double precioMax = Double.parseDouble(sc.nextLine());
		System.out.println("introduce el porcentaje de ajuste");
		double porcentajeAjuste = Double.parseDouble(sc.nextLine());
		
		accesoDB.ajustarPrecios(precioMin, precioMax, porcentajeAjuste);
		
	}

	//6. Consultar productos coincidentes con peso introducido por usuario mayor que X
	private static void consultaProductosPesoMayorQue() {
		System.out.println("Introduce el peso mínimo para la consulta");
		double pesoMinimo = Double.parseDouble(sc.nextLine());
		ObjectSet<Producto> productos = accesoDB.consultaProductosPesoMayorQue(pesoMinimo);
		
		if(productos.isEmpty()) {
			System.out.println("No se encontraron productos con peso mayor que " + pesoMinimo + " kg");
		} else {
			System.out.println("Productos con peso mayor que " + pesoMinimo + " kg");
			System.out.println("----------------------------------------------------");
			
			// Itero sobre el ObjectSet obtenido
			while(productos.hasNext()) {
				Producto producto = productos.next();
				System.out.printf("Nombre: %s | Precio: %.2f € | Cantidad: %d unidades | Peso: %.2f kg | Tamaño: %s | EAN: %s | Rendimiento: %.2f %% \n",
						producto.getNombre(),
						producto.getPrecio(),
						producto.getCantidad(),
						producto.getPeso(),
						producto.getTamanio(),
						producto.getEAN(),
						producto.getRendimiento());
			}
			System.out.println("-----------------------------------------------------");
		}
		
	}

	//5. Eliminar producto
	private static void eliminaProducto() {
		System.out.println("EAN del producto a eliminar");
		String EAN = sc.nextLine();
		
		if (accesoDB.eliminarProducto(EAN)) {
			System.out.println("Producto " + EAN + " eliminado con éxito");
		} else {
			System.out.println("Producto no encontrado");
		}
		
	}

	//4. Modificar precio a todos los productos con precio x
	private static void modificaProductoPorPrecio() {
		System.out.println("Precio actual de los productos a modificar: ");
		double precioActual = Double.parseDouble(sc.nextLine());
		System.out.println("Nuevo precio: ");
		double nuevoPrecio = Double.parseDouble(sc.nextLine());
		
		if (accesoDB.modificarProductoPorPrecio(precioActual, nuevoPrecio)) {
			System.out.println("Producto modificado con éxito");
			
		}else {
			System.out.println("Producto no encontrado");
		}
		
	}
	
	

	//3. Modificar precio de un producto por EAN
	private static void modificaProductoPorEAN() {
		System.out.println("EAN del producto a modificar");
		String EAN = sc.nextLine();
		System.out.println("Nuevo precio");
		double nuevoPrecio = Double.parseDouble(sc.nextLine());
		
		// funcion modificarProductoPorEAN boolean que me devuelve true si se ha modificado y false si no ha sido posible
		if (accesoDB.modificarProductoPorEAN(EAN, nuevoPrecio)) {
			System.out.println("Producto modificado con éxito");
		} else {
			System.out.println("No ha habido modificación, producto no encontrado");
		}
		
	}

	//2. Consiltar productos
	private static void consultaProductos() {
		ObjectSet<Producto> productos = accesoDB.consultarProductos();
		
		if (productos.isEmpty()) {
			System.out.println("no hay productos en la base de datos");
		} else {
			System.out.println("Listado de productos:");
			System.out.println("----------------------------------");
			
			// Iterar por el ObjectSet usando hasNext() y next()
			// printf, que es printformatter (recibe %s para string, &f para float o double, %d para entero)
			while (productos.hasNext()) {
				Producto producto = productos.next();
				System.out.printf("Nombre: %s | Precio: %.2f € | Cantidad: %d unidades | Peso: %.2f kg | Tamaño: %.2f cm | EAN: %s | Rendimiento: %.2f %% \n",
						producto.getNombre(),
						producto.getPrecio(),
						producto.getCantidad(),
						producto.getPeso(),
						producto.getTamanio(),
						producto.getEAN(),
						producto.getRendimiento());
			}
			System.out.println("----------------------------------------");
		}
		
	}

	//1. Insertar un producto
	private static void insertaProducto() {

		System.out.println("Nombre del producto: ");
		String nombre = sc.nextLine();
		System.out.println("Precio: ");
		double precio = Double.parseDouble(sc.nextLine());
		System.out.println("Cantidad: ");
		int cantidad = Integer.parseInt(sc.nextLine());
		System.out.println("Peso: ");
		double peso = Double.parseDouble(sc.nextLine());
		System.out.println("Tamaño: ");
		double tamanio = Double.parseDouble(sc.nextLine());
		System.out.println("EAN: ");
		String EAN = sc.nextLine();
		System.out.println("Rendimiento de ventas: ");
		double rendimiento = Double.parseDouble(sc.nextLine());
		
		Producto producto = new Producto(nombre, precio, cantidad, peso, tamanio, EAN, rendimiento);
		accesoDB.insertarProducto(producto);
		System.out.println("Producto se ha insertado correctamente");
		
	}

}
