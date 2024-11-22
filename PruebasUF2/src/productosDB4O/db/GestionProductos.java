package productosDB4O.db;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import productosDB4O.javabeans.Producto;

public class GestionProductos {

	public static void main(String[] args) {
		// Crear e inicializar la base de datos
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "DB4O/productos.yap");
		
        // Crear array de productos
        Producto[] productos = {
                new Producto("Televisor", 299.99, 30, 8.5, "48 pulgadas", "9876543210987", 40),
                new Producto("Licuadora", 89.99, 20, 3.2, "15x15x40cm", "1234567890124", 30),
                new Producto("Smartphone", 499.99, 50, 0.2, "6 pulgadas", "1928374650123", 30),
                new Producto("Horno Microondas", 150.00, 25, 12.0, "45x30x35cm", "5647382910567", 25),
                new Producto("Tostadora", 79.99, 35, 3.3, "15x15x40cm", "8765467890124", 30)
            };
        
        // Almacenar cada producto en la base de datos
        for (Producto producto : productos) {
        	db.store(producto);
        }

        // Defino un ObjectSet
        //ObjectSet<Producto> resultados = db.queryByExample(new Producto(null,0,0,0, null, null, 0));
        ObjectSet<Producto> resultados = db.queryByExample(new Producto());
        
        // Consultar todos los resultados
        System.out.println("TODOS LOS RESULTADOS DE LA BASE DE DATOS");
        System.out.println("---------------------------------------------");
        for (Producto producto : resultados) {
        	System.out.println(producto);
        }
        
        // modificar un producto
        resultados = db.queryByExample(new Producto("Televisor", 0, 0, 0, null, null, 0));
        if (!resultados.isEmpty()) {
        	Producto productoModificar =  resultados.next();
        	productoModificar.setPrecio(349.99);
        	db.store(productoModificar);
			
		}
        
        // Consultar todos los producto
        resultados = db.queryByExample(new Producto());
        System.out.println("\nPRODUCTOS RESTANTES DESPUÉS DE LA MODIFICACIÓN");
        System.out.println("---------------------------------------------");
        for (Producto producto : resultados) {
        	System.out.println(producto);
        }
        
        // Eliminar la licuadora
        resultados = db.queryByExample(new Producto("Licuadora", 0,0,0,null,null,0));
        if (!resultados.isEmpty()) {
        	Producto productoEliminar = resultados.next();
        	db.delete(productoEliminar);
        	
        }
              
        // Consultar después de la eliminación
        resultados = db.queryByExample(new Producto());
        System.out.println("\nPRODUCTOS DESPUÉS DE LA ELIMINACIÓN");
        System.out.println("---------------------------------------------");

        for (Producto producto : resultados) {
        	System.out.println(producto);
        }
        
        // CONSULTAS AVANZADAS
        // Query para encontrar los prductos con precio mayor que 349
        Query query = db.query();
        query.constrain(Producto.class);
        query.descend("precio").constrain(349.00).greater();
        
        System.out.println("\nPRODUCTOS RESULTANTES QUERY MAYOR QUE 349");
        System.out.println("---------------------------------------------");
        ObjectSet<Producto> resultadoQuery = query.execute();
        for (Producto p : resultadoQuery) {
        	System.out.println(p);
        }
        
        // Query para encontrar los prductos con precio menor que 349
        query = db.query();
        query.constrain(Producto.class);
        query.descend("precio").constrain(349.00).smaller();
        
        System.out.println("\nPRODUCTOS RESULTANTES QUERY MENOR QUE 349");
        System.out.println("---------------------------------------------");
        resultadoQuery = query.execute();
        for (Producto p : resultadoQuery) {
        	System.out.println(p);
        }
        
        //Cerra la base de datos
        db.close();
        
	}

}
