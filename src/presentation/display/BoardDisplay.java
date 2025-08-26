package presentation.display;

import domain.chess.ChessPiece;
import domain.chess.Color;
import infrastructure.constants.GameConstants;
import infrastructure.constants.UIConstants;
import infrastructure.utils.ColorUtils;

/**
 * Handles the display of the chess board
 */
public final class BoardDisplay {
    
    /**
     * Prints the chess board without highlighting possible moves
     */
    public static void printBoard(ChessPiece[][] pieces) {
        printBoard(pieces, null);
    }
    
    /**
     * Prints the chess board with possible moves highlighted
     */
    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        printBoardRows(pieces, possibleMoves);
        printColumnLabels();
    }
    
    private static void printBoardRows(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i = 0; i < pieces.length; i++) {
            printRowNumber(i);
            printRowPieces(pieces[i], possibleMoves != null ? possibleMoves[i] : null);
            System.out.println();
        }
    }
    
    private static void printRowNumber(int row) {
        int displayRow = GameConstants.BOARD_SIZE - row;
        System.out.print(displayRow + UIConstants.SPACE);
    }
    
    private static void printRowPieces(ChessPiece[] row, boolean[] possibleMovesRow) {
        for (int j = 0; j < row.length; j++) {
            boolean isHighlighted = possibleMovesRow != null && possibleMovesRow[j];
            printPiece(row[j], isHighlighted);
        }
    }
    
    private static void printPiece(ChessPiece piece, boolean isHighlighted) {
        String output = formatPiece(piece);
        
        if (isHighlighted) {
            output = ColorUtils.colorizeBackground(output, UIConstants.ANSI_BLUE_BACKGROUND);
        }
        
        System.out.print(output + UIConstants.SPACE);
    }
    
    private static String formatPiece(ChessPiece piece) {
        if (piece == null) {
            return UIConstants.EMPTY_SQUARE;
        }
        
        String pieceSymbol = piece.toString();
        String color = piece.getColor() == Color.WHITE ? 
            UIConstants.ANSI_WHITE : UIConstants.ANSI_YELLOW;
            
        return ColorUtils.colorize(pieceSymbol, color);
    }
    
    private static void printColumnLabels() {
        System.out.println(ColorUtils.colorize(UIConstants.COLUMN_LABELS, UIConstants.ANSI_RED));
    }
    
    private BoardDisplay() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
