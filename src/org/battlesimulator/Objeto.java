package org.battlesimulator;

public abstract class Objeto {
	private int id;
	private String nombre;
	
	public Objeto(int pId, String pNombre) {
		this.id = pId;
		this.nombre = pNombre;
	}
	
	protected String getNombre() {
		return this.nombre;
	}
	
	//Solo para imprimir
	public int getId() {
		return this.id;
	}
	
	public boolean tieneEsteId(int pId) {
		return pId == this.id;
	}
	
	public abstract void imprimirObjeto();
}
