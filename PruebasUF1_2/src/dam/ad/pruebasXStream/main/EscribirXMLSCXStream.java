package dam.ad.pruebasXStream.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.stream.XMLStreamWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import dam.ad.pruebasXStream.javabeans.ListaObjetos;
import dam.ad.pruebasXStream.javabeans.Objeto;

public class EscribirXMLSCXStream {

	public static void main(String[] args) {

		// Crear la instancia de XStream
		XStream xstream = new XStream();
		
		// Configurar los permisos básicos de seguridad
		xstream.addPermission(NoTypePermission.NONE);
		xstream.addPermission(NullPermission.NULL);
		xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		
		// Especificar las clases permitidas:
		Class[] clases = {ListaObjetos.class, Objeto.class};
		xstream.allowTypes(clases);
		
		// Permitir cualquier tipo procedente del mismo paquete
		xstream.allowTypesByWildcard(new String[] { "dam.ad.pruebasXStream.*" });
		
		// Las etiquetas XML se corresponden con el nombre de los atributos de la clase, aunque se podrían cambiar usando el método alias()
		xstream.alias("ListaObjetos",ListaObjetos.class);
		xstream.alias("Objeto",Objeto.class);
		
		// Para que no aparezca el atributo listaObjetos de la clase ListaObjetos en el XML utilizamos el método addImplicitCollection();
		xstream.addImplicitCollection(ListaObjetos.class, "listaObjetos");

		// Crear una instancia de ListaObjetos y añado algún objeto
		ListaObjetos listaObj = new ListaObjetos();
		listaObj.addObjeto(new Objeto("Objeto1", 100));
		listaObj.addObjeto(new Objeto("Objeto2", 200));
		listaObj.addObjeto(new Objeto("Objeto3", 300));
		listaObj.addObjeto(new Objeto("Objeto4", 400));
		listaObj.addObjeto(new Objeto("Objeto5", 500));
		
		// Serializar a XML
		try {
			FileOutputStream fos = new FileOutputStream("Ficheros/objetos.xml");
			xstream.toXML(listaObj, fos);
			
			System.out.println("Archivo XML creado con éxito");
			
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
			e.printStackTrace();
		}
		
	}

}
