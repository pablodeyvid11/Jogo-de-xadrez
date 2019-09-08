package xadresCamada;

import tabuleiro.tab;
import xadresCamada.pecas.Rei;
import xadresCamada.pecas.torre;

public class partidaXadrez {
	private tab tabuleiro;
	
	public partidaXadrez() {
		tabuleiro = new tab(8, 8);
		iniciarPartida();
	}
	
	public pecaXadrez[][] getPecasXadrez(){
		pecaXadrez[][] mat = new pecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] =  (pecaXadrez) tabuleiro.Peca(i, j);
			}
		}
		return mat; 
	}
	
	private void lugarNovaPeca(char coluna, int linha, pecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new xadrezPosicao(coluna, linha).toPosicao());
	}
	
	
	private void iniciarPartida() {
		lugarNovaPeca('c', 1, new torre(tabuleiro, cor.BRANCO));
        lugarNovaPeca('c', 2, new torre(tabuleiro, cor.BRANCO));
        lugarNovaPeca('d', 2, new torre(tabuleiro, cor.BRANCO));
        lugarNovaPeca('e', 2, new torre(tabuleiro, cor.BRANCO));
        lugarNovaPeca('e', 1, new torre(tabuleiro, cor.BRANCO));
        lugarNovaPeca('d', 1, new Rei(tabuleiro, cor.BRANCO));


        lugarNovaPeca('c', 7, new torre(tabuleiro, cor.PRETO));
        lugarNovaPeca('c', 8, new torre(tabuleiro, cor.PRETO));
        lugarNovaPeca('d', 7, new torre(tabuleiro, cor.PRETO));
        lugarNovaPeca('e', 7, new torre(tabuleiro, cor.PRETO));
        lugarNovaPeca('e', 8, new torre(tabuleiro, cor.PRETO));
        lugarNovaPeca('d', 8, new Rei(tabuleiro, cor.PRETO));
	}
}
