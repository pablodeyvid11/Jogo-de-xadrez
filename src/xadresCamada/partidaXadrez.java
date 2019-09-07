package xadresCamada;

import tabuleiro.posicao;
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
	
	private void iniciarPartida() {
		tabuleiro.colocarPeca(new torre(tabuleiro, cor.BRANCO), new posicao(2, 1));
		tabuleiro.colocarPeca(new Rei(tabuleiro, cor.PRETO), new posicao(0, 4));
		tabuleiro.colocarPeca(new Rei(tabuleiro, cor.BRANCO), new posicao(7, 4));
	}
}
