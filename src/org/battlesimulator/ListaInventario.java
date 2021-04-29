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
		if(!this.lista.contains(pObjeto) && !this.estaObjetoConId(pObjeto.getId())) {
			this.lista.add(pObjeto);
		}else {
			System.out.println("El objeto que intenta añadir ya se encuentra en el inventario o hay otro con el mismo id");
		}
	}
	
	private Iterator<Objeto> getIterator(){
		return this.lista.iterator();
	}
	
	public boolean estaObjetoConId(int pId) {
		Iterator<Objeto> itr = this.getIterator();
		Objeto miObjeto = null;
		boolean esta = false;
		
		while(itr.hasNext() && !esta) {
			miObjeto = itr.next();
			esta = miObjeto.tieneEsteId(pId);
		}
		
		return esta;
	}
	
	private Objeto buscarObjetoPorId(int pId) {
		Iterator<Objeto> itr = this.getIterator();
		Objeto miObjeto = null;
		boolean esta = false;
		
		while(itr.hasNext() && !esta) {
			miObjeto = itr.next();
			esta = miObjeto.tieneEsteId(pId);
		}
		
		return miObjeto;
	}
	
	//Itera todos los elementos del inventario y acumula el valor de ataque de todos los objetos de ataque
	public int obtenerDanoObjetos() {
		Iterator<Objeto> itr = this.getIterator();
		Objeto miObjeto = null;
		int computedDamage = 0;
		
		while(itr.hasNext()) {
			miObjeto = itr.next();
			
			if(miObjeto instanceof ObjetoAtaque) {
				computedDamage += ((ObjetoAtaque) miObjeto).getAtaque();
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
				computedProtection += ((ObjetoDefensa) miObjeto).getDefensa();
			}
		}
		
		return computedProtection;
	}
	
	//Itera todos los elementos del inventario y acumula el valor de curacion de todos los objetos de curacion
	public int obtenerCuracionPrimerObjeto() {
		Iterator<Objeto> itr = this.getIterator();
		Objeto miObjeto = null;
		int curacion = 0;
		boolean terminado = false;
		
		while(itr.hasNext() && !terminado) {
			miObjeto = itr.next();
			
			if(miObjeto instanceof ObjetoDefensa) {
				curacion = ((ObjetoDefensa) miObjeto).getDefensa();
				((ObjetoCuracion) miObjeto).imprimirUso();
				this.lista.remove(miObjeto);
			}
		}
		
		return curacion;
	}
	
	public int obtenerCuracionObjetoConId(int pId) {
		Objeto miObjeto = this.buscarObjetoPorId(pId);
		int curacion = 0;
		if(miObjeto != null) {
			if(miObjeto instanceof ObjetoCuracion) {
				curacion = ((ObjetoCuracion) miObjeto).getCuracion();
				((ObjetoCuracion) miObjeto).imprimirUso();
				this.lista.remove(miObjeto);
			}
		}
		
		return curacion;
	}
	
	public void imprimirInventario() {
		Iterator<Objeto> itr = this.getIterator();
		Objeto miObjeto = null;
		
		while(itr.hasNext()) {
			miObjeto = itr.next();
			miObjeto.imprimirObjeto();
		}
		System.out.println("(" + this.lista.size() + " objetos)");
	}
	
	public void imprimirOpcionesCuracion() {
		Iterator<Objeto> itr = this.getIterator();
		Objeto miObjeto = null;
		
		while(itr.hasNext()) {
			miObjeto = itr.next();
			
			if(miObjeto instanceof ObjetoCuracion) {
				System.out.print(miObjeto.getId() + " ) ");
				((ObjetoCuracion) miObjeto).imprimirObjeto();
			}
		}
	}
}
