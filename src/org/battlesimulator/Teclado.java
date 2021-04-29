package org.battlesimulator;

import java.util.Scanner;

public class Teclado {
	private static Teclado miTeclado;
	private Scanner sc;
	
	private Teclado() {
		this.sc = new Scanner(System.in);
	}
	
	public static Teclado getTeclado() {
		if(Teclado.miTeclado == null) { Teclado.miTeclado = new Teclado(); }
		return Teclado.miTeclado;
	}
	
	public String leerString(String pMensajePrevio) {
		System.out.println(pMensajePrevio);
		return sc.nextLine();
	}
	
	public int leerEntero(String pMensajePrevio) {
		System.out.println(pMensajePrevio);
		String miLinea = sc.nextLine();
		int entero = 0;
		boolean valorCorrecto = false;
		do {
			try{
		      entero = Integer.parseInt(miLinea.trim()); 
			      
		    }
			catch (NumberFormatException nfe){
				System.out.println("Introduce un numero entero positivo por favor");
			}
		}while(!valorCorrecto);
		
		return entero;
	}
}
