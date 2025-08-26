package infrastructure.utils;

import domain.board.Position;
import domain.chess.ChessPosition;
import infrastructure.constants.GameConstants;

/**
 * Utility class for position-related operations
 */
public final class PositionUtils {
    
    /**
     * Checks if a chess position is within valid bounds
     */
    public static boolean isValidChessPosition(char column, int row) {
        return column >= GameConstants.MIN_COLUMN && 
               column <= GameConstants.MAX_COLUMN &&
               row >= GameConstants.MIN_ROW && 
               row <= GameConstants.MAX_ROW;
    }
    
    /**
     * Checks if a board position is within valid bounds
     */
    public static boolean isValidBoardPosition(int row, int column) {
        return row >= 0 && row < GameConstants.BOARD_SIZE &&
               column >= 0 && column < GameConstants.BOARD_SIZE;
    }
    
    /**
     * Creates a new position with offset from current position
     */
    public static Position createOffsetPosition(Position current, int rowOffset, int columnOffset) {
        return new Position(current.getRow() + rowOffset, current.getColumn() + columnOffset);
    }
    
    /**
     * Calculates distance between two positions
     */
    public static int calculateDistance(Position pos1, Position pos2) {
        int rowDiff = Math.abs(pos1.getRow() - pos2.getRow());
        int colDiff = Math.abs(pos1.getColumn() - pos2.getColumn());
        return Math.max(rowDiff, colDiff);
    }
    
    /**
     * Checks if two positions are on the same diagonal
     */
    public static boolean isOnSameDiagonal(Position pos1, Position pos2) {
        int rowDiff = Math.abs(pos1.getRow() - pos2.getRow());
        int colDiff = Math.abs(pos1.getColumn() - pos2.getColumn());
        return rowDiff == colDiff && rowDiff > 0;
    }
    
    /**
     * Checks if two positions are on the same row or column
     */
    public static boolean isOnSameRowOrColumn(Position pos1, Position pos2) {
        return pos1.getRow() == pos2.getRow() || pos1.getColumn() == pos2.getColumn();
    }
    
    private PositionUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
