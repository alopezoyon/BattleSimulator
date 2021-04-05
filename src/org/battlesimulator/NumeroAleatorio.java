package org.battlesimulator;

import java.util.Random;

public class NumeroAleatorio {
	
	public int obtenerNumAleatorio(int pNumMax) {
		Random r = new Random();
		int num = r.nextInt() + 1;
		return num;
	}
}
