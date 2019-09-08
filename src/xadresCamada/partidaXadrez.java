package xadresCamada;

import java.util.ArrayList;
import java.util.List;

import tabuleiro.peca;
import tabuleiro.posicao;
import tabuleiro.tab;
import xadresCamada.pecas.Rei;
import xadresCamada.pecas.torre;

public class partidaXadrez {
	private int turno;
	private cor jogadorAtual;
	private tab tabuleiro;

	List<peca> pecasNoTabuleiro = new ArrayList<>();
	List<peca> pecasCapturadas = new ArrayList<>();
	
	public partidaXadrez() {
		tabuleiro = new tab(8, 8);
		turno = 1;
		jogadorAtual = cor.BRANCO;
		iniciarPartida();
	}

	public int getTurno() {
		return turno;
	}

	public cor getJogadorAtual() {
		return jogadorAtual;
	}

	public pecaXadrez[][] getPecasXadrez() {
		pecaXadrez[][] mat = new pecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];

		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (pecaXadrez) tabuleiro.Peca(i, j);
			}
		}
		return mat;
	}

	public boolean[][] movimentosPossiveis(xadrezPosicao XadrPos) {
		posicao pos = XadrPos.toPosicao();
		validarPosicaoOrigem(pos);
		return tabuleiro.Peca(pos).movimentosPossiveis();
	}

	public pecaXadrez executarMovimento(xadrezPosicao origemPosicao, xadrezPosicao finalPosicao) {
		posicao origem = origemPosicao.toPosicao();
		posicao fim = finalPosicao.toPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, fim);
		peca capturarPeca = fazerMovimento(origem, fim);
		proximoTurno();
		return (pecaXadrez) capturarPeca;
	}

	private peca fazerMovimento(posicao origem, posicao fim) {
		peca p = tabuleiro.removerPeca(origem);
		peca pecaCapturada = tabuleiro.removerPeca(fim);
		tabuleiro.colocarPeca(p, fim);
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		return pecaCapturada;
	}

	private void validarPosicaoOrigem(posicao pos) {
		if (!tabuleiro.existePecaAqui(pos)) {
			throw new xadrezExcessao("Não existe peça na posição de origem.");
		}
		if (jogadorAtual != ((pecaXadrez)(tabuleiro.Peca(pos))).getCOR()) {
			throw new xadrezExcessao("A peça escolhida não é sua");
		}
				
				
		if (!tabuleiro.Peca(pos).existeAlgumMovimentoPossivel()) {
			throw new xadrezExcessao("Não existe movimentos possiveis para a peça escolhida.");
		}
	}

	private void validarPosicaoDestino(posicao origem, posicao fim) {
		if (!tabuleiro.Peca(origem).podeMover(fim)) {
			throw new xadrezExcessao("A peça escolhida não pode se mover para a posição de destino");
		}
	}

	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == cor.BRANCO) ? cor.PRETO : cor.BRANCO;
	}

	private void lugarNovaPeca(char coluna, int linha, pecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new xadrezPosicao(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
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
