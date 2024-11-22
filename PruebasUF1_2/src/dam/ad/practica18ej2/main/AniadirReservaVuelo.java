package dam.ad.practica18ej2.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dam.ad.practica18ej2.javabeans.ListaReservas;
import dam.ad.practica18ej2.javabeans.Reserva;

public class AniadirReservaVuelo {

	private static ListaReservas listaReservas = new ListaReservas();
	private static int id = 1;

	public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException {
		
		cargarReservasDesdeXML();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Ingrese el nombre del cliente: ");
		String cliente = scanner.nextLine();
		System.out.print("Ingrese la ciudad de origen: ");
		String origen = scanner.nextLine();
		System.out.print("Ingrese la ciudad de destino: ");
		String destino = scanner.nextLine();
		System.out.print("Ingrese la fecha del vuelo (dd/MM/yyyy): ");
		String fechaVuelo = scanner.nextLine();

		Reserva nuevaReserva = new Reserva(id++, cliente, origen, destino, fechaVuelo);
		listaReservas.getReservas().add(nuevaReserva);
		System.out.println("Reserva añadida con éxito.");

		generarXML(); 

	}

	private static void generarXML() throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Usar DOMImplementation para crear el documento XML
		DOMImplementation implementation = builder.getDOMImplementation();
		Document doc = implementation.createDocument(null, "Reservas", null);

		// Añadir todas las reservas de listaReservas al documento XML
		for (Reserva reserva : listaReservas.getReservas()) {
			Element elementoReserva = doc.createElement("reserva");
			doc.getDocumentElement().appendChild(elementoReserva);

			Element elementoId = doc.createElement("id");
			elementoId.appendChild(doc.createTextNode(String.valueOf(reserva.getId())));
			elementoReserva.appendChild(elementoId);

			Element elementoCliente = doc.createElement("cliente");
			elementoCliente.appendChild(doc.createTextNode(reserva.getCliente()));
			elementoReserva.appendChild(elementoCliente);

			Element elementoOrigen = doc.createElement("origen");
			elementoOrigen.appendChild(doc.createTextNode(reserva.getOrigen()));
			elementoReserva.appendChild(elementoOrigen);

			Element elementoDestino = doc.createElement("destino");
			elementoDestino.appendChild(doc.createTextNode(reserva.getDestino()));
			elementoReserva.appendChild(elementoDestino);

			Element elementoFechaVuelo = doc.createElement("fechaVuelo");
			elementoFechaVuelo.appendChild(doc.createTextNode(reserva.getFechaVuelo()));
			elementoReserva.appendChild(elementoFechaVuelo);

		}

		// Guardar el documento XML con formato adecuado
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		// Configurar el origen y destino de la transformación XML
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("Ficheros/reservas.xml"));

		// Transformar y guardar el XML en el archivo
		transformer.transform(source, result);
		System.out.println("Archivo XML 'reservas.xml' actualizado con éxito.");
	}

	private static void cargarReservasDesdeXML() throws ParserConfigurationException, SAXException, IOException {

		File file = new File("Ficheros/reservas.xml");
		if (file.exists()) {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();
			
			NodeList reservas = doc.getElementsByTagName("reserva");
			
			// Ajustar el ID al número de reservas existentes + 1
			id = reservas.getLength() + 1;
			
			for (int i = 0; i < reservas.getLength(); i++) {
				Element reservaElement = (Element) reservas.item(i);
				
				int id = Integer.parseInt(reservaElement.getElementsByTagName("id").item(0).getTextContent());
				String cliente = reservaElement.getElementsByTagName("cliente").item(0).getTextContent();
				String origen = reservaElement.getElementsByTagName("origen").item(0).getTextContent();
				String destino = reservaElement.getElementsByTagName("destino").item(0).getTextContent();
				String fechaVuelo = reservaElement.getElementsByTagName("fechaVuelo").item(0).getTextContent();
				
				// Crear y añadir la reserva a la lista
				Reserva reserva = new Reserva(id, cliente, origen, destino, fechaVuelo);
				listaReservas.getReservas().add(reserva);
			

		}
		
	}

}
}
