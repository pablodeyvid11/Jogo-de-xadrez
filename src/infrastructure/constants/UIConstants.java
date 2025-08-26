package infrastructure.constants;

/**
 * Contains all UI-related constants for consistent styling and messages
 */
public final class UIConstants {
    
    // ANSI Color codes
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    // ANSI Background colors
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    
    // Console control
    public static final String CLEAR_SCREEN = "\033[H\033[2J";
    
    // UI Messages
    public static final String TURN_LABEL = "Turn: ";
    public static final String WAITING_PLAYER_LABEL = "Waiting player: ";
    public static final String CHECK_MESSAGE = "CHECK!";
    public static final String CHECKMATE_MESSAGE = "CHECKMATE!";
    public static final String WINNER_LABEL = "Winner: ";
    public static final String CAPTURED_PIECES_LABEL = "Captured pieces: ";
    public static final String WHITE_PIECES_LABEL = "White: ";
    public static final String BLACK_PIECES_LABEL = "Black: ";
    
    // Input prompts
    public static final String SOURCE_PROMPT = "Source: ";
    public static final String TARGET_PROMPT = "Target: ";
    public static final String PROMOTION_PROMPT = "Enter piece for promotion (B/C/T/Q): ";
    public static final String CONTINUE_PROMPT = "Press enter to continue:";
    
    // Board display
    public static final String EMPTY_SQUARE = "-";
    public static final String COLUMN_LABELS = "  a b c d e f g h";
    public static final String SPACE = " ";
    
    // Tutorial
    public static final String TUTORIAL_BORDER = "================================================================================";
    public static final String TUTORIAL_TITLE = "                            CHESS GAME TUTORIAL                               ";
    public static final String TUTORIAL_START_PROMPT = "                        Press ENTER to start the game!                       ";
    
    private UIConstants() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
