package presentation.input;

import java.util.InputMismatchException;
import java.util.Scanner;

import domain.chess.ChessPosition;
import infrastructure.constants.UIConstants;
import infrastructure.validation.InputValidator;

/**
 * Handles user input operations
 */
public class InputController {
    
    private final Scanner scanner;
    
    public InputController(Scanner scanner) {
        this.scanner = scanner;
    }
    
    /**
     * Reads a chess position from user input
     * @return ChessPosition object
     * @throws InputMismatchException if input is invalid
     */
    public ChessPosition readChessPosition() {
        String input = scanner.nextLine();
        String sanitizedInput = InputValidator.sanitizeInput(input);
        
        if (!InputValidator.isValidPositionInput(sanitizedInput)) {
            throw new InputMismatchException("Error reading position, valid values are from a1 to h8");
        }
        
        char column = sanitizedInput.charAt(0);
        int row = Character.getNumericValue(sanitizedInput.charAt(1));
        
        return new ChessPosition(column, row);
    }
    
    /**
     * Reads promotion piece type from user input
     * @return promotion type string
     * @throws InputMismatchException if input is invalid
     */
    public String readPromotionType() {
        String input = scanner.nextLine();
        String sanitizedInput = InputValidator.sanitizeInput(input);
        
        if (!InputValidator.isValidPromotionType(sanitizedInput)) {
            throw new InputMismatchException("Invalid promotion type. Valid types are: B, C, T, Q");
        }
        
        return sanitizedInput.toUpperCase();
    }
    
    /**
     * Waits for user to press enter
     */
    public void waitForEnter() {
        scanner.nextLine();
    }
    
    /**
     * Prompts user for input and returns the response
     * @param prompt the prompt message
     * @return user input
     */
    public String promptForInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    /**
     * Prompts user with a message and waits for enter
     * @param message the message to display
     */
    public void promptAndWait(String message) {
        System.out.println(message);
        System.out.println(UIConstants.CONTINUE_PROMPT);
        waitForEnter();
    }
}
