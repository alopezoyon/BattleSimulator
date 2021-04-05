package org.battlesimulator;

public class Jugador {
	//Atributos del jugador
	private int id;
	
	private int vidaMaxima;
	private int vida;
	private int ataque;
	private int defensa;
	
	private ListaInventario inventario;
	
	public Jugador(int pId, int pVidaMaxima, int pAtaque, int pDefensa) {
		this.id = pId;
		this.vidaMaxima = pVidaMaxima;
		this.vida = pVidaMaxima;
		this.ataque = pAtaque;
		this.defensa = pDefensa;
		this.inventario = new ListaInventario();
	}
	
	public boolean estaVivo() {
		return !(this.vida >= 0);
	}
	
	public boolean tieneEsteId(int pId) {
		return this.id == pId;
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
		pJugador.recibirDano(danoTotal);
	}
	
	public void curarJugador(Jugador pJugador) {
		//Habra que implementarlo de otra forma. Usar solo una venda por ejemplo
		int vidaTotal = this.inventario.obtenerCuracionObjetos();
		pJugador.curarVida(vidaTotal);
	}
	
	public void imprimirJugador() {
		System.out.println("El jugador " + this.id + " tiene " + this.vida + " puntos de vida, " + this.ataque + " puntos de ataque y " + this.defensa + " puntos de defensa.");
		System.out.println("Además, su inventario esta formado por: ");
		this.inventario.imprimirInventario();
	}
}
