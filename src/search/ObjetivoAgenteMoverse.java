package search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoAgenteMoverse extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		
		EstadoAgente e = (EstadoAgente) agentState;
		
		
			if (e.getPosicion().y == 8 ) 
				return true;
			else return false;
		
		
	}

}
