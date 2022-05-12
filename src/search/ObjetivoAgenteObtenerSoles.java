package search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoAgenteObtenerSoles extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		
		EstadoAgente e = (EstadoAgente) agentState;
		
		if (
			 e.getCantSolesDisponibles() > 20
			) 
			return true;
		else return false;
	}

}
