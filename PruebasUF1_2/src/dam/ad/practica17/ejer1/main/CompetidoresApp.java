package dam.ad.practica17.ejer1.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CompetidoresApp {

    // Definimos el tamaño de cada campo en el registro
    static final int TAM_NOMBRE = 30; // caracteres para el nombre
    static final int TAM_VEHICULO = 20; // caracteres para el modelo del vehículo

    // Cada registro tiene un tamaño fijo en bytes: 4 + 60 + 40 + 8 = 112 bytes
    static final int TAM_REG = 112;

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("Ficheros/competidores.dat", "rw")) {

            // Escribir competidores
            escribirDesdeArchivoTexto(raf);

            // Leer y mostrar los competidores almacenados
            leer(raf);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para escribir competidores en el archivo binario desde el archivo de texto
    private static void escribirDesdeArchivoTexto(RandomAccessFile raf) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("Ficheros/competidores.txt"))) {
            
            // Leer el último ID registrado (si existe)
            int id = 0;
            if (raf.length() > 0) {
                raf.seek(raf.length() - TAM_REG); // Posicionarse en el último registro
                id = raf.readInt(); // Leer el último ID
            }

            raf.seek(raf.length()); // Posicionarse al final del archivo para escribir
            String linea;

            while ((linea = br.readLine()) != null) {
                id++; // Incrementar el ID único
                raf.writeInt(id); // Escribir el ID en el archivo

                // Dividir la línea en partes usando el punto y coma como separador
                String[] datos = linea.split(";");
                String nombre = datos[0].trim();
                String vehiculo = datos[1].trim();
                double tiempo = Double.parseDouble(datos[2].trim());

                // Escribir el nombre, ajustando su longitud
                StringBuffer sbNombre = new StringBuffer(nombre);
                sbNombre.setLength(TAM_NOMBRE);
                raf.writeChars(sbNombre.toString());

                // Escribir el vehículo, ajustando su longitud
                StringBuffer sbVehiculo = new StringBuffer(vehiculo);
                sbVehiculo.setLength(TAM_VEHICULO);
                raf.writeChars(sbVehiculo.toString());

                // Escribir el tiempo total
                raf.writeDouble(tiempo);
            }
            System.out.println("Archivo binario competidores.dat creado con éxito a partir del archivo de texto.");
        }
    }

    // Método para leer los competidores del archivo binario
    private static void leer(RandomAccessFile raf) throws IOException {
        raf.seek(0); // Volver al principio del archivo
        int id;
        double tiempo;
        String nombre, vehiculo;
        char[] cNombre = new char[TAM_NOMBRE];
        char[] cVehiculo = new char[TAM_VEHICULO];

        // Leer hasta llegar al final del archivo
        while (raf.getFilePointer() < raf.length()) {
            id = raf.readInt(); // Leer el ID

            // Leer y reconstruir el nombre
            for (int i = 0; i < cNombre.length; i++) {
                cNombre[i] = raf.readChar();
            }
            nombre = new String(cNombre).trim();

            // Leer y reconstruir el modelo del vehículo
            for (int i = 0; i < cVehiculo.length; i++) {
                cVehiculo[i] = raf.readChar();
            }
            vehiculo = new String(cVehiculo).trim();

            // Leer el tiempo total
            tiempo = raf.readDouble();

            // Mostrar los datos leídos
            System.out.println(id + " - " + nombre + " - " + vehiculo + " - " + tiempo + " minutos");
        }
    }
}


