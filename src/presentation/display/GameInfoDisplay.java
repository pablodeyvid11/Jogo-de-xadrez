package presentation.display;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.chess.ChessPiece;
import domain.chess.Color;
import domain.services.GameState;
import infrastructure.constants.UIConstants;
import infrastructure.utils.ColorUtils;

/**
 * Handles the display of game information (turn, captured pieces, etc.)
 */
public final class GameInfoDisplay {
    
    /**
     * Prints captured pieces information
     */
    public static void printCapturedPieces(List<ChessPiece> capturedPieces) {
        List<ChessPiece> whitePieces = filterPiecesByColor(capturedPieces, Color.WHITE);
        List<ChessPiece> blackPieces = filterPiecesByColor(capturedPieces, Color.BLACK);
        
        System.out.println(UIConstants.CAPTURED_PIECES_LABEL);
        printColoredPieceList(UIConstants.WHITE_PIECES_LABEL, whitePieces, UIConstants.ANSI_WHITE);
        printColoredPieceList(UIConstants.BLACK_PIECES_LABEL, blackPieces, UIConstants.ANSI_YELLOW);
    }
    
    /**
     * Prints current game status information
     */
    public static void printGameStatus(GameState gameState) {
        printTurnInfo(gameState);
        
        if (gameState.isCheckMate()) {
            printCheckMateInfo(gameState);
        } else {
            printCurrentPlayerInfo(gameState);
            printCheckInfo(gameState);
        }
    }
    
    private static List<ChessPiece> filterPiecesByColor(List<ChessPiece> pieces, Color color) {
        return pieces.stream()
                .filter(piece -> piece.getColor() == color)
                .collect(Collectors.toList());
    }
    
    private static void printColoredPieceList(String label, List<ChessPiece> pieces, String color) {
        System.out.print(label);
        System.out.println(ColorUtils.colorize("", color));
        System.out.println(Arrays.toString(pieces.toArray()));
        System.out.println(UIConstants.ANSI_RESET);
    }
    
    private static void printTurnInfo(GameState gameState) {
        System.out.println(UIConstants.TURN_LABEL + gameState.getTurn());
    }
    
    private static void printCurrentPlayerInfo(GameState gameState) {
        System.out.println(UIConstants.WAITING_PLAYER_LABEL + gameState.getCurrentPlayer());
    }
    
    private static void printCheckInfo(GameState gameState) {
        if (gameState.isInCheck()) {
            String checkColor = gameState.getCurrentPlayer() == Color.WHITE ? 
                UIConstants.ANSI_WHITE : UIConstants.ANSI_YELLOW;
            System.out.print(ColorUtils.colorize(UIConstants.CHECK_MESSAGE, checkColor));
        }
    }
    
    private static void printCheckMateInfo(GameState gameState) {
        System.out.println(UIConstants.CHECKMATE_MESSAGE);
        Color winner = gameState.getCurrentPlayer() == Color.WHITE ? Color.BLACK : Color.WHITE;
        System.out.println(UIConstants.WINNER_LABEL + winner);
    }
    
    private GameInfoDisplay() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
