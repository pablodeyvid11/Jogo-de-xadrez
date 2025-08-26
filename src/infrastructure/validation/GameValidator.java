package infrastructure.validation;

import domain.board.Board;
import domain.board.Position;
import domain.chess.ChessPiece;
import domain.chess.Color;

/**
 * Validates game state and move legality
 */
public final class GameValidator {
    
    /**
     * Validates that there is a piece at the given position
     * @param board the game board
     * @param position the position to check
     * @return true if there is a piece at the position
     */
    public static boolean hasPieceAt(Board board, Position position) {
        return board.thereIsAPiece(position);
    }
    
    /**
     * Validates that the piece at the position belongs to the current player
     * @param board the game board
     * @param position the position to check
     * @param currentPlayer the current player's color
     * @return true if the piece belongs to the current player
     */
    public static boolean isPieceOwnedByCurrentPlayer(Board board, Position position, Color currentPlayer) {
        if (!hasPieceAt(board, position)) {
            return false;
        }
        
        ChessPiece piece = (ChessPiece) board.piece(position);
        return piece.getColor() == currentPlayer;
    }
    
    /**
     * Validates that the piece at the position has possible moves
     * @param board the game board
     * @param position the position to check
     * @return true if the piece has possible moves
     */
    public static boolean hasPossibleMoves(Board board, Position position) {
        if (!hasPieceAt(board, position)) {
            return false;
        }
        
        return board.piece(position).isThereAnyPossibleMove();
    }
    
    /**
     * Validates that a move is possible for the piece at the source position
     * @param board the game board
     * @param source the source position
     * @param target the target position
     * @return true if the move is possible
     */
    public static boolean isPossibleMove(Board board, Position source, Position target) {
        if (!hasPieceAt(board, source)) {
            return false;
        }
        
        return board.piece(source).possibleMove(target);
    }
    
    private GameValidator() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
