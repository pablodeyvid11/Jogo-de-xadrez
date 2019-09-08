package tabuleiro;

public class boardExcessao extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public boardExcessao(String msg) {
		super(msg);
	}
}
