package util;

import java.awt.Point;

public class Girasol {
	
	public Point ubicacionGirasol;
	public int cantSoles; //cantidad de soles que da el girasol
	
	public Girasol() {}
	
	public Girasol(Point ubi) {
		super();
		this.ubicacionGirasol = ubi;
		setCantSoles(0);
	}

	public Point getUbicacionGirasol() {
		return ubicacionGirasol;
	}

	public void setUbicacionGirasol(Point ubicacionGirasol) {
		this.ubicacionGirasol = ubicacionGirasol;
	}

	public int getCantSoles() {
		return cantSoles;
	}

	public void setCantSoles(int cantSoles) {
		this.cantSoles = cantSoles;
	}
	
	

}
