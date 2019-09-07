package xadresCamada.pecas;

import tabuleiro.tab;
import xadresCamada.cor;
import xadresCamada.pecaXadrez;

public class torre extends pecaXadrez {

	public torre(tab tabuleiro, cor cOR) {
		super(tabuleiro, cOR);
	}
	
	@Override
	public String toString() {
		return "T";
	}
}
