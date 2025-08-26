package domain.services;

import java.util.ArrayList;
import java.util.List;

import domain.chess.ChessMatch;
import domain.chess.ChessPiece;
import domain.chess.ChessPosition;
import domain.board.Position;
import infrastructure.validation.GameValidator;
import infrastructure.validation.InputValidator;
import domain.chess.ChessException;

/**
 * Service class that handles game operations and business logic
 */
public class GameService {
    
    private final ChessMatch chessMatch;
    private final List<ChessPiece> capturedPieces;
    
    public GameService() {
        this.chessMatch = new ChessMatch();
        this.capturedPieces = new ArrayList<>();
    }
    
    /**
     * Gets the current chess match
     */
    public ChessMatch getChessMatch() {
        return chessMatch;
    }
    
    /**
     * Gets the list of captured pieces
     */
    public List<ChessPiece> getCapturedPieces() {
        return new ArrayList<>(capturedPieces);
    }
    
    /**
     * Validates and performs a chess move
     * @param sourceInput the source position input
     * @param targetInput the target position input
     * @return the captured piece, if any
     * @throws ChessException if the move is invalid
     */
    public ChessPiece performMove(String sourceInput, String targetInput) {
        ChessPosition source = parseChessPosition(sourceInput);
        ChessPosition target = parseChessPosition(targetInput);
        
        validateMove(source, target);
        
        ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
        if (capturedPiece != null) {
            capturedPieces.add(capturedPiece);
        }
        
        return capturedPiece;
    }
    
    /**
     * Gets possible moves for a piece at the given position
     * @param positionInput the position input string
     * @return matrix of possible moves
     * @throws ChessException if position is invalid
     */
    public boolean[][] getPossibleMoves(String positionInput) {
        ChessPosition position = parseChessPosition(positionInput);
        return chessMatch.possibleMoves(position);
    }
    
    /**
     * Handles pawn promotion
     * @param promotionType the type of piece to promote to
     * @return the new promoted piece
     * @throws ChessException if promotion is invalid
     */
    public ChessPiece handlePromotion(String promotionType) {
        if (!InputValidator.isValidPromotionType(promotionType)) {
            throw new ChessException("Invalid promotion type: " + promotionType);
        }
        
        return chessMatch.replacePromotedPiece(promotionType.toUpperCase());
    }
    
    /**
     * Checks if the game is over
     */
    public boolean isGameOver() {
        return chessMatch.getCheckMate();
    }
    
    /**
     * Gets the current game state information
     */
    public GameState getGameState() {
        return new GameState(
            chessMatch.getTurn(),
            chessMatch.getCurrentPlayer(),
            chessMatch.getCheck(),
            chessMatch.getCheckMate(),
            chessMatch.getPromoted() != null
        );
    }
    
    private ChessPosition parseChessPosition(String input) {
        String sanitizedInput = InputValidator.sanitizeInput(input);
        
        if (!InputValidator.isValidPositionInput(sanitizedInput)) {
            throw new ChessException("Error reading position, valid values are from a1 to h8");
        }
        
        char column = sanitizedInput.charAt(0);
        int row = Character.getNumericValue(sanitizedInput.charAt(1));
        
        return new ChessPosition(column, row);
    }
    
    private void validateMove(ChessPosition source, ChessPosition target) {
        Position sourcePos = source.toPosition();
        Position targetPos = target.toPosition();
        
        if (!GameValidator.hasPieceAt(chessMatch.getBoard(), sourcePos)) {
            throw new ChessException("There is no piece on source position.");
        }
        
        if (!GameValidator.isPieceOwnedByCurrentPlayer(chessMatch.getBoard(), sourcePos, chessMatch.getCurrentPlayer())) {
            throw new ChessException("The chosen piece is not yours");
        }
        
        if (!GameValidator.hasPossibleMoves(chessMatch.getBoard(), sourcePos)) {
            throw new ChessException("There is no possible moves for the chosen piece.");
        }
        
        if (!GameValidator.isPossibleMove(chessMatch.getBoard(), sourcePos, targetPos)) {
            throw new ChessException("The chosen piece can't move to target position");
        }
    }
}
