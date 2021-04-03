package org.battlesimulator;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaInventario {
	//Lista de items o objetos
	private ArrayList<Objeto> lista;
	
	public ListaInventario() {
		this.lista = new ArrayList<Objeto>();
	}
	
	public void anadirObjeto(Objeto pObjeto) {
		this.lista.add(pObjeto);
	}
	
	private Iterator<Objeto> getIterator(){
		return this.lista.iterator();
	}
	
	//Itera todos los elementos del inventario y acumula el valor de ataque de todos los objetos de ataque
	public int obtenerDanoObjetos() {
		Iterator<Objeto> itr = this.getIterator();
		Objeto miObjeto = null;
		int computedDamage = 0;
		
		while(itr.hasNext()) {
			miObjeto = itr.next();
			
			if(miObjeto instanceof ObjetoAtaque) {
				computedDamage += miObjeto.getAtaque();
			}
		}
		
		return computedDamage;
	}
	
	//Itera todos los elementos del inventario y acumula el valor de defensa de todos los objetos de defensa
	public int obtenerProteccionObjetos() {
		Iterator<Objeto> itr = this.getIterator();
		Objeto miObjeto = null;
		int computedProtection = 0;
		
		while(itr.hasNext()) {
			miObjeto = itr.next();
			
			if(miObjeto instanceof ObjetoDefensa) {
				computedProtection += miObjeto.getAtaque();
			}
		}
		
		return computedProtection;
	}
	
	//Itera todos los elementos del inventario y acumula el valor de curacion de todos los objetos de curacion
	public int obtenerCuracionObjetos() {
		Iterator<Objeto> itr = this.getIterator();
		Objeto miObjeto = null;
		int computedHealth = 0;
		
		while(itr.hasNext()) {
			miObjeto = itr.next();
			
			if(miObjeto instanceof ObjetoCuracion) {
				computedHealth += miObjeto.getAtaque();
			}
		}
		
		return computedHealth;
	}
}
