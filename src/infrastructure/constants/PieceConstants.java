package infrastructure.constants;

/**
 * Contains constants related to chess pieces
 */
public final class PieceConstants {
    
    // Piece symbols
    public static final String PAWN_SYMBOL = "P";
    public static final String ROOK_SYMBOL = "T";
    public static final String KNIGHT_SYMBOL = "C";
    public static final String BISHOP_SYMBOL = "B";
    public static final String QUEEN_SYMBOL = "Q";
    public static final String KING_SYMBOL = "R";
    
    // Promotion piece types
    public static final String BISHOP_TYPE = "B";
    public static final String KNIGHT_TYPE = "C";
    public static final String ROOK_TYPE = "T";
    public static final String QUEEN_TYPE = "Q";
    
    // Initial piece positions for setup
    public static final char[] BACK_ROW_COLUMNS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    public static final char[] PAWN_ROW_COLUMNS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    
    public static final int WHITE_BACK_ROW = 1;
    public static final int WHITE_PAWN_ROW = 2;
    public static final int BLACK_BACK_ROW = 8;
    public static final int BLACK_PAWN_ROW = 7;
    
    private PieceConstants() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
