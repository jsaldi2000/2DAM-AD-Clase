package dam.ad.practica9.main;

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

import dam.ad.practica9.javabeans.Direccion;
import dam.ad.practica9.javabeans.Empleado;

public class EscribirListaEmpleadosDom {

	public static void main(String[] args) {

		try {
			
			// Crear lista de empleados
			List<Empleado> listaEmpleados = new ArrayList<>();
			listaEmpleados.add(new Empleado(1, "Carlos", "Gómez", new Direccion("calle Mayor", "Madrid", "Madrid", "28028")));
			listaEmpleados.add(new Empleado(2, "María", "Martínez", new Direccion("calle X", "Burgos", "Burgos", "09004")));
			listaEmpleados.add(new Empleado(3, "Manuel", "Fernández", new Direccion("Pasep del prado", "Madrid", "Madrid", "28028")));
			listaEmpleados.add(new Empleado(4, "Raquel", "López", new Direccion("Gran Vía", "Barcelona", "Barcelona", "08001")));
			listaEmpleados.add(new Empleado(5, "Alba", "Torres", new Direccion("calle Real", "Zaragoza", "Aragón", "50001")));

			// Crear documento XML usando DOM
			DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			
			// Crear el nodo raíz <Empleados>
			Document doc = implementation.createDocument(null, "Empleados", null);
			doc.setXmlVersion("1.0");
			
			// Crear los nodos para empleados
			for (Empleado emp : listaEmpleados) {
				Element empleado = doc.createElement("empleado");
				doc.getDocumentElement().appendChild(empleado);
				
				Element elementoId = doc.createElement("id");
				Text textoId = doc.createTextNode(String.valueOf(emp.getId())); // Convierto desde int a String
				empleado.appendChild(elementoId);
				elementoId.appendChild(textoId);
				
				Element elementoNombre = doc.createElement("nombre");
				Text textoNombre = doc.createTextNode(emp.getNombre());
				empleado.appendChild(elementoNombre);
				elementoNombre.appendChild(textoNombre);
				
				Element elementoApellido = doc.createElement("apellido");
				Text textoApellido = doc.createTextNode(emp.getApellido());
				empleado.appendChild(elementoApellido);
				elementoApellido.appendChild(textoApellido);
				
				// Crear nodo de dirección y sus elementos
				Element elementoDireccion = doc.createElement("direccion");
				empleado.appendChild(elementoDireccion);
				
				Element elementoCalle = doc.createElement("calle");
				Text textoCalle = doc.createTextNode(emp.getDireccion().getCalle());
				elementoDireccion.appendChild(elementoCalle);
				elementoCalle.appendChild(textoCalle);
				
				Element elementoCiudad = doc.createElement("ciudad");
				Text textoCiudad = doc.createTextNode(emp.getDireccion().getCiudad());
				elementoDireccion.appendChild(elementoCiudad);
				elementoCiudad.appendChild(textoCiudad);
				
				Element elementoProvincia = doc.createElement("provincia");
				Text textoProvincia = doc.createTextNode(emp.getDireccion().getProvincia());
				elementoDireccion.appendChild(elementoProvincia);
				elementoProvincia.appendChild(textoProvincia);
				
				Element elementoCodigoPostal = doc.createElement("codigoPostal");
				Text textoCodigoPostal = doc.createTextNode(emp.getDireccion().getCodigoPostal());
				elementoDireccion.appendChild(elementoCodigoPostal);
				elementoCodigoPostal.appendChild(textoCodigoPostal);	
					
			}
			
			// Transformar el documento en un archivo XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            // Crear la fuente y volcar el XML en el archivo
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Ficheros/empleadosConDireccion.xml"));
            transformer.transform(source, result);
            
            // Crear la fuente y volcar el XML en consola
            StreamResult console = new StreamResult(System.out);
            transformer.transform(source, console);
            
            System.out.println("Archivo se ha creado correctamente");

			
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error inesperado");
		}

	}
	


}
