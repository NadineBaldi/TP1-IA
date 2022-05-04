package actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.EstadoAgente;
import search.EstadoAmbiente;
import util.Celda;
import util.Girasol;

public class PlantarGirasol extends SearchAction{


	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		//PLANTA GIRASOL EN LA POSICION EN LA QUE SE ENCUENTRA
		EstadoAgente estadoPlanta = (EstadoAgente) s;
		Girasol nuevoGirasol = new Girasol();
		Celda[][] mapa= estadoPlanta.getMapa();
		
		nuevoGirasol.setUbicacionGirasol(estadoPlanta.getPosicion());
		nuevoGirasol.setCantSoles(0);
		
		mapa[estadoPlanta.getPosicion().x][estadoPlanta.getPosicion().y].setCelda(nuevoGirasol);
	
		estadoPlanta.setMapa(mapa);
		
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
	     EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
	     EstadoAgente estadoPlanta = (EstadoAgente) ast;
	
	     	Girasol nuevoGirasol = new Girasol();
			Celda[][] mapa= estadoPlanta.getMapa();
			
			nuevoGirasol.setUbicacionGirasol(estadoPlanta.getPosicion());
			nuevoGirasol.setCantSoles(0);
			
			mapa[estadoPlanta.getPosicion().x][estadoPlanta.getPosicion().y].setCelda(nuevoGirasol);
		
			estadoPlanta.setMapa(mapa);
			estadoAmbiente.addGirasol(nuevoGirasol);
			estadoAmbiente.setMapa(mapa);
	     
	     return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
