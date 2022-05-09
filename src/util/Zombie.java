package util;

import java.awt.Point;

public class Zombie {
	
	private TipoZombie tipo; //tipo de zombie
	private int costeDeSoles; //cantidad de soles con los que se puede matar al zombie
	public Point ubicacionZombie;
	public int turnosDetenido; //cantidad de turnos (de 1 a 3) que el zombie se mantuvo quieto en la misma celda
	
	public Zombie() {
		this.turnosDetenido = 0;
	}
	
	public Zombie(TipoZombie tipo) {
		super();
		this.turnosDetenido = 0;
		this.tipo = tipo;
		switch(tipo) {
			case ZOMBIE:
				setCosteDeSoles(1);
				break;
			case CARACONO:
				setCosteDeSoles(2);
				break;
			case ZOMBISTEIN:
				setCosteDeSoles(5);
				break;
			case CARACUBO:
				setCosteDeSoles(3);
				break;
			case ABANDERADO:
				setCosteDeSoles(2);
				break;
			case YETI:
				setCosteDeSoles(4);
				break;
		}
	}
	
	//Getters and setters

	public TipoZombie getTipo() {
		return tipo;
	}
	public void setTipo(TipoZombie tipo) {
		this.tipo = tipo;
	}
	public int getCosteDeSoles() {
		return costeDeSoles;
	}
	public void setCosteDeSoles(int costeDeSoles) {
		this.costeDeSoles = costeDeSoles;
	}
	public Point getUbicacionZombie() {
		return ubicacionZombie;
	}
	public void setUbicacionZombie(Point ubicacionZombie) {
		this.ubicacionZombie = ubicacionZombie;
	}
	public int getTurnosDetenido() {
		return turnosDetenido;
	}
	public void setTurnosDetenido(int turnosDetenido) {
		this.turnosDetenido = turnosDetenido;
	}

	
	@Override
	public String toString() {
		return tipo.toString();
	}
	
	public Zombie clone() {
		
		Zombie clon = new Zombie();
		
		clon.setTipo(this.tipo);
		clon.setCosteDeSoles(this.costeDeSoles);
		clon.setUbicacionZombie(new Point(ubicacionZombie.x, ubicacionZombie.y));
		
		return clon;
	}

}
