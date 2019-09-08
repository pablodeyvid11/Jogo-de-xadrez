package xadresCamada;

import tabuleiro.peca;
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

}
