package org.battlesimulator;

public class Jugador {
	//Atributos del jugador
	private int id;
	
	private int vida;
	private int ataque;
	private int defensa;
	
	private ListaInventario inventario;
	
	public Jugador(int pId, int pVida, int pAtaque, int pDefensa) {
		this.id = pId;
		this.vida = 10;
		this.ataque = 10;
		this.defensa = 10;
		
	}
	
	public boolean estaVivo() {
		return !(this.vida >= 0);
	}
	
	public boolean tieneEsteId(int pId) {
		return this.id == pId;
	}
	
	//Método para recibir daño
	public void recibirDano(int pDano) {
		//Daño total = dañoBase - defensaJugador
		int defensaTotal = this.defensa + this.inventario.obtenerProteccionObjetos();
		pDano -= defensaTotal; 
		
		//Comprobar si el daño es negativo, en cuyo caso no se hace nada
		if(this.estaVivo() && pDano > 0) {
			this.vida -= pDano;
		}
		
	}
	
	public void curarVida(int pVida) {
		if(this.estaVivo() && pVida > 0) {
			this.vida += pVida;
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
	
}
