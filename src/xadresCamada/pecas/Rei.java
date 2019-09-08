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

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];
		return mat;
	}
}
