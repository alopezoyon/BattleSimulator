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
		this.iniciarBatallaAutomatica();
	}
	
	private void iniciarBatallaAutomatica() {
		/*Jugador j11 = new Jugador(11, 10, 3, 2);
		Jugador j12 = new Jugador(12, 15, 3, 2);
		ListaInventario inv1 = new ListaInventario();
		inv1.anadirObjeto(new ObjetoAtaque(10));
		inv1.anadirObjeto(new ObjetoDefensa(10));
		inv1.anadirObjeto(new ObjetoCuracion(10));
		j12.setInventario(inv1);
		
		Jugador j23 = new Jugador(23, 18, 4, 3);
		
		this.equipo1.anadirJugador(j11);
		this.equipo1.anadirJugador(j12);
		this.equipo2.anadirJugador(j23);
		
		this.equipo1.imprimirEquipo();*/
		
		//this.equipo1 = GeneradorDeElementos.getGeneradorDeElementos().GenerarEquipoAleatorio(1);
		//this.equipo1.imprimirEquipo();
		//this.equipo2 = GeneradorDeElementos.getGeneradorDeElementos().GenerarEquipoAleatorio(2);
		//this.equipo2.imprimirEquipo();
		
		Teclado.getTeclado().leerString("Esto es una prueba");
		
		boolean terminado = false;
		
		/*while(!terminado) {
			if(this.equipo1.alguienVivo()) {
				this.equipo1.atacarEquipoAutomaticamente(this.equipo2);
				
				if(this.equipo2.alguienVivo()) {
					this.equipo1.atacarEquipoAutomaticamente(this.equipo2);
				}else {
					terminado = true;
				}
			}else {
				terminado = true;
			}
		}*/
		
	}
	
	/*private void iniciarBatallaConDecision() {
		
	}*/
	
}
