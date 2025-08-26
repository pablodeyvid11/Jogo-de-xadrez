package domain.services;

import domain.chess.Color;

/**
 * Immutable class representing the current state of the game
 */
public final class GameState {
    
    private final int turn;
    private final Color currentPlayer;
    private final boolean isInCheck;
    private final boolean isCheckMate;
    private final boolean hasPendingPromotion;
    
    public GameState(int turn, Color currentPlayer, boolean isInCheck, 
                    boolean isCheckMate, boolean hasPendingPromotion) {
        this.turn = turn;
        this.currentPlayer = currentPlayer;
        this.isInCheck = isInCheck;
        this.isCheckMate = isCheckMate;
        this.hasPendingPromotion = hasPendingPromotion;
    }
    
    public int getTurn() {
        return turn;
    }
    
    public Color getCurrentPlayer() {
        return currentPlayer;
    }
    
    public boolean isInCheck() {
        return isInCheck;
    }
    
    public boolean isCheckMate() {
        return isCheckMate;
    }
    
    public boolean hasPendingPromotion() {
        return hasPendingPromotion;
    }
    
    @Override
    public String toString() {
        return String.format("GameState{turn=%d, currentPlayer=%s, isInCheck=%s, isCheckMate=%s, hasPendingPromotion=%s}",
                turn, currentPlayer, isInCheck, isCheckMate, hasPendingPromotion);
    }
}
