package search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoAgentePlantarGirasoles extends GoalTest{

	@Override
	public boolean isGoalState(AgentState agentState) {
		
		EstadoAgente e = (EstadoAgente) agentState;
		
		if ( 
				e.getGirasolesPercibidos().size() > 3
			) return true;
		else return false;
	}

}
