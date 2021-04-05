package org.battlesimulator;

import java.util.Scanner;

public class Teclado {
	private static Teclado miTeclado;
	private Scanner sc;
	
	private Teclado() {
	}
	
	public Teclado getTeclado() {
		if(Teclado.miTeclado == null) { Teclado.miTeclado = new Teclado(); }
		return Teclado.miTeclado;
	}
	
	public String leerString(String pMensajePrevio) {
		System.out.println(pMensajePrevio);
		return sc.nextLine();
	}
	
	public int leerEntero(String pMensajePrevio) {
		System.out.println(pMensajePrevio);
		return sc.nextInt();
	}
}
