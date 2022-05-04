package search;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.Search;
import actions.AvanzarEste;
import actions.AvanzarNorte;
import actions.AvanzarOeste;
import actions.AvanzarSur;
import actions.DerribarZombieEste;
import actions.DerribarZombieNorte;
import actions.DerribarZombieOeste;
import actions.DerribarZombieSur;
import actions.ObtenerSoles;
import actions.PlantarGirasol;

public class AgentePlanta extends SearchBasedAgent{
	
	public AgentePlanta() {
		ObjetivoAgente objetivoAgente = new ObjetivoAgente();
		EstadoAgente estadoPlanta = new EstadoAgente();
		this.setAgentState(estadoPlanta);
	
		Vector<SearchAction> operadores = new Vector<SearchAction>();
		operadores.add(new AvanzarNorte());
		operadores.add(new AvanzarSur());
		operadores.add(new AvanzarEste());
		operadores.add(new AvanzarOeste());
		operadores.add(new DerribarZombieNorte());
		operadores.add(new DerribarZombieSur());
		operadores.add(new DerribarZombieEste());
		operadores.add(new DerribarZombieOeste());
		operadores.add(new ObtenerSoles());
		operadores.add(new PlantarGirasol());
		
		Problem problem = new Problem(objetivoAgente,estadoPlanta,operadores);
		this.setProblem(problem);
	
	}

	@Override
	public void see(Perception p) {
		this.getAgentState().updateState(p);
	}

	@Override
	public Action selectAction() {
		DepthFirstSearch strategy = new DepthFirstSearch();
		Search searchSolver = new Search(strategy);
		searchSolver.setVisibleTree(Search.EFAIA_TREE);
		this.setSolver(searchSolver);
		Action selectedAction = null;
	    try {
	      selectedAction = this.getSolver().solve(new Object[]{this.getProblem()});
	    } catch (Exception ex) {
	      Logger.getLogger(AgentePlanta.class.getName()).log(Level.SEVERE, null, ex);
	    }
	      
	    return selectedAction;
	}

}
