package xadresCamada;

import tabuleiro.tab;

public class partidaXadrez {
	private tab tabuleiro;
	
	public partidaXadrez() {
		tabuleiro = new tab(8, 8);
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
}
