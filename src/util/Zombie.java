package util;

import java.awt.Point;

public class Zombie {
	
	private TipoZombie tipo; //tipo de zombie
	private int costeDeSoles; //cantidad de soles con los que se puede matar al zombie
	public Point ubicacionZombie;
	
	public Zombie() {}
	
	public Zombie(TipoZombie tipo) {
		super();
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

}
