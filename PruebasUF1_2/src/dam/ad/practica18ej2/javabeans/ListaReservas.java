package dam.ad.practica18ej2.javabeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaReservas implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Reserva> reservas = new ArrayList<>();

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void añadirReserva(Reserva reserva) {
        this.reservas.add(reserva);
    }
}