package com.project.tictactoe_backend;

public class GameState {
	private char[][] board; // 3x3 char array to represent the board (' ', 'X', 'O')
    private char currentPlayer; // 'X' or 'O'
    private boolean gameOver;
    private char winner;

    public GameState() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' '; // Initialize empty board
            }
        }
        currentPlayer = 'X'; // Player X starts
        gameOver = false;
        winner = ' ';
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char getWinner() {
        return winner;
    }

    public void setCurrentPlayer(char currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setWinner(char winner) {
        this.winner = winner;
    }

    public boolean makeMove(int row, int col) {
    	System.out.println("Attempting move at row: " + row + ", col: " + col);
    	System.out.println("Board at [" + row + "][" + col + "]: '" + board[row][col] + "'");
    	System.out.println("gameOver: " + gameOver);
        if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ' || gameOver) {
            return false; // Invalid move
        }
        board[row][col] = currentPlayer;
        return true;
    }

    public void switchPlayer() {
        if (!gameOver) {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    // Method to check for a winner (horizontally, vertically, diagonally)
    public void checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                gameOver = true;
                winner = currentPlayer;
                return;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                gameOver = true;
                winner = currentPlayer;
                return;
            }
        }
        // Check diagonals
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            gameOver = true;
            winner = currentPlayer;
            return;
        }
        // Check for a draw
        boolean boardFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    boardFull = false;
                    break;
                }
            }
            if (!boardFull) {
                break;
            }
        }
        if (boardFull && !gameOver) {
            gameOver = true;
            winner = 'D'; // 'D' for Draw
        }
    }
}


