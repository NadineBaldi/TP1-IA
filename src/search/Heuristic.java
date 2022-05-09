package search;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

/**
 * This class allows to define a function to be used by any
 * informed search strategy, like A Star or Greedy.
 */
public class Heuristic implements IEstimatedCostFunction {
	
	public Heuristic() {
		
	}

    /**
     * It returns the estimated cost to reach the goal from a NTree node.
     */
    @Override
    public double getEstimatedCost(NTree node) {
        EstadoAgente estadoAgente = (EstadoAgente) node.getAgentState();
        double cantidadZombiesAMatar = estadoAgente.getCantZombies();
        //Toma el camino que le permita reducir la cantidad de zombies a matar (es decir, le permita matar zombies)	
        return cantidadZombiesAMatar;
    }
}
