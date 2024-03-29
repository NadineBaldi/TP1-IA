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
	
	public AgentePlanta(int cantidadZombiesEnJuego) {
		
		ObjetivoAgente objetivoAgente = new ObjetivoAgente();
		
		EstadoAgente estadoPlanta = new EstadoAgente(cantidadZombiesEnJuego);
		this.setAgentState(estadoPlanta);
	
		Vector<SearchAction> operadores = new Vector<SearchAction>();
		operadores.addElement(new ObtenerSoles());
		operadores.addElement(new PlantarGirasol());
		operadores.addElement(new DerribarZombieNorte());
		operadores.addElement(new DerribarZombieSur());
		operadores.addElement(new DerribarZombieEste());
		operadores.addElement(new DerribarZombieOeste());
		operadores.addElement(new AvanzarNorte());
		operadores.addElement(new AvanzarSur());
		operadores.addElement(new AvanzarEste());
		operadores.addElement(new AvanzarOeste());

		
		Problem problem = new Problem(objetivoAgente, estadoPlanta, operadores);
		this.setProblem(problem);
	
	}

	@Override
    public Action selectAction() {
		
        // Create the search strategy
    	//CostFunction.Type=CostFunctionType.TRAVELMONEY;
        //UniformCostSearch strategy = new UniformCostSearch(new CostFunction());
        //GreedySearch strategy = new GreedySearch(new Heuristic());
    	//AStarSearch strategy = new AStarSearch(new CostFunction(), new Heuristic());
        BreathFirstSearch strategy = new BreathFirstSearch(); //AMPLITUD
    	//DepthFirstSearch strategy = new DepthFirstSearch();
    	
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
        	if(((EstadoAgente) this.getAgentState()).getGirasolesPercibidos().size() < 3) {
        		this.getProblem().setGoalState(new ObjetivoAgentePlantarGirasoles());
        	} /*else if(((EstadoAgente) this.getAgentState()).getCantSolesDisponibles() < 20){
        		this.getProblem().setGoalState(new ObjetivoAgenteObtenerSoles());
        	}else if(((EstadoAgente) this.getAgentState()).getZombiesPercibidos().size() == 0 && ((EstadoAgente) this.getAgentState()).getCantZombies() > 0 ) {
            		this.getProblem().setGoalState(new ObjetivoAgenteMoverse());
            	}else if(((EstadoAgente) this.getAgentState()).getZombiesPercibidos().size() > 0){
            			this.getProblem().setGoalState(new ObjetivoAgenteOleada());
            		}else 
            			this.getProblem().setGoalState(new ObjetivoAgente());
            		*/
        	

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
