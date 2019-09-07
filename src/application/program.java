package application;

import tabuleiro.tab;
import xadresCamada.partidaXadrez;

public class program {

	public static void main(String[] args) {
		partidaXadrez partida = new partidaXadrez();
		UI.escreverTabuleiro(partida.getPecasXadrez());
	}

}
