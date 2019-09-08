package xadresCamada;

import tabuleiro.peca;
import tabuleiro.posicao;
import tabuleiro.tab;

public abstract class pecaXadrez  extends peca{

	private cor COR;

	public pecaXadrez(tab tabuleiro, cor cOR) {
		super(tabuleiro);
		this.COR = cOR;
	}

	public cor getCOR() {
		return COR;
	}
	
	public xadrezPosicao getXadrezPosicao() {
		return xadrezPosicao.fromPosicao(pos);
	}
	
	protected boolean existeUmaPecadoOponente(posicao pos) {
		pecaXadrez p = (pecaXadrez)getTab().Peca(pos);
		return p != null && p.getCOR() != COR;
	}
}
