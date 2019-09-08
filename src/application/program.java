package application;

import java.util.Scanner;

import xadresCamada.partidaXadrez;
import xadresCamada.pecaXadrez;
import xadresCamada.xadrezPosicao;

public class program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		partidaXadrez partida = new partidaXadrez();
		
		while (true) {
			UI.escreverTabuleiro(partida.getPecasXadrez());
			System.out.println();
			System.out.print("Digite a posição de origem: ");
			xadrezPosicao origem = UI.lerPosicaoXadrez(sc);
			
			System.out.println();
			System.out.print("Digite a posição de destino: ");
			xadrezPosicao destino = UI.lerPosicaoXadrez(sc);
			
			pecaXadrez pecaCapturada = partida.executarMovimento(origem, destino);
		}
	}

}
