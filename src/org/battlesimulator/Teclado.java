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
		boolean entradaValida = esNumero(miLinea);

		while(!entradaValida) {
			if(esNumero(miLinea)) {
				entradaValida = true;
			}else {
				System.out.println("Introduce un numero entero positivo");
				miLinea = sc.nextLine();
			}
		}
		
		 
		
		return Integer.parseInt(miLinea.trim());
	}
	
	 private static boolean esNumero(String str){
	        return str != null && str.matches("[0-9.]+");
	    }
}
