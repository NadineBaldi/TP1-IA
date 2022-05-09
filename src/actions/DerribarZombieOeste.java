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
import util.TipoEnum;
import util.Zombie;

public class DerribarZombieOeste extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		
		EstadoAgente estadoPlanta = (EstadoAgente) s;
		EstadoAgente siguienteEstado = estadoPlanta.clone();
		
		if (estadoPlanta.getCantSolesDisponibles() > 0) { //ejecutar la accion solo si el agente tiene soles disponibles (CONDICION DE PARADA)
		
			Celda[][] mapa= estadoPlanta.getMapa();
			Point ubicacion = estadoPlanta.getPosicion();
			List<Zombie> zombiesPercibidos = estadoPlanta.getZombiesPercibidos();
			
			if (ubicacion.y > 0) { //Chequeo que el agente no se encuentre en la primera columna (sobre el borde izq) y quiera matar a alguien de atras
				
				Celda celdaAdyacente = mapa[ubicacion.x][ubicacion.y-1];
				
				if(celdaAdyacente.hayZombie()){ //si hay zombie en la celda adyacente
					
						boolean bandera=true;
						int index = -1;
					
						for(int i=0;i<zombiesPercibidos.size() && bandera;i++) {
								
								if((zombiesPercibidos.get(i).getUbicacionZombie().x==ubicacion.x) &&
								  (zombiesPercibidos.get(i).getUbicacionZombie().y==ubicacion.y-1)) {
									
									// Chequeo si le alcanzan los soles para matar al zombie
									if (zombiesPercibidos.get(i).getCosteDeSoles() < estadoPlanta.getCantSolesDisponibles()) {
										index = 1;
									}
									bandera=false;
								}
							}
						
						if (index != -1) {
							
							siguienteEstado.setZombiesPercibidos(zombiesPercibidos);
							siguienteEstado.setCantSolesDisponibles(estadoPlanta.getCantSolesDisponibles()-zombiesPercibidos.get(index).getCosteDeSoles());
							mapa[ubicacion.x][ubicacion.y-1].setTipo(TipoEnum.VACIO);
							estadoPlanta.setCantZombies(estadoPlanta.getCantZombies()-1);
							
							zombiesPercibidos.remove(index);
							
							return siguienteEstado;
						}
							
				}
			
			}
		}
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return 2.0;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		
		EstadoAgente estadoPlanta = (EstadoAgente) ast;
		EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
		
		if (estadoPlanta.getCantSolesDisponibles() > 0) { //ejecutar la accion solo si el agente tiene soles disponibles (CONDICION DE PARADA)
		
			Celda[][] mapaAgente = estadoPlanta.getMapa();
			Celda[][] mapaAmbiente = estadoAmbiente.getMapa();
			
			Point ubicacion = estadoPlanta.getPosicion();
			
			List<Zombie> zombiesPercibidos = estadoPlanta.getZombiesPercibidos();
			List<Zombie> listaZombies = estadoAmbiente.getZombies();
			
			if (ubicacion.y > 0) { //Chequeo que el agente no se encuentre en la primera columna (sobre el borde izq) y quiera matar a alguien de atras
				
				Celda celdaAdyacente = mapaAgente[ubicacion.x][ubicacion.y-1];
				
				if(celdaAdyacente.hayZombie()){ //si hay zombie en la celda adyacente
					
					boolean bandera=true;
					int index1 = -1;
					int index2 = -1;
				
					// Recorro la lista de zombies que hay en el ambiente y actualizo el estado de la lista si se elimina uno
					for(int i=0;i<listaZombies.size() && bandera;i++) {
							
						if((listaZombies.get(i).getUbicacionZombie().x==ubicacion.x) &&
						  (listaZombies.get(i).getUbicacionZombie().y==ubicacion.y-1)) {
								
							// Chequeo si le alcanzan los soles para matar al zombie
							if (listaZombies.get(i).getCosteDeSoles() < estadoPlanta.getCantSolesDisponibles()) {
								index1 = i;
							}
							bandera=false;
						}
					}
					
					// Recorro la lista de zombies percibidos por el agente y la actualizo si se borra un zombie de la misma
					for(int j=0;j<zombiesPercibidos.size() && bandera;j++) {
						
						if((zombiesPercibidos.get(j).getUbicacionZombie().x==ubicacion.x) &&
						  (zombiesPercibidos.get(j).getUbicacionZombie().y==ubicacion.y-1)) {
							// Chequeo si le alcanzan los soles para matar al zombie
							if (zombiesPercibidos.get(j).getCosteDeSoles() < estadoPlanta.getCantSolesDisponibles()) {
								index2 = j;
							}
							bandera=false;
						}
					}
					
					if (index1 != -1) {
						
						estadoAmbiente.setZombies(listaZombies);
						estadoPlanta.setCantSolesDisponibles(estadoPlanta.getCantSolesDisponibles()-listaZombies.get(index1).getCosteDeSoles());
						mapaAmbiente[ubicacion.x][ubicacion.y-1].setTipo(TipoEnum.VACIO);
						estadoPlanta.setCantZombies(estadoPlanta.getCantZombies()-1);
						estadoAmbiente.setMapa(mapaAmbiente);
						
						listaZombies.remove(index1);
						estadoAmbiente.setZombies(listaZombies);
						
						return estadoAmbiente;
					}
					
					if (index2 != -1) {
						
						estadoPlanta.setZombiesPercibidos(zombiesPercibidos);
						estadoPlanta.setCantSolesDisponibles(estadoPlanta.getCantSolesDisponibles()-zombiesPercibidos.get(index2).getCosteDeSoles());
						mapaAgente[ubicacion.x][ubicacion.y-1].setTipo(TipoEnum.VACIO);
						estadoPlanta.setCantZombies(estadoPlanta.getCantZombies()-1);
						estadoPlanta.setMapa(mapaAgente);
						
						zombiesPercibidos.remove(index2);
					}
				}		
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Derribar zombie oeste";
	}

}
