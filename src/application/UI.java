package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadresCamada.cor;
import xadresCamada.partidaXadrez;
import xadresCamada.pecaXadrez;
import xadresCamada.xadrezPosicao;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void LimparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static xadrezPosicao lerPosicaoXadrez(Scanner sc) {
		try {
			String s = sc.nextLine().trim();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new xadrezPosicao(coluna, linha);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro ao iniciar a posi��o, valores validos s�o: de a1 at� h8");
		}

	}

	public static void escreverPartida(partidaXadrez parXadr, List<pecaXadrez> capturadas) {
		escreverTabuleiro(parXadr.getPecasXadrez());
		System.out.println();
		escreverPecasCapturadas(capturadas);
		System.out.println();
		System.out.println("Turno: " + parXadr.getTurno());
		if (!parXadr.getCheckMate()) {
			System.out.println("Esperando o jogador: " + parXadr.getJogadorAtual());
			if (parXadr.getCheck()) {
				if (parXadr.getCorDoCheck() == cor.BRANCO) {
					System.out.print(ANSI_WHITE + "CHECK!" + ANSI_RESET);
				} else {
					System.out.print(ANSI_YELLOW + "CHECK!" + ANSI_RESET);
				}
			}
		} else {
			System.out.println("CHECKMATE!");
			System.out.println("Vencedor: " + parXadr.oponente(parXadr.getJogadorAtual()));
		}

	}

	public static void escreverTabuleiro(pecaXadrez[][] pecaxadr) {
		for (int i = 0; i < pecaxadr.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecaxadr[0].length; j++) {
				escreverPeca(pecaxadr[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	public static void escreverTabuleiro(pecaXadrez[][] pecaxadr, boolean[][] movimentosPossiveis) {
		for (int i = 0; i < pecaxadr.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecaxadr[0].length; j++) {
				escreverPeca(pecaxadr[i][j], movimentosPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void escreverPeca(pecaXadrez peca, boolean telaFundo) {
		if (telaFundo) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (peca.getCOR() == cor.BRANCO) {
				System.out.print(ANSI_WHITE + peca + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
			}
		}

		System.out.print(" ");
	}

	private static void escreverPecasCapturadas(List<pecaXadrez> capturadas) {
		List<pecaXadrez> branca = capturadas.stream().filter(x -> x.getCOR() == cor.BRANCO)
				.collect(Collectors.toList());
		List<pecaXadrez> preta = capturadas.stream().filter(x -> x.getCOR() == cor.PRETO).collect(Collectors.toList());
		System.out.println("Pe�as capturadas: ");
		System.out.print("Brancas: ");
		System.out.println(ANSI_WHITE);
		System.out.println(Arrays.toString(branca.toArray()));
		System.out.println(ANSI_RESET);
		System.out.print("Pretas: ");
		System.out.println(ANSI_YELLOW);
		System.out.println(Arrays.toString(preta.toArray()));
		System.out.println(ANSI_RESET);
	}

}
