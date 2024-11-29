package practica22.db;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import practica22.javabeans.Sesion;
import practica22.javabeans.Streamer;

public class AccesoStreamersDB {

    // Objeto que maneja la conexión con la base de datos DB4O
    private ObjectContainer db;

    // Constructor que inicializa la conexión con la base de datos
    public AccesoStreamersDB() {
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "DB4O/streamers.yap");
    }

    // Método para cerrar la conexión con la base de datos
    public void cerrarDB() {
        db.close();
    }

    // Método para insertar o actualizar un streamer en la base de datos
    public void insertarStreamer(Streamer streamer) {
        db.store(streamer);
        db.commit();
    }

    // Método para buscar streamers por nombre (case insensitive)
    public ObjectSet<Streamer> buscarStreamerPorNombre(String nombre) {
        Streamer ejemplo = new Streamer();
        ejemplo.setNombre(nombre.toLowerCase()); // Normalizar a minúsculas
        return db.queryByExample(ejemplo);
    }

    // Método para obtener el siguiente ID basado en el tamaño actual de la base de datos
    public int obtenerSiguienteId() {
        ObjectSet<Streamer> resultados = db.query(Streamer.class);
        return resultados.size() + 1;
    }

    // Método para agregar una sesión a un streamer existente
    public boolean agregarSesion(String nombreStreamer, Sesion sesion) {
        ObjectSet<Streamer> resultados = buscarStreamerPorNombre(nombreStreamer);
        if (resultados.hasNext()) {
            Streamer streamer = resultados.next();
            
            streamer.getSesiones().add(sesion);
            db.store(streamer);
            db.commit();
            return true;
        }
        return false;
    }

    // Método para consultar todos los streamers almacenados en la base de datos
    public ObjectSet<Streamer> consultarStreamers() {
        return db.query(Streamer.class);
    }

    // Método para consultar streamers por plataforma
    public ObjectSet<Streamer> consultarPorPlataforma(String plataforma) {
        Streamer ejemplo = new Streamer();
        ejemplo.setPlataforma(plataforma);
        return db.queryByExample(ejemplo);
    }

    // Método para consultar streamers con más de X suscriptores
    public ObjectSet<Streamer> consultarConSuscriptores(int minSuscriptores) {
        Query query = db.query();
        query.constrain(Streamer.class);
        query.descend("suscriptores").constrain(minSuscriptores).greater();
        return query.execute();
    }
}
