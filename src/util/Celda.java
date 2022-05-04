package util;

public class Celda {
	
	public TipoEnum tipo;
	
	public Celda() {
		
	}
	
	public Celda(TipoEnum tipo) {
		super();
		this.tipo = tipo; //la celda tiene algun tipo: vacia - zombie - girasol - desconocido (para cuando no se percibe)
	}

	public TipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
	}

	public void setCelda(Girasol girasol) {
		this.setTipo(TipoEnum.GIRASOL);
	}
}
