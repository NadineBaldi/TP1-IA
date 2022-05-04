package actions;



import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import search.EstadoAgente;
import search.EstadoAmbiente;


public class AvanzarNorte extends SearchAction{
	
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		// TODO Auto-generated method stub
	
	EstadoAgente estadoPlanta = (EstadoAgente) s;
	java.awt.Point posicion = null;
	
	int fila=estadoPlanta.getPosicion().x;
	int columna = estadoPlanta.getPosicion().y;

	if(fila==4) {
		fila-=1;
	}
	else {
		fila+=1;
	}
	posicion.x=fila;
	posicion.y=columna;
	estadoPlanta.setPosicion(posicion);
	
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
		
		java.awt.Point posicion = null;
		int fila=estadoPlanta.getPosicion().x;
		int columna = estadoPlanta.getPosicion().y;
		
		if(fila==4) {
			fila-=1;
		}
		else {
			fila+=1;
		}
		posicion.x=fila;
		posicion.y=columna;
		estadoPlanta.setPosicion(posicion);
		
		estadoAmbiente.setUbicacionAgente(posicion);
        
		
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
