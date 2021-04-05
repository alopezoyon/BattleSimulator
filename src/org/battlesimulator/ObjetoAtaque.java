package org.battlesimulator;

public class ObjetoAtaque extends Objeto{
	
	public ObjetoAtaque(int pAtaque) {
		super(pAtaque, 0, 0, "Hacha");
		
	}
	
	@Override
	public int getAtaque() {
		return super.getAtaque();
	}

	@Override
	public void imprimirObjeto() {
		System.out.println(super.getNombre() + " con " + super.getAtaque() + " puntos de ataque");
	}
}
