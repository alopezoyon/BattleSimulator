package org.battlesimulator;

public abstract class Objeto {
	private String nombre;
	
	private int ataque;
	private int defensa;
	private int curacion;
	
	public Objeto(int pAtaque, int pDefensa, int pCuracion, String pNombre) {
		if(pAtaque < 0) {pAtaque = 0; }
		if(pDefensa < 0) {pDefensa = 0; }
		if(pCuracion < 0) {pCuracion = 0; }
		
		this.ataque = pAtaque;
		this.defensa = pDefensa;
		this.curacion = pCuracion;
		this.nombre = pNombre;
	}
	
	protected String getNombre() {
		return this.nombre;
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
	
	public abstract void imprimirObjeto();
}
