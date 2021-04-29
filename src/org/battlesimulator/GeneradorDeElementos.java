package org.battlesimulator;

public class GeneradorDeElementos {
	
	private static GeneradorDeElementos miGenerador;
	//Configuracion Equipo
	private int numMaxIntegrantesEquipo = 2;
	private ListaNombres listaNombresEquipo;
	
	//Configuracion Jugador
	private int valorVidaMaxima = 30;
	private int valorAtaqueMaximo = 10;
	private int valorDefensaMaxima = 10;
	private ListaNombres listaNombresJugador;
	
	//Configuracion Objetos
	private int numMaxObjetosInventario = 2;
	private int valorAtaqueObjetoMaximo = 7;
	private int valorDefensaObjetoMaxima = 4;
	private int valorCuracionObjetoMaximo = 3;
	private ListaNombres listaNombresObjetoAtaque;
	private ListaNombres listaNombresObjetoDefensa;
	private ListaNombres listaNombresObjetoCuracion;
	
	
	private GeneradorDeElementos() {
		String[] nombresEquipo = {"Anticipacion", "Snidel", "La Compania"};
		this.listaNombresEquipo = new ListaNombres(nombresEquipo);
		
		String[] nombresJugador = {"Francisco", "Gustavo", "Sara"};
		this.listaNombresJugador = new ListaNombres(nombresJugador);
		
		String[] nombresObjetoAtaque = {"Hacha", "Espada", "Hoz"};
		this.listaNombresObjetoAtaque = new ListaNombres(nombresObjetoAtaque);
		
		String[] nombresObjetoDefensa = {"Casco", "Armadura", "Pantorrillas"};
		this.listaNombresObjetoDefensa = new ListaNombres(nombresObjetoDefensa);
		
		String[] nombresObjetoCuracion = {"Venda", "Venda", "Botiquin"};
		this.listaNombresObjetoCuracion = new ListaNombres(nombresObjetoCuracion);
		
	}
	
	public static GeneradorDeElementos getGeneradorDeElementos() {
		if(GeneradorDeElementos.miGenerador == null) {GeneradorDeElementos.miGenerador = new GeneradorDeElementos(); }
		return GeneradorDeElementos.miGenerador;
	}
	
	//Id de los jugadores: primer digito el id del equipo y segundo el id del jugador
	public Equipo GenerarEquipoAleatorio() {
		String nombre = this.listaNombresEquipo.obtenerNombreAleatorio();
		Equipo miEquipo = new Equipo(nombre);
		
		int numIntegrantes = NumeroAleatorio.obtenerNumAleatorio(this.numMaxIntegrantesEquipo);
		
		while(numIntegrantes > 0) {
			Jugador miJugador = this.GenerarJugadorAleatorio();
			miEquipo.anadirJugador(miJugador);
			numIntegrantes--;
		}
		
		return miEquipo;
	}
	
	private Jugador GenerarJugadorAleatorio() {
		int vida = NumeroAleatorio.obtenerNumAleatorio(this.valorVidaMaxima);
		int ataque = NumeroAleatorio.obtenerNumAleatorio(this.valorAtaqueMaximo);
		int defensa = NumeroAleatorio.obtenerNumAleatorio(this.valorDefensaMaxima);
		String nombre = this.listaNombresJugador.obtenerNombreAleatorio();
		Jugador miJugador = new Jugador(nombre, vida, ataque, defensa);
		ListaInventario miInventario = this.GenerarInventarioAleatorio();
		miJugador.setInventario(miInventario);
		
		return miJugador;
	}
	
	private ListaInventario GenerarInventarioAleatorio() {
		ListaInventario miInventario = new ListaInventario();
		int numObjetosInventario = NumeroAleatorio.obtenerNumAleatorio(this.numMaxObjetosInventario);
		int index = 1;
		while(numObjetosInventario > 0) {
			Objeto miObjeto = this.GenerarObjetoAleatorio(index);
			
			if(miObjeto != null) {
				miInventario.anadirObjeto(miObjeto);
			}
			numObjetosInventario--;
			index++;
		}
		
		return miInventario;
	}
	
	private Objeto GenerarObjetoAleatorio(int pId) {
		Objeto miObjeto = null;
		int numAleatorio = NumeroAleatorio.obtenerNumAleatorio(3);
		
		if(numAleatorio == 1) {
			int valorAtaque = NumeroAleatorio.obtenerNumAleatorio(this.valorAtaqueObjetoMaximo);
			String nombre = this.listaNombresObjetoAtaque.obtenerNombreAleatorio();
			miObjeto = new ObjetoAtaque(pId, nombre, valorAtaque);
			
		}else if(numAleatorio == 2) {
			int valorDefensa = NumeroAleatorio.obtenerNumAleatorio(this.valorDefensaObjetoMaxima);
			String nombre = this.listaNombresObjetoDefensa.obtenerNombreAleatorio();
			miObjeto = new ObjetoDefensa(pId, nombre, valorDefensa);
			
		}else if(numAleatorio == 3) {
			int valorCuracion = NumeroAleatorio.obtenerNumAleatorio(this.valorCuracionObjetoMaximo);
			String nombre = this.listaNombresObjetoCuracion.obtenerNombreAleatorio();
			miObjeto = new ObjetoCuracion(pId, nombre, valorCuracion);
		}
		
		return miObjeto;
	}
	
	public Equipo generarEquipoUsuario() {
		System.out.print("Creacion de Equipo");
		Teclado.getTeclado().leerString("");
		String nombre = Teclado.getTeclado().leerString("Introduzca el nombre del equipo");
		Equipo miEquipo = new Equipo(nombre);
		
		int integrantes = Teclado.getTeclado().leerEntero("Introduzca el numero de integrantes del equipo (1 - " + this.numMaxIntegrantesEquipo + ")");
		if(integrantes < 1) { integrantes = 1; }
		if(integrantes > this.numMaxIntegrantesEquipo) { integrantes = this.numMaxIntegrantesEquipo; }
		
		int index = 0;
		while(index < integrantes) {
			System.out.print("Jugador " + index+1);
			miEquipo.anadirJugador(this.generarJugadorUsuario());
			index++;
		}
		
		System.out.println("-- El equipo se ha generado satisfactoriamente: ");
		miEquipo.imprimirEquipo();
		return miEquipo;
	}
	
	private Jugador generarJugadorUsuario() {
		Jugador miJugador = null;
		Teclado.getTeclado().leerString("");
		String nombre = Teclado.getTeclado().leerString("Introduzca el nombre del Jugador (String)");
		int vidaMaxima = Teclado.getTeclado().leerEntero("Introduzca la vida maxima del jugador (1 - " + this.valorVidaMaxima + " )");
		int ataque = Teclado.getTeclado().leerEntero("Introduzca el ataque base del jugador (1 - " + this.valorAtaqueMaximo + " )");
		int defensa = Teclado.getTeclado().leerEntero("Introduzca la defensa basae del jugador (1 - " + this.valorDefensaMaxima + " )");
		int numObjetos = Teclado.getTeclado().leerEntero("Introduzca el numero de objetos del inventario del jugador (1 - " + this.numMaxObjetosInventario + " )");
		
		if(vidaMaxima < 1) { vidaMaxima = 1; }
		if(ataque < 1) { ataque = 1; }
		if(defensa < 1) { defensa = 1; }
		if(numObjetos < 1) { numObjetos = 1; }
		
		if(vidaMaxima > this.valorVidaMaxima) { vidaMaxima = this.valorVidaMaxima; }
		if(ataque > this.valorAtaqueMaximo) { ataque = this.valorAtaqueMaximo; }
		if(defensa > this.valorDefensaMaxima) { defensa = this.valorDefensaMaxima; }
		if(numObjetos > this.numMaxObjetosInventario) { numObjetos = this.numMaxObjetosInventario; }
		
		ListaInventario inventario = this.generarInventarioUsuario(numObjetos);
		miJugador = new Jugador(nombre, vidaMaxima, ataque, defensa);
		miJugador.setInventario(inventario);
		
		return miJugador;
	}
	
	private ListaInventario generarInventarioUsuario(int pNumObjetos) {
		ListaInventario inventario = new ListaInventario();
		int index = 0;
		while(index < pNumObjetos) {
			System.out.println("Objeto " + index+1);
			inventario.anadirObjeto(this.generarObjetoUsuario(index +1));
			index++;
		}
		
		return inventario;
	}
	
	private Objeto generarObjetoUsuario(int pId) {
		Objeto miObjeto = null;
		boolean terminado = false;
		int tipo = 0;
		while(!terminado) {
			tipo = Teclado.getTeclado().leerEntero("Seleccione el tipo de objeto (1 ataque, 2 defensa, 3 curacion)");
			if(tipo == 1 || tipo == 2 || tipo == 3) { 
				terminado = true; 
			}else {
				System.out.println("introduzca un valor valido");
			}
		}
		
		if(tipo == 1) {
			int ataque = Teclado.getTeclado().leerEntero("Introduzca el valor de ataque del objeto (1 - " + this.valorAtaqueObjetoMaximo + " )");
			String nombre = Teclado.getTeclado().leerString("Introduzca el nombre del objeto de ataque");
			miObjeto = new ObjetoAtaque(pId, nombre, ataque);
		}else if(tipo == 2) {
			int defensa = Teclado.getTeclado().leerEntero("Introduzca el valor de defensa del objeto (1 - " + this.valorDefensaObjetoMaxima + " )");
			String nombre = Teclado.getTeclado().leerString("Introduzca el nombre del objeto de defensa");
			miObjeto = new ObjetoDefensa(pId, nombre, defensa);
		}else {
			int curacion = Teclado.getTeclado().leerEntero("Introduzca el valor de curacion del objeto (1 - " + this.valorCuracionObjetoMaximo + " )");
			String nombre = Teclado.getTeclado().leerString("Introduzca el nombre del objeto de curacion");
			miObjeto = new ObjetoCuracion(pId, nombre, curacion);
		}
		
		return miObjeto;
	}
}
