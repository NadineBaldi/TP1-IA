package search;

import frsf.cidisi.faia.environment.Environment;

public class Ambiente extends Environment {
	
	public Ambiente(int cantidadZombiesEnJuego) {
        // Create the environment state
        this.environmentState = new EstadoAmbiente(cantidadZombiesEnJuego);
    }
	
	public EstadoAmbiente getEnvironmentState() {
        return (EstadoAmbiente) super.getEnvironmentState();
    }
    
    public String toString() {
        return environmentState.toString();
    }

    @Override
    public  AgentePercepciones getPercept() {
       
    	// Create a new perception to return
        AgentePercepciones perception = new AgentePercepciones();
		
		EstadoAmbiente state = (EstadoAmbiente) this.environmentState;
        
		state.generarSoles();
		state.comportamientoZombie();
		
		perception.initPerception(null, this);
		
		//perception.setMatrizPercibida();
		//perception.setGirasolesPercibidos(state.getGirasoles());
		//perception.setZombiesPercibidos(state.getZombies());
		
		
        return perception;
    }



}
