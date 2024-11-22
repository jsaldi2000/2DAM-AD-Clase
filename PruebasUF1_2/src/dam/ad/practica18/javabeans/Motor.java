package dam.ad.practica18.javabeans;

import java.io.Serializable;

public class Motor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int potencia;
	private String combustible;
	private double consumo;
	
	public Motor(int potencia, String combustible, double consumo) {
		this.potencia = potencia;
		this.combustible = combustible;
		this.consumo = consumo;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	public double getConsumo() {
		return consumo;
	}

	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}

	@Override
	public String toString() {
		return "Motor [potencia=" + potencia + ", combustible=" + combustible + ", consumo=" + consumo + "]";
	}
	
}
