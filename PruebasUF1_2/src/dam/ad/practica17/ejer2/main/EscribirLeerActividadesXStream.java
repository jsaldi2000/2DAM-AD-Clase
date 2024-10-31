package dam.ad.practica17.ejer2.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import dam.ad.practica17.ejer2.javabeans.Actividad;
import dam.ad.practica17.ejer2.javabeans.ListaActividades;

public class EscribirLeerActividadesXStream {

	// Inicializar la lista de tipo ListaObjetos, para las actividades
    private static ListaActividades listaActividades = new ListaActividades();
    private static int id = 1; // Para autoincrementar el ID basado en la cantidad de actividades

    public static void main(String[] args) {
    	
        // dejar para el final una vez tenga todas las opciones
    	cargarActividadesDesdeXML(); // Cargar actividades existentes al inicio
        
    	// empezar aquí
        Scanner scanner = new Scanner(System.in);
        String opcion = "";

        // Mientras la opción no sea "3", sigue ejecutando el ciclo
        while (!opcion.equals("3")) {
            System.out.println("\n--- Seleccione una opción: ---");
            System.out.println("1. Registrar nueva actividad");
            System.out.println("2. Mostrar XML generado en consola");
            System.out.println("3. Salir");

            opcion = scanner.nextLine(); // Leer opción del usuario

            switch (opcion) {
                case "1":
                    registrarActividad(scanner); // Registrar y generar automáticamente la actividad en el XML
                    break;
                case "2":
                    mostrarXML(); // Mostrar el XML en consola
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Opción no válida. Escoja opción de nuevo.");
            }
        }

        scanner.close(); // Cerrar el scanner al final
        System.out.println("Aplicación terminada");
    }

    // Opción 1: Registrar nueva actividad y generar automáticamente el XML
    private static void registrarActividad(Scanner scanner) {
        System.out.print("Ingrese el nombre de la actividad: ");
        String nombreActividad = scanner.nextLine();

        System.out.print("Ingrese la zona del parque: ");
        String zona = scanner.nextLine();

        System.out.print("Ingrese la duración de la actividad (en minutos): ");
        int duracion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        // Crear la actividad con ID autoincrementado basado en la cantidad de actividades
        Actividad nuevaActividad = new Actividad(id++, nombreActividad, zona, duracion);
        listaActividades.addActividad(nuevaActividad);
        System.out.println("Actividad registrada con éxito.");

        // Generar el archivo XML después de recibir la nueva actividad
        generarXML();
    }

    // Opción 2: Mostrar contenido del archivo XML en consola
    private static void mostrarXML() {
        try {
            XStream xstream = new XStream();

            // Configurar permisos de seguridad
            xstream.addPermission(NoTypePermission.NONE);
            xstream.addPermission(NullPermission.NULL);
            xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);

            Class[] clasesPermitidas = { ListaActividades.class, Actividad.class };
            xstream.allowTypes(clasesPermitidas);
            xstream.allowTypesByWildcard(new String[] { "dam.ad.practica17.*" });

            // Establecer los alias correctos
            xstream.alias("Actividades", ListaActividades.class);
            xstream.alias("actividad", Actividad.class);

            xstream.addImplicitCollection(ListaActividades.class, "listaActividades");

            try (FileInputStream fis = new FileInputStream("Ficheros/actividades.xml")) {
                ListaActividades listaLeida = (ListaActividades) xstream.fromXML(fis);

                // Mostrar el contenido deserializado
                System.out.println("\n--- Actividades del Parque ---");
                for (Actividad actividad : listaLeida.getListaActividades()) {
                    System.out.println("ID: " + actividad.getId());
                    System.out.println("Nombre: " + actividad.getNombreActividad());
                    System.out.println("Zona: " + actividad.getZona());
                    System.out.println("Duración: " + actividad.getDuracion() + " minutos");
                    System.out.println("-------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("El archivo no tiene contenido");
        }
    }

    // Método para generar el archivo XML
    private static void generarXML() {
        try {
            XStream xstream = new XStream();

            // Configurar permisos de seguridad
            xstream.addPermission(NoTypePermission.NONE);
            xstream.addPermission(NullPermission.NULL);
            xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);

			// Especificar las clases permitidas
            Class[] clasesPermitidas = { ListaActividades.class, Actividad.class };
            xstream.allowTypes(clasesPermitidas);

			// Permitir cualquier tipo procedente del mismo paquete
            xstream.allowTypesByWildcard(new String[] { "dam.ad.practica17.*" });

            // Establecer los alias correctos
            xstream.alias("Actividades", ListaActividades.class);
            xstream.alias("actividad", Actividad.class);

			// Para que no aparezca el atributo lista de la clase ListaActividades en el XML utilizamos el método addImplicitCollection();
            xstream.addImplicitCollection(ListaActividades.class, "listaActividades");

            try (FileOutputStream fos = new FileOutputStream("Ficheros/actividades.xml")) {
                xstream.toXML(listaActividades, fos);
                System.out.println("Archivo XML 'actividades.xml' actualizado con éxito.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para cargar las actividades del archivo XML al iniciar el programa
    private static void cargarActividadesDesdeXML() {
        File archivo = new File("Ficheros/actividades.xml");
        if (archivo.exists()) {
            try {
                XStream xstream = new XStream();

                // Configurar permisos de seguridad
                xstream.addPermission(NoTypePermission.NONE);
                xstream.addPermission(NullPermission.NULL);
                xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);

    			// Especificar las clases permitidas
                Class[] clasesPermitidas = { ListaActividades.class, Actividad.class };
                xstream.allowTypes(clasesPermitidas);
                
    			// Permitir cualquier tipo procedente del mismo paquete
                xstream.allowTypesByWildcard(new String[] { "dam.ad.practica17.*" });

                // Establecer los alias correctos
                xstream.alias("Actividades", ListaActividades.class);
                xstream.alias("actividad", Actividad.class);

    			// Para que no aparezca el atributo lista de la clase ListaActividades en el XML utilizamos el método addImplicitCollection();
                xstream.addImplicitCollection(ListaActividades.class, "listaActividades");

                // Leer las actividades existentes del archivo XML
                try (FileInputStream fis = new FileInputStream(archivo)) {
                    listaActividades = (ListaActividades) xstream.fromXML(fis);

                    // Establecer el id basado en el tamaño de la lista, hay que tener en cuenta que el tamaño real es tamaño + 1 ya que size cuenta 0
                    id = listaActividades.getListaActividades().size() + 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


