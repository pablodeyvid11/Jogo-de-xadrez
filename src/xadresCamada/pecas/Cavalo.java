package xadresCamada.pecas;

import tabuleiro.posicao;
import tabuleiro.tab;
import xadresCamada.cor;
import xadresCamada.pecaXadrez;

public class Cavalo extends pecaXadrez {

	public Cavalo(tab tabuleiro, cor cOR) {
		super(tabuleiro, cOR);
	}

	@Override
	public String toString() {
		return "C";
	}

	private boolean pode(posicao pos) {
		pecaXadrez p = (pecaXadrez) getTab().Peca(pos);
		return p == null || p.getCOR() != getCOR();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

		posicao p = new posicao(0, 0);
		
		p.setValores(pos.getLinha() - 1, pos.getColuna() -2);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(pos.getLinha() -2, pos.getColuna() -1);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(pos.getLinha() -2, pos.getColuna() + 1);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(pos.getLinha() -1, pos.getColuna() + 2);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(pos.getLinha() +1, pos.getColuna() +2);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(pos.getLinha() +2, pos.getColuna() +1);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(pos.getLinha() +2, pos.getColuna() - 1);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(pos.getLinha() + 1, pos.getColuna() -2);
		if (getTab().posicaoExistente(p) && pode(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}
}
