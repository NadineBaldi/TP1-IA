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
	
	public boolean hayZombie() {
		if (this.tipo == TipoEnum.ZOMBIE) return true;
		else return false;
	}
	
	public boolean hayGirasol() {
		if (this.tipo == TipoEnum.GIRASOL) return true;
		else return false;
	}
	
	public boolean esVacia() {
		if (this.tipo == TipoEnum.VACIO) return true;
		else return false;
	}
	
	public boolean puedePlantar() {
		if (this.tipo != TipoEnum.GIRASOL && this.tipo != TipoEnum.ZOMBIE && this.tipo != TipoEnum.GIRASOL_CON_PLANTA) return true;
		else return false;
	}
	
	public boolean hayGirasolConPlanta() {
		if (this.tipo == TipoEnum.GIRASOL_CON_PLANTA) return true;
		else return false;
	}
	
	public Celda clone() {
		
		Celda clon = new Celda();
		
		clon.setTipo(this.tipo);
		
		return clon;
		
	}
	
	@Override
	public String toString() {
		return tipo.toString();
	}
}
