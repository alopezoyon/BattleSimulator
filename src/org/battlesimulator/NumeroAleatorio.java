package org.battlesimulator;

import java.util.Random;

public class NumeroAleatorio {
	
	public static int obtenerNumAleatorio(int pNumMax) {
		Random r = new Random();
		int num = r.nextInt(pNumMax) + 1;
		return num;
	}
}
