package search;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.GreedySearch;
import frsf.cidisi.faia.solver.search.Search;
import frsf.cidisi.faia.solver.search.UniformCostSearch;
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
	
	public AgentePlanta(int cantidadZombiesEnJuego) {
		
		ObjetivoAgente objetivoAgente = new ObjetivoAgente();
		
		EstadoAgente estadoPlanta = new EstadoAgente(cantidadZombiesEnJuego);
		this.setAgentState(estadoPlanta);
	
		Vector<SearchAction> operadores = new Vector<SearchAction>();
		operadores.addElement(new AvanzarNorte());
		operadores.addElement(new AvanzarSur());
		operadores.addElement(new AvanzarEste());
		operadores.addElement(new AvanzarOeste());
		operadores.addElement(new ObtenerSoles());
		operadores.addElement(new PlantarGirasol());
		operadores.addElement(new DerribarZombieNorte());
		operadores.addElement(new DerribarZombieSur());
		operadores.addElement(new DerribarZombieEste());
		operadores.addElement(new DerribarZombieOeste());
		
		Problem problem = new Problem(objetivoAgente, estadoPlanta, operadores);
		this.setProblem(problem);
	
	}

	@Override
    public Action selectAction() {
		
        // Create the search strategy
        //DepthFirstSearch strategy = new DepthFirstSearch(); //PROFUNDIDAD         
        BreathFirstSearch strategy = new BreathFirstSearch(); //AMPLITUD
        //UniformCostSearch strategy = new UniformCostSearch(new CostFunction()); //COSTO UNIFORME
    	//GreedySearch strategy = new GreedySearch(new Heuristic()); //AVARA
    	
        // Create a Search object with the strategy
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        //searchSolver.setVisibleTree(Search.EFAIA_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

        // Ask the solver for the best action
        Action selectedAction = null;
        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(AgentePlanta.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;

    }
	

	@Override
	public void see(Perception p) {
		this.getAgentState().updateState(p);
	}
}
