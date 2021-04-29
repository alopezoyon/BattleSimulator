package org.battlesimulator;

public class ObjetoCuracion  extends Objeto{
	
	private int curacion;
	
	public ObjetoCuracion(int pId, String pNombre, int pCuracion) {
		super(pId, pNombre);
		if(pCuracion < 0) {pCuracion = 0; }
		this.curacion = pCuracion;
	}
	
	public int getCuracion() {
		return this.curacion;
	}

	@Override
	public void imprimirObjeto() {
		System.out.println(super.getNombre() + " con " + this.curacion + " puntos de curacion");
	}
	
	public void imprimirUso() {
		System.out.println("Se ha usado el objeto " + super.getNombre() + " con " + this.curacion + " puntos de curacion");
	}
}
