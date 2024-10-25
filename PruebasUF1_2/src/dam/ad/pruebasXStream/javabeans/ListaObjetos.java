package dam.ad.pruebasXStream.javabeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaObjetos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Lista como atributo
	private List<Objeto> listaObjetos;
	
	// Constructor que inicializa la lista
	public ListaObjetos() {
		listaObjetos = new ArrayList<>();
	}

	// Método para añadir objetos a la lista
	public void addObjeto(Objeto objeto) {
		listaObjetos.add(objeto);
	}
	
	// Método de acceso para obtener la lista
	public List<Objeto> getListaObjetos(){
		return listaObjetos;
	}
}
