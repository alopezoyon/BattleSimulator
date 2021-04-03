package org.battlesimulator;

public class Batalla {
	private static Batalla miBatalla;
	
	private Equipo equipo1;
	private Equipo equipo2;
	
	//constructora (privada MAE)
	private Batalla(){
	}
	
	//getBatalla patron singleton
	public static Batalla getBatalla() {
		if(Batalla.miBatalla == null) { Batalla.miBatalla = new Batalla(); }
		return Batalla.miBatalla;
	}
	
	public void iniciarBatalla() {
		
	}
	
}
