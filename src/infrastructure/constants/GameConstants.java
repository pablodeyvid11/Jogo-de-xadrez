package infrastructure.constants;

/**
 * Contains all game-related constants to avoid magic numbers and hardcoded values
 */
public final class GameConstants {
    
    // Board dimensions
    public static final int BOARD_SIZE = 8;
    public static final int MIN_BOARD_SIZE = 1;
    
    // Chess notation bounds
    public static final char MIN_COLUMN = 'a';
    public static final char MAX_COLUMN = 'h';
    public static final int MIN_ROW = 1;
    public static final int MAX_ROW = 8;
    
    // Initial game state
    public static final int INITIAL_TURN = 1;
    public static final int INITIAL_MOVE_COUNT = 0;
    
    // Pawn movement
    public static final int PAWN_DOUBLE_MOVE_DISTANCE = 2;
    public static final int WHITE_PAWN_PROMOTION_ROW = 0;
    public static final int BLACK_PAWN_PROMOTION_ROW = 7;
    public static final int WHITE_EN_PASSANT_ROW = 3;
    public static final int BLACK_EN_PASSANT_ROW = 4;
    
    // Castling
    public static final int KINGSIDE_ROOK_COLUMN_OFFSET = 3;
    public static final int QUEENSIDE_ROOK_COLUMN_OFFSET = -4;
    public static final int CASTLING_KING_MOVE_DISTANCE = 2;
    
    // Piece movement offsets
    public static final int[][] KING_MOVES = {
        {-1, -1}, {-1, 0}, {-1, 1},
        {0, -1},           {0, 1},
        {1, -1},  {1, 0},  {1, 1}
    };
    
    public static final int[][] KNIGHT_MOVES = {
        {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
        {1, -2},  {1, 2},  {2, -1},  {2, 1}
    };
    
    public static final int[][] ROOK_DIRECTIONS = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    
    public static final int[][] BISHOP_DIRECTIONS = {
        {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };
    
    private GameConstants() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
