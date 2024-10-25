package dam.ad.practica11.javabeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaEmpleados implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Lista como atributo
	private List<Empleado> listaEmpleados;
	
	// Construtor que inicializa la lista
	public ListaEmpleados() {
		listaEmpleados = new ArrayList<>();
	}

	// Método para agregar elementos
	public void addEmpleado(Empleado empleado) {
		listaEmpleados.add(empleado);
	}
	
	// y un método de acceso para obtener la lista
	public List<Empleado> getListaEmpleados(){
		return listaEmpleados;
	}

}
