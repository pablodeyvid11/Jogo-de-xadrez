package xadresCamada.pecas;

import tabuleiro.posicao;
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

	private boolean pode(posicao pos) {
		pecaXadrez p = (pecaXadrez) getTab().Peca(pos);
		return p == null || p.getCOR() != getCOR();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

		posicao p = new posicao(0, 0);
		// acima
		p.setValores(pos.getLinha() - 1, pos.getColuna());
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// abaixo
		p.setValores(pos.getLinha() + 1, pos.getColuna());
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// esquerda
		p.setValores(pos.getLinha(), pos.getColuna() - 1);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// direita
		p.setValores(pos.getLinha(), pos.getColuna() + 1);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// noroeste
		p.setValores(pos.getLinha() - 1, pos.getColuna() -1);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// nordeste
		p.setValores(pos.getLinha() - 1, pos.getColuna() +1);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// sudoeste
		p.setValores(pos.getLinha() + 1, pos.getColuna() - 1);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// sudeste
		p.setValores(pos.getLinha() + 1, pos.getColuna() + 1);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}
}
