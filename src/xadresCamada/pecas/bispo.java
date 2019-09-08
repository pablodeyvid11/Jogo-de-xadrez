package xadresCamada.pecas;

import tabuleiro.posicao;
import tabuleiro.tab;
import xadresCamada.cor;
import xadresCamada.pecaXadrez;

public class bispo extends pecaXadrez {

	public bispo(tab tabuleiro, cor cOR) {
		super(tabuleiro, cOR);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

		posicao p = new posicao(0, 0);

		// verificar a noroeste da peça
		p.setValores(pos.getLinha() - 1, pos.getColuna() - 1);

		while (getTab().posicaoExistente(p) && !getTab().existePecaAqui(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTab().posicaoExistente(p) && existeUmaPecadoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verificar a nordeste da peça
		p.setValores(pos.getLinha() - 1, pos.getColuna() + 1);

		while (getTab().posicaoExistente(p) && !getTab().existePecaAqui(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTab().posicaoExistente(p) && existeUmaPecadoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// verificar a sudeste da peça
		p.setValores(pos.getLinha() +1, pos.getColuna() + 1);

		while (getTab().posicaoExistente(p) && !getTab().existePecaAqui(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTab().posicaoExistente(p) && existeUmaPecadoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// verificar a sudoeste da peça
		p.setValores(pos.getLinha() + 1, pos.getColuna() - 1);

		while (getTab().posicaoExistente(p) && !getTab().existePecaAqui(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTab().posicaoExistente(p) && existeUmaPecadoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}
}
