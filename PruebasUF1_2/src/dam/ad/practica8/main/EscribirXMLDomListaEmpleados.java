package dam.ad.practica8.main;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

import org.w3c.dom.*;

import dam.ad.practica8.javabeans.Empleado;

public class EscribirXMLDomListaEmpleados {

	public static void main(String[] args) {
		// Crear la lista de empleados
		List<Empleado> listaEmpelados = crearListaEmpleados();
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			
			// Crear el nodo raíz <empleados>
			Document doc = implementation.createDocument(null, "empleados", null);
			doc.setXmlVersion("1.0");
			
			for(Empleado emp : listaEmpelados) {
				
				// Crear el elemento empleado
                Element elementoEmpleado = doc.createElement("empleado");
                doc.getDocumentElement().appendChild(elementoEmpleado);
                
                // Crear el elemento ID y añadimos a empleado
                Element elementoId = doc.createElement("id");
                Text textoId = doc.createTextNode(String.valueOf(emp.getId()));
                elementoEmpleado.appendChild(elementoId);
                elementoId.appendChild(textoId);
                
                // Crear el elemento Nombre y añadirlo al empleado
                Element elementoNombre = doc.createElement("nombre");
                Text textoNombre = doc.createTextNode(emp.getNombre());
                elementoEmpleado.appendChild(elementoNombre);
                elementoNombre.appendChild(textoNombre);
                
                // Crear el elemento Apellido y añadirlo al empleado
                Element elementoApellido = doc.createElement("apellido");
                Text textoApellido = doc.createTextNode(emp.getApellido());
                elementoEmpleado.appendChild(elementoApellido);
                elementoApellido.appendChild(textoApellido);
			}
			
			// Transformar el documento en archivo XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            
            // Definir el archivo de salida y volcar la fuente en el fichero
            DOMSource source = new DOMSource(doc);
            StreamResult fichero = new StreamResult(new File("Ficheros/listaempleados.xml"));
            transformer.transform(source, fichero);
            
            System.out.println("Archivo XML creado correctamente");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
}

	private static List<Empleado> crearListaEmpleados() {
		
		List<Empleado> listaEmpleados = new ArrayList<>();
		listaEmpleados.add(new Empleado(1, "Juan", "Coloma"));
		listaEmpleados.add(new Empleado(2, "Ávaro", "Sordo"));
		listaEmpleados.add(new Empleado(3, "Andrea", "García"));
		listaEmpleados.add(new Empleado(4, "Javier", "Ochando"));
		listaEmpleados.add(new Empleado(5, "Jaime", "Ferrer"));
		
		return listaEmpleados;
	}
}