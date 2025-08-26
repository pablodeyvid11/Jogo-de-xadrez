package infrastructure.utils;

import infrastructure.constants.UIConstants;

/**
 * Utility class for color-related operations in the UI
 */
public final class ColorUtils {
    
    /**
     * Applies color to text and resets after
     */
    public static String colorize(String text, String color) {
        return color + text + UIConstants.ANSI_RESET;
    }
    
    /**
     * Applies background color to text and resets after
     */
    public static String colorizeBackground(String text, String backgroundColor) {
        return backgroundColor + text + UIConstants.ANSI_RESET;
    }
    
    /**
     * Creates a colored border line
     */
    public static String createColoredBorder(String color) {
        return colorize(UIConstants.TUTORIAL_BORDER, color);
    }
    
    /**
     * Creates a colored title with padding
     */
    public static String createColoredTitle(String title, String color) {
        return colorize(title, color);
    }
    
    /**
     * Creates a section header with color
     */
    public static String createSectionHeader(String header, String color) {
        return colorize(header + ":", color);
    }
    
    private ColorUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
