package xadresCamada;

import tabuleiro.posicao;

public class xadrezPosicao {
	private char coluna;
	private int linha;
	
	public xadrezPosicao(char coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha <1 || linha > 8) {
			throw new xadrezExcessao("Erro ao iniciar a posição, valores validos são: de a1 até h8");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	protected posicao toPosicao() {
		return new posicao(8 - linha, coluna - 'a');
	}
	
	protected static xadrezPosicao fromPosicao(posicao pos) {
		return new xadrezPosicao((char)('a' + pos.getColuna()), 8 - pos.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
}
