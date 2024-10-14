package dam.ad.pruebasdom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.rmi.server.UnicastRemoteObject;

import org.w3c.dom.*;

public class EscribirXMLDom {

	public static void main(String[] args) {
		try {
			
			// Crear una instancia de DocumentBuilderFactorry
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			// Crear el parser
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			// DOMImplementation nos facilita el método createDocument para crear un documento XML inicial
			DOMImplementation implementation = builder.getDOMImplementation();
			
			// Creo un documento vacío indicando el nombre del nodo raíz
			Document doc = implementation.createDocument(null, "empleados", null);
			
			// Asignamos la versión
			doc.setXmlVersion("1.0");
			
			// Crear primer elemento empleado1 y añadirlo al nodo raíz
			Element elementoEmpleado1 = doc.createElement("empleado");
			doc.getDocumentElement().appendChild(elementoEmpleado1);
			
			// Crear elementos finales en el documento XML
			// Primero creo el elemento (Element o Text) y luego lo asigno a un nodo existente (elementoEmpleado1 o elementoId1)
			Element elementoId1 = doc.createElement("id");
			Text textoId1 = doc.createTextNode("1");
			elementoEmpleado1.appendChild(elementoId1);
			elementoId1.appendChild(textoId1);
			
			Element elementoNombre1 = doc.createElement("nombre");
			Text textoNombre1 = doc.createTextNode("Axel");
			elementoEmpleado1.appendChild(elementoNombre1);
			elementoNombre1.appendChild(textoNombre1);
			
			Element elementoApellido1 = doc.createElement("apellido");
			Text textoApellido1 = doc.createTextNode("León");
			elementoEmpleado1.appendChild(elementoApellido1);
			elementoApellido1.appendChild(textoApellido1);
			
			// SEGUIMOS AÑADIENDO ELEMENTOS
			
			
			
			// Una vez que hemos generado la estructura, creamos la fuente XML a partir de la estructura
			DOMSource source = new DOMSource(doc);
			
			// Obtenemos un TransformerFactory
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			// Le damos formato y realizamos la transformación del documento a fichero
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // esto es para que aparezca indentado y no en una sola línea
			transformer.setOutputProperty(OutputKeys.METHOD, "xml"); // para que me muestre en el xml, la version y la codification
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // añadir los 4 espacios por tabulador
			
			// Defino el canal de salida y volcamos la fuente XML en el ficero
			StreamResult fichero = new StreamResult(new File("Ficheros/empleados2.xml"));
			transformer.transform(source, fichero);
			
			System.out.println("Archivo XML creado correctamente");
			
			// A modo de comprobación podemos mostra el documento por pantalla
			StreamResult console = new StreamResult(System.out);
			transformer.transform(source, console);
						
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
