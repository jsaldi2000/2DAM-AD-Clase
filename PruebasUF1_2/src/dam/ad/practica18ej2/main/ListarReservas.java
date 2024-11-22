package dam.ad.practica18ej2.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import dam.ad.practica18ej2.javabeans.ListaReservas;
import dam.ad.practica18ej2.javabeans.Reserva;

public class ListarReservas {

	private static final String FILE_XML = "Ficheros/reservas.xml";

	public static void main(String[] args) {

		try {
			XStream xstream = new XStream();

			// Configuración de permisos de seguridad
			xstream.addPermission(NoTypePermission.NONE);
			xstream.addPermission(NullPermission.NULL);
			xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);

			xstream.allowTypes(new Class[] { Reserva.class, ListaReservas.class });
			xstream.allowTypesByWildcard(new String[] { "dam.ad.practica18.*" });

			// Configuración de alias
			xstream.alias("Reservas", ListaReservas.class);
			xstream.alias("reserva", Reserva.class);

			xstream.addImplicitCollection(ListaReservas.class, "reservas");

			// Leer el archivo XML
			ListaReservas listaReservas;
			try (FileInputStream fis = new FileInputStream(FILE_XML)) {
				listaReservas = (ListaReservas) xstream.fromXML(fis);

				// Imprimir reservas en consola
				System.out.println("\n--- Listado de Reservas ---");
				for (Reserva reserva : listaReservas.getReservas()) {
					System.out.println("ID: " + reserva.getId());
					System.out.println("Cliente: " + reserva.getCliente());
					System.out.println("Origen: " + reserva.getOrigen());
					System.out.println("Destino: " + reserva.getDestino());
					System.out.println("Fecha de Vuelo: " + reserva.getFechaVuelo());
					System.out.println("-------------------------");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivo XML no encontrado: " + FILE_XML);
		} catch (IOException e) {
			System.out.println("Error al cerrar el archivo XML: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error al leer el archivo XML: " + e.getMessage());
		}
	}
}
