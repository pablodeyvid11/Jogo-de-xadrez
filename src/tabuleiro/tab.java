package tabuleiro;

public class tab {
	private int linhas;
	private int colunas;
	private peca[][] pecas;
	
	public tab(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pecas= new peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
	
	
}
