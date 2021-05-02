package org.battlesimulator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Fichero {
	private static Fichero miFichero;
	private String rutaFichero;
	private String datosSobreescribirFichero;
	
	private Fichero() {
		String dirActual = System.getProperty("user.dir");
		this.rutaFichero = dirActual + File.separator + "assets" +  File.separator + "entrada.txt";
		this.datosSobreescribirFichero = "";
		
		try {
			InputStream fich = new FileInputStream(this.rutaFichero);
		} catch (FileNotFoundException e) {
			// Fichero no encontrado, lo creamos
			File fichero = new File(this.rutaFichero);
			
			try {
				fichero.createNewFile();
			} catch (IOException e1) {
				System.out.println("No se ha podido crear el fichero. Probablemente haya un error en la ruta del mismo");
				e1.printStackTrace();
			}
			
		}
	}
	
	public static Fichero getFichero() {
		if(Fichero.miFichero == null) { Fichero.miFichero = new Fichero(); }
		return Fichero.miFichero;
	}
	
	/*
	 * Paleta de comandos
	 * 
	 * E -> inicio de descripcion de un equipo
	 * "" -> introduccion de nombres
	 * [] -> descripcion de jugadores o objetos
	 * V,A,D -> caractesistica a la que se le establece un valor (Vida, Ataque, Defensa)
	 * I -> inicio de descripcion de un inventario
	 * , -> separadores de caracteristicas
	 * INTRO -> Siguiente equipo
	 * */
	
	public Equipo[] crearEquiposDesdeFichero() throws SintaxisErrorException{
		InputStream fich = null;
		try {
			fich = new FileInputStream(this.rutaFichero);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(fich);
		Equipo[] misEquipos = {null, null};
		int index = 0;
		
		while (sc.hasNextLine() && index <= 1){
			String linea = sc.nextLine();
		    //System.out.println(linea);
		    
		    if(linea.charAt(0) == 'E') {
		    	//Inicia un equipo
		    	String nombreEquipo = "";
		    	
		    	if(linea.indexOf(34) < 0 || linea.indexOf(34) + 1 >= linea.length()) {
		    		throw (new SintaxisErrorException());
		    	
		    	}
		    	linea = linea.substring(linea.indexOf(34) + 1); // buscamos el inicio del nombre del equipo por las comillas "
		    	//System.out.println(linea);
		    	
		    	if(linea.indexOf(34) < 0) {
		    		throw (new SintaxisErrorException());
				    		
		    	}
		    	nombreEquipo = linea.substring(0, linea.indexOf(34));
		    	misEquipos[index] = new Equipo(nombreEquipo);
				    	
		    	//Recorremos los jugadores (un jugador por linea, si la linea no empieza por '[' se concluye la creacion del equipo)
		    	boolean equipoTerminado = false;
		    	while(sc.hasNextLine() && !equipoTerminado) {
		    		linea = sc.nextLine();
		    		//System.out.println(linea);
		    		if(linea == "") {
		    			equipoTerminado = true;
		    		}
		    		else if(linea.charAt(0) == '[') {
			    		misEquipos[index].anadirJugador(this.crearJugadorDesdeLinea(linea)); 
		    			
		    		}else {
		    			equipoTerminado = true;
		    		}
		    		
		    	}
		    }
		    index++;
		}
		
		if(misEquipos[0] == null || misEquipos[1] == null) {
			System.out.println("Algun equipo no esta bien definido");
			throw (new SintaxisErrorException());
		}	
		
		return misEquipos;
	}
	
	private Jugador crearJugadorDesdeLinea(String pLinea) throws SintaxisErrorException {
		Jugador miJugador = null;
		String nombreJugador = "";
		int vida = 0;
		int ataque = 0;
		int defensa = 0;
		
		//Comprobamos que el string recibido contiene el nombre del jugador (inicio de las comillas ")
		if(pLinea.indexOf(34) < 0 || pLinea.indexOf(34) + 1 >= pLinea.length()) {
	   		throw (new SintaxisErrorException());
	    	
	   	}
		pLinea = pLinea.substring(pLinea.indexOf(34) + 1); 
	   	
		//Comprobamos que el string contiene el nombre del jugador (final de las comillas ") 
		if(pLinea.indexOf(34) < 0) {
			throw (new SintaxisErrorException());
		}
		nombreJugador = pLinea.substring(0, pLinea.indexOf(34)); //Obtencion del nombre del jugador
		//System.out.println(pLinea);
		
		//Preparamos el string para la obtencion de los datos del jugador (busqueda de la comida anterior a la palabra V)
		if(pLinea.indexOf(",") +1 >= pLinea.length()) {
			throw (new SintaxisErrorException());
		}	
		pLinea = pLinea.substring(pLinea.indexOf(",") +1);
		//System.out.println(pLinea);
		
		//Comprobamos que se encuentra la informacion de vida y la sintaxis es correcta (V___,)
		if(pLinea.indexOf("V") < 0 || pLinea.indexOf("V") +1 > pLinea.indexOf(",")) {
			throw (new SintaxisErrorException());
				    		
		}
		pLinea = pLinea.substring(pLinea.indexOf("V") +1);
		vida = Integer.parseInt(pLinea.substring(0, pLinea.indexOf(","))); 
		//System.out.println(pLinea);
		
		//Preparamos el string para la obtencion de los datos del jugador (busqueda de la comida anterior a la palabra A)
		if(pLinea.indexOf(",") +1 >= pLinea.length()) {
			throw (new SintaxisErrorException());
		}	
		pLinea = pLinea.substring(pLinea.indexOf(",") +1);
		//System.out.println(pLinea);
		
		//Comprobamos que se encuentra la informacion de ataque y la sintaxis es correcta (A___,)
		if(pLinea.indexOf("A") < 0 || pLinea.indexOf("A") +1 > pLinea.indexOf(",")) {
			throw (new SintaxisErrorException());
					    		
		}
		pLinea = pLinea.substring(pLinea.indexOf("A") +1);
		ataque =Integer.parseInt(pLinea.substring(0, pLinea.indexOf(","))); 
		//System.out.println(pLinea);
		
		//Preparamos el string para la obtencion de los datos del jugador (busqueda de la comida anterior a la palabra D)
		if(pLinea.indexOf(",") +1 >= pLinea.length()) {
			throw (new SintaxisErrorException());
		}	
		pLinea = pLinea.substring(pLinea.indexOf(",") +1);
		//System.out.println(pLinea);
		
		//Comprobamos que se encuentra la informacion de curacion y la sintaxis es correcta (D___,)
		if(pLinea.indexOf("D") < 0 || pLinea.indexOf("D") +1 > pLinea.indexOf(",")) {
			throw (new SintaxisErrorException());
						    		
		}
		pLinea = pLinea.substring(pLinea.indexOf("D") +1);
		defensa =Integer.parseInt(pLinea.substring(0, pLinea.indexOf(","))); 
		//System.out.println(pLinea);
						    	
		miJugador = new Jugador(nombreJugador, vida, ataque, defensa);
		miJugador.setInventario(this.crearInventarioDesdeLinea(pLinea));
		
		return miJugador;
	}
	
	private ListaInventario crearInventarioDesdeLinea(String pLinea) throws SintaxisErrorException {
		ListaInventario miInventario = new ListaInventario();
		if(pLinea.indexOf("I") < 0 || pLinea.indexOf("I") +2 < 0) {
	   		throw (new SintaxisErrorException());
	    	
	   	}
		pLinea = pLinea.substring(pLinea.indexOf("I") +2); // buscamos el inicio del nombre del Jugador por las comillas "
		//System.out.println(pLinea);
		int index = 1;
		
		while(pLinea.indexOf("[") >= 0) {
			
			pLinea = pLinea.substring(pLinea.indexOf("["));
			
			if(pLinea.indexOf("]") < 0 || pLinea.indexOf("]") +1 >= pLinea.length()) {
			   		throw (new SintaxisErrorException());
			}
			
			miInventario.anadirObjeto(this.crearObjetoDesdeLinea(index, pLinea.substring(0, pLinea.indexOf("]")))); 
			pLinea = pLinea.substring(pLinea.indexOf("]")+1);
			//System.out.println(pLinea);
			index++;
			   		
		}
		
		return miInventario;
	}
	
	private Objeto crearObjetoDesdeLinea( int pId, String pLinea) throws SintaxisErrorException {
		Objeto miObjeto = null;
		String nombreObjeto = "";
		int valorObjeto = 0;
		
		//System.out.println(pLinea);
		
		if(pLinea.indexOf(34) < 0 && pLinea.indexOf(34) + 1 >= pLinea.length()) {
	   		throw (new SintaxisErrorException());
	   		
	   	}
		pLinea = pLinea.substring(pLinea.indexOf(34) + 1); // buscamos el inicio del nombre del Objeto por las comillas "
	   	
		if(pLinea.indexOf(34) < 0) {
			throw (new SintaxisErrorException());
		    		
		}
		nombreObjeto = pLinea.substring(0, pLinea.indexOf(34));
		//System.out.println(pLinea);
		  
		//Preparamos el string para leer el tipo de objeto
		if(pLinea.indexOf(",") +1 >= pLinea.length()) {
			throw (new SintaxisErrorException());
		}	
		pLinea = pLinea.substring(pLinea.indexOf(",") +1);
		//System.out.println(pLinea);
		
		//Leemos el tipo de objeto del que se trata (primera posicion) (A ataque, D defensa, C curacion)
		if(pLinea.charAt(0) != 'A' && pLinea.charAt(0) != 'D' && pLinea.charAt(0) != 'C') {
			throw (new SintaxisErrorException());
		}
		
		
		if(pLinea.charAt(0) == 'A') {
			if(pLinea.indexOf("A") + 1 >= pLinea.length()) {
		   		throw (new SintaxisErrorException());
		   		
		   	}
			valorObjeto = Integer.parseInt(pLinea.substring(1, pLinea.length()));
			miObjeto = new ObjetoAtaque(pId, nombreObjeto, valorObjeto);
			
		}else if(pLinea.charAt(0) == 'D') {
			if(pLinea.indexOf("D") + 1 >= pLinea.length()) {
		   		throw (new SintaxisErrorException());
		   		
		   	}
			valorObjeto = Integer.parseInt(pLinea.substring(1, pLinea.length()));
			miObjeto = new ObjetoDefensa(pId, nombreObjeto, valorObjeto);
			
		}else if(pLinea.charAt(0) == 'C') {
			if(pLinea.indexOf("C") + 1 >= pLinea.length()) {
		   		throw (new SintaxisErrorException());
		   		
		   	}
			valorObjeto = Integer.parseInt(pLinea.substring(1, pLinea.length()));
			miObjeto = new ObjetoCuracion(pId, nombreObjeto, valorObjeto);
			
		}
		
		
		return miObjeto;
	}
	
	public void iniciarGuardarEquipoEnFichero(String pNombre) {
		this.datosSobreescribirFichero += "E" + "\"" + pNombre +"\"" + ":" + "\n";
	}
	
	public void terminarGuardarEquipoEnFichero() {
		this.datosSobreescribirFichero += "\n";
	}
	
	public void iniciarGuardarJugadorEnFichero(String pNombre, int pVida, int pAtaque, int pDefensa) {
		this.datosSobreescribirFichero += "[" + "\"" + pNombre +"\"" +  ",V" + pVida + ",A" + pAtaque + ",D" + pDefensa + ",";
	}
	
	public void terminarGuardarJugadorEnFichero() {
		this.datosSobreescribirFichero += "]" + "\n";
	}
	
	public void iniciarGuardarInventarioEnFichero() {
		this.datosSobreescribirFichero += "I(";
	}
	
	public void terminarGuardarInventarioEnFichero() {
		this.datosSobreescribirFichero += ")";
	}
	
	public void guardarObjetoInventarioEnFichero(String pNombre, String pTipo, int pValor) {
		this.datosSobreescribirFichero += "[" + "\"" + pNombre +"\"" + "," + pTipo + pValor + "]";
	}
	
	public void confirmarSobreescribirFichero() {
		FileWriter fichero = null;
		try {
			fichero = new FileWriter(this.rutaFichero, false);
		} catch (IOException e) {
			System.out.println("Se ha producido un error al ecceder al fichero de guardado. Probablemente exista un problema con la ruta del mismo");

		}
		
		PrintWriter pw = new PrintWriter(fichero);
		pw.print(this.datosSobreescribirFichero);
		pw.close();
		System.out.println("Se han sobreescrito los datos satisfactoriamente");
	}
}
