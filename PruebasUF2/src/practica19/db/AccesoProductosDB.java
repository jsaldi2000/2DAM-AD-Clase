package practica19.db;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import practica19.javabeans.Producto;

public class AccesoProductosDB {

	// Variable de tipo ObjectContainer, necesaria para crear y mantener la conexión a la bbdd
	private ObjectContainer db = null;
	
	// Crear e inicilizar el archivo de bbdd. Si ya existe previamente simplemente lo estamos inicializando
	public AccesoProductosDB() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "DB4O/P19_productos.yap");
	}
	
	// Método para cerrar la conexión a la bbddd
	public void cerrarDB() {
		db.close();
	}

	// Método para insertar un nuevo producto en la base de datos
	public void insertarProducto(Producto producto) {
		db.store(producto);
		db.commit();
		
	}

	// Método para consultar todos los productos almacenados en la base de datos
	public ObjectSet<Producto> consultarProductos() {
		return db.queryByExample(Producto.class);
	}

	// Método para modificar el precio a un producto específico por EAN
	public boolean modificarProductoPorEAN(String EAN, double nuevoPrecio) {

		// Crear un producto genérico con el EAN cuyo precio se quiere cambiar
		Producto ejemploProductoPorEAN = new Producto();
		ejemploProductoPorEAN.setEAN(EAN);
		
		ObjectSet<Producto> resultado = db.queryByExample(ejemploProductoPorEAN);
		if(!resultado.isEmpty()) {
			Producto p = resultado.next();
			p.setPrecio(nuevoPrecio);
			db.store(p);
			db.commit();
			return true;
		}

		return false;
	}

	// Método para modificar el precio de un producto buscando por precio específico
	public boolean modificarProductoPorPrecio(double precioActual, double nuevoPrecio) {


		// Creo un producto genérico cuyo precio se quiere cambiar
		Producto ejemploProductoPorPrecio = new Producto();
		ejemploProductoPorPrecio.setPrecio(precioActual);
		ObjectSet<Producto> resultado = db.queryByExample(ejemploProductoPorPrecio);
		
		// Verificamos si hay resulstados, para poder cambiar al nuevo precio
		if (!resultado.isEmpty()) {
			
			while (resultado.hasNext()) {
				Producto p = resultado.next();
				p.setPrecio(nuevoPrecio);
				db.store(p);
			}
			db.commit();
			return true;
		}
		return false;
	}

	// Método para eliminar el producto seleccionado por EAN
	public boolean eliminarProducto(String EAN) {
		Producto productoPorEan = new Producto();
		productoPorEan.setEAN(EAN);
		ObjectSet<Producto> resultado = db.queryByExample(productoPorEan);
		
		if (!resultado.isEmpty()) {
			Producto p = resultado.next();
			db.delete(p);
			db.commit();
			return true;
		}
		return false;
	}

	// Método para consultar prductos por peso mayor que X
	public ObjectSet<Producto> consultaProductosPesoMayorQue(double pesoMinimo) {

		Query query = db.query();
		query.constrain(Producto.class); // Restrinjo a objectos de la clase Producto
		query.descend("peso").constrain(pesoMinimo).greater();
		
		// Retorno el ObjectSet devuelto por la query
		return query.execute();
	}

	// Método para ajustar el precio de productos dentro de un rango
	public void ajustarPrecios(double precioMin, double precioMax, double porcentajeAjuste) {


		// Creo una query
		Query query = db.query();
		query.constrain(Producto.class);
		query.descend("precio").constrain(precioMin).greater();
		query.descend("precio").constrain(precioMax).smaller();
		
		// Obtener ObjectSet
		ObjectSet<Producto> productos = query.execute();
		
		// Verificar si hay productos que cumplen con la consulta
		if (!productos.hasNext()) {
			System.err.println("No se encontraron productos en el rango de precios");
			return;
		}
		
		// Ajusto los precios con el nuevo ajuste de porcentaje
		for (Producto producto : productos) {
			double nuevoPrecio = producto.getPrecio() * (1 + porcentajeAjuste /100);
			producto.setPrecio(nuevoPrecio);
			db.store(producto);
			System.out.printf("Producto actualizado: %s, Nuevo Precio: %.2f € \n", producto.getNombre(), nuevoPrecio);
		}
		db.commit();
		
	}

	// Nétodo para calcula precios con IVA por país
	public void calcularPreciosConIVA(String pais, double iva) {
		
		ObjectSet<Producto> productos = db.queryByExample(Producto.class);
		
		if (!productos.hasNext()) {
			System.out.println("No hay productos en la bbdd");
			return;
		}
		
		System.out.printf("Precios con IVA para %s (IVA: %.2f%%", pais, iva);
		System.out.printf("------------------------------------------------------");
		
		for (Producto producto : productos) {
			double precionConIVA = producto.getPrecio() * (1 + iva / 100);
			System.out.printf("Producto %s | Precio sin IVA: %.2f € | Precio con IVA: %.2f @ \n",
					producto.getNombre(),
					producto.getPrecio(),
					precionConIVA);
		}
		
		System.out.printf("------------------------------------------------------");
		
	}
	
}
