package org.battlesimulator;

public class Batalla {
	private static Batalla miBatalla;
	
	private Equipo equipo1;
	private Equipo equipo2;
	
	//constructora (privada MAE)
	private Batalla(){
		this.equipo1 = new Equipo("Los Colosos");
		this.equipo2 = new Equipo("Los Fuertes");
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
		this.iniciarBatallaConDecision();
	}
	
	private void iniciarBatallaAutomatica() {
		System.out.println("---CONTENDIENTES---");
		this.equipo1 = GeneradorDeElementos.getGeneradorDeElementos().GenerarEquipoAleatorio();
		this.equipo1.imprimirEquipo();
		this.equipo2 = GeneradorDeElementos.getGeneradorDeElementos().GenerarEquipoAleatorio();
		this.equipo2.imprimirEquipo();
		
		boolean terminado = false;
		System.out.println("---INICIO DE LA BATALLA---");
		System.out.println("");
		System.out.println("Equipo1 alguien vivo: " + this.equipo1.alguienVivo());
		System.out.println("Equipo2 alguien vivo: " + this.equipo2.alguienVivo());
		
		int index = 0;
		while(!terminado) {
			if(this.equipo1.alguienVivo()) {
				this.equipo1.atacarEquipoAutomaticamente(this.equipo2);
				
				if(this.equipo2.alguienVivo()) {
					this.equipo1.atacarEquipoAutomaticamente(this.equipo2);
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
		
		System.out.println("La batalla ha tomado " + index + " rondas");
		
	}
	
	private void iniciarBatallaConDecision() {
		System.out.println("---CREACION DE CONTENDIENTES---");
		/*this.equipo1 = GeneradorDeElementos.getGeneradorDeElementos().generarEquipoUsuario();
		this.equipo2 = GeneradorDeElementos.getGeneradorDeElementos().generarEquipoUsuario();*/
		
		this.equipo1 = GeneradorDeElementos.getGeneradorDeElementos().GenerarEquipoAleatorio();
		this.equipo1.imprimirEquipo();
		this.equipo2 = GeneradorDeElementos.getGeneradorDeElementos().GenerarEquipoAleatorio();
		this.equipo2.imprimirEquipo();
		
		System.out.println("---INICIO DE LA BATALLA---");
		int index = 1;
		boolean terminado = false;
		
		while(!terminado) {
			System.out.println("Ronda numero " + index);
			
			if(this.equipo1.alguienVivo()) {
				this.equipo1.atacarEquipoConDecision(equipo2);
					
				if(this.equipo2.alguienVivo()) {
					this.equipo2.atacarEquipoConDecision(equipo1);					}else {
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
