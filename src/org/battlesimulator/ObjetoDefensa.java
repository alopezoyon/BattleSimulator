package org.battlesimulator;

public class ObjetoDefensa extends Objeto{

	public ObjetoDefensa(int pDefensa) {
		super(0, pDefensa, 0, "Casco");
	}
	
	@Override
	public int getDefensa() {
		return super.getDefensa();
	}

	@Override
	public void imprimirObjeto() {
		System.out.println(super.getNombre() + " con " + super.getDefensa() + " puntos de defensa");
	}
}

