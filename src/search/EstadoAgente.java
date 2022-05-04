package search;

import java.awt.Point;
import java.util.List;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import util.Celda;
import util.Girasol;

public class EstadoAgente extends SearchBasedAgentState{
	
	private Point posicion;
	private String direccion;
	private int cantSolesDisponibles;
	private int cantZombies;
	private Celda[][] mapa;
	
	//Getters y setters
	
	public Point getPosicion() {
		return posicion;
	}
	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public EstadoAgente clone() {
		EstadoAgente nuevoEstado = new EstadoAgente();
        nuevoEstado.setPosicion(posicion);
        nuevoEstado.setCantSolesDisponibles(cantSolesDisponibles);
        nuevoEstado.setCantZombies(cantZombies);
        Celda[][] nuevoMapa= mapa.clone();
        nuevoEstado.setMapa(nuevoMapa);

        return nuevoEstado;
	}
	
	@Override
	public void updateState(Perception p) {
		// TODO ACTUALIZAR ESTADO 
		int solesPercibidos=0;
		AgentePercepciones nuevaPercepcion = (AgentePercepciones) p;
		List<Girasol> girasoles = nuevaPercepcion.getGirasolesPercibidos();
		for(int i=0;i<girasoles.size();i++) {
			solesPercibidos+=girasoles.get(i).cantSoles;
		}
		this.setCantSolesDisponibles(solesPercibidos);
		this.setCantZombies(nuevaPercepcion.getZombiesPercibidos().size());
		this.setMapa(nuevaPercepcion.getMatrizPercibida());
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void initState() {
		// TODO Auto-generated method stub
		EstadoAgente estadoInicial = new EstadoAgente();
		Point posicion = new Point();
		posicion.x=3;
		posicion.y=1;
		estadoInicial.setPosicion(posicion);
		int solesDisponibles= (int) Math.floor(Math.random()*(20-2+1)+2); 
		estadoInicial.setCantSolesDisponibles(solesDisponibles);
		estadoInicial.setCantZombies(0);
		estadoInicial.setMapa(new Celda[5][4]);
	}
	
	public AgentePercepciones getPercepcion() {
		// TODO Auto-generated method stub
		return null;
	}

}
