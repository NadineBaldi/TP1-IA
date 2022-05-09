package search;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class AgenteMain {
	
	public static void main(String[] args) throws PrologConnectorException {
		
		//Cantidad maxima de zombies en el inicio del juego
		int cantidadZombiesEnJuego = (int) Math.floor(Math.random()*(20-5+1)+5); 
		
		AgentePlanta agent = new AgentePlanta(cantidadZombiesEnJuego);
		
		Ambiente environment = new Ambiente(cantidadZombiesEnJuego);
		
		SearchBasedAgentSimulator simulator = 
				new SearchBasedAgentSimulator(environment, agent);
		
		simulator.start();
		
	}

}
