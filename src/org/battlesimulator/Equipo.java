package org.battlesimulator;
import java.util.ArrayList;
import java.util.Iterator;

public class Equipo {
	//La funcionalidad de Equipo es similar a la de una lista de jugadores
	private ArrayList<Jugador> lista;
	private String nombre;//para imprimir nada mas
	
	public Equipo(String pNombre) {
		this.lista = new ArrayList<Jugador>();
		this.nombre = pNombre;
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
	
	public Jugador obtenerJugadorEnPos(int pPos) {
		Jugador miJugador = null;
		if(this.indexValido(pPos)) {
			miJugador = this.lista.get(pPos);
		}
		return miJugador;
	}
	
	private boolean indexValido(int pPos) {
		return pPos >= 0 && pPos < this.numeroDeIntegrantes();
	}
	
	//Devuelve el jugador en caso de encontrarlo y null en caso contrario TERMINAR DE IMPLEMENTAR!!!!
	public boolean alguienVivo() {
		Iterator<Jugador> itr = this.getIterator();
		Jugador miJugador = null;
		boolean encontrado = false;
		
		while (!encontrado && itr.hasNext()) {
			miJugador = itr.next();
			if(miJugador.estaVivo()) {
				encontrado = true;
			}
		}
		
		return encontrado;
	}
	
	//Ataca al otro equipo siguiendo la logica del ataque automático TERMINAR DE IMPLEMENTAR!!!!
	public void atacarEquipoAutomaticamente(Equipo pEquipo2) {
		Iterator<Jugador> itr = this.getIterator();
		boolean terminado = false;
		Jugador jugadorE1 = null;
		Jugador jugadorE2 = null;
		int index = 0;
		
		if(this.alguienVivo()){
			while(itr.hasNext() && !terminado) {
				jugadorE1 = itr.next();
				
				if(jugadorE1.estaVivo()) {
					if(pEquipo2.alguienVivo()) {
						jugadorE2 = pEquipo2.obtenerJugadorEnPos(index);
							
						while(!jugadorE2.estaVivo()) {
							index++;
							if(index >= pEquipo2.numeroDeIntegrantes()) {
								index = 0;
							}
							
							jugadorE2 = pEquipo2.obtenerJugadorEnPos(index);
						}
						 
						jugadorE1.atacarJugador(jugadorE2);
						index++;
						
					}else {
						System.out.println("El equipo al que se quiere atacar ya esta debilitado");
						terminado = true;
					}
				}
			}
				
				
		}else {
			System.out.println("Este equipo esta debilitado, no puede atacar");
		}
		
	}
	
	public void imprimirEquipo() {
		System.out.println("El equipo " + this.nombre + " cuenta con " + this.numeroDeIntegrantes() + " integrantes: ");
		
		Iterator<Jugador> itr = this.getIterator();
		Jugador miJugador = null;
		int index = 1;
		while (itr.hasNext()) {
			miJugador = itr.next();
			System.out.print(index + ") ");
			miJugador.imprimirJugador();
			System.out.println("");
			index++;
		}
		
		System.out.print("");
	}
}
