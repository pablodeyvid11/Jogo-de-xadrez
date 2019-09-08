package xadresCamada;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.peca;
import tabuleiro.posicao;
import tabuleiro.tab;
import xadresCamada.pecas.Rei;
import xadresCamada.pecas.torre;

public class partidaXadrez {
	private int turno;
	private cor jogadorAtual;
	private tab tabuleiro;
	private boolean check;
	private cor corDoCheck;
	private boolean checkMate;

	List<peca> pecasNoTabuleiro = new ArrayList<>();
	List<peca> pecasCapturadas = new ArrayList<>();

	public partidaXadrez() {
		tabuleiro = new tab(8, 8);
		turno = 1;
		jogadorAtual = cor.BRANCO;
		check = false;
		iniciarPartida();
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}

	public int getTurno() {
		return turno;
	}
	
	public boolean getCheck() {
		return check;
	}

	public cor getCorDoCheck() {
		return corDoCheck;
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
		
		if (testarCheck(jogadorAtual)) {
			desfazerMovimento(origem, fim, capturarPeca);
			throw new xadrezExcessao("Você não pode se colocar em Check");
		}
		
		check = (testarCheck(oponente(jogadorAtual))? true : false);
		corDoCheck = oponente(jogadorAtual);
		if (testarCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}
		
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

	private void desfazerMovimento(posicao origem, posicao destino, peca pecaCapturada) {
		peca p = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, origem);
		if (pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}

	private void validarPosicaoOrigem(posicao pos) {
		if (!tabuleiro.existePecaAqui(pos)) {
			throw new xadrezExcessao("Não existe peça na posição de origem.");
		}
		if (jogadorAtual != ((pecaXadrez) (tabuleiro.Peca(pos))).getCOR()) {
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

	public cor oponente(cor COR) {
		return (COR == cor.BRANCO) ? cor.PRETO : cor.BRANCO;
	}

	private pecaXadrez rei(cor COR) {
		List<peca> list = pecasNoTabuleiro.stream().filter(x -> ((pecaXadrez) x).getCOR() == COR)
				.collect(Collectors.toList());
		for (peca p : list) {
			if (p instanceof Rei) {
				return (pecaXadrez) p;
			}
		}
		throw new IllegalStateException("Não existe um rei da cor: " + COR);
	}

	private boolean testarCheck(cor c) {
		posicao posicaoRei = rei(c).getXadrezPosicao().toPosicao();
		List<peca> pecasOponentes = pecasNoTabuleiro.stream().filter(x -> ((pecaXadrez) x).getCOR() == oponente(c))
				.collect(Collectors.toList());
		for (peca p : pecasOponentes) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testarCheckMate(cor c) {
		if (!testarCheck(c)) {
			return false;
		}
		List<peca> list = pecasNoTabuleiro.stream().filter(x -> ((pecaXadrez) x).getCOR() == c).collect(Collectors.toList());
		
		for (peca p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						posicao origem = ((pecaXadrez)p).getXadrezPosicao().toPosicao();
						posicao destino = new posicao(i, j);
						peca pecaCapturada = fazerMovimento(origem, destino);
						boolean testCheck = testarCheck(c);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void lugarNovaPeca(char coluna, int linha, pecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new xadrezPosicao(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}

	private void iniciarPartida() {
		lugarNovaPeca('h', 7, new torre(tabuleiro, cor.BRANCO));
		lugarNovaPeca('d', 1, new torre(tabuleiro, cor.BRANCO));
		lugarNovaPeca('e', 1, new Rei(tabuleiro, cor.BRANCO));

		lugarNovaPeca('b', 8, new torre(tabuleiro, cor.PRETO));
		lugarNovaPeca('a', 8, new Rei(tabuleiro, cor.PRETO));
	}
}
