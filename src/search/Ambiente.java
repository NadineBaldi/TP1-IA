package search;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class Ambiente extends Environment {
	
	public Ambiente() {
        // Create the environment state
        this.environmentState = new EstadoAmbiente();
    }
	
	public EstadoAmbiente getEnvironmentState() {
        return (EstadoAmbiente) super.getEnvironmentState();
    }
	

    
    public String toString() {
        return environmentState.toString();
    }

	@Override
	public Perception getPercept() {
		// TODO Auto-generated method stub
		return null;
	}



}
