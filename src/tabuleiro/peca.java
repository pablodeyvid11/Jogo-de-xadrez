package tabuleiro;

public class peca {
	protected posicao pos;
	private tab tabuleiro;
	
	public peca(tab tabuleiro) {
		this.tabuleiro = tabuleiro;
		pos = null;
	} 
	
	protected tab getTab() {
		return tabuleiro;
	}
	
}
