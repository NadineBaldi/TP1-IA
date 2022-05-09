package actions;

import java.awt.Point;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.EstadoAgente;
import search.EstadoAmbiente;
import util.Celda;
import util.Girasol;
import util.TipoEnum;

public class PlantarGirasol extends SearchAction{


	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		//PLANTA GIRASOL EN LA POSICION EN LA QUE SE ENCUENTRA
		EstadoAgente estadoPlanta = (EstadoAgente) s;
		EstadoAgente siguienteEstado = estadoPlanta.clone();
		
		if (estadoPlanta.getCantSolesDisponibles() > 0) { //ejecutar la accion solo si el agente tiene soles disponibles (CONDICION DE PARADA)
		
			Girasol nuevoGirasol = new Girasol();
			Celda[][] mapa = siguienteEstado.getMapa();
			Point ubicacion = siguienteEstado.getPosicion();
			Celda celdaActual = mapa[ubicacion.x][ubicacion.y];
			
			if (celdaActual.puedePlantar()) {
				nuevoGirasol.setUbicacionGirasol(new Point(ubicacion.x, ubicacion.y));
				nuevoGirasol.setCantSoles(0);
				
				mapa[ubicacion.x][ubicacion.y] = new Celda(TipoEnum.GIRASOL_CON_PLANTA);
			
				siguienteEstado.setMapa(mapa);
				siguienteEstado.agregarGirasol(nuevoGirasol);
				
				return siguienteEstado;
				
			} 
		}
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return 1.5;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		
	     EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
	     EstadoAgente estadoPlanta = (EstadoAgente) ast;
	
	     if (estadoPlanta.getCantSolesDisponibles() > 0) { //ejecutar la accion solo si el agente tiene soles disponibles (CONDICION DE PARADA)
	     
		     Girasol nuevoGirasol = new Girasol();
			 Celda[][] mapaAgente = estadoPlanta.getMapa();
			 Celda[][] mapaAmbiente = estadoAmbiente.getMapa();
			 Point ubicacion = estadoPlanta.getPosicion();
			 Celda celdaActual = mapaAgente[ubicacion.x][ubicacion.y];
			
			 if (celdaActual.puedePlantar()) {
				nuevoGirasol.setUbicacionGirasol(new Point(ubicacion.x, ubicacion.y));
				nuevoGirasol.setCantSoles(0);
				
				mapaAgente[ubicacion.x][ubicacion.y] = new Celda(TipoEnum.GIRASOL_CON_PLANTA);
				mapaAmbiente[ubicacion.x][ubicacion.y] = new Celda(TipoEnum.GIRASOL_CON_PLANTA);
				
				estadoPlanta.setMapa(mapaAgente);
				estadoPlanta.agregarGirasol(nuevoGirasol);
				estadoAmbiente.setMapa(mapaAmbiente);
				estadoAmbiente.agregarGirasol(nuevoGirasol);
				
				return estadoAmbiente;
				
			 } 
	     }
	     return null;
	}

	@Override
	public String toString() {
		return "Plantar girasol";
	}

}
