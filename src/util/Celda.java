package util;

public class Celda {
	
	public TipoEnum tipo;
	
	public Celda() {
		
	}
	
	public Celda(TipoEnum tipo) {
		super();
		this.tipo = tipo;
	}

	public TipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
	}

}
