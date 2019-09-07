package xadresCamada.pecas;

import tabuleiro.tab;
import xadresCamada.cor;
import xadresCamada.pecaXadrez;

public class Rei extends pecaXadrez {

	public Rei(tab tabuleiro, cor cOR) {
		super(tabuleiro, cOR);
	}
	
	@Override
	public String toString() {
		return "R";
	}
}
