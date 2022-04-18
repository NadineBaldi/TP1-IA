package search;

import java.awt.Point;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import util.Celda;

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
	public SearchBasedAgentState clone() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateState(Perception p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void initState() {
		// TODO Auto-generated method stub
		
	}
	

}
