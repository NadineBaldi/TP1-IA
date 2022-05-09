package search;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

/**
 * This class can be used in any search strategy like
 * Uniform Cost.
 */
public class CostFunction implements IStepCostFunction {
	public CostFunction() {
		
	}
    /**
     * This method calculates the cost of the given NTree node.
     */
    @Override
    public double calculateCost(NTree node) {        
       
    	/*EstadoAgente e = (EstadoAgente) node.getAgentState();
    
       return e.getCostoAcumulado();*/
    	return node.getAction().getCost();
    }
}
