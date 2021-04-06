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
		if(this.estaVivo() && pVida > 0) {
			this.vida += pVida;
		}
		
		if(this.vida > this.vidaMaxima) {
			this.vida = this.vidaMaxima;
		}
	}
	
	public void atacarJugador(Jugador pJugador) {
		int danoTotal = this.ataque + this.inventario.obtenerDanoObjetos();
		
		System.out.println("El jugador " + this.nombre + " ha atacado al jugador " + pJugador.getNombre() + " con " + danoTotal + " puntos de ataque");
		pJugador.recibirDano(danoTotal);
	}
	
	public void curarJugador(Jugador pJugador) {
		//Habra que implementarlo de otra forma. Usar solo una venda por ejemplo
		int vidaTotal = this.inventario.obtenerCuracionObjetos();
		
		System.out.println("El jugador " + this.nombre + " ha curado al jugador " + pJugador.getNombre() + " con " + vidaTotal + " puntos de vida");
		pJugador.curarVida(vidaTotal);
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
