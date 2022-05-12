package search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoAgente extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		
		EstadoAgente e = (EstadoAgente) agentState;
		
		if (
				//e.getCantZombies() == e.getZombiesPercibidos().size() &&
				 e.getCantSolesDisponibles() > 1 &&
				 e.getCantZombies() <= 0
					//e.getPosicion().x == 2 &&
					//e.getPosicion().y == 7
					) 
				return true;
			else return false;
	}

}
