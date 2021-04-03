package org.battlesimulator;

public abstract class Objeto {
	
	private int ataque;
	private int defensa;
	private int curacion;
	
	public Objeto(int pAtaque, int pDefensa, int pCuracion) {
		if(pAtaque >= 0 && pDefensa >= 0 && pCuracion >= 0) {
			this.ataque = pAtaque;
			this.defensa = pDefensa;
			this.curacion = pCuracion;
		}else {
			//ERRORRRRR!!!!
		}
	}
	
	protected int getAtaque() {
		return this.ataque;
	}
	
	protected int getDefensa() {
		return this.defensa;
	}
	
	protected int getCuracion() {
		return this.curacion;
	}
}
