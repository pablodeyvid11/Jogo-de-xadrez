package xadresCamada;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.peca;
import tabuleiro.posicao;
import tabuleiro.tab;
import xadresCamada.pecas.Cavalo;
import xadresCamada.pecas.Rainha;
import xadresCamada.pecas.Rei;
import xadresCamada.pecas.bispo;
import xadresCamada.pecas.peao;
import xadresCamada.pecas.torre;

public class partidaXadrez {
	private int turno;
	private cor jogadorAtual;
	private tab tabuleiro;
	private boolean check;
	private cor corDoCheck;
	private boolean checkMate;
	private pecaXadrez pecaVulneravel;
	private pecaXadrez promocao;

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

	public pecaXadrez getPromocao() {
		return promocao;
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

	public pecaXadrez getPecaVulneravel() {
		return pecaVulneravel;
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

		pecaXadrez pecaMovimentada = (pecaXadrez) tabuleiro.Peca(fim);
		
		//promocao
		promocao = null;
		if (pecaMovimentada instanceof peao) {
			if (pecaMovimentada.getCOR() == cor.BRANCO && fim.getLinha() == 0 || pecaMovimentada.getCOR() == cor.PRETO && fim.getLinha() == 7) {
				promocao = (pecaXadrez)tabuleiro.Peca(fim);
				promocao = trocarPecaPromovida("Q");
			}
		}
		
		check = (testarCheck(oponente(jogadorAtual)) ? true : false);
		
		corDoCheck = oponente(jogadorAtual);
		if (testarCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}

		proximoTurno();

		// en passant

		if (pecaMovimentada instanceof peao
				&& (fim.getLinha() == origem.getLinha() - 2 || fim.getLinha() == origem.getLinha() + 2)) {
			pecaVulneravel = pecaMovimentada;
		} else {
			pecaVulneravel = null;
		}

		return (pecaXadrez) capturarPeca;
	}
	public pecaXadrez trocarPecaPromovida(String escolha) {
		if(promocao == null) {
			throw new IllegalStateException("Não há peça para ser promovida");
		}
		if (!escolha.equals("B") && !escolha.equals("C") && !escolha.equals("T") && !escolha.equals("Q")  ) {
			throw new InvalidParameterException("Tipo para promocao invalido");
		}
		posicao pos = promocao.getXadrezPosicao().toPosicao();
		peca p = tabuleiro.removerPeca(pos);
		pecasNoTabuleiro.remove(p);
		
		pecaXadrez novaeca = novaPeca(escolha, promocao.getCOR());
		tabuleiro.colocarPeca(novaeca, pos);
		pecasNoTabuleiro.add(novaeca);
		return novaeca;
	}
	
	private pecaXadrez novaPeca(String escolha, cor c) {
		if (escolha.equals("B")) return new bispo(tabuleiro, c);
		if (escolha.equals("C")) return new Cavalo(tabuleiro, c);
		if (escolha.equals("Q")) return new Rainha(tabuleiro, c);
		return new torre(tabuleiro, c);
	}
	
	private peca fazerMovimento(posicao origem, posicao fim) {
		pecaXadrez p = (pecaXadrez) tabuleiro.removerPeca(origem);
		p.incrementarContagem();
		peca pecaCapturada = tabuleiro.removerPeca(fim);
		tabuleiro.colocarPeca(p, fim);
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}

		// roque pequeno
		if (p instanceof Rei && fim.getColuna() == origem.getColuna() + 2) {
			posicao origemT = new posicao(origem.getLinha(), origem.getColuna() + 3);
			posicao fimT = new posicao(origem.getLinha(), origem.getColuna() + 1);
			pecaXadrez torre = (pecaXadrez) tabuleiro.removerPeca(origemT);
			tabuleiro.colocarPeca(torre, fimT);
			torre.incrementarContagem();
		}

		// roque grande
		if (p instanceof Rei && fim.getColuna() == origem.getColuna() - 2) {
			posicao origemT = new posicao(origem.getLinha(), origem.getColuna() - 4);
			posicao fimT = new posicao(origem.getLinha(), origem.getColuna() - 1);
			pecaXadrez torre = (pecaXadrez) tabuleiro.removerPeca(origemT);
			tabuleiro.colocarPeca(torre, fimT);
			torre.incrementarContagem();
		}

		// en passant
		if (p instanceof peao) {
			if (origem.getColuna() != fim.getColuna() && pecaCapturada == null) {
				posicao posicaoPeao;
				if (p.getCOR() == cor.BRANCO) {
					posicaoPeao = new posicao(fim.getLinha() + 1, fim.getColuna());
				} else {
					posicaoPeao = new posicao(fim.getLinha() - 1, fim.getColuna());
				}
				pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);
			}
		}

		return pecaCapturada;
	}

	private void desfazerMovimento(posicao origem, posicao destino, peca pecaCapturada) {
		pecaXadrez p = (pecaXadrez) tabuleiro.removerPeca(destino);
		p.decrementarContagem();
		tabuleiro.colocarPeca(p, origem);
		if (pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}

		// roque pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			posicao origemT = new posicao(origem.getLinha(), origem.getColuna() + 3);
			posicao fimT = new posicao(origem.getLinha(), origem.getColuna() + 1);
			pecaXadrez torre = (pecaXadrez) tabuleiro.removerPeca(fimT);
			tabuleiro.colocarPeca(torre, origemT);
			torre.decrementarContagem();
		}

		// roque grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			posicao origemT = new posicao(origem.getLinha(), origem.getColuna() - 4);
			posicao fimT = new posicao(origem.getLinha(), origem.getColuna() - 1);
			pecaXadrez torre = (pecaXadrez) tabuleiro.removerPeca(fimT);
			tabuleiro.colocarPeca(torre, origemT);
			torre.decrementarContagem();
		}

		// en passant
		if (p instanceof peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == pecaVulneravel) {
				pecaXadrez peaoPeca = (pecaXadrez)tabuleiro.removerPeca(destino);
				posicao posicaoPeao;
				if (p.getCOR() == cor.BRANCO) {
					posicaoPeao = new posicao(3, destino.getColuna());
				} else {
					posicaoPeao = new posicao(4, destino.getColuna());
				}
				tabuleiro.colocarPeca(peaoPeca, posicaoPeao);
			}
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
		List<peca> list = pecasNoTabuleiro.stream().filter(x -> ((pecaXadrez) x).getCOR() == c)
				.collect(Collectors.toList());

		for (peca p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						posicao origem = ((pecaXadrez) p).getXadrezPosicao().toPosicao();
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
		lugarNovaPeca('a', 1, new torre(tabuleiro, cor.BRANCO));
		lugarNovaPeca('b', 1, new Cavalo(tabuleiro, cor.BRANCO));
		lugarNovaPeca('d', 1, new Rainha(tabuleiro, cor.BRANCO));
		lugarNovaPeca('c', 1, new bispo(tabuleiro, cor.BRANCO));
		lugarNovaPeca('f', 1, new bispo(tabuleiro, cor.BRANCO));
		lugarNovaPeca('e', 1, new Rei(tabuleiro, cor.BRANCO, this));
		lugarNovaPeca('h', 1, new torre(tabuleiro, cor.BRANCO));
		lugarNovaPeca('a', 2, new peao(tabuleiro, cor.BRANCO, this));
		lugarNovaPeca('b', 2, new peao(tabuleiro, cor.BRANCO, this));
		lugarNovaPeca('c', 2, new peao(tabuleiro, cor.BRANCO, this));
		lugarNovaPeca('d', 2, new peao(tabuleiro, cor.BRANCO, this));
		lugarNovaPeca('e', 2, new peao(tabuleiro, cor.BRANCO, this));
		lugarNovaPeca('f', 2, new peao(tabuleiro, cor.BRANCO, this));
		lugarNovaPeca('g', 1, new Cavalo(tabuleiro, cor.BRANCO));
		lugarNovaPeca('g', 2, new peao(tabuleiro, cor.BRANCO, this));
		lugarNovaPeca('h', 2, new peao(tabuleiro, cor.BRANCO, this));

		lugarNovaPeca('a', 8, new torre(tabuleiro, cor.PRETO));
		lugarNovaPeca('b', 8, new Cavalo(tabuleiro, cor.PRETO));
		lugarNovaPeca('d', 8, new Rainha(tabuleiro, cor.PRETO));
		lugarNovaPeca('g', 8, new Cavalo(tabuleiro, cor.PRETO));
		lugarNovaPeca('e', 8, new Rei(tabuleiro, cor.PRETO, this));
		lugarNovaPeca('c', 8, new bispo(tabuleiro, cor.PRETO));
		lugarNovaPeca('f', 8, new bispo(tabuleiro, cor.PRETO));
		lugarNovaPeca('h', 8, new torre(tabuleiro, cor.PRETO));
		lugarNovaPeca('a', 7, new peao(tabuleiro, cor.PRETO, this));
		lugarNovaPeca('b', 7, new peao(tabuleiro, cor.PRETO, this));
		lugarNovaPeca('c', 7, new peao(tabuleiro, cor.PRETO, this));
		lugarNovaPeca('d', 7, new peao(tabuleiro, cor.PRETO, this));
		lugarNovaPeca('e', 7, new peao(tabuleiro, cor.PRETO, this));
		lugarNovaPeca('f', 7, new peao(tabuleiro, cor.PRETO, this));
		lugarNovaPeca('g', 7, new peao(tabuleiro, cor.PRETO, this));
		lugarNovaPeca('h', 7, new peao(tabuleiro, cor.PRETO, this));
	}
}
