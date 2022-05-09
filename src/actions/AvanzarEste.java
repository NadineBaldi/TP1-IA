package actions;

import java.awt.Point;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.EstadoAgente;
import search.EstadoAmbiente;
import util.Celda;
import util.TipoEnum;

public class AvanzarEste extends SearchAction{


	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		
		EstadoAgente estadoPlanta = (EstadoAgente) s;
		EstadoAgente siguienteEstado = estadoPlanta.clone();
		
		if (estadoPlanta.getCantSolesDisponibles() > 0) { //ejecutar la accion solo si el agente tiene soles disponibles (CONDICION DE PARADA)
		
			Celda[][] mapa = estadoPlanta.getMapa();
			Point posicion = estadoPlanta.getPosicion();
	
			// Chequeo si se puede ir para adelante o estoy en el borde derecho
			if(posicion.y < 8) {
			
				siguienteEstado.setPosicion(new Point(posicion.x, posicion.y+1));
				// Actualizo el mapa del agente poniendo en vacia su posicion anterior (o si hay un girasol, pongo el girasol)
				// y colocando al agente en la nueva posicion
				if (mapa[posicion.x][posicion.y].getTipo() == TipoEnum.GIRASOL_CON_PLANTA) {
					mapa[posicion.x][posicion.y].setTipo(TipoEnum.GIRASOL); 
				} else {
					mapa[posicion.x][posicion.y].setTipo(TipoEnum.VACIO); 	
				}
				if (mapa[posicion.x][posicion.y+1].getTipo() == TipoEnum.GIRASOL) {
					mapa[posicion.x][posicion.y+1].setTipo(TipoEnum.GIRASOL_CON_PLANTA);
				} else {
					mapa[posicion.x][posicion.y+1].setTipo(TipoEnum.PLANTA_AGENTE);
				}
				siguienteEstado.setMapa(mapa);
				
				return siguienteEstado;
				
			} 
		} 
		
		return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
		EstadoAgente estadoPlanta = (EstadoAgente) ast;
		
		if (estadoPlanta.getCantSolesDisponibles() > 0) { //ejecutar la accion solo si el agente tiene soles disponibles (CONDICION DE PARADA)
		
			Celda[][] mapaAgente = estadoPlanta.getMapa();
			Celda[][] mapaAmbiente = estadoAmbiente.getMapa();
			Point posicion = estadoAmbiente.getUbicacionAgente();
		
			int columna = posicion.y;
	
			// Chequeo si se puede ir para atras o estoy en el borde izquierdo
			if(columna < 8) {
							
				estadoPlanta.setPosicion(new Point(posicion.x, posicion.y+1));
				estadoAmbiente.setUbicacionAgente(new Point(posicion.x, posicion.y+1));
				// Actualizo el mapa del agente poniendo en vacia su posicion anterior (o si hay un girasol, pongo el girasol)
				// y colocando al agente en la nueva posicion
				if (mapaAgente[posicion.x][posicion.y].getTipo() == TipoEnum.GIRASOL_CON_PLANTA) {
					mapaAgente[posicion.x][posicion.y].setTipo(TipoEnum.GIRASOL); 
				} else {
					mapaAgente[posicion.x][posicion.y].setTipo(TipoEnum.VACIO); 	
				} 
				if (mapaAgente[posicion.x][posicion.y+1].getTipo() == TipoEnum.GIRASOL) {
					mapaAgente[posicion.x][posicion.y+1].setTipo(TipoEnum.GIRASOL_CON_PLANTA);
				} else {
					mapaAgente[posicion.x][posicion.y+1].setTipo(TipoEnum.PLANTA_AGENTE);
				}
				estadoPlanta.setMapa(mapaAgente);
				// Actualizo el mapa del agente poniendo en vacia su posicion anterior (o si hay un girasol, pongo el girasol)
				// y colocando al agente en la nueva posicion
				if (mapaAmbiente[posicion.x][posicion.y].getTipo() == TipoEnum.GIRASOL_CON_PLANTA) {
					mapaAmbiente[posicion.x][posicion.y].setTipo(TipoEnum.GIRASOL); 
				} else {
					mapaAmbiente[posicion.x][posicion.y].setTipo(TipoEnum.VACIO); 	
				} 
				if (mapaAmbiente[posicion.x][posicion.y+1].getTipo() == TipoEnum.GIRASOL) {
					mapaAmbiente[posicion.x][posicion.y+1].setTipo(TipoEnum.GIRASOL_CON_PLANTA);
				} else {
					mapaAmbiente[posicion.x][posicion.y+1].setTipo(TipoEnum.PLANTA_AGENTE);
				}
				estadoAmbiente.setMapa(mapaAmbiente);
				
				return estadoAmbiente;
						
			}
		}	
		return null;
	}
	
	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return 1.0;
	}

	@Override
	public String toString() {
		return "Avanza este";
	}
	
}
