package org.battlesimulator;

public abstract class Objeto {
	
	private int ataque;
	private int defensa;
	private int curacion;
	
	public Objeto(int pAtaque, int pDefensa, int pCuracion) {
		if(pAtaque < 0) {pAtaque = 0; }
		if(pDefensa < 0) {pAtaque = 0; }
		if(pCuracion < 0) {pAtaque = 0; }
		
		this.ataque = pAtaque;
		this.defensa = pDefensa;
		this.curacion = pCuracion;
		
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
