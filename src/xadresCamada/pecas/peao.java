package xadresCamada.pecas;

import tabuleiro.posicao;
import tabuleiro.tab;
import xadresCamada.cor;
import xadresCamada.partidaXadrez;
import xadresCamada.pecaXadrez;

public class peao extends pecaXadrez {
	private partidaXadrez xadrez;

	public peao(tab tabuleiro, cor cOR, partidaXadrez xadrez) {
		super(tabuleiro, cOR);
		this.xadrez = xadrez;
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

		posicao p = new posicao(0, 0);

		if (getCOR() == cor.BRANCO) {
			p.setValores(pos.getLinha() - 1, pos.getColuna());
			if (getTab().posicaoExistente(p) && !getTab().existePecaAqui(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(pos.getLinha() - 2, pos.getColuna());
			posicao p2 = new posicao(pos.getLinha() - 1, pos.getColuna());
			if (getTab().posicaoExistente(p) && !getTab().existePecaAqui(p) && getTab().posicaoExistente(p2)
					&& !getTab().existePecaAqui(p2) && getMoverContagem() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(pos.getLinha() - 1, pos.getColuna() - 1);
			if (getTab().posicaoExistente(p) && existeUmaPecadoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(pos.getLinha() - 1, pos.getColuna() + 1);
			if (getTab().posicaoExistente(p) && existeUmaPecadoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// en passant

			if (pos.getLinha() == 3) {
				posicao esquerda = new posicao(pos.getLinha(), pos.getColuna() - 1);
				if (getTab().posicaoExistente(esquerda) && existeUmaPecadoOponente(esquerda)
						&& getTab().Peca(esquerda) == xadrez.getPecaVulneravel()) {
					mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				posicao direita = new posicao(pos.getLinha(), pos.getColuna() + 1);
				if (getTab().posicaoExistente(direita) && existeUmaPecadoOponente(direita)
						&& getTab().Peca(direita) == xadrez.getPecaVulneravel()) {
					mat[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}

		} else {
			p.setValores(pos.getLinha() + 1, pos.getColuna());
			if (getTab().posicaoExistente(p) && !getTab().existePecaAqui(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(pos.getLinha() + 2, pos.getColuna());
			posicao p2 = new posicao(pos.getLinha() + 1, pos.getColuna());
			if (getTab().posicaoExistente(p) && !getTab().existePecaAqui(p) && getTab().posicaoExistente(p2)
					&& !getTab().existePecaAqui(p2) && getMoverContagem() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(pos.getLinha() + 1, pos.getColuna() - 1);
			if (getTab().posicaoExistente(p) && existeUmaPecadoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(pos.getLinha() + 1, pos.getColuna() + 1);
			if (getTab().posicaoExistente(p) && existeUmaPecadoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			if (pos.getLinha() == 4) {
				posicao esquerda = new posicao(pos.getLinha(), pos.getColuna() - 1);
				if (getTab().posicaoExistente(esquerda) && existeUmaPecadoOponente(esquerda)
						&& getTab().Peca(esquerda) == xadrez.getPecaVulneravel()) {
					mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				posicao direita = new posicao(pos.getLinha(), pos.getColuna() + 1);
				if (getTab().posicaoExistente(direita) && existeUmaPecadoOponente(direita)
						&& getTab().Peca(direita) == xadrez.getPecaVulneravel()) {
					mat[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}
		return mat;
	}

}
