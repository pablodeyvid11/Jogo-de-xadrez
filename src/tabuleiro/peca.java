package tabuleiro;

public abstract class peca {
	protected posicao pos;
	private tab tabuleiro;
	
	public peca(tab tabuleiro) {
		this.tabuleiro = tabuleiro;
		pos = null;
	} 
	
	protected tab getTab() {
		return tabuleiro;
	}
	
	public abstract boolean[][] movimentosPossiveis();
	
	public boolean podeMover(posicao pos){
		return movimentosPossiveis()[pos.getLinha()][pos.getColuna()];
	}
	
	public boolean existeAlgumMovimentoPossivel() {
		boolean[][] mat = movimentosPossiveis();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (mat[i][j] == true) {
					return true;
				}
			}
		}
		return false;
	}
}
 