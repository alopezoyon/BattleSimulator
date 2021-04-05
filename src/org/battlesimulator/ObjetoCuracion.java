package org.battlesimulator;

public class ObjetoCuracion  extends Objeto{
	
	public ObjetoCuracion(int pCuracion) {
		super(0, 0, pCuracion, "Venda");
	}
	
	@Override
	public int getCuracion() {
		return super.getCuracion();
	}

	@Override
	public void imprimirObjeto() {
		System.out.println(super.getNombre() + " con " + super.getCuracion() + " puntos de curacion");
	}
}
