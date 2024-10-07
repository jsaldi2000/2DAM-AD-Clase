package dam.ad.uf1.practica5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class InsertLibrosAlea {

	// Definimos tamaños de 	los registros
	static final int TAM_TITULO = 30;
	static final int TAM_AUTOR = 20;
	
	// Cada registro completo tiene un tamaño en bytes de
	// 1 int * 4 bytes/int + 30 car * 2 bytes/car + 20 car * 2 bytes/car + 1 int * 4 bytes/int + 1 int * 4 bytes/int = 112 bytes
	static final int TAM_REG = 112;
	
	public static void main(String[] args) {
		try (RandomAccessFile raf = new RandomAccessFile("Ficheros/libros.dat", "rw")) {
			
			// Escribir libros
			escribir(raf);
			
			// Leer y mostrar los libros almacenados
			leer(raf);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	//Método para escribir los libros en el archivo binario de forma aleatoria
	private static void escribir(RandomAccessFile raf) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		// Leer el último ID registrado (si existe)
		int id = 0;
		if (raf.length() > 0) {
			raf.seek(raf.length() - TAM_REG); // Me sitúo justo antes del último registro, es decir justo antes de su ID
			id = raf.readInt(); // Leemos el último ID
			
		}
			
		raf.seek(raf.length()); // Posicionamos el puntero al final del archivo para comenza a escribir;
		boolean continuar = true;
		String titulo, autor;
		int anio, numPag;
		
		while (continuar) {
			id++; // Incrementamos el ID
			raf.writeInt(id); // Escribo el id del siguiente registro
			
			// Pedir por consola el título y guardarlo en el archvo
			System.out.println("Introduce el título del libro:");
			titulo = sc.nextLine();
			StringBuffer sbTitulo = new StringBuffer(titulo);
			sbTitulo.setLength(TAM_TITULO); // Ajustamos el tamaño máximo y FIJO para el título
			raf.writeChars(sbTitulo.toString());
						
			// Pedir por consola el autor y guardarlo en el archvo
			System.out.println("Introduce el nombre del autor:");
			autor = sc.nextLine();
			StringBuffer sbAutor = new StringBuffer(autor);
			sbAutor.setLength(TAM_AUTOR); // Ajustamos el tamaño máximo y FIJO para el autor
			raf.writeChars(sbAutor.toString());
			
            // Pedir y escribir el año de edición
			System.out.println("Introduce el año de edición:");
			anio = sc.nextInt();
			raf.writeInt(anio);

            // Pedir y escribir el número de páginas
			System.out.println("Introduce el nº de páginas:");
			numPag = sc.nextInt();
			raf.writeInt(numPag);
			
			sc.nextLine(); // Limpiamos el buffer
			
			// Presuntar al usuario si desea continuar
			System.out.println("¿Desea continuar introduciendo otro libro? (SI/NO)");
			continuar = sc.nextLine().equalsIgnoreCase("SI");
			
		}
		
		sc.close();
	}
	
	
	// Método para leer los libros del binario
	private static void leer(RandomAccessFile raf) throws IOException {
		
		raf.seek(0); // Volver al principio del archivo
		
		int id, anioEd, numPag;
		String titulo, autor;
		char[] cTitulo = new char[TAM_TITULO];
		char[] cAutor = new char[TAM_AUTOR];
		
		// Leer hasta llegar al final del archivo
	
		while (raf.getFilePointer()< raf.length()) {
			id = raf.readInt(); // Leer el ID
			
			// Leer y reconstruir el título
			for (int i=0; i < cTitulo.length; i++) {
				cTitulo[i] = raf.readChar();
			}
			titulo = new String(cTitulo).trim();
			
			// Leer y reconstruir el autor
			for (int i=0; i < cAutor.length; i++) {
				cAutor[i] = raf.readChar();
			}
			autor = new String(cAutor).trim();
			
			anioEd = raf.readInt(); // Leer el año de edicion, que como es un entero uso directamente el método readInt();
			numPag = raf.readInt(); //Leo num de paginas))
			
			//Mostrar por consola cada uno de los registros
			System.out.println(id + " - " + titulo + " - " + autor + " - " + anioEd + " - " + numPag + "páginas");
			
		}
		
	}
	
	

}
