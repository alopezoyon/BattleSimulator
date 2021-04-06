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
	
	public String getNombre() {
		return this.nombre;
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
		
		System.out.println("El equipo " + this.nombre + " (" + this.numeroDeIntegrantes() + " integrantes) se dispone a atacar al equipo " + pEquipo2.getNombre() + " (" + pEquipo2.numeroDeIntegrantes() + " integrantes)");
		if(this.alguienVivo()){
			while(itr.hasNext() && !terminado) {
				jugadorE1 = itr.next();
				
				if(jugadorE1.estaVivo()) {
					int numAleatorio = NumeroAleatorio.obtenerNumAleatorio(2);
					
					if(numAleatorio == 1) {
						//Atacar al otro equipo (si se puede)
						if(pEquipo2.alguienVivo()) {
							jugadorE2 = pEquipo2.obtenerJugadorEnPos(index);
								
							while(!jugadorE2.estaVivo()) {
								index++;
								if(index > pEquipo2.numeroDeIntegrantes()) {
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
					}else if(numAleatorio == 2) {
						//Curar a un miembro de tu equipo
						Iterator<Jugador> itr2 = this.getIterator();
						Jugador jugadorMismoEquipo = null;
						boolean encontrado = false;
						
						while(itr2.hasNext() && !encontrado) {
							jugadorMismoEquipo = itr2.next();
							if(jugadorMismoEquipo.estaVivo()) {
								jugadorE1.curarJugador(jugadorMismoEquipo);
								encontrado = true;
							}
						}
					}
				}
			}
				
				
		}else {
			System.out.println("Este equipo esta debilitado, no puede atacar");
		}
		
		System.out.println("");
	}
	
	public void atacarEquipoConDecision(Equipo pEquipo){
		System.out.println("Es el turno del equipo " + this.nombre);
		
		Iterator<Jugador> itr = this.getIterator();
		Jugador miJugador = null;
		Jugador otroJugador = null;
		boolean terminado = false;
		while(!terminado && itr.hasNext()) {
			miJugador = itr.next();
			System.out.println("Es el turno del jugador " + miJugador.getNombre());
			int accion = Teclado.getTeclado().leerEntero("Seleccione la accion a realizar (1 atacar, 2 curar, otro numero para no realizar ninguna accion)");
			
			if(accion == 1) {
				//atacar al otro equipo
				pEquipo.imprimirOpcionesDeAtaque();
				System.out.println("Seleccione el jugador a atacar");
				
				int seleccion = 0;
				boolean seleccionado = false;
				while(!seleccionado) {
					seleccion = Teclado.getTeclado().leerEntero("");
					if(seleccion > 0 && seleccion < pEquipo.numeroDeIntegrantes()) { 
						otroJugador = pEquipo.obtenerJugadorEnPos(seleccion);
						if(otroJugador.estaVivo()) {
							seleccionado = true; 
						}else {
							System.out.println("No puedes seleccionar un jugador debilitado");
						}
					}else {
						System.out.println("Introduce un valor valido");
					}
				}
				
				miJugador.atacarJugador(otroJugador);
				
				
			}else if(accion == 2){
				//Curar a un jugador de tu equipo
				this.imprimirOpcionesDeAtaque();
				System.out.println("Seleccione el jugador a curar");
				
				int seleccion = 0;
				boolean seleccionado = false;
				while(!seleccionado) {
					seleccion = Teclado.getTeclado().leerEntero("") -1;
					if(seleccion > 0 && seleccion < this.numeroDeIntegrantes()) { 
						otroJugador = pEquipo.obtenerJugadorEnPos(seleccion);
						if(otroJugador.estaVivo()) {
							seleccionado = true; 
						}else {
							System.out.println("No puedes seleccionar un jugador debilitado");
						}
					}else {
						System.out.println("Introduce un valor valido");
					}
				}
				
				miJugador.curarJugador(otroJugador);
			}
		}
		
	}
	
	private int preguntarAccion() {
		int accion = 0;
		boolean terminado = false;
		while(!terminado) {
			accion = Teclado.getTeclado().leerEntero("Seleccione la accion a realizar (1 atacar, 2 curar)");
			if(accion == 1 || accion == 2) { 
				terminado = true; 
			}else {
				System.out.println("Introduce un valor valido");
			}
		}
		
		return accion;
	}
	
	public void imprimirEquipo() {
		System.out.println("-");
		System.out.println("El equipo " + this.nombre + " cuenta con " + this.numeroDeIntegrantes() + " integrantes: ");
		
		Iterator<Jugador> itr = this.getIterator();
		Jugador miJugador = null;
		int index = 1;
		while (itr.hasNext()) {
			miJugador = itr.next();
			System.out.print(index + ") ");
			miJugador.imprimirJugadorInventario();
			System.out.println("");
			index++;
		}
		
		System.out.print("");
	}
	
	public void imprimirOpcionesDeAtaque() {
		Iterator<Jugador> itr = this.getIterator();
		Jugador miJugador = null;
		int index = 1;
		while(itr.hasNext()) {
			miJugador = itr.next();
			System.out.print(index + ") ");
			miJugador.imprimirJugador();
			index++;
		}
	}
}
