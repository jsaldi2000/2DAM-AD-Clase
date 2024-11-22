package dam.ad.personasDB4O.db;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import dam.ad.personasDB4O.javabeans.Person;

public class GestionPersonas {
	
	// Definir la ruta de la base de datos
	final static String BDPer = "DB4O/personas.yap";

	public static void main(String[] args) {
		
		// Crear y abrir la base de datos a través de una instancia de ObjectContainer
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDPer);
		
		// Crear objetos de tipo Person
		Person p1 = new Person("Juan", "Guadalajara");
		Person p2 = new Person("Ana", "Madrid");
		Person p3 = new Person("Luis", "Granada");
		Person p4 = new Person("Pedro", "Gijón");
		Person p5 = new Person("Juan", "Gijón");
				
		// Almacenar objetos Persona en la base de datos
		db.store(p1);
		db.store(p2);
		db.store(p3);
		db.store(p4);
		db.store(p5);
//		System.out.println("Personas almacenadas correctamente en la bbdd");
		
		// Consultar los objetos guardados (forma 1)
//		Person person = new Person(null, null);
	
//		ObjectSet<Person> result = db.queryByExample(person);
		
		// Consultar los objetos guardados (forma 2)
		ObjectSet<Person> result = db.queryByExample(Person.class);

		
		if (!result.hasNext()) {
			System.out.println("No hay objetos de personas en la base de datos");
		} else {
			System.out.println("Numero de objetos encontrados: " + result.size());
			while (result.hasNext()) {
				Person p = result.next();
				System.out.println("Nombre: " + p.getName() + " | Ciudad: " + p.getCity());
			}
			
		}
		
		// Cerrar SIEMBRE después de acceder a la bbddd
		//db.close();
		
		// Consultar personas por nombre Juan
		String nombrePersona = "Juan";
		Person p = new Person(nombrePersona, null);
		ObjectSet<Person> resultPersonasPorNombre = db.queryByExample(p);
		
		if (resultPersonasPorNombre.hasNext()) {
			System.out.println("\nPerosnas encontradas llamadas " + nombrePersona);
			System.out.println("--------------------------------");
			while (resultPersonasPorNombre.hasNext()) {
				Person personaEncontrada = resultPersonasPorNombre.next();
				System.out.println("Nombre: " + personaEncontrada.getName() + " Ciudad: " + personaEncontrada.getCity());
				
			}
			
		} else {
			System.out.println("No existen ningún " + nombrePersona + "en la base de datos");
		}
		
		// Consulta personas por ciudad Guadalajara
		String nombreCiudad = "Guadalajara";
		p = new Person (null, nombreCiudad);
		ObjectSet<Person> resultPersonasPorCiudad = db.queryByExample(p);
		
		if (resultPersonasPorCiudad.hasNext()) {
			System.out.println("\nPerosnas encontradas en la ciudad de " + nombreCiudad);
			System.out.println("--------------------------------");
			while (resultPersonasPorCiudad.hasNext()) {
				Person personaEncontrada = resultPersonasPorCiudad.next();
				System.out.println("Nombre: " + personaEncontrada.getName() + " Ciudad: " + personaEncontrada.getCity());
				
			}
			
		} else {
			System.out.println("No existen ninguna persona en " + nombreCiudad + "en la base de datos");
		}
		
		// Buscar y modificar la ciudad de Luis
		ObjectSet<Person> resultModificacion = db.queryByExample(new Person("Luis", null));
		if (!resultModificacion.hasNext()) {
			System.out.println("No se puede encontrar a Luis en la base de datos");
		} else {
			// Modificamos la ciudad
			p = resultModificacion.next();
			p.setCity("Toledo");
			db.store(p);
			
			System.out.println("\nLa ciudad de Luis ha sido modificada a Toledo");
		}
		
		
		// Consultar nuevamente para verificar la modificacion
		ObjectSet<Person> resultVerificacion = db.queryByExample(new Person("Luis", null));
		if (resultVerificacion.hasNext()) {
			Person personaLuis = resultVerificacion.next();
			System.out.println("Nombre: " + personaLuis.getName() + " | Nueva Ciudad: " + personaLuis.getCity());
		}
	
		
		// Eliminar personas con nombre "Juan"
		ObjectSet<Person> resultEliminacion = db.queryByExample(new Person("Juan", null));
		if (!resultEliminacion.hasNext()) {
			System.out.println("No se puede eliminar ningún Juan porque no existe este nombre en la base de datos");
		} else {
			System.out.println("\nPersonas a elimnar: ");
			System.out.println("------------------------------------------");
			
			while (resultEliminacion.hasNext()) {
				Person person = resultEliminacion.next();
				System.out.println("Nombre: " + person.getName() + " | Ciudad: " + person.getCity());
				//Eliminar el registro
				db.delete(person);
				
			}
			System.out.println("\nSe han eliminado todos los registros con nombre Juan de la base de datos");
		}
		
		// Cerrar SIEMBRE después de acceder a la bbddd
		db.close();
	}

}
