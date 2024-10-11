package dam.ad.uf1.practica7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class InsertPeliculasTerrorAlea {

	// Declaro las variables para guardar los tamaños
	static final int TAM_TITULO = 35;
	static final int TAM_DIRECTOR = 35;
	static final int TAM_SINOPSIS = 1000;

	// Declaro la variable para el tamaño total del registro (id, el título, el año, la duración, el director y la sinopsis)
	static final int TAM_REG = 4 + TAM_TITULO * 2 + 4 + 4 + TAM_DIRECTOR * 2 + TAM_SINOPSIS * 2;

	public static void main(String[] args) {

		// leer el fichero pelis_terror.txt y generar un fichero binario de acceso aleatorio PelisTerror.dat (a la vez)
		try (BufferedReader br = new BufferedReader(new FileReader("Ficheros/pelis_terror.txt"));
				RandomAccessFile raf = new RandomAccessFile("Ficheros/PelisTerror.dat", "rw");) {

			// Creo variable string para almacenar cada línea del fichero de texto
			String linea;
			
			// Creo array de Strings para almacenar los datos de cada línea
			String[] datos;

			// Leemos el último id del archivo binario, si está vacío tomará el id=1
			int id = 1;
			if (raf.length() > 0) {
				raf.seek(raf.length() - TAM_REG);
				id = raf.readInt() + 1;
			}

			// Me posiciono al final del archivo para continuar escribiendo registros
			raf.seek(raf.length());

			// Creo el StringBuffer para poder fijar más tarde los tamaños con espacios
			StringBuffer sb;
			
			// Recorro cada línea, hasta que no haya más datos y voy escribiendo en el archivo binario
			while ((linea = br.readLine()) != null) {
				datos = linea.split("-");

				// escritura del id
				raf.writeInt(id);
				id++;

				// escritura de título
				sb = new StringBuffer(datos[0]);
				sb.setLength(TAM_TITULO);
				raf.writeChars(sb.toString());

				// escritura del año, debo pasarlo a entero ya que es un String
				raf.writeInt(Integer.parseInt(datos[1]));

				// escritura de la duración
				raf.writeInt(Integer.parseInt(datos[2]));

				// escritura del director
				sb = new StringBuffer(datos[3]);
				sb.setLength(TAM_DIRECTOR);
				raf.writeChars(sb.toString());

				// escritura de la sinopsis
				sb = new StringBuffer(datos[4]);
				sb.setLength(TAM_SINOPSIS);
				raf.writeChars(sb.toString());

			}

			// lectura de comprobación, pasando el objeto raf para poder leer //raf es una instancia de RandomAccessFile
			leerFicheroBin(raf);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void leerFicheroBin(RandomAccessFile raf) throws IOException {
		
		// Me posiciono al inicio del fichero
		raf.seek(0);

		// Declaración de variables para la lectura
		int id;
		
		String titulo;
		char[] cTitulo = new char[TAM_TITULO]; //array de caracteres de tamaño = tamaño del título
		
		int anio;
		
		int duracion;
		
		String director;
		char[] cDirector = new char[TAM_DIRECTOR]; //array de caracteres de tamaño = tamaño del director

		String sinopsis;
		char[] cSinopsis = new char[TAM_SINOPSIS]; //array de caracteres de tamaño = tamaño del sinopsis


		// Siempre que el puntero esté antes que el final del fichero, sigo leyendo
		while (raf.getFilePointer() < raf.length()) {
			
			// leo el id
			id = raf.readInt();

			// leo el título caracter a caracter hasta 35 bytes
			for (int i = 0; i < cTitulo.length; i++) {
				cTitulo[i] = raf.readChar();
			}
			titulo = new String(cTitulo); // convierto la cadena de caracteres leída a String

			// leo el año
			anio = raf.readInt();
			
			// leo la duración
			duracion = raf.readInt();

			// leo el director caracter a caracter hasta 35 bytes
			for (int i = 0; i < cDirector.length; i++) {
				cDirector[i] = raf.readChar();
			}
			director = new String(cDirector); // convierto la cadena de caracteres leída a String

			// leo la sinopsis caracter a caracter hasta 1000 bytes
			for (int i = 0; i < cSinopsis.length; i++) {
				cSinopsis[i] = raf.readChar();
			}
			sinopsis = new String(cSinopsis); // convierto la cadena de caracteres leída a String

			// Muestro el contenido de cada registro por consola, quitando los espacios en blanco con trim()
			System.out.println(id + ": " + "Título: " + titulo.trim() + "; Director: " + director.trim() + " (" + anio
					+ ")" + "; Duración: " + duracion + " min \n" + "Sinopsis: " + sinopsis.trim());

		}

	}

}
