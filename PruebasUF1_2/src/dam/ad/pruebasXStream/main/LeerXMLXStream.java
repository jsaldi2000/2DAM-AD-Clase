package dam.ad.pruebasXStream.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import dam.ad.pruebasXStream.javabeans.ListaObjetos;
import dam.ad.pruebasXStream.javabeans.Objeto;

public class LeerXMLXStream {

	public static void main(String[] args) {
		
		// Crear una instancia de la clase XStream
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
		
		// Establecemos canal al fichero XML y volcamos/deserializamo el contenido en un objeto de tipo ListaObjetos que podemos después leer
		try {
			FileInputStream fis = new FileInputStream("Ficheros/objetos.xml");
			ListaObjetos listaObj = (ListaObjetos) xstream.fromXML(fis);
			
			// Mostra el contenido de la lista ya deserializada, por consola
			for (Objeto obj : listaObj.getListaObjetos()) {
				System.out.println("Nombre: " + obj.getNombre() + ", Valor: " + obj.getValor());
			}		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
