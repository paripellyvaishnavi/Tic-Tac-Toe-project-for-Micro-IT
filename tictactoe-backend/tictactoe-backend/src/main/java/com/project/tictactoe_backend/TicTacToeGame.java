package com.project.tictactoe_backend;

public class TicTacToeGame {
	private GameState gameState;

    public TicTacToeGame() {
        gameState = new GameState();
    }

    public GameState getGameState() {
        return gameState;
    }

    public boolean playTurn(int row, int col) {
        if (gameState.isGameOver()) {
            return false; // Game is over
        }
        if (gameState.makeMove(row, col)) {
            gameState.checkWin();
            if (!gameState.isGameOver()) {
                gameState.switchPlayer();
            }
            return true; // Move was successful
        }
        return false; // Invalid move
    }

    public void resetGame() {
        gameState = new GameState();
    }

}
