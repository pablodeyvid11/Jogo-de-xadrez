package presentation.console;

import infrastructure.constants.UIConstants;

/**
 * Manages console operations like clearing screen
 */
public final class ConsoleManager {
    
    /**
     * Clears the console screen
     */
    public static void clearScreen() {
        System.out.print(UIConstants.CLEAR_SCREEN);
        System.out.flush();
    }
    
    /**
     * Prints a blank line
     */
    public static void printBlankLine() {
        System.out.println();
    }
    
    /**
     * Prints multiple blank lines
     * @param count number of blank lines to print
     */
    public static void printBlankLines(int count) {
        for (int i = 0; i < count; i++) {
            printBlankLine();
        }
    }
    
    private ConsoleManager() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
