package org.battlesimulator;

import java.util.ArrayList;
import java.util.Iterator;

public class Equipo {
	//La funcionalidad de Equipo es similar a la de una lista de jugadores
	private ArrayList<Jugador> lista;
	
	public Equipo() {
		this.lista = new ArrayList<Jugador>();
	}
	
	//Devuelve el iterador de la lista de jugadores
	private Iterator<Jugador> getIterator(){
		return this.lista.iterator();
	}
	
	//Añade un jugador al equipo
	public void anadirJugador(Jugador pJugador) {
		this.lista.add(pJugador);
	}
	
	//Devuelve el numero de integrantes del equipo 
	public int numeroDeIntegrantes() {
		return this.lista.size();
	}
	
	//Devuelve el jugador en caso de encontrarlo y null en caso contrario TERMINAR DE IMPLEMENTAR!!!!
	public Jugador buscarJugadorPorId(int pId) {
		Iterator<Jugador> itr = this.getIterator();
		Jugador miJugador = null;
		boolean encontrado = false;
		
		while (!encontrado && itr.hasNext()) {
			miJugador = itr.next();
			
			if(miJugador.tieneEsteId(pId)) {
				encontrado = true;
			}
		}
		
		if(!encontrado) {
			miJugador = null;
		}
		
		return miJugador;
	}
	
	//Ataca al otro equipo siguiendo la logica del ataque automático TERMINAR DE IMPLEMENTAR!!!!
	public void atacarEquipoAutomaticamente() {
	}
}
