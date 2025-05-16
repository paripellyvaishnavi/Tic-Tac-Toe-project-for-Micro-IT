package com.project.tictactoe_backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*") 
public class GameController {
	private TicTacToeGame game = new TicTacToeGame();

    @PostMapping("/move")
    public ResponseEntity<GameState> makeMove(@RequestParam int row, @RequestParam int col) {
        if (game.playTurn(row, col)) {
            return ResponseEntity.ok(game.getGameState());
        } else {
            return ResponseEntity.badRequest().body(game.getGameState()); // Or a custom error response
        }
    }

    @GetMapping("/state")
    public ResponseEntity<GameState> getGameState() {
        return ResponseEntity.ok(game.getGameState());
    }

    @PostMapping("/reset")
    public ResponseEntity<GameState> resetGame() {
        game.resetGame();
        return ResponseEntity.ok(game.getGameState());
    }

}
