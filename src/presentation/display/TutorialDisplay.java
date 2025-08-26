package presentation.display;

import infrastructure.constants.UIConstants;
import infrastructure.utils.ColorUtils;

/**
 * Handles the display of the game tutorial
 */
public final class TutorialDisplay {
    
    /**
     * Displays the complete game tutorial
     */
    public static void displayTutorial() {
        displayHeader();
        displayHowToPlay();
        displayExamples();
        displayPieceSymbols();
        displaySpecialMoves();
        displayColors();
        displayRules();
        displayFooter();
    }
    
    private static void displayHeader() {
        System.out.println(ColorUtils.createColoredBorder(UIConstants.ANSI_CYAN));
        System.out.println(ColorUtils.createColoredTitle(UIConstants.TUTORIAL_TITLE, UIConstants.ANSI_CYAN));
        System.out.println(ColorUtils.createColoredBorder(UIConstants.ANSI_CYAN));
        System.out.println();
    }
    
    private static void displayHowToPlay() {
        System.out.println(ColorUtils.createSectionHeader("HOW TO PLAY", UIConstants.ANSI_GREEN));
        System.out.println("   - Enter positions using chess notation (a1 to h8)");
        System.out.println("   - First enter the SOURCE position (where the piece is)");
        System.out.println("   - Then enter the TARGET position (where you want to move)");
        System.out.println();
    }
    
    private static void displayExamples() {
        System.out.println(ColorUtils.createSectionHeader("EXAMPLES OF MOVES", UIConstants.ANSI_YELLOW));
        System.out.println("   - e2 -> e4  (Move pawn 2 squares forward)");
        System.out.println("   - g1 -> f3  (Move knight)");
        System.out.println("   - b1 -> c3  (Move knight)");
        System.out.println("   - f1 -> c4  (Move bishop)");
        System.out.println();
    }
    
    private static void displayPieceSymbols() {
        System.out.println(ColorUtils.createSectionHeader("PIECES SYMBOLS", UIConstants.ANSI_PURPLE));
        System.out.println("   - P = Pawn    - T = Rook    - C = Knight");
        System.out.println("   - B = Bishop  - Q = Queen   - R = King");
        System.out.println();
    }
    
    private static void displaySpecialMoves() {
        System.out.println(ColorUtils.createSectionHeader("SPECIAL MOVES", UIConstants.ANSI_BLUE));
        System.out.println("   - Castling: Move king 2 squares toward rook");
        System.out.println("   - En Passant: Automatic when conditions are met");
        System.out.println("   - Pawn Promotion: Choose B/C/T/Q when pawn reaches end");
        System.out.println();
    }
    
    private static void displayColors() {
        System.out.println(ColorUtils.createSectionHeader("COLORS", UIConstants.ANSI_RED));
        System.out.print("   - White pieces = ");
        System.out.print(ColorUtils.colorize("WHITE color", UIConstants.ANSI_WHITE));
        System.out.println();
        System.out.print("   - Black pieces = ");
        System.out.print(ColorUtils.colorize("YELLOW color", UIConstants.ANSI_YELLOW));
        System.out.println();
        System.out.print("   - Blue background = ");
        System.out.print(ColorUtils.colorizeBackground("Possible moves", UIConstants.ANSI_BLUE_BACKGROUND));
        System.out.println();
        System.out.println();
    }
    
    private static void displayRules() {
        System.out.println(ColorUtils.createSectionHeader("RULES", UIConstants.ANSI_RED));
        System.out.println("   - White always moves first");
        System.out.println("   - You cannot put your own king in check");
        System.out.println("   - Game ends when checkmate is achieved");
        System.out.println();
    }
    
    private static void displayFooter() {
        System.out.println(ColorUtils.createColoredBorder(UIConstants.ANSI_CYAN));
        System.out.println(ColorUtils.createColoredTitle(UIConstants.TUTORIAL_START_PROMPT, UIConstants.ANSI_CYAN));
        System.out.println(ColorUtils.createColoredBorder(UIConstants.ANSI_CYAN));
    }
    
    private TutorialDisplay() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
