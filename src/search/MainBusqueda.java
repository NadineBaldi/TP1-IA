package search;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class MainBusqueda {
	  public static void main(String[] args) throws PrologConnectorException {
	        AgentePlanta agentePlanta = new AgentePlanta();
	        Ambiente ambiente = new Ambiente();
	     
	        SearchBasedAgentSimulator simulator =
	                new SearchBasedAgentSimulator(ambiente,agentePlanta);
	        
	        simulator.start();
	    }
}
