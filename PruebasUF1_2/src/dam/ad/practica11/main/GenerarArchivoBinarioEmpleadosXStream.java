package dam.ad.practica11.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import dam.ad.practica11.javabeans.Empleado;
import dam.ad.practica11.javabeans.ListaEmpleados;

public class GenerarArchivoBinarioEmpleadosXStream {

	public static void main(String[] args) {


		//paso 1 crear la lista empleados
		ListaEmpleados listaEmpleados = crearArrayEmpleados();
		
		//paso 2 crear el archivo binario empleadosObj.dat
		crearArchivoBinario(listaEmpleados);
		
		//paso 3 generar el archivo empleadosXStream.xml usando XStream (serializar de Java a XML)
		generarXMLDesdeBinario();
		
		//paso 4 leer el archivo xml y convertirlo nuevamente a objetos (deserializar XML a Java)
		leerArchivoXML();

	}


	private static ListaEmpleados crearArrayEmpleados() {
		
		// arrays del enunciado
		String[] nombres = {"Alberto", "Guillermo", "Alejandro", "Ana", "Patricia"};
		int[] departamentos = {10, 20, 30, 20, 10};
		double[] salarios = {2000.00, 1500.50, 3000.40, 2300.60, 1900.10};

		
		// Crear la instancia de ListaEmpleados
		ListaEmpleados listaEmpleados = new ListaEmpleados();
		
		// Interar sobre los arrays para crear los objetos empleado y añadirlos a la lista
		for(int i=0; i < nombres.length; i++) {
			listaEmpleados.addEmpleado(new Empleado(i+1, nombres[i], departamentos[i], salarios[i]));
		}
		System.out.println("1. Lista de empleados creada correctamente");
		return listaEmpleados;
	}
	
	private static void crearArchivoBinario(ListaEmpleados listaEmpleados) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Ficheros/empleadosObj.dat"))){
			
			oos.writeObject(listaEmpleados);
			System.out.println("2. Archivo binario empleadosObj.dat creado con éxito");
			
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ha ocurrido un error inesperado");
			e.printStackTrace();
		}
		
	}
	
	private static void generarXMLDesdeBinario() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Ficheros/empleadosObj.dat"))) {
	
				ListaEmpleados listaEmpleados = (ListaEmpleados) ois.readObject();
				
				// Crear la instancia de XStream
				XStream xstream = new XStream();
				
				// Configurar los persmisos de seguridad
				xstream.addPermission(NoTypePermission.NONE); 
				xstream.addPermission(NullPermission.NULL); 
				xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
				
				// Especificar las clases permitidas
				Class[] clasesPermitidas = { ListaEmpleados.class, Empleado.class};
				xstream.allowTypes(clasesPermitidas);
				
				// Permitir cualquier tipo procedente del mismo paquete
				xstream. allowTypesByWildcard(new String[] { "dam.ad.practica11"});
				
				// Alias para las etiquetas del XML (nodo raíz y secundario)
				xstream.alias("Empleados", ListaEmpleados.class);
				xstream.alias("empleado", Empleado.class);
				
				// Para que no aparezca el atributo listaEmpleados de la clase ListaObjetos
				xstream.addImplicitCollection(ListaEmpleados.class, "listaEmpleados");
				
				// Escribir el archivo XML
				try (FileOutputStream fos = new FileOutputStream("Ficheros/empleadosXStream.xml")){
					xstream.toXML(listaEmpleados, fos);
					System.out.println("3. Archivo XML empleadosXStream.xml generado con éxito");
				}catch (Exception e) {
					System.out.println("No se encuentra el archivo especificado o ha ocurrido un error inesperado");
					e.printStackTrace();
				}
			
		} catch (FileNotFoundException e) {
			System.out.println("Archivo empleadosObj.dat no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error inesperado");
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			System.out.println("Clase no encontrada");
			e1.printStackTrace();
		}
	}
	
	private static void leerArchivoXML() {

		// Crear la instancia de XStream
		XStream xstream = new XStream();
		
		// Configurar los persmisos de seguridad
		xstream.addPermission(NoTypePermission.NONE); 
		xstream.addPermission(NullPermission.NULL); 
		xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		
		// Especificar las clases permitidas
		Class[] clasesPermitidas = { ListaEmpleados.class, Empleado.class};
		xstream.allowTypes(clasesPermitidas);
		
		// Permitir cualquier tipo procedente del mismo paquete
		xstream. allowTypesByWildcard(new String[] { "dam.ad.practica11.*"});
		
		// Alias para las etiquetas del XML (nodo raíz y secundario)
		xstream.alias("Empleados", ListaEmpleados.class);
		xstream.alias("empleado", Empleado.class);
		
		// Para que no aparezca el atributo listaEmpleados de la clase ListaObjetos
		xstream.addImplicitCollection(ListaEmpleados.class, "listaEmpleados");
		
		// Leer el archivo XML y convertirlo a objetos de tipo Empleado
		try(FileInputStream fis = new FileInputStream("Ficheros/empleadosXStream.xml")){
			ListaEmpleados listaEmpleados = (ListaEmpleados) xstream.fromXML(fis);
			
			// Mostrar los empleados leídos en el XML
			for (Empleado emp : listaEmpleados.getListaEmpleados()) {
				System.out.println("ID: " + emp.getId());
				System.out.println("Nombre: " + emp.getNombre());
				System.out.println("Departamento: " + emp.getDep());
				System.out.println("Salario: " + emp.getSalario());
				System.out.println("-------------------------");
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("no se ha encontrado el archivo");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error inesperado");
			e.printStackTrace();
		}
	
	
	}
	

}
