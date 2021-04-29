package org.battlesimulator;

public class ObjetoAtaque extends Objeto{
	
	private int ataque;
	
	public ObjetoAtaque(int pId, String pNombre, int pAtaque) {
		super(pId, pNombre);
		if(pAtaque < 0) {pAtaque = 0; }
		this.ataque = pAtaque;
	}
	
	public int getAtaque() {
		return this.ataque;
	}

	@Override
	public void imprimirObjeto() {
		System.out.println(super.getNombre() + " con " + this.ataque + " puntos de ataque");
	}
}
