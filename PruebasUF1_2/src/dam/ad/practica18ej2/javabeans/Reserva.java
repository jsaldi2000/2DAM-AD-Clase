package dam.ad.practica18ej2.javabeans;

import java.io.Serializable;

public class Reserva implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private int id;
    private String cliente;
    private String origen;
    private String destino;
    private String fechaVuelo;
    
    public Reserva(int id, String cliente, String origen, String destino, String fechaVuelo) {
        this.id = id;
        this.cliente = cliente;
        this.origen = origen;
        this.destino = destino;
        this.fechaVuelo = fechaVuelo;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaVuelo() {
        return fechaVuelo;
    }

    public void setFechaVuelo(String fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }
    
    @Override
    public String toString() {
        return "ReservaVuelo [id=" + id + ", cliente=" + cliente + ", origen=" + origen +
               ", destino=" + destino + ", fechaVuelo=" + fechaVuelo + "]";
    }
    
}
