package tabuleiro;

public class tab {
	private int linhas;
	private int colunas;
	private peca[][] pecas;
	
	public tab(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new boardExcessao("Erro criando tabuleiro! É necessário que haja, ao menos, uma linha e uma coluna, consulte a documentação.");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas= new peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public peca Peca(int linha, int coluna) {
		if (!posicaoExistente(linha, coluna)) {
			throw new boardExcessao("Posição inexistente");
		}
		return pecas[linha][coluna];
	}
	
	public peca Peca(posicao pos) {
		if (!posicaoExistente(pos)) {
			throw new boardExcessao("Posição inexistente");
		}
		return pecas[pos.getLinha()][pos.getColuna()];
	}
	
	public void colocarPeca(peca pes, posicao pos) {
		if (existePecaAqui(pos)) {
			throw new boardExcessao("Já existe uma peça colocada aqui");
		}
		pecas[pos.getLinha()][pos.getColuna()]  = pes;
		pes.pos = pos;
	}
	
	public peca removerPeca(posicao pos) {
		if(!posicaoExistente(pos)) {
			throw new boardExcessao("Posição inexistente");
		}
		if(Peca(pos) == null) {
			return null;
		}
		peca aux = Peca(pos);
		aux.pos = null;
		pecas[pos.getLinha()][pos.getColuna()] = null;
		return aux;
	}
	
	private boolean posicaoExistente(int linha, int coluna) {
		return linha >= 0 && linha < colunas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posicaoExistente(posicao pos){
		return posicaoExistente(pos.getLinha(), pos.getColuna());
	}
	
	public boolean existePecaAqui(posicao pos) {
		if (!posicaoExistente(pos)) {
			throw new boardExcessao("Posição inexistente");
		}
		return Peca(pos) != null;
	}
}
