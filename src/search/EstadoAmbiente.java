package search;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import frsf.cidisi.faia.state.EnvironmentState;
import util.Celda;
import util.Girasol;
import util.TipoEnum;
import util.TipoZombie;
import util.Zombie;

public class EstadoAmbiente extends EnvironmentState {
	
	private Celda[][] mapa;
	private Point ubicacionAgente; //Ubicacion agente
	private List<Girasol> girasoles; //Lista de girasoles en el mapa actualmente
	private List<Zombie> zombies; //Lista de zombies en el mapa actualmente
	private int cantZombies; //Cantidad maxima de zombies en el juego
	
	 public Celda[][] getMapa() {
		return mapa;
	}

	public void setMapa(Celda[][] mapa) {
		this.mapa = mapa;
	}
		
	public List<Girasol> getGirasoles() {
		return girasoles;
	}

	public void setGirasoles(List<Girasol> girasoles) {
		this.girasoles = girasoles;
	}

	public List<Zombie> getZombies() {
		return zombies;
	}

	public void setZombies(List<Zombie> zombies) {
		this.zombies = zombies;
	}

	public int getCantZombies() {
		return cantZombies;
	}

	public void setCantZombies(int cantZombies) {
		this.cantZombies = cantZombies;
	}

	public EstadoAmbiente(Celda[][] c) {
		mapa = c;
	}
	
	public EstadoAmbiente() {
		this.initState();	 
	 }

	public Point getUbicacionAgente() {
		return ubicacionAgente;
	}

	public void setUbicacionAgente(Point ubicacionAgente) {
		this.ubicacionAgente = ubicacionAgente;
	}

	@Override
	public void initState() {
		
		//Ubicacion inicial
		this.ubicacionAgente = new Point(2,1);
		
		//Cantidad maxima de zombies en el inicio del juego
		this.cantZombies = (int) (Math.random()*(20-5+1)+5);
		
		//Cargar el mapa
		
		this.mapa = new Celda[5][9];
		
		Celda nuevaCelda = new Celda(TipoEnum.VACIO);
		Celda celdaConZombie = new Celda(TipoEnum.ZOMBIE);
		
		this.girasoles = new ArrayList<>();
		this.zombies = new ArrayList<>();
		
		for (int i=0; i<5; i++) {
			for (int j=0; j<9; j++) {
				if(j == 8) { //Si estamos en la ultima columna, puede haber o no zombies, entonces:
					int hayZombie = (int)(Math.random()*(1-0+1)+0); //numero aleatorio entre 1 y 0: si sale 1 hay zombie, sino celda vacia
					if (hayZombie == 0) {
						this.mapa[i][j] = nuevaCelda; //no hay zombie
					} else { //hay zombie
						int tipoZombie = (int)(Math.random()*(6-1+1)+1); //numero aleatorio para ver que tipo de zombie toca
						this.mapa[i][j] = celdaConZombie;
						Zombie zombie = getZombie(tipoZombie);
						zombie.setUbicacionZombie(new Point(i, j));
						this.zombies.add(getZombie(tipoZombie));
						
						//Una vez inicialidados los zombies, resto del total de zombies
						//la cantidad que ya se muestran en el mapa, para saber cuantos faltan mostrar luego
						this.cantZombies--; 
					}
				} else { //no hay zombie
					this.mapa[i][j] = nuevaCelda;
				}
			}
		}
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Zombie getZombie(int n) {
		switch(n) {
		case 1: 
			return new Zombie(TipoZombie.ZOMBIE);
		case 2:
			return new Zombie(TipoZombie.CARACONO);
		case 3:
			return new Zombie(TipoZombie.ZOMBISTEIN);
		case 4:
			return new Zombie(TipoZombie.CARACUBO);
		case 5:
			return new Zombie(TipoZombie.ABANDERADO);
		case 6:
			return new Zombie(TipoZombie.YETI);
		default:
			return new Zombie(TipoZombie.ZOMBIE);
		}
	}

}
