package org.battlesimulator;

public class ObjetoDefensa extends Objeto{
	
	private int defensa;
	
	public ObjetoDefensa(int pId, String pNombre, int pDefensa) {
		super(pId, pNombre);
		if(pDefensa < 0) {pDefensa = 0; }
		this.defensa = pDefensa;
	}
	
	public int getDefensa() {
		return this.defensa;
	}

	@Override
	public void imprimirObjeto() {
		System.out.println(super.getNombre() + " con " + this.defensa + " puntos de defensa");
	}
}

