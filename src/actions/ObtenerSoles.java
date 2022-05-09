package actions;

import java.awt.Point;
import java.util.List;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.EstadoAgente;
import search.EstadoAmbiente;
import util.Celda;
import util.Girasol;

public class ObtenerSoles extends SearchAction {

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		
		EstadoAgente estadoPlanta = (EstadoAgente) s;
		EstadoAgente siguienteEstado = estadoPlanta.clone();
		
		if (estadoPlanta.getCantSolesDisponibles() > 0) { //ejecutar la accion solo si el agente tiene soles disponibles (CONDICION DE PARADA)
		
			Celda[][] mapa= estadoPlanta.getMapa();
			Point ubicacion = estadoPlanta.getPosicion();
			Celda celdaActual = mapa[ubicacion.x][ubicacion.y];
			List<Girasol> girasolesPercibidos = estadoPlanta.getGirasolesPercibidos();
			
			if (celdaActual.hayGirasolConPlanta()) {
					boolean bandera=true;
					int index = -1;
				
					for(int i=0;i<girasolesPercibidos.size() && bandera;i++) {
							
							if((girasolesPercibidos.get(i).getUbicacionGirasol().x==ubicacion.x) &&
							  (girasolesPercibidos.get(i).getUbicacionGirasol().y==ubicacion.y)) {
								
								if(girasolesPercibidos.get(i).getCantSoles() > 0) {
									index = i;
								}
								
								bandera=false;
							}
						}
					
					if (index != -1) {
						// Le sumo a la cant de soles que tenia el agente los nuevos soles obtenidos
						siguienteEstado.setCantSolesDisponibles(siguienteEstado.getCantSolesDisponibles() + girasolesPercibidos.get(index).cantSoles);
						// Le saco al girasol todos los soles que ya fueron consumidos por el agente
						girasolesPercibidos.get(index).setCantSoles(0);
							
						siguienteEstado.setGirasolesPercibidos(girasolesPercibidos);
						return siguienteEstado;
					}
						
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
		
		EstadoAgente estadoPlanta = (EstadoAgente) ast;
		EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
		
		if (estadoPlanta.getCantSolesDisponibles() > 0) { //ejecutar la accion solo si el agente tiene soles disponibles (CONDICION DE PARADA)
		
			Celda[][] mapaAgente = estadoPlanta.getMapa();
			Point ubicacion = estadoPlanta.getPosicion();
			Celda celdaActual = mapaAgente[ubicacion.x][ubicacion.y];
			List<Girasol> girasolesPercibidos = estadoPlanta.getGirasolesPercibidos();
			List<Girasol> listaGirasoles = estadoAmbiente.getGirasoles();
			
			if(celdaActual.hayGirasolConPlanta()){
					boolean bandera=true;
					int index1 = -1;
					int index2 = -1;
				
					// Recorro la lista de girasoles que hay en el ambiente y actualizo
					for(int i=0;i<listaGirasoles.size() && bandera;i++) {
							
						if((listaGirasoles.get(i).getUbicacionGirasol().x==ubicacion.x) &&
						  (listaGirasoles.get(i).getUbicacionGirasol().y==ubicacion.y)) {
							
							if(listaGirasoles.get(i).getCantSoles() > 0) {
								index1 = i;
							}
						bandera=false;
						}
					}
					
					bandera = true;
					
					// Recorro la lista de girasoles percibidos por el agente y la actualizo
					for(int j=0;j<girasolesPercibidos.size() && bandera;j++) {
						
						if((girasolesPercibidos.get(j).getUbicacionGirasol().x==ubicacion.x) &&
						  (girasolesPercibidos.get(j).getUbicacionGirasol().y==ubicacion.y)) {
							
							if(girasolesPercibidos.get(j).getCantSoles() > 0) {
								index2 = j;
							}
							
							bandera=false;
						}
					}
					
					if (index2 != -1) {
						// Le sumo a la cant de soles que tenia el agente los nuevos soles obtenidos
						estadoPlanta.setCantSolesDisponibles(estadoPlanta.getCantSolesDisponibles() + girasolesPercibidos.get(index2).cantSoles);
						// Le saco al girasol todos los soles que ya fueron consumidos por el agente
						girasolesPercibidos.get(index2).setCantSoles(0);
							
						estadoPlanta.setGirasolesPercibidos(girasolesPercibidos);
					}
					
					if (index1 != -1) {
						// Le saco al girasol todos los soles que ya fueron consumidos por el agente
						listaGirasoles.get(index1).setCantSoles(0);	
						return estadoAmbiente;
					}
					
			}
		}
		
		return null;	
	}

	@Override
	public String toString() {
		return "Obtener soles";
	}
}
