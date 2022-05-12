package search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoAgenteOleada extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		
		EstadoAgente e = (EstadoAgente) agentState;
		
		if (e.getZombiesPercibidos().size() == 0 
				) 
				return true;
			else return false;
	}

}
