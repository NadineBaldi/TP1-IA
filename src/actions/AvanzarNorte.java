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
import util.Zombie;


public class AvanzarNorte extends SearchAction{
	
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
	
	EstadoAgente estadoPlanta = (EstadoAgente) s;
	EstadoAgente siguienteEstado = estadoPlanta.clone();
	
	if (estadoPlanta.getCantSolesDisponibles() > 0) { //ejecutar la accion solo si el agente tiene soles disponibles (CONDICION DE PARADA)
	
		Celda[][] mapa = siguienteEstado.getMapa();
		Point posicion = siguienteEstado.getPosicion();
	
			// Chequeo si se puede ir para arriba o estoy en el borde superior
			if (posicion.x > 0) {
		
				siguienteEstado.setPosicion(new Point(posicion.x-1, posicion.y));
				// Actualizo el mapa del agente poniendo en vacia su posicion anterior (o si hay un girasol, pongo el girasol)
				// y colocando al agente en la nueva posicion
				
				// Celda anterior
				if (mapa[posicion.x][posicion.y].getTipo() == TipoEnum.GIRASOL_CON_PLANTA) {
					mapa[posicion.x][posicion.y].setTipo(TipoEnum.GIRASOL); 
				} else {
					mapa[posicion.x][posicion.y].setTipo(TipoEnum.VACIO); 	
				}
				
				// Celda siguiente a la que se mueve el agente
				if (mapa[posicion.x-1][posicion.y].getTipo() == TipoEnum.GIRASOL) {
					mapa[posicion.x-1][posicion.y].setTipo(TipoEnum.GIRASOL_CON_PLANTA);
				} else {
					if (mapa[posicion.x-1][posicion.y].getTipo() == TipoEnum.ZOMBIE) { // si en la proxima celda hay un zombie
						
						Zombie zombieEncontrado = siguienteEstado.buscarZombie(posicion.x-1, posicion.y);
						int costoDeMatarZombie = zombieEncontrado.getCosteDeSoles()*2; //Cuesta el doble matar a un zombie si la planta se mueve a su celda
						
						//Si la planta tiene mas soles que la cantidad necesaria p matar al zombie multiplicado por dos
						if (costoDeMatarZombie < siguienteEstado.getCantSolesDisponibles()) { 
							mapa[posicion.x-1][posicion.y].setTipo(TipoEnum.PLANTA_AGENTE);	//reemplazo la celda con zombie por una celda con agente
							siguienteEstado.setCantSolesDisponibles(siguienteEstado.getCantSolesDisponibles() - costoDeMatarZombie); //le resto los soles sacados x el zombie
							siguienteEstado.getZombiesPercibidos().remove(zombieEncontrado); //elimino de la lista de zombies percibidos al zombie matado
							siguienteEstado.setCantZombies(siguienteEstado.getCantZombies() - 1);
						} else { //no le alcanzan los soles y muere la planta
							siguienteEstado.setCantSolesDisponibles(0);
							return null;
						}
					} else {
						mapa[posicion.x-1][posicion.y].setTipo(TipoEnum.PLANTA_AGENTE);	
					}
				}
				siguienteEstado.setMapa(mapa);
									
				return siguienteEstado;
						
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
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
		EstadoAgente estadoPlanta = (EstadoAgente) ast;
		
		if (estadoPlanta.getCantSolesDisponibles() > 0) { //ejecutar la accion solo si el agente tiene soles disponibles (CONDICION DE PARADA)
			
			Celda[][] mapaAgente = estadoPlanta.getMapa();
			Celda[][] mapaAmbiente = estadoAmbiente.getMapa();
			Point posicion = estadoPlanta.getPosicion();
			
			int fila = posicion.x;
			
			// Chequeo si se puede ir para abajo o estoy en el borde inferior
				if (fila > 0) {
					
					estadoPlanta.setPosicion(new Point(posicion.x-1, posicion.y));
					estadoAmbiente.setUbicacionAgente(new Point(posicion.x-1, posicion.y));
					// Actualizo el mapa del agente poniendo en vacia su posicion anterior (o si hay un girasol, pongo el girasol)
					// y colocando al agente en la nueva posicion
					
					//Celda anterior
					if (mapaAgente[posicion.x][posicion.y].getTipo() == TipoEnum.GIRASOL_CON_PLANTA) {
						mapaAgente[posicion.x][posicion.y].setTipo(TipoEnum.GIRASOL); 
					} else {
						mapaAgente[posicion.x][posicion.y].setTipo(TipoEnum.VACIO); 	
					} 
					
					//Celda siguiente
					if (mapaAgente[posicion.x-1][posicion.y].getTipo() == TipoEnum.GIRASOL) {
						mapaAgente[posicion.x-1][posicion.y].setTipo(TipoEnum.GIRASOL_CON_PLANTA);
					} else {
						if (mapaAgente[posicion.x-1][posicion.y].getTipo() == TipoEnum.ZOMBIE) { // si en la proxima celda hay un zombie
							
							Zombie zombieEncontrado = estadoPlanta.buscarZombie(posicion.x-1, posicion.y);
							int costoDeMatarZombie = zombieEncontrado.getCosteDeSoles()*2; //Cuesta el doble matar a un zombie si la planta se mueve a su celda
							
							//Si la planta tiene mas soles que la cantidad necesaria p matar al zombie multiplicado por dos
							if (costoDeMatarZombie < estadoPlanta.getCantSolesDisponibles()) { 
								mapaAgente[posicion.x-1][posicion.y].setTipo(TipoEnum.PLANTA_AGENTE);	//reemplazo la celda con zombie por una celda con agente
								estadoPlanta.setCantSolesDisponibles(estadoPlanta.getCantSolesDisponibles() - costoDeMatarZombie); //le resto los soles sacados x el zombie
								estadoPlanta.getZombiesPercibidos().remove(zombieEncontrado); //elimino de la lista de zombies percibidos al zombie matado
								estadoPlanta.setCantZombies(estadoPlanta.getCantZombies() - 1);
							} else { //no le alcanzan los soles y muere la planta
								estadoPlanta.setCantSolesDisponibles(0);
							}
						} else {
							mapaAgente[posicion.x-1][posicion.y].setTipo(TipoEnum.PLANTA_AGENTE);	
						}
					}
					estadoPlanta.setMapa(mapaAgente);
					
					// Actualizo el mapa del agente poniendo en vacia su posicion anterior (o si hay un girasol, pongo el girasol)
					// y colocando al agente en la nueva posicion
					
					//Celda anterior
					if (mapaAmbiente[posicion.x][posicion.y].getTipo() == TipoEnum.GIRASOL_CON_PLANTA) {
						mapaAmbiente[posicion.x][posicion.y].setTipo(TipoEnum.GIRASOL); 
					} else {
						mapaAmbiente[posicion.x][posicion.y].setTipo(TipoEnum.VACIO); 	
					} 
					//Celda siguiente
					if (mapaAmbiente[posicion.x-1][posicion.y].getTipo() == TipoEnum.GIRASOL) {
						mapaAmbiente[posicion.x-1][posicion.y].setTipo(TipoEnum.GIRASOL_CON_PLANTA);
					} else {
						if (mapaAmbiente[posicion.x-1][posicion.y].getTipo() == TipoEnum.ZOMBIE) { // si en la proxima celda hay un zombie
							
							Zombie zombieEncontrado = estadoPlanta.buscarZombie(posicion.x-1, posicion.y);
							int costoDeMatarZombie = zombieEncontrado.getCosteDeSoles()*2; //Cuesta el doble matar a un zombie si la planta se mueve a su celda
							
							//Si la planta tiene mas soles que la cantidad necesaria p matar al zombie multiplicado por dos
							if (costoDeMatarZombie < estadoPlanta.getCantSolesDisponibles()) { 
								mapaAmbiente[posicion.x-1][posicion.y].setTipo(TipoEnum.PLANTA_AGENTE);	//reemplazo la celda con zombie por una celda con agente
								estadoPlanta.setCantSolesDisponibles(estadoPlanta.getCantSolesDisponibles() - costoDeMatarZombie); //le resto los soles sacados x el zombie
								estadoPlanta.getZombiesPercibidos().remove(zombieEncontrado); //elimino de la lista de zombies percibidos al zombie matado
								estadoPlanta.setCantZombies(estadoPlanta.getCantZombies() - 1);
							} else { //no le alcanzan los soles y muere la planta
								estadoPlanta.setCantSolesDisponibles(0);
							}
						} else {
							mapaAmbiente[posicion.x-1][posicion.y].setTipo(TipoEnum.PLANTA_AGENTE);	
						}
					}
					estadoAmbiente.setMapa(mapaAmbiente);
						
					return estadoAmbiente;
						
				}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Avanzar norte";
	}	
	
}
