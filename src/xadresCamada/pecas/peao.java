package xadresCamada.pecas;

import tabuleiro.posicao;
import tabuleiro.tab;
import xadresCamada.cor;
import xadresCamada.pecaXadrez;

public class peao extends pecaXadrez {

	public peao(tab tabuleiro, cor cOR) {
		super(tabuleiro, cOR);
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTab().getLinhas()][getTab().getColunas()];

		posicao p = new posicao(0, 0);
		
		if(getCOR() == cor.BRANCO) {
			p.setValores(pos.getLinha() -1 , pos.getColuna());
			if (getTab().posicaoExistente(p) && !getTab().existePecaAqui(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(pos.getLinha() -2 , pos.getColuna());
			posicao p2 = new posicao(pos.getLinha() -1 , pos.getColuna());
			if (getTab().posicaoExistente(p) && !getTab().existePecaAqui(p) && getTab().posicaoExistente(p2) && !getTab().existePecaAqui(p2) && getMoverContagem() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(pos.getLinha() -1 , pos.getColuna() -1 );
			if (getTab().posicaoExistente(p) && existeUmaPecadoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(pos.getLinha() -1 , pos.getColuna() +1 );
			if (getTab().posicaoExistente(p) && existeUmaPecadoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		} else {
			p.setValores(pos.getLinha() +1 , pos.getColuna());
			if (getTab().posicaoExistente(p) && !getTab().existePecaAqui(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(pos.getLinha() +2 , pos.getColuna());
			posicao p2 = new posicao(pos.getLinha() -1 , pos.getColuna());
			if (getTab().posicaoExistente(p) && !getTab().existePecaAqui(p) && getTab().posicaoExistente(p2) && !getTab().existePecaAqui(p2) && getMoverContagem() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(pos.getLinha() +1 , pos.getColuna() -1 );
			if (getTab().posicaoExistente(p) && existeUmaPecadoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(pos.getLinha() +1 , pos.getColuna() +1 );
			if (getTab().posicaoExistente(p) && existeUmaPecadoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		return mat;
	}

}
