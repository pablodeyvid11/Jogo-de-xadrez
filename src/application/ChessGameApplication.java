package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import domain.board.BoardException;
import domain.chess.ChessException;
import domain.chess.ChessPiece;
import domain.services.GameService;
import domain.services.GameState;
import infrastructure.constants.UIConstants;
import presentation.console.ConsoleManager;
import presentation.display.BoardDisplay;
import presentation.display.GameInfoDisplay;
import presentation.display.TutorialDisplay;
import presentation.input.InputController;

/**
 * Main application class that orchestrates the chess game
 */
public class ChessGameApplication {
    
    private final GameService gameService;
    private final InputController inputController;
    
    public ChessGameApplication() {
        this.gameService = new GameService();
        this.inputController = new InputController(new Scanner(System.in));
    }
    
    /**
     * Starts the chess game application
     */
    public void start() {
        showTutorial();
        runGameLoop();
        showFinalGameState();
    }
    
    private void showTutorial() {
        ConsoleManager.clearScreen();
        TutorialDisplay.displayTutorial();
        inputController.waitForEnter();
    }
    
    private void runGameLoop() {
        while (!gameService.isGameOver()) {
            try {
                displayCurrentGameState();
                processPlayerTurn();
                handlePromotionIfNeeded();
            } catch (ChessException e) {
                handleGameError(e);
            } catch (InputMismatchException e) {
                handleGameError(e);
            }
        }
    }
    
    private void displayCurrentGameState() {
        ConsoleManager.clearScreen();
        displayGameInfo();
    }
    
    private void displayGameInfo() {
        BoardDisplay.printBoard(gameService.getChessMatch().getPieces());
        ConsoleManager.printBlankLine();
        GameInfoDisplay.printCapturedPieces(gameService.getCapturedPieces());
        ConsoleManager.printBlankLine();
        GameInfoDisplay.printGameStatus(gameService.getGameState());
        ConsoleManager.printBlankLine();
    }
    
    private void processPlayerTurn() {
        String sourceInput = inputController.promptForInput(UIConstants.SOURCE_PROMPT);
        
        boolean[][] possibleMoves = gameService.getPossibleMoves(sourceInput);
        displayBoardWithPossibleMoves(possibleMoves);
        
        String targetInput = inputController.promptForInput(UIConstants.TARGET_PROMPT);
        
        ChessPiece capturedPiece = gameService.performMove(sourceInput, targetInput);
        // Captured piece is automatically handled by GameService
    }
    
    private void displayBoardWithPossibleMoves(boolean[][] possibleMoves) {
        ConsoleManager.clearScreen();
        BoardDisplay.printBoard(gameService.getChessMatch().getPieces(), possibleMoves);
        ConsoleManager.printBlankLine();
    }
    
    private void handlePromotionIfNeeded() {
        GameState gameState = gameService.getGameState();
        if (gameState.hasPendingPromotion()) {
            String promotionType = inputController.promptForInput(UIConstants.PROMOTION_PROMPT);
            gameService.handlePromotion(promotionType);
        }
    }
    
    private void handleGameError(Exception e) {
        inputController.promptAndWait(e.getMessage());
    }
    
    private void showFinalGameState() {
        ConsoleManager.clearScreen();
        displayGameInfo();
    }
}
