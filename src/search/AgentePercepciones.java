package search;

import java.util.List;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import util.Celda;
import util.Girasol;
import util.TipoEnum;
import util.Zombie;

public class AgentePercepciones extends Perception{
	
	private Celda[][] matrizPercibida; // Matriz que contiene desconocido - vacio - zombie o girasol (segun que ve el agente)
	//en la matriz solo tengo si hay zombie o girasol o nada, no los atributos de ese objeto, entonces defino:
	private List<Zombie> zombiesPercibidos; //si percibe zombies, los agrego en esta lista con sus atributos y su posicion
	private List<Girasol> girasolesPercibidos; //Si percibe girasoles, los voy agregando en la lista con sus atributos y su posicion
	
	public AgentePercepciones() {}
	
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

	public List<Zombie> getZombiesPercibidos() {
		return zombiesPercibidos;
	}

	public void setZombiesPercibidos(List<Zombie> zombiesPercibidos) {
		this.zombiesPercibidos = zombiesPercibidos;
	}

	public List<Girasol> getGirasolesPercibidos() {
		return girasolesPercibidos;
	}

	public void setGirasolesPercibidos(List<Girasol> girasolesPercibidos) {
		this.girasolesPercibidos = girasolesPercibidos;
	}

	@Override
	public void initPerception(Agent agent, Environment environment) {
		// TODO Auto-generated method stub
		
		//AgentePlanta agent = (AgentePlanta) agent;
        Ambiente ambiente = (Ambiente) environment;
        EstadoAmbiente estadoAmbiente = ambiente.getEnvironmentState();
        
        java.awt.Point posicionInicial = estadoAmbiente.getUbicacionAgente();
        
        Celda[][] mapa = estadoAmbiente.getMapa();
        boolean banderaTope = true;

        //recorre izquierda-derecha
        recorrerFila(posicionInicial.x,4,posicionInicial.y,mapa);
        // recorre derecha-izquierda
        recorrerFila(0,posicionInicial.x,posicionInicial.y,mapa);
        // recorre norte-sur
        recorrerColumna(posicionInicial.y,8,posicionInicial.x,mapa);
        //recorre sur-norte
        recorrerColumna(0,posicionInicial.y,posicionInicial.x,mapa);
        
        //this.matrizPercibida = environmentState.getMapa();
        //this.zombiesPercibidos = environmentState.getZombies();
        //this.girasolesPercibidos = environmentState.getGirasoles();       
		
	}
	
	public void recorrerFila(int extremoInicio,int extremoFin,int columna,Celda[][] mapa){
		boolean banderaTope=true;
		
		for(int fila=extremoInicio; fila<=extremoFin && banderaTope;fila++) {
		    if(mapa[fila][columna].getTipo()==TipoEnum.ZOMBIE){
		   		matrizPercibida[fila][columna].setTipo(TipoEnum.ZOMBIE);
		   		zombiesPercibidos.add(buscarZombie(fila,columna));
	 			banderaTope=false;
		    } else if(mapa[fila][columna].getTipo()==TipoEnum.GIRASOL){
		    	matrizPercibida[fila][columna].setTipo(TipoEnum.GIRASOL);
		   		girasolesPercibidos.add(buscarGirasol(fila,columna));
	 			banderaTope=false;
			} else if(mapa[fila][columna].getTipo()==TipoEnum.VACIO){
		    	matrizPercibida[fila][columna].setTipo(TipoEnum.VACIO);
		   	}
		}
	}

	public void recorrerColumna(int extremoInicio,int extremoFin,int fila,Celda[][] mapa){
		boolean banderaTope=true;
		
		for(int columna=extremoInicio; columna<=extremoFin && banderaTope;columna++) {
			if(mapa[fila][columna].getTipo()==TipoEnum.ZOMBIE){
	    		matrizPercibida[fila][columna].setTipo(TipoEnum.ZOMBIE);
	   			zombiesPercibidos.add(buscarZombie(fila,columna));
	   			banderaTope=false;
	   		} else if(mapa[fila][columna].getTipo()==TipoEnum.GIRASOL){
	    		matrizPercibida[fila][columna].setTipo(TipoEnum.GIRASOL);
	    		girasolesPercibidos.add(buscarGirasol(fila,columna));
	    		banderaTope=false;
	   		} else if(mapa[fila][columna].getTipo()==TipoEnum.VACIO){
	    		matrizPercibida[fila][columna].setTipo(TipoEnum.VACIO);
	    	}
	    }
	}

	public Zombie buscarZombie(int fila,int columna){
		boolean bandera=true;
		int i=0;
		Zombie zombie= null;
		while(bandera && zombiesPercibidos.size()>i){
			if(zombiesPercibidos.get(i).ubicacionZombie.x==fila && zombiesPercibidos.get(i).ubicacionZombie.y==columna)	{
				zombie = zombiesPercibidos.get(i);
				bandera=false;
			}
			i++;
		}
		return zombie;
	}
		
	public Girasol buscarGirasol(int fila,int columna){
		boolean bandera=true;
		int i=0;
		Girasol girasol= null;
		while(bandera && girasolesPercibidos.size()>i){
			if(girasolesPercibidos.get(i).ubicacionGirasol.x==fila && girasolesPercibidos.get(i).ubicacionGirasol.y==columna)	{
				girasol = girasolesPercibidos.get(i);
				bandera=false;
			}
			i++;
			}
		return girasol;
	}
	
}
