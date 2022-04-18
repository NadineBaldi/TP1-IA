package search;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import util.Celda;
import util.Girasol;
import util.Zombie;

public class AgentePercepciones extends Perception{
	
	private Celda[][] matrizPercibida;
	private Zombie[] zombiesPercibidos;
	private Girasol[] girasolesPercibidos;
	
	public AgentePercepciones() {
		
	}
	
	public AgentePercepciones(Agent agent, Environment env) {
		super(agent, env);
	}
	
	//Getters y setters

	public Celda[][] getMatrizPercibida() {
		return matrizPercibida;
	}

	public void setMatrizPercibida(Celda[][] matrizPercibida) {
		this.matrizPercibida = matrizPercibida;
	}

	public Zombie[] getZombiesPercibidos() {
		return zombiesPercibidos;
	}

	public void setZombiesPercibidos(Zombie[] zombiesPercibidos) {
		this.zombiesPercibidos = zombiesPercibidos;
	}

	public Girasol[] getGirasolesPercibidos() {
		return girasolesPercibidos;
	}

	public void setGirasolesPercibidos(Girasol[] girasolesPercibidos) {
		this.girasolesPercibidos = girasolesPercibidos;
	}

	@Override
	public void initPerception(Agent agent, Environment environment) {
		// TODO Auto-generated method stub
		
	}
	
}
