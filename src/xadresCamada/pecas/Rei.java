package xadresCamada.pecas;

import tabuleiro.posicao;
import tabuleiro.tab;
import xadresCamada.cor;
import xadresCamada.partidaXadrez;
import xadresCamada.pecaXadrez;

public class Rei extends pecaXadrez {

	private partidaXadrez xadrez;

	public Rei(tab tabuleiro, cor cOR, partidaXadrez xadrez) {
		super(tabuleiro, cOR);
		this.xadrez = xadrez;
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean pode(posicao pos) {
		pecaXadrez p = (pecaXadrez) getTab().Peca(pos);
		return p == null || p.getCOR() != getCOR();
	}

	private boolean testeRook(posicao pos) {
		pecaXadrez p = (pecaXadrez) getTab().Peca(pos);
		return p != null && p instanceof torre && p.getCOR() == getCOR() && p.getMoverContagem() == 0;
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
		p.setValores(pos.getLinha() - 1, pos.getColuna() - 1);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// nordeste
		p.setValores(pos.getLinha() - 1, pos.getColuna() + 1);
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

		// Movimento roque
		if (getMoverContagem() == 0 && !xadrez.getCheck()) {
			// roque pequeno
			posicao posicaoTorre1 = new posicao(pos.getLinha(), pos.getColuna() + 3);
			if (testeRook(posicaoTorre1)) {
				posicao p1 = new posicao(pos.getLinha(), pos.getColuna() + 1);
				posicao p2 = new posicao(pos.getLinha(), pos.getColuna() + 2);
				if (getTab().Peca(p1) == null && getTab().Peca(p2) == null) {
					mat[pos.getLinha()][pos.getColuna() + 2] = true;
				}
			}
			// roque grande
			posicao posicaoTorre2 = new posicao(pos.getLinha(), pos.getColuna() - 4);
			if (testeRook(posicaoTorre2)) {
				posicao p1 = new posicao(pos.getLinha(), pos.getColuna() - 1);
				posicao p2 = new posicao(pos.getLinha(), pos.getColuna() - 2);
				posicao p3 = new posicao(pos.getLinha(), pos.getColuna() - 3);
				if (getTab().Peca(p1) == null && getTab().Peca(p2) == null && getTab().Peca(p3) == null) {
					mat[pos.getLinha()][pos.getColuna() - 2] = true;
				}
			}
		}

		return mat;
	}
}
