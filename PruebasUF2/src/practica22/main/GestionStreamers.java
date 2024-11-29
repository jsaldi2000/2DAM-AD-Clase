package practica22.main;

import java.util.Scanner;
import com.db4o.ObjectSet;
import practica22.db.AccesoStreamersDB;
import practica22.javabeans.Sesion;
import practica22.javabeans.Streamer;

public class GestionStreamers {

    private static final AccesoStreamersDB accesoDB = new AccesoStreamersDB();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String opcion = "";
        while (!opcion.equals("S")) { // Ejecutar el menú siempre que la opción no sea Salir (S).
            System.out.println("\nMenú de Gestión de Streamers:");
            System.out.println("--------------------------------------------------");
            System.out.println("IS: Insertar streamer y sesión");
            System.out.println("CT: Consultar todos los streamers");
            System.out.println("CP: Consultar streamers por plataforma");
            System.out.println("CS: Consultar streamers con más de X suscriptores");
            System.out.println("S: Salir");
            System.out.println("--------------------------------------------------");
            System.out.print("Elige una opción: ");

            opcion = sc.nextLine().toUpperCase().trim();

            switch (opcion) {
                case "IS":
                    insertaStreamerYSesion();
                    break;
                case "CT":
                    consultaTodos();
                    break;
                case "CP":
                    consultaPorPlataforma();
                    break;
                case "CS":
                    consultaConSuscriptores();
                    break;
                case "S":
                    System.out.println("Aplicación terminada");
                    break;
                default:
                    System.out.println("Opción no válida. Escoja de nuevo");
            }
        }

        accesoDB.cerrarDB();
        sc.close();
    }

    private static void insertaStreamerYSesion() {
        System.out.print("Nombre del Streamer: ");
        String nombre = sc.nextLine();

        // Busca el streamer por nombre en la base de datos
        ObjectSet<Streamer> resultados = accesoDB.buscarStreamerPorNombre(nombre);
        Streamer streamer;

        // Si el streamer no existe, solicitamos sus datos y lo creamos
        if (!resultados.hasNext()) {
            System.out.print("Plataforma: ");
            String plataforma = sc.nextLine();
            System.out.print("Categoría: ");
            String categoria = sc.nextLine();
            System.out.print("Seguidores: ");
            int seguidores = Integer.parseInt(sc.nextLine());
            System.out.print("Suscriptores: ");
            int suscriptores = Integer.parseInt(sc.nextLine());

            // Calculamos el siguiente ID
            int id = accesoDB.obtenerSiguienteId();

            // Creamos un nuevo Streamer
    		streamer = new Streamer();
    		streamer.setId(id);
    		streamer.setNombre(nombre);
    		streamer.setPlataforma(plataforma);
    		streamer.setCategoria(categoria);
    		streamer.setSeguidores(seguidores);
    		streamer.setSuscriptores(suscriptores);

            // Solicitar datos de la sesión
            System.out.print("Fecha de la sesión (yyyy-MM-dd): ");
            String fecha = sc.nextLine();
            System.out.print("Duración de la sesión (en minutos): ");
            int duracion = Integer.parseInt(sc.nextLine());
            System.out.print("Espectadores en la sesión: ");
            int espectadores = Integer.parseInt(sc.nextLine());

            // Crear y agregar la sesión
            Sesion sesion = new Sesion(fecha, duracion, espectadores);
			streamer.getSesiones().add(sesion);

            // Almacenar el streamer con la sesión
            accesoDB.insertarStreamer(streamer);
            System.out.println("Streamer insertado correctamente con una sesión.");
        } else {
            // Si ya existe, obtenemos el streamer existente
            streamer = resultados.next();
            System.out.println("El streamer ya existe. Se agregará una nueva sesión.");

            // Solicitar datos de la sesión
            System.out.print("Fecha de la sesión (yyyy-MM-dd): ");
            String fecha = sc.nextLine();
            System.out.print("Duración de la sesión (en minutos): ");
            int duracion = Integer.parseInt(sc.nextLine());
            System.out.print("Espectadores en la sesión: ");
            int espectadores = Integer.parseInt(sc.nextLine());

            // Crear la sesión
            Sesion sesion = new Sesion(fecha, duracion, espectadores);

            // Agregar la sesión al streamer existente
            if (accesoDB.agregarSesion(nombre, sesion)) {
                System.out.println("Sesión añadida correctamente.");
            } else {
                System.out.println("Error al agregar la sesión.");
            }
        }
    }

    private static void consultaTodos() {
        System.out.println("Todos los streamers:");
        ObjectSet<Streamer> streamers = accesoDB.consultarStreamers();

        if (!streamers.hasNext()) {
            System.out.println("No se encontraron streamers.");
        } else {
            while (streamers.hasNext()) {
                Streamer streamer = streamers.next();
                System.out.printf("ID: %d | Nombre: %s | Plataforma: %s | Categoría: %s | Seguidores: %d | Suscriptores: %d%n",
                        streamer.getId(),
                        streamer.getNombre(),
                        streamer.getPlataforma(),
                        streamer.getCategoria(),
                        streamer.getSeguidores(),
                        streamer.getSuscriptores());

                if (streamer.getSesiones().isEmpty()) {
                    System.out.println("  - Este streamer no tiene sesiones asociadas -");
                } else {
                    for (Sesion sesion : streamer.getSesiones()) {
                        System.out.printf("    - Fecha: %s | Duración: %d minutos | Espectadores: %d%n",
                                sesion.getFecha(),
                                sesion.getDuracion(),
                                sesion.getEspectadores());
                    }
                }
            }
            System.out.println("----------------------------------------------------");
        }
    }


    private static void consultaPorPlataforma() {
        System.out.print("Plataforma: ");
        String plataforma = sc.nextLine();
        ObjectSet<Streamer> streamers = accesoDB.consultarPorPlataforma(plataforma);

        if (!streamers.hasNext()) {
            System.out.println("No se encontraron streamers.");
        } else {
            while (streamers.hasNext()) {
                Streamer streamer = streamers.next();
                System.out.printf("ID: %d | Nombre: %s | Plataforma: %s | Categoría: %s | Seguidores: %d | Suscriptores: %d%n",
                        streamer.getId(),
                        streamer.getNombre(),
                        streamer.getPlataforma(),
                        streamer.getCategoria(),
                        streamer.getSeguidores(),
                        streamer.getSuscriptores());

                if (streamer.getSesiones().isEmpty()) {
                    System.out.println("  - Este streamer no tiene sesiones asociadas -");
                } else {
                    for (Sesion sesion : streamer.getSesiones()) {
                        System.out.printf("    - Fecha: %s | Duración: %d minutos | Espectadores: %d%n",
                                sesion.getFecha(),
                                sesion.getDuracion(),
                                sesion.getEspectadores());
                    }
                }
            }
            System.out.println("----------------------------------------------------");
        }
    }

    private static void consultaConSuscriptores() {
        System.out.print("Número mínimo de suscriptores: ");
        int minSuscriptores = Integer.parseInt(sc.nextLine());
        ObjectSet<Streamer> streamers = accesoDB.consultarConSuscriptores(minSuscriptores);

        if (!streamers.hasNext()) {
            System.out.println("No se encontraron streamers.");
        } else {
            while (streamers.hasNext()) {
                Streamer streamer = streamers.next();
                System.out.printf("ID: %d | Nombre: %s | Plataforma: %s | Categoría: %s | Seguidores: %d | Suscriptores: %d%n",
                        streamer.getId(),
                        streamer.getNombre(),
                        streamer.getPlataforma(),
                        streamer.getCategoria(),
                        streamer.getSeguidores(),
                        streamer.getSuscriptores());

                if (streamer.getSesiones().isEmpty()) {
                    System.out.println("  - Este streamer no tiene sesiones asociadas -");
                } else {
                    for (Sesion sesion : streamer.getSesiones()) {
                        System.out.printf("    - Fecha: %s | Duración: %d minutos | Espectadores: %d%n",
                                sesion.getFecha(),
                                sesion.getDuracion(),
                                sesion.getEspectadores());
                    }
                }
            }
            System.out.println("----------------------------------------------------");
        }
    }
        

}
