package dam.ad.uf1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class EscribirFicheroBinarioAleatorio {

    public static void main(String[] args) {
        // Ruta del fichero binario
        String rutaArchivo = "Ficheros/ejemplo.bin";

        try (RandomAccessFile archivo = new RandomAccessFile(rutaArchivo, "rw")) {

            // Borra el archivo para comenzar de nuevo
            archivo.setLength(0);
            
            // Se posiciona al final del archivo, que coincide igualmente con el principio
            archivo.seek(archivo.length());

            // Escribir una cadena como UTF-8 usando writeChar() caracter a caracter
            String textoSimple = "Hello World de forma aleatoria";
            for (int i = 0; i < textoSimple.length(); i++) {
            	archivo.writeChar(textoSimple.charAt(i));
			}
            
            // Escribir un texto completo como UTF-8
            String textoCompleto = "Este es un texto adicional escrito en binario";
            archivo.writeUTF(textoCompleto);  // Escribe la cadena en UTF-8

            // Escribir un valor entero
            archivo.writeInt(12345);  // Escribe 4 bytes

            // Escribir un valor booleano
            archivo.writeBoolean(true);  // Escribe 1 byte

            // Escribir un carácter
            archivo.writeChar('A');  // Escribe 2 bytes

            // Escribir un número de punto flotante (double)
            archivo.writeDouble(3.14159);  // Escribe 8 bytes

            System.out.println("Datos escritos exitosamente en el archivo binario.");
            
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo.");
            e.printStackTrace();
        }
    }
}