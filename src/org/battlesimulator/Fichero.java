package org.battlesimulator;

public class Fichero {
	private static Fichero miFichero;
	private String rutaFicheroEquipos;
	private String rutaDesarrolloBatalla;
	
	private Fichero() {}
	
	public static Fichero getFichero() {
		if(Fichero.miFichero == null) { Fichero.miFichero = new Fichero(); }
		return Fichero.miFichero;
	}
	
	public Equipo crearEquipoDesdeFichero() {
		return null;
	}
	
	public void guardarEquipoEnFichero(String nombreEquipo) {
	}
	
	public void escribirAccionEnFichero(String pAccion) {
	}
	
}
