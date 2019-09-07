package application;

import xadresCamada.pecaXadrez;

public class UI {
	public static void escreverTabuleiro(pecaXadrez[][] pecaxadr) {
		for (int i = 0; i < pecaxadr.length; i++) {
			System.out.print((8-i) + " ");
			for (int j = 0; j < pecaxadr[0].length; j++) {
				escreverPeca(pecaxadr[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void escreverPeca(pecaXadrez pe) {
		if (pe == null) {
			System.out.print("-");
		} else {
			System.out.print(pe);
		}
		System.out.print(" ");
	}
}
