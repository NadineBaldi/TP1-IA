package actions;

import java.awt.Point;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.AgentePercepciones;
import search.EstadoAgente;
import search.EstadoAmbiente;
import util.Celda;
import util.TipoEnum;
import util.Zombie;

public class DerribarZombieEste extends SearchAction {

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		// TODO Auto-generated method stub
		EstadoAgente estadoPlanta = (EstadoAgente) s;
		
		Celda[][] mapa= estadoPlanta.getMapa();
		AgentePercepciones percepcion = estadoPlanta.getPercepcion();
		Point ubicacion = estadoPlanta.getPosicion();
		java.util.List<Zombie> listaZombies = percepcion.getZombiesPercibidos();
		
		
		if((mapa[ubicacion.x][ubicacion.y+1].getTipo().equals(TipoEnum.ZOMBIE))){
				boolean bandera=true;
			
				for(int i=0;i<listaZombies.size() && bandera;i++) {
						
						if((listaZombies.get(i).getUbicacionZombie().x==ubicacion.x) &&
						  (listaZombies.get(i).getUbicacionZombie().y==ubicacion.y+1)) {
							listaZombies.remove(i);
						
							percepcion.setZombiesPercibidos(listaZombies);
							estadoPlanta.setCantSolesDisponibles(estadoPlanta.getCantSolesDisponibles()-listaZombies.get(i).getCosteDeSoles());
							mapa[ubicacion.x][ubicacion.y+1].setTipo(TipoEnum.VACIO);
							estadoPlanta.setCantZombies(estadoPlanta.getCantZombies()-1);
							bandera=false;
						}
					}
					
				}
		
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		// TODO Auto-generated method stub
		EstadoAgente estadoPlanta = (EstadoAgente) ast;
		EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
		
		Celda[][] mapa= estadoPlanta.getMapa();
		java.util.List<Zombie> listaZombies = estadoAmbiente.getZombies();
		AgentePercepciones percepcion = estadoPlanta.getPercepcion();
		Point ubicacion = estadoPlanta.getPosicion();
		if((mapa[ubicacion.x][ubicacion.y+1].getTipo().equals(TipoEnum.ZOMBIE))){
				boolean bandera=true;
			
				for(int i=0;i<listaZombies.size() && bandera;i++) {
						
						if((listaZombies.get(i).getUbicacionZombie().x==ubicacion.x) &&
						  (listaZombies.get(i).getUbicacionZombie().y==ubicacion.y+1)) {
							listaZombies.remove(i);
						
							percepcion.setZombiesPercibidos(listaZombies);
							estadoPlanta.setCantSolesDisponibles(estadoPlanta.getCantSolesDisponibles()-listaZombies.get(i).getCosteDeSoles());
							mapa[ubicacion.x][ubicacion.y+1].setTipo(TipoEnum.VACIO);
							estadoPlanta.setCantZombies(estadoPlanta.getCantZombies()-1);
							
							estadoAmbiente.setZombies(listaZombies);
							estadoAmbiente.setMapa(mapa);
							bandera=false;
						}
					}
					
				}
		
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
