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
	
	private Celda[][] mapaAmbiente; 
	private Point ubicacionAgente; //Ubicacion agente
	private List<Girasol> girasoles; //Lista de girasoles en el mapa actualmente
	private List<Zombie> zombies; //Lista de zombies en el mapa actualmente
	private int cantZombies; //Cantidad maxima de zombies en el juego
	
	// Getters y setters
	 public Celda[][] getMapa() {
		return mapaAmbiente;
	}
	
	public void setMapa(Celda[][] mapaAmbiente) {
		this.mapaAmbiente = mapaAmbiente;
	}
	
	public Point getUbicacionAgente() {
		return ubicacionAgente;
	}

	public void setUbicacionAgente(Point ubicacionAgente) {
		this.ubicacionAgente = ubicacionAgente;
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

	public EstadoAmbiente(int cantidadZombiesEnJuego) {
		this.setCantZombies(cantidadZombiesEnJuego);
		this.initState();	 
	 }
	
	public EstadoAmbiente(Celda[][] c) {
		mapaAmbiente = c;
	}

	@Override
	public void initState() {
		
		//Ubicacion inicial
		this.ubicacionAgente = new Point(2,1);
		Celda celdaConAgente = new Celda(TipoEnum.PLANTA_AGENTE);
		
		//Cantidad maxima de zombies en el inicio del juego
		//this.cantZombies = (int) 6;
		
		//Cargar el mapa
		
		this.mapaAmbiente = new Celda[5][9];
		
		this.girasoles = new ArrayList<>();
		this.zombies = new ArrayList<>();
		
		for (int i=0; i<5; i++) {
			for (int j=0; j<9; j++) {
				/*if(j == 8) { //Si estamos en la ultima columna, puede haber o no zombies, entonces:
					int agregarZombie = (int)(Math.random()*(1-0+1)+0); //numero aleatorio entre 1 y 0: si sale 1 agregar zombie, sino celda vacia
					if (agregarZombie == 0) {
						this.mapaAmbiente[i][j] = new Celda(TipoEnum.VACIO); //no hay zombie
					} else { //hay zombie
						int tipoZombie = (int)(Math.random()*(6-1+1)+1); //numero aleatorio para ver que tipo de zombie toca
						this.mapaAmbiente[i][j] =  new Celda(TipoEnum.ZOMBIE); //seteo en la celda un zombie
						Zombie zombie = getZombie(tipoZombie); //obtengo el tipo de zombie
						zombie.setUbicacionZombie(new Point(i, j)); //al zombie le seteo esa ubicacion (la de la celda)
						
						this.zombies.add(zombie); //agrego al vector de zombies el zombie que agrego
						
						//Una vez inicialidados los zombies, resto del total de zombies
						//la cantidad que ya se muestran en el mapa, para saber cuantos faltan mostrar luego
						this.cantZombies--; 
					
				} else { //no hay zombie */
					this.mapaAmbiente[i][j] = new Celda(TipoEnum.VACIO);
				//}
			}
		}
		
		this.mapaAmbiente[2][1] = celdaConAgente;
		
	}

	@Override
	public String toString() {
		 String str = "\n Girasoles en mapa: " + girasoles.toString() + "\n";
	     str+="Zombies en mapa: \n" +"  "+ zombies.toString() + "\n";
	     str += "Ubicacion actual del agente: ("+ ubicacionAgente.x+","+ubicacionAgente.y+")\n";
	     str += "Cantidad de zombies que quedan por mostrar: "+cantZombies+"\n";
	     
	     str+="Matriz ambiente: \n";
			for(int i=0; i<5; i++) {
				for(int j=0; j<9; j++) {
					str+="["+this.mapaAmbiente[i][j].toString()+"]";
				}
				str+="\n";
			}
	     
	     return str;
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
	 
	 // Metodo para generar soles
	 public void generarSoles() {
		 if (girasoles.size() > 0) {
			 for (int i=0; i<girasoles.size(); i++) {
				 int cantidadSolesASumar = (int) Math.floor(Math.random()*(3-1+1)+1); //numero aleatorio entre 1 y 3 de soles que puede incrementar el girasol x percepcion
				 girasoles.get(i).setCantSoles(girasoles.get(i).getCantSoles() + cantidadSolesASumar);
			 }
		 }
	 }
	 
	 // Metodo que hace que los zombies avancen
	 public void comportamientoZombie() {
		 if (zombies.size() > 0) {
			 //moverZombiesActuales
			 for (int i=0; i<5; i++) {
				 for (int j=0; j<9; j++) {
					 if (mapaAmbiente[i][j].getTipo() == TipoEnum.ZOMBIE) {
						 //buscar en el arreglo de zombies el mismo zombie de la posicion ij y mover su ubicacion 1 lugar
						 Zombie zombieAMover = buscarZombie(i, j);
						 
						 if (j > 0) { //chequeo que el zombie no este en el borde izquierdo
							 
							 Celda celdaAdyacente = mapaAmbiente[i][j-1];
							 
							 // El zombie se puede mover solo si hay una celda vacia adelante o hay un girasol en dicha celda
							 // se interpreto que si hay un agente en la celda de adelante al zombie, este no puede moverse
							 if (celdaAdyacente.tipo != TipoEnum.PLANTA_AGENTE && 
								 celdaAdyacente.tipo != TipoEnum.GIRASOL_CON_PLANTA && 
								 celdaAdyacente.tipo != TipoEnum.ZOMBIE) {
							 
								 //Si se puede mover el zombie, primero chequeo que los turnos quietos sean menores a 4 (porque pueden estar hasta 3 ciclos de percepcion quietos)
								 
								 int numAleatorio = (int) (Math.random()*(1-0+1)+0); //numero aleatorio entre 1 y 0: si sale 1 avanza el zombie, sino se queda quieto en la misma celda
								 
								 if (zombieAMover.turnosDetenido > 3 || numAleatorio == 1) { //chequeo si la cantidad de turnos detenido ya supero el maximo posible (3) o si no lo supero pero salio 1 en el numAleatorio
									 
									 if (celdaAdyacente.tipo == TipoEnum.VACIO) { // Si la celda adyacente al zombie esta vacia
										 zombieAMover.setUbicacionZombie(new Point(i, j-1)); //muevo el zombie una posicion para adelante
										 mapaAmbiente[i][j-1] = new Celda(TipoEnum.ZOMBIE); //actualizo la celda adyacente con el zombie
										 mapaAmbiente[i][j] = new Celda(TipoEnum.VACIO); //actualizo la celda anterior con vacio
									 } else if (celdaAdyacente.tipo == TipoEnum.GIRASOL) { // Si la celda adyacente al zombie tiene un girasol, lo mata al girasol
										 zombieAMover.setUbicacionZombie(new Point(i, j-1)); //muevo el zombie una posicion para adelante
										 mapaAmbiente[i][j-1] = new Celda(TipoEnum.ZOMBIE); // actualizo la celda adyacente al zombie, eliminando el girasol que hay en ella
										 Girasol girasolARemover = buscarGirasol(i, j);
										 girasoles.remove(girasolARemover); //borro el girasol de la lista
										 mapaAmbiente[i][j] = new Celda(TipoEnum.VACIO); //actualizo la celda anterior con vacio
									 }
									 
									 zombieAMover.setTurnosDetenido(0);
									 
								 } else {
									 zombieAMover.setTurnosDetenido(zombieAMover.getTurnosDetenido()+1);
								 }
							 }
						 }
					 }
				 }
			 }
		 }
		 
		 agregarNuevosZombies();
	 }
	 
	 // Metodo para seguir agregando zombies al mapa (si todavía quedan por agregar)
	 public void agregarNuevosZombies() {
		 
		 if (cantZombies > 0) { //si todavia hay zombies para aparecer en el ambiente
			 
			 for (int i=0; i<5 && cantZombies > 0; i++) { //recorro las filas de la ultima columna (donde pueden aparecer los zombies nuevos)
				 
				 if (mapaAmbiente[i][8].getTipo() != TipoEnum.ZOMBIE) { //si en la celda no hay un zombie, puede aparecer uno nuevo alli
					 
					 int agregarZombie = (int)(Math.random()*(1-0+1)+0); //numero aleatorio entre 1 y 0: si sale 1 hay zombie, sino celda vacia
						
					 if (agregarZombie == 1) {
							int tipoZombie = (int)(Math.random()*(6-1+1)+1); //numero aleatorio para ver que tipo de zombie toca
							this.mapaAmbiente[i][8] =  new Celda(TipoEnum.ZOMBIE);
							Zombie zombie = getZombie(tipoZombie);
							zombie.setUbicacionZombie(new Point(i, 8));
							this.zombies.add(zombie);
							
							//Una vez inicialidados los zombies, resto del total de zombies
							//la cantidad que ya se muestran en el mapa, para saber cuantos faltan mostrar luego
							this.cantZombies--; 
						}
				 }
			 }
			 
		 }
		 
	 }
	 
	 public void moverZombie(Zombie z) {
		 
	 }
	 
	 public void agregarGirasol(Girasol nuevoGirasol) {
		 this.girasoles.add(nuevoGirasol);
	 }
	
	public Zombie buscarZombie(int fila,int columna){
		boolean bandera=true;
		int i=0;
		Zombie zombie= null;
		while(bandera && zombies.size()>i){
			if(zombies.get(i).ubicacionZombie.x==fila && zombies.get(i).ubicacionZombie.y==columna)	{
				zombie = zombies.get(i);
				bandera=false;
			}
			i++;
		}
		return zombie;
	}
	
	public Girasol buscarGirasol(int fila, int columna){
		boolean bandera=true;
		int i=0;
		Girasol girasol= null;
		while(bandera && girasoles.size()>i){
			if(girasoles.get(i).ubicacionGirasol.x==fila && girasoles.get(i).ubicacionGirasol.y==columna)	{
				girasol = girasoles.get(i);
				bandera=false;
			}
			i++;
			}
		return girasol;
	}

}
