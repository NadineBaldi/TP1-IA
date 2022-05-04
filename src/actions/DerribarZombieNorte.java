package actions;

import java.awt.Point;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.AgentePercepciones;
import search.AgentePlanta;
import search.EstadoAgente;
import search.EstadoAmbiente;
import util.Celda;
import util.TipoEnum;
import util.Zombie;

public class DerribarZombieNorte extends SearchAction {	

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		
		EstadoAgente estadoPlanta = (EstadoAgente) s;
		
		Celda[][] mapa= estadoPlanta.getMapa();
		AgentePercepciones percepcion = estadoPlanta.getPercepcion();
		Point ubicacion = estadoPlanta.getPosicion();
		java.util.List<Zombie> listaZombies = percepcion.getZombiesPercibidos();
		
		
		if((mapa[ubicacion.x+1][ubicacion.y].getTipo().equals(TipoEnum.ZOMBIE))){
				boolean bandera=true;
			
				for(int i=0;i<listaZombies.size() && bandera;i++) {
						
						if((listaZombies.get(i).getUbicacionZombie().x==ubicacion.x+1) &&
						  (listaZombies.get(i).getUbicacionZombie().y==ubicacion.y)) {
							listaZombies.remove(i);
						
							percepcion.setZombiesPercibidos(listaZombies);
							estadoPlanta.setCantSolesDisponibles(estadoPlanta.getCantSolesDisponibles()-listaZombies.get(i).getCosteDeSoles());
							mapa[ubicacion.x+1][ubicacion.y].setTipo(TipoEnum.VACIO);
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
		if((mapa[ubicacion.x+1][ubicacion.y].getTipo().equals(TipoEnum.ZOMBIE))){
				boolean bandera=true;
			
				for(int i=0;i<listaZombies.size() && bandera;i++) {
						
						if((listaZombies.get(i).getUbicacionZombie().x==ubicacion.x+1) &&
						  (listaZombies.get(i).getUbicacionZombie().y==ubicacion.y)) {
							listaZombies.remove(i);
						
							percepcion.setZombiesPercibidos(listaZombies);
							estadoPlanta.setCantSolesDisponibles(estadoPlanta.getCantSolesDisponibles()-listaZombies.get(i).getCosteDeSoles());
							mapa[ubicacion.x+1][ubicacion.y].setTipo(TipoEnum.VACIO);
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
