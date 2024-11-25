package practica22.db;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import practica22.javabeans.Sesion;
import practica22.javabeans.Streamer;

public class AccesoStreamersDB {
	
	private ObjectContainer db;
	
	// Constructor que inicializa la conexión
	public AccesoStreamersDB() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"DB4O/streamers.yap");
	}
	
	// Método para cerrar la conexión con la bbdd
	public void cerrarDB() {
		db.close();
	}

	// Método para buscar un Streamer por su nombre
	public ObjectSet<Streamer> buscarStreamerPorNombre(String nombre) {
		
		Streamer ejemplo = new Streamer();
		ejemplo.setNombre(nombre);
		return db.queryByExample(ejemplo);
	}

	// Método para calcular el siguiente ID
	public int obtenerSiguienteId() {
		ObjectSet<Streamer> resultados = db.query(Streamer.class);
		return resultados.size() + 1;
	}

	// Insertar streamer en la bbdd
	public void insertarStreamer(Streamer streamer) {
		db.store(streamer);
		db.commit();
	}

	// Método para agregar una sesión a un streamer existente
	public boolean agregarSesion(String nombre, Sesion sesion) {
		// Buscar streamer por nombre
		ObjectSet<Streamer> resultados = buscarStreamerPorNombre(nombre);
		
		// Verificar resultados y si ya existe
		if (resultados.hasNext()) {
			Streamer streamer = resultados.next();
			
			// Agregamos la nueva sesión al streamer encontrado
			streamer.agregarSesion(sesion);
			
			//Actualizar el streamer con la nueva sesión
			db.store(streamer);
			db.commit();
			return true;
		}
		return false;
	}
	

	
	
}
