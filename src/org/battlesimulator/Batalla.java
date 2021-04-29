package org.battlesimulator;

public class Batalla {
	private static Batalla miBatalla;
	
	private Equipo equipo1;
	private Equipo equipo2;
	
	//constructora (privada MAE)
	private Batalla(){
		this.equipo1 = new Equipo("Anticipacion");
		this.equipo2 = new Equipo("Virgo");
	}
	
	//getBatalla patron singleton
	public static Batalla getBatalla() {
		if(Batalla.miBatalla == null) { Batalla.miBatalla = new Batalla(); }
		return Batalla.miBatalla;
	}
	
	public static void main(String args[]) {
		Batalla.getBatalla().iniciarBatalla();
	}
	
	public void iniciarBatalla() {
		System.out.println("");
		System.out.println("Bienvenido al simulador de batalla");
		System.out.println("");
		Teclado.getTeclado().leerString("Presiona cualquier tecla para empzar...");
		System.out.println("¿Como deseas comenzar la partida?");
		System.out.println("1) Generar dos equipos de forma aleatoria");
		System.out.println("2) Cargar los equipos guardados");
		System.out.println("3) Crear dos equipos nuevos");
		
		int seleccion = 0;
		boolean seleccionValida = false;
		while(!seleccionValida) {
			seleccion = Teclado.getTeclado().leerEntero("Seleccione una de las opciones anteriores");
			System.out.println(seleccion);
			if(seleccion == 1 || seleccion == 2 || seleccion == 3) {
				seleccionValida = true;
			}else {
				System.out.println("Introduce un valor valido");
			}
		}
		
		if(seleccion == 1) {
			this.equipo1 = GeneradorDeElementos.getGeneradorDeElementos().GenerarEquipoAleatorio();
			this.equipo2 = GeneradorDeElementos.getGeneradorDeElementos().GenerarEquipoAleatorio();
			//Introducir opcion de guardar los equipos
			
		}else if(seleccion == 2) {
			try {
				Equipo misEquipos[] = Fichero.getFichero().crearEquiposDesdeFichero();
				this.equipo1 = misEquipos[0];
				this.equipo2 = misEquipos[1];
				
			} catch (SintaxisErrorException e) {
				System.out.println("Hay un error de sintaxis en el fichero de equipos. Los equipos se generaran automaticamente");
				this.equipo1 = GeneradorDeElementos.getGeneradorDeElementos().GenerarEquipoAleatorio();
				this.equipo2 = GeneradorDeElementos.getGeneradorDeElementos().GenerarEquipoAleatorio();
			}
			
		}else {
			this.equipo1 = GeneradorDeElementos.getGeneradorDeElementos().generarEquipoUsuario();
			this.equipo2 = GeneradorDeElementos.getGeneradorDeElementos().generarEquipoUsuario();
			//Introducir opcion de guardar los equipos
			
		}
		
		if(this.equipo1 == null) {
			System.out.println("Se ha producido un error con la generacion del equipo 1. Se generará uno nuevo");
			this.equipo1 = GeneradorDeElementos.getGeneradorDeElementos().GenerarEquipoAleatorio();
		}
		
		if(this.equipo2 == null) {
			System.out.println("Se ha producido un error con la generacion del equipo 2. Se generará uno nuevo");
			this.equipo2 = GeneradorDeElementos.getGeneradorDeElementos().GenerarEquipoAleatorio();
		}
		System.out.println("");
		System.out.println("---CONTENDIENTES---");
		
		this.equipo1.imprimirEquipo();
		this.equipo2.imprimirEquipo();
		
		System.out.println("");
		System.out.println("Los equipos han sido generados con exito.");
		System.out.println("¿Que modo de juego quiere jugar?");
		System.out.println("1) Automatico");
		System.out.println("2) Con seleccion");
		
		seleccion = 0;
		seleccionValida = false;
		while(!seleccionValida) {
			seleccion = Teclado.getTeclado().leerEntero("Seleccione una de las opciones anteriores");
			if(seleccion == 1 || seleccion == 2) {
				seleccionValida = true;
			}else {
				System.out.println("Introduce un valor valido");
			}
		}
		
		System.out.println("");
		System.out.println("---INICIO DE LA BATALLA---");
		
		if(seleccion == 1) {
			this.iniciarBatallaAutomatica();
		}else if(seleccion == 2) {
			this.iniciarBatallaConDecision();
		}
		
	}
	
	private void iniciarBatallaAutomatica() {
		boolean terminado = false;
		int index = 1;
		
		while(!terminado && index <= 100) {
			System.out.println("--- RONDA " + (index) + " ---");
			if(this.equipo1.alguienVivo()) {
				this.equipo1.atacarEquipoAutomaticamente(this.equipo2);
				
				if(this.equipo2.alguienVivo()) {
					this.equipo2.atacarEquipoAutomaticamente(this.equipo1);
				}else {
					terminado = true;
					System.out.println("El vencedor es el equipo " + this.equipo1.getNombre());
				}
			}else {
				terminado = true;
				System.out.println("El vencedor es el equipo " + this.equipo2.getNombre());
			}
			index++;
			
		}
		int numEquipo1 = this.equipo1.numeroDeIntegrantesNoDebilitados();
		int numEquipo2 = this.equipo2.numeroDeIntegrantesNoDebilitados();
		
		if(!terminado) {
			if(numEquipo1 == numEquipo2) {
				System.out.println("No hay ningun equipo vencedor. Estamos ante un empate.");
			}else if(numEquipo1 > numEquipo2) {
				System.out.println("El vencedor es el equipo " + this.equipo1.getNombre() + " por contar con más integrantes no debilitados");
			}else {
				System.out.println("El vencedor es el equipo " + this.equipo2.getNombre() + " por contar con más integrantes no debilitados");
			}
		}
		
		System.out.println("La batalla ha tomado " + (index -1) + " rondas");
	}
	
	private void iniciarBatallaConDecision() {
		
		System.out.println("---INICIO DE LA BATALLA---");
		int index = 1;
		boolean terminado = false;
		
		while(!terminado) {
			System.out.println("Ronda numero " + index);
			
			if(this.equipo1.alguienVivo()) {
				this.equipo1.atacarEquipoConDecision(equipo2);
					
				if(this.equipo2.alguienVivo()) {
					this.equipo2.atacarEquipoConDecision(equipo1);					
				}else {
					terminado = true;
					System.out.println("Ha ganado el equipo " + this.equipo1.getNombre() + " porque los del equipo contrario estan debilitados");
				}
					
			}else if(this.equipo2.alguienVivo()){
				terminado = true;
				System.out.println("Ha ganado el equipo " + this.equipo2.getNombre() + " porque los del equipo contrario estan debilitados");
			}else {
				terminado = true;
				System.out.println("No ha ganado nadie, todos los jugadores estan debilitados");
			}

			int accion = Teclado.getTeclado().leerEntero("Seleccione la accion a realizar (1 continuar con otra ronda, otro valor para terminar la batalla");
			if(accion != 1) { terminado = true; }
			index++;
		}
	}
	
}
