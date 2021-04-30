package org.battlesimulator;

public class Jugador {
	//Atributos del jugador
	private String nombre;
	
	private int vidaMaxima;
	private int vida;
	private int ataque;
	private int defensa;
	
	private ListaInventario inventario;
	
	public Jugador(String pNombre, int pVidaMaxima, int pAtaque, int pDefensa) {
		this.nombre = pNombre;
		this.vidaMaxima = pVidaMaxima;
		this.vida = pVidaMaxima;
		this.ataque = pAtaque;
		this.defensa = pDefensa;
		this.inventario = new ListaInventario();
	}
	
	public boolean estaVivo() {
		return this.vida > 0;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setInventario(ListaInventario pInventario) {
		this.inventario = pInventario;
	}
	
	//Método para recibir daño
	public void recibirDano(int pDano) {
		//Daño total = dañoBase - defensaJugador
		int vidaAntes = this.vida;
		
		int defensaTotal = this.defensa + this.inventario.obtenerProteccionObjetos();
		pDano -= defensaTotal; 
		
		//Comprobar si el daño es negativo, en cuyo caso no se hace nada
		if(this.estaVivo() && pDano > 0) {
			this.vida -= pDano;
		}
		
		//Para evitar posibles bugs
		if(this.vida > vidaAntes) {
			this.vida = vidaAntes;
		}
		
		if(this.vida > 0) {
			System.out.println("Al jugador " + this.nombre + " le quedan " + this.vida + " puntos de vida");
		}else {
			System.out.println("El jugador de id " + this.nombre + " se ha debilitado");
		}
	}
	
	public void curarVida(int pVida) {
		if(this.vida < this.vidaMaxima) {
			if(this.estaVivo() && pVida > 0) {
				this.vida += pVida;
			}
			
			if(this.vida > this.vidaMaxima) {
				this.vida = this.vidaMaxima;
			}
		}else {
			System.out.println("Sin embargo, la vida de este jugador ya esta al máximo. Siguiente turno");
		}
	}
	
	public void atacarJugador(Jugador pJugador) {
		int danoTotal = this.ataque + this.inventario.obtenerDanoObjetos();
		
		System.out.println("El jugador " + this.nombre + " ha atacado al jugador " + pJugador.getNombre() + " con " + danoTotal + " puntos de ataque");
		pJugador.recibirDano(danoTotal);
	}
	
	public void curarJugadorAutomaticamente(Jugador pJugador) {
		//Habra que implementarlo de otra forma. Usar solo una venda por ejemplo
		int vidaCuracion = this.inventario.obtenerCuracionPrimerObjeto();
		if(vidaCuracion == 0) {
			vidaCuracion = 1;
			System.out.println("El jugador no cuenta con objetos de curacion, por lo que usa Hierbajo (1 curacion)");
		}
		
		System.out.println("El jugador " + this.nombre + " ha curado al jugador " + pJugador.getNombre() + " con " + vidaCuracion + " puntos de vida");
		pJugador.curarVida(vidaCuracion);
	}
	
	public void curarJugadorConDecision(Jugador pJugador) {
		System.out.println("Opciones de curacion: ");
		this.inventario.imprimirOpcionesCuracion();
		
		int vidaCuracion = 0;
		
		if(this.inventario.obtenerCuracionPrimerObjeto() == 0) {
			int seleccion = Teclado.getTeclado().leerEntero("El jugador no cuenta con objetos de curacion. ¿Quiere usar un Hierbajo (1 curacion)? Presione 1 en caso afirmativo, otra tecla en caso contrario");
			if(seleccion == 1) {
				vidaCuracion = 1;
				System.out.println("El jugador " + this.nombre + " ha curado al jugador " + pJugador.getNombre() + " con " + vidaCuracion + " puntos de vida");
			}
		}else {
			this.inventario.imprimirOpcionesCuracion();
			System.out.println("Seleccione el objeto de curacion");
			boolean terminado = false;
			int seleccion;
			
			while(!terminado) {
				seleccion = Teclado.getTeclado().leerEntero("");
				if(this.inventario.estaObjetoConId(seleccion)) {
					vidaCuracion = this.inventario.obtenerCuracionObjetoConId(seleccion);
					terminado = true;
				}
			}
			
			System.out.println("El jugador " + this.nombre + " ha curado al jugador " + pJugador.getNombre() + " con " + vidaCuracion + " puntos de vida");
		}
		
		
	}
	
	public void imprimirJugadorInventario() {
		this.imprimirJugador();
		System.out.println("Además, su inventario esta formado por: ");
		this.inventario.imprimirInventario();
	}
	
	public void imprimirJugador() {
		String debilitado = "(debilitado)";
		if(this.estaVivo()) { debilitado = ""; }
		
		System.out.println("El jugador " + this.nombre + " tiene " + this.vida + " puntos de vida, " + this.ataque + " puntos de ataque y " + this.defensa + " puntos de defensa." + debilitado);
	}
	
}
