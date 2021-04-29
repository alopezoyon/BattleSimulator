package org.battlesimulator;

public class ListaNombres {
	
	private String[] lista;
	private int cuantos;
	
	public ListaNombres(String[] pLista) {
		this.lista = pLista;
		this.cuantos = pLista.length - 1;
	}
	
	private void eliminarNombreEnPos(int pPos) {
		if(this.cuantos >= 0) {
			this.lista[pPos] = this.lista[cuantos];
			this.cuantos--;
		}
	}
	
	public String obtenerNombreAleatorio() {
		String nombre = "";
		if(this.cuantos >= 0) {
			int index = NumeroAleatorio.obtenerNumAleatorio(this.cuantos + 1) - 1;
			nombre = this.lista[index];
			this.eliminarNombreEnPos(index);
		}
		
		return nombre;
	}
	
}
