package xadresCamada;

import tabuleiro.peca;
import tabuleiro.posicao;
import tabuleiro.tab;

public abstract class pecaXadrez  extends peca{

	private cor COR;
	private int moverContagem;

	public pecaXadrez(tab tabuleiro, cor cOR) {
		super(tabuleiro);
		this.COR = cOR;
	}

	public cor getCOR() {
		return COR;
	}
	
	public void incrementarContagem() {
		moverContagem++;
	}
	
	public void decrementarContagem() {
		moverContagem--;
	}
	
	public int getMoverContagem() {
		return moverContagem;
	}
	
	public xadrezPosicao getXadrezPosicao() {
		return xadrezPosicao.fromPosicao(pos);
	}
	
	protected boolean existeUmaPecadoOponente(posicao pos) {
		pecaXadrez p = (pecaXadrez)getTab().Peca(pos);
		return p != null && p.getCOR() != COR;
	}

	
}
