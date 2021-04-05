package org.battlesimulator;

public class GeneradorDeElementos {
	
	private static GeneradorDeElementos miGenerador;
	//Configuracion Equipo
	private int numMaxIntegrantesEquipo = 7;
	private String[] nombresEquipoAleatorio;
	
	//Configuracion Jugador
	private int valorVidaMaxima = 30;
	private int valorAtaqueMaximo = 10;
	private int valorDefensaMaxima = 10;
	
	//Configuracion Objetos
	private int numMaxObjetosInventario = 5;
	private int valorAtaqueObjetoMaximo = 7;
	private int valorDefensaObjetoMaxima = 4;
	private int valorCuracionObjetoMaximo = 3;
	
	
	private GeneradorDeElementos() {
		this.nombresEquipoAleatorio =  new String[]{"Los Colosos", "Los Fuertes"};
	}
	
	public static GeneradorDeElementos getGeneradorDeElementos() {
		if(GeneradorDeElementos.miGenerador == null) {GeneradorDeElementos.miGenerador = new GeneradorDeElementos(); }
		return GeneradorDeElementos.miGenerador;
	}
	
	//Id de los jugadores: primer digito el id del equipo y segundo el id del jugador
	public Equipo GenerarEquipoAleatorio(int pNumEquipo) {
		int numAleatorio = NumeroAleatorio.obtenerNumAleatorio(this.nombresEquipoAleatorio.length)-1;
		String nombre = this.nombresEquipoAleatorio[numAleatorio];
		Equipo miEquipo = new Equipo(nombre);
		
		int numIntegrantes = NumeroAleatorio.obtenerNumAleatorio(this.numMaxIntegrantesEquipo);
		int idJugador = 1 + pNumEquipo*10;
		
		while(numIntegrantes > 0) {
			Jugador miJugador = this.GenerarJugadorAleatorio(idJugador);
			miEquipo.anadirJugador(miJugador);
			numIntegrantes--;
			idJugador++;
		}
		
		return miEquipo;
	}
	
	private Jugador GenerarJugadorAleatorio(int pId) {
		Jugador miJugador = new Jugador(pId, this.valorVidaMaxima, this.valorAtaqueMaximo, this.valorDefensaMaxima);
		ListaInventario miInventario = this.GenerarInventarioAleatorio();
		miJugador.setInventario(miInventario);
		
		return miJugador;
	}
	
	private ListaInventario GenerarInventarioAleatorio() {
		ListaInventario miInventario = new ListaInventario();
		int numObjetosInventario = NumeroAleatorio.obtenerNumAleatorio(this.numMaxObjetosInventario);
		
		while(numObjetosInventario > 0) {
			Objeto miObjeto = this.GenerarObjetoAleatorio();
			
			if(miObjeto != null) {
				miInventario.anadirObjeto(miObjeto);
			}
			
		}
		
		return miInventario;
	}
	
	private Objeto GenerarObjetoAleatorio() {
		Objeto miObjeto = null;
		int numAleatorio = NumeroAleatorio.obtenerNumAleatorio(3);
		
		if(numAleatorio == 1) {
			int valorAtaque = NumeroAleatorio.obtenerNumAleatorio(this.valorAtaqueObjetoMaximo);
			miObjeto = new ObjetoAtaque(valorAtaque);
			
		}else if(numAleatorio == 2) {
			int valorDefensa = NumeroAleatorio.obtenerNumAleatorio(this.valorDefensaObjetoMaxima);
			miObjeto = new ObjetoDefensa(valorDefensa);
			
		}else if(numAleatorio == 3) {
			int valorCuracion = NumeroAleatorio.obtenerNumAleatorio(this.valorCuracionObjetoMaximo);
			miObjeto = new ObjetoCuracion(valorCuracion);
		}
		
		return miObjeto;
	}
}
