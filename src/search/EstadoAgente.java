package search;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import util.Celda;
import util.Girasol;
import util.Zombie;

public class EstadoAgente extends SearchBasedAgentState{
	
	private Point posicion;
	private int cantSolesDisponibles; //Cantidad de soles con los que cuenta el agente 
	private int cantZombies; //Cantidad de zombies que va a haber en el juego
	private Celda[][] mapa;
	private List<Zombie> zombiesPercibidos; //Lista de zombies que va percibiendo el agente
	private List<Girasol> girasolesPercibidos; //Lista de girasoles que va percibiendo el agente
	
	public EstadoAgente(int cantidadZombiesEnJuego) {
		this.setCantZombies(cantidadZombiesEnJuego);
		this.initState();
	}
	
	public EstadoAgente() {
		this.initState();
	}
	
	//Getters y setters
	
	public Point getPosicion() {
		return posicion;
	}
	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}
	public int getCantSolesDisponibles() {
		return cantSolesDisponibles;
	}
	public void setCantSolesDisponibles(int cantSolesDisponibles) {
		this.cantSolesDisponibles = cantSolesDisponibles;
	}
	public int getCantZombies() {
		return cantZombies;
	}
	public void setCantZombies(int cantZombies) {
		this.cantZombies = cantZombies;
	}
	public Celda[][] getMapa() {
		return mapa;
	}
	public void setMapa(Celda[][] mapa) {
		this.mapa = mapa;
	}
	public List<Zombie> getZombiesPercibidos() {
		return zombiesPercibidos;
	}
	public void setZombiesPercibidos(List<Zombie> zombiesPercibidos) {
		this.zombiesPercibidos = zombiesPercibidos;
	}
	public List<Girasol> getGirasolesPercibidos() {
		return girasolesPercibidos;
	}
	public void setGirasolesPercibidos(List<Girasol> girasolesPercibidos) {
		this.girasolesPercibidos = girasolesPercibidos;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public EstadoAgente clone() {
		EstadoAgente nuevoEstado = new EstadoAgente();
        nuevoEstado.setPosicion(new Point(posicion.x, posicion.y));
        nuevoEstado.setCantSolesDisponibles(cantSolesDisponibles);
        nuevoEstado.setCantZombies(cantZombies);
        
    	Celda[][] clonMapa = new Celda[mapa.length][mapa[0].length];
    	for(int i = 0; i<mapa.length; i++) {
    		for(int j = 0; j<mapa[i].length; j++) {
    			clonMapa[i][j]= mapa[i][j].clone();
    		}
    	}
        
        Celda[][] nuevoMapa=clonMapa;
        nuevoEstado.setMapa(nuevoMapa);
        
        List<Zombie> clonZombies = new ArrayList<Zombie>();
        for(int i=0; i<zombiesPercibidos.size(); i++) {
        	clonZombies.add(zombiesPercibidos.get(i).clone());
        }
        nuevoEstado.setZombiesPercibidos(clonZombies);
        
        List<Girasol> clonGirasoles = new ArrayList<Girasol>();
        for(int i=0; i<girasolesPercibidos.size(); i++) {
        	clonGirasoles.add(girasolesPercibidos.get(i).clone());
        }
        nuevoEstado.setGirasolesPercibidos(clonGirasoles);

        return nuevoEstado;
	}
	
	@Override
	public void updateState(Perception p) {
		
		AgentePercepciones nuevaPercepcion = (AgentePercepciones) p;
		
		this.setGirasolesPercibidos(nuevaPercepcion.getGirasolesPercibidos());
		this.setZombiesPercibidos(nuevaPercepcion.getZombiesPercibidos());
		this.setMapa(nuevaPercepcion.getMatrizPercibida());

	}
	
	@Override
	public String toString() {
		String str = "\n";
		
		str+="Ubicacion actual: ("+ this.posicion.x+","+this.posicion.y+")\n";
		str+="Cantidad de soles: "+this.cantSolesDisponibles+"\n";
		str+="Cantidad de zombies a matar: "+this.cantZombies+"\n";
		str+="Zombies percibidos: "+this.zombiesPercibidos+"\n";
		str+="Girasoles percibidos: "+this.girasolesPercibidos+"\n";
		
		str+="Matriz percibida: \n";
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				str+="["+this.mapa[i][j].toString()+"]";
			}
			str+="\n";
		}
		return str;
	}
	@Override
	public void initState() {
		Point posicion = new Point();
		posicion.x=2;
		posicion.y=1;
		this.setPosicion(posicion);
		int solesDisponibles= (int) Math.floor(Math.random()*(20-2+1)+2);
		this.setCantSolesDisponibles(solesDisponibles);
		//this.setCantZombies((int) Math.floor(Math.random()*(20-5+1)+5)); //Cantidad maxima de zombies en el inicio del juego
		//this.setCantZombies(1);
		this.setMapa(new Celda[5][9]);
		this.zombiesPercibidos = new ArrayList<Zombie>();
	}
	
	public void agregarGirasol(Girasol nuevoGirasol) {
		 this.girasolesPercibidos.add(nuevoGirasol);
	 }

}
