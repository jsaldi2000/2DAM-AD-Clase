package dam.ad.pruebasdom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import org.w3c.dom.*;


public class LeerXMLDom {

	public static void main(String[] args) {
		try{
		
		// Crear una instancia de DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		// Creamos el parser
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		// Leer el archivo XML
		Document doc = builder.parse(new File("Ficheros/empleados.xml"));
		
		// Normalizar el archivo XML (eliminar espacios y tabuladores que no se necesiten)
		doc.getDocumentElement().normalize();
		
		// Obtener la lista de elementos que contienen la etiqueta "empleado"
		NodeList listaEmpleados = doc.getElementsByTagName("empleado");
		
		// Recorrer la lista de empleados
		for (int i = 0; i < listaEmpleados.getLength(); i++) {
			
			// Obtener el primer elemento en la lista del nodo principal.
			Node empleado = listaEmpleados.item(i);
			
			// Comprobar que el elemento es de tipo ELEMENT, y no de texto. A continuación lo convertimos a tipo Element.
			if (empleado.getNodeType() == Node.ELEMENT_NODE) {
				Element elemento = (Element) empleado;
				
				// Obtengo los valores de cada campo del elemento empleado
				String id = elemento.getElementsByTagName("id").item(0).getTextContent();
				String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
				String apellido = elemento.getElementsByTagName("apellido").item(0).getTextContent();
				
				// Imprimir los datos de elemento empledo
				System.out.println("Empleado ID: " + id);
				System.out.println("Nombre: " + nombre);
				System.out.println("Apellido: " + apellido);
				System.out.println("------------------------");
				
			}
		}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

}
