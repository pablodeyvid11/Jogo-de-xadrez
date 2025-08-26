package infrastructure.validation;

import domain.chess.ChessPosition;
import infrastructure.constants.PieceConstants;
import infrastructure.utils.PositionUtils;

/**
 * Validates user input for chess game operations
 */
public final class InputValidator {
    
    /**
     * Validates chess position input string
     * @param input the input string to validate
     * @return true if valid chess position format
     */
    public static boolean isValidPositionInput(String input) {
        if (input == null || input.length() != 2) {
            return false;
        }
        
        char column = input.charAt(0);
        char rowChar = input.charAt(1);
        
        if (!Character.isDigit(rowChar)) {
            return false;
        }
        
        int row = Character.getNumericValue(rowChar);
        return PositionUtils.isValidChessPosition(column, row);
    }
    
    /**
     * Validates promotion piece type
     * @param type the piece type to validate
     * @return true if valid promotion type
     */
    public static boolean isValidPromotionType(String type) {
        if (type == null || type.length() != 1) {
            return false;
        }
        
        String upperType = type.toUpperCase();
        return PieceConstants.BISHOP_TYPE.equals(upperType) ||
               PieceConstants.KNIGHT_TYPE.equals(upperType) ||
               PieceConstants.ROOK_TYPE.equals(upperType) ||
               PieceConstants.QUEEN_TYPE.equals(upperType);
    }
    
    /**
     * Sanitizes input string by trimming and converting to lowercase
     * @param input the input to sanitize
     * @return sanitized input
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }
        return input.trim().toLowerCase();
    }
    
    /**
     * Validates that input is not null or empty
     * @param input the input to validate
     * @return true if input is valid
     */
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
    
    private InputValidator() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
