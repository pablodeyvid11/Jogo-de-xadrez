package tabuleiro;

public class boardExcecao extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public boardExcecao(String msg) {
		super(msg);
	}
}
