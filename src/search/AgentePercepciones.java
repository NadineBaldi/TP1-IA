package search;

import java.awt.Point;
import java.util.ArrayList;
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
		//initPerception(agent, env);
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
		
		//AgentePlanta agentePlanta = (AgentePlanta) agent;
        Ambiente ambiente = (Ambiente) environment;
        EstadoAmbiente estadoAmbiente = ambiente.getEnvironmentState();
        
        Point posicionInicial = estadoAmbiente.getUbicacionAgente();
        Celda[][] mapaAmbiente = estadoAmbiente.getMapa();
        
        inicializarMatrizDesconocido(posicionInicial, mapaAmbiente); //inicializo una matriz con todas las celdas en "desconocido"
        setZombiesPercibidos(new ArrayList<Zombie>()); //inicializo los zombies percibidos como una lista vacia
        setGirasolesPercibidos(new ArrayList<Girasol>()); //inicializo los girasoles percibidos como una lista vacia

        //recorre izquierda-derecha
        recorrerFila(posicionInicial.x+1,4,posicionInicial.y,estadoAmbiente);
        // recorre derecha-izquierda
        recorrerFila(0,posicionInicial.x-1,posicionInicial.y,estadoAmbiente);
        // recorre norte-sur
        recorrerColumna(posicionInicial.y+1,8,posicionInicial.x,estadoAmbiente);
        //recorre sur-norte
        recorrerColumna(0,posicionInicial.y-1,posicionInicial.x,estadoAmbiente);
        //percibir celda actual
        percibirCeldaActual(posicionInicial,estadoAmbiente);
        
        setMatrizPercibida(matrizPercibida); //seteo la matriz percibida con los datos actualizados
	}
	
	public void recorrerFila(int extremoInicio,int extremoFin,int columna, EstadoAmbiente estadoAmbiente){
		boolean banderaTope=true;
		List<Zombie> zombiesEnAmbiente = estadoAmbiente.getZombies(); 
		List<Girasol> girasolesEnAmbiente = estadoAmbiente.getGirasoles();
		Celda[][] mapa = estadoAmbiente.getMapa();
		
		for(int fila=extremoInicio; fila<=extremoFin && banderaTope;fila++) {
		    if(mapa[fila][columna].getTipo()==TipoEnum.ZOMBIE){
		   		matrizPercibida[fila][columna].setTipo(TipoEnum.ZOMBIE);
		   		zombiesPercibidos.add(buscarZombie(fila,columna,zombiesEnAmbiente));
	 			banderaTope=false;
		    } else if(mapa[fila][columna].getTipo()==TipoEnum.GIRASOL){
		    	matrizPercibida[fila][columna].setTipo(TipoEnum.GIRASOL);
		   		girasolesPercibidos.add(buscarGirasol(fila,columna,girasolesEnAmbiente));
	 			banderaTope=false;
			} else if(mapa[fila][columna].getTipo()==TipoEnum.VACIO){
		    	matrizPercibida[fila][columna].setTipo(TipoEnum.VACIO);
		   	}
		}
	}

	public void recorrerColumna(int extremoInicio,int extremoFin,int fila, EstadoAmbiente estadoAmbiente){
		boolean banderaTope=true;
		List<Zombie> zombiesEnAmbiente = estadoAmbiente.getZombies(); 
		List<Girasol> girasolesEnAmbiente = estadoAmbiente.getGirasoles();
		Celda[][] mapa = estadoAmbiente.getMapa();
		
		for(int columna=extremoInicio; columna<=extremoFin && banderaTope;columna++) {
			if(mapa[fila][columna].getTipo()==TipoEnum.ZOMBIE){
	    		matrizPercibida[fila][columna].setTipo(TipoEnum.ZOMBIE);
	   			zombiesPercibidos.add(buscarZombie(fila,columna,zombiesEnAmbiente));
	   			banderaTope=false;
	   		} else if(mapa[fila][columna].getTipo()==TipoEnum.GIRASOL){
	    		matrizPercibida[fila][columna].setTipo(TipoEnum.GIRASOL);
	    		girasolesPercibidos.add(buscarGirasol(fila,columna,girasolesEnAmbiente));
	    		banderaTope=false;
	   		} else if(mapa[fila][columna].getTipo()==TipoEnum.VACIO){
	    		matrizPercibida[fila][columna].setTipo(TipoEnum.VACIO);
	    	}
	    }
	}

	public Zombie buscarZombie(int fila,int columna, List<Zombie> zombiesEnAmbiente){
		boolean bandera=true;
		int i=0;
		Zombie zombie= null;
		while(bandera && zombiesEnAmbiente.size()>i){
			if(zombiesEnAmbiente.get(i).ubicacionZombie.x==fila && zombiesEnAmbiente.get(i).ubicacionZombie.y==columna)	{
				zombie = zombiesEnAmbiente.get(i);
				bandera=false;
			}
			i++;
		}
		return zombie;
	}
		
	public Girasol buscarGirasol(int fila, int columna, List<Girasol> girasolesEnAmbiente){
		boolean bandera=true;
		int i=0;
		Girasol girasol= null;
		while(bandera && girasolesEnAmbiente.size()>i){
			if(girasolesEnAmbiente.get(i).ubicacionGirasol.x==fila && girasolesEnAmbiente.get(i).ubicacionGirasol.y==columna)	{
				girasol = girasolesEnAmbiente.get(i);
				bandera=false;
			}
			i++;
			}
		return girasol;
	}
	
	public void inicializarMatrizDesconocido(Point posicionAgente, Celda[][] mapaAmbiente) {
		Celda[][] nuevoMapa = new Celda[5][9];
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				if (posicionAgente.x != i && posicionAgente.y != j) {
					nuevoMapa[i][j] = new Celda(TipoEnum.DESCONOCIDO);
				} else {
					nuevoMapa[i][j] = mapaAmbiente[i][j].clone();
				}
			}
		}
		
		setMatrizPercibida(nuevoMapa);
	}
	
	public void percibirCeldaActual(Point posicionAgente, EstadoAmbiente estadoAmbiente) {
		
		Celda[][] mapaAmbiente = estadoAmbiente.getMapa();
		
		if (mapaAmbiente[posicionAgente.x][posicionAgente.y].getTipo() == TipoEnum.GIRASOL_CON_PLANTA) {
			girasolesPercibidos.add(buscarGirasol(posicionAgente.x,posicionAgente.y,estadoAmbiente.getGirasoles()));
		}
	}
	
    @Override
    public String toString() {
    	String str = "";

    	if(matrizPercibida != null) {
    	str+="Matriz percibida: \n";
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				str+="["+this.matrizPercibida[i][j].toString()+"]";
			}
			str+="\n";
		}
    	}
    	if(zombiesPercibidos != null && girasolesPercibidos != null) {
        str+= "\n Zombies percibidos: "+zombiesPercibidos.toString();
        str+="\n Girasoles percibidos: "+girasolesPercibidos.toString();
    	}
        return str.toString();
    }
}
