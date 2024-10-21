package dam.ad.practica10.main;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import dam.ad.practica10.javabeans.Empleado;

public class GenerarArchivoBinarioEmpleadosDOM {

	public static void main(String[] args) {


		// 1. Crear el array de empleados a partir de los array que me da el enunciaod
		List<Empleado> listaEmpleados = crearArrayEmpleados();
		
		// 2. Crear el archivo binario empleadosObj.dat
		crearArchivoBinario(listaEmpleados);
		
		// 3. Leer y comprobar el archivo binario
		leerArchivoBinario();
		
		// 4. Generar el archivo XML desde el archivo binario con DOM
		generarXMLDesdeBinario();
		
	}


	private static List<Empleado> crearArrayEmpleados() {
		
		String[] nombres = {"Alberto", "Guillermo", "Alejandro", "Ana", "Patricia"};
		int[] departamentos = {10, 20, 30, 20, 10};
		double[] salarios = {2000.00, 1500.50, 3000.40, 2300.60, 1900.10};

		List<Empleado> listaEmpleados = new ArrayList<>(); // Nueva lista de objetos empleado, que sea redimensionable 
		
		for (int i = 0; i< nombres.length; i++) {
			listaEmpleados.add(new Empleado(i+1, nombres[i], departamentos[i], salarios[i]));
		}
		
		System.out.println("1. Array de empleados creado correctamente");
		return listaEmpleados;
	}

	
	private static void crearArchivoBinario(List<Empleado> listaEmpleados) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Ficheros/empleadosObj.dat"))){
			for (Empleado empleado : listaEmpleados) {
				oos.writeObject(empleado);
			}
			System.out.println("2. Archivo binario creado correctamente");
		} catch (FileNotFoundException e) {
			System.out.println("Ruta de archivo no encontrada");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error inesperado");
			e.printStackTrace();
		}
		
	}
	
	
	public static void leerArchivoBinario() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Ficheros/empleadosObj.dat"))) {
			Empleado empleado; // Inicializo variable empleado que almacenará los objetos empleado leídos del bin
			System.out.println("3. Inicio de la lectura del archivo binario empleadosObj.dat:\n");
			while ((empleado = (Empleado) ois.readObject()) != null) {
				System.out.println("Empleado " + empleado.getId());
				System.out.println("Nombre: " + empleado.getNombre());
				System.out.println("Departamento: " + empleado.getDepartamento());
				System.out.println("Salario: " + empleado.getSalario() + "€");
				System.out.println("");
			}
		} catch (EOFException e) {
			System.out.println("\n3. Fin de la lectura del archivo binario.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void generarXMLDesdeBinario() {
	
		try {
			
			// Crear una instancia de DocumentBuilderFactory y DocumentBuilder
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        
	        // DOMImplementation nos facilita la creación del documento XML inicial
	        DOMImplementation implementation = builder.getDOMImplementation();
	        
	        // Crear un documento vacío indicando el nombre del nodo raíz
	        Document doc = implementation.createDocument(null, "Empleados", null);
	        
	        // leer el archivo binario empleadosObj.dat
	        
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Ficheros/empleadosObj.dat"))){
	        	Empleado empleado;
	        	
	        	while (true) { // Bucle infinito que se rompe cuando se alcanza el final del archivo binario
	        		try {
	        			
	        			// Leer el empleado del archivo binario
	        			empleado = (Empleado) ois.readObject();
	        			
	        			// Crear el nodo empleado
	        			Element elementoEmpleado = doc.createElement("empleado");
	        			doc.getDocumentElement().appendChild(elementoEmpleado);
	        			
	                    // Agregar id
	                    Element elementoId = doc.createElement("id");
	                    Text textoId = doc.createTextNode(String.valueOf(empleado.getId()));
	                    elementoEmpleado.appendChild(elementoId);
	                    elementoId.appendChild(textoId);

	                    // Agregar nombre
	                    Element elementoNombre = doc.createElement("nombre");
	                    Text textoNombre = doc.createTextNode(empleado.getNombre());
	                    elementoEmpleado.appendChild(elementoNombre);
	                    elementoNombre.appendChild(textoNombre);

	                    // Agregar departamento
	                    Element elementoDepartamento = doc.createElement("departamento");
	                    Text textoDepartamento = doc.createTextNode(String.valueOf(empleado.getDepartamento())); //Alternativamente Integer.toString()
	                    elementoEmpleado.appendChild(elementoDepartamento);
	                    elementoDepartamento.appendChild(textoDepartamento);
	                    
	                    // Agregar salario
	                    Element elementoSalario = doc.createElement("salario");
	                    Text textoSalario = doc.createTextNode(Double.toString(empleado.getSalario())); // Como alternativa a String.valueOf()
	                    elementoEmpleado.appendChild(elementoSalario);
	                    elementoSalario.appendChild(textoSalario);	                    
	                    
	        			
	        		}catch (EOFException eof) {
						break;
					}
	        	}
	        }
	        
	        
	        // Transformar el documento DOM en un archivo XML
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	        
	        // Crear la fuente y volcar el resultado en el archivo XML
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File("Ficheros/empleadosBinarioDom.xml"));
	        transformer.transform(source, result);
	        
	        System.out.println("4. Archivo XML generado con éxito");
	        
	        
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
