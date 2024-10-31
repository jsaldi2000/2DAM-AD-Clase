package dam.ad.practica17.ejer2.javabeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaActividades implements Serializable {

    private static final long serialVersionUID = 1L;

    // Lista como atributo
    private List<Actividad> listaActividades;

    // Constructor que inicializa la lista
    public ListaActividades() {
        listaActividades = new ArrayList<>();
    }

    // Método para añadir actividades a la lista
    public void addActividad(Actividad actividad) {
        listaActividades.add(actividad);
    }

    // Método para obtener la lista de actividades
    public List<Actividad> getListaActividades() {
        return listaActividades;
    }
}