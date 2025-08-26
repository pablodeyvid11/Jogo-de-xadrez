package presentation;

import application.ChessGameApplication;

/**
 * Main entry point for the Chess Game application
 */
public class Program {

    /**
     * Main method that starts the chess game
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        ChessGameApplication app = new ChessGameApplication();
        app.start();
    }
}