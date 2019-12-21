package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import tabuleiro.boardExcecao;
import xadresCamada.partidaXadrez;
import xadresCamada.pecaXadrez;
import xadresCamada.xadrezExcecao;
import xadresCamada.xadrezPosicao;

public class program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		partidaXadrez partida = new partidaXadrez();
		List<pecaXadrez> capturadas = new ArrayList<>();
		
		while (!partida.getCheckMate()) {
			try {
				UI.LimparTela();
				UI.escreverPartida(partida, capturadas);
				System.out.println();
				System.out.print("Digite a posição de origem: ");
				xadrezPosicao origem = UI.lerPosicaoXadrez(sc);

				boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);
				UI.LimparTela();
				UI.escreverTabuleiro(partida.getPecasXadrez(), movimentosPossiveis);
				
				System.out.println();
				System.out.print("Digite a posição de destino: ");
				xadrezPosicao destino = UI.lerPosicaoXadrez(sc);

				pecaXadrez pecaCapturada = partida.executarMovimento(origem, destino);
				if (pecaCapturada != null) {
					capturadas.add(pecaCapturada);
				}
				if (partida.getPromocao() != null) {
					System.out.print("Digite a letra cujo representa a peca que sera promovida: ");
					String escolha = sc.nextLine();
					partida.trocarPecaPromovida(escolha);
				}
			} catch (xadrezExcecao e) {
				System.out.println(e.getMessage());
				System.out.println("Aperte enter para continuar:");
				sc.nextLine();
			} catch (boardExcecao e) {
				System.out.println(e.getMessage());
				System.out.println("Aperte enter para continuar:");
				sc.nextLine();
			}catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				System.out.println("Aperte enter para continuar:");
				sc.nextLine();
			}
		}
		UI.LimparTela();
		UI.escreverPartida(partida, capturadas);
	}
}
