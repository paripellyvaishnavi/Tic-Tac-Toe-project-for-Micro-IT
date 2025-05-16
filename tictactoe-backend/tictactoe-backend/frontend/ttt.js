const cells = document.querySelectorAll('.cell');
const turnDisplay = document.getElementById('turn');
const winnerDisplay = document.getElementById('winner');
const resetButton = document.getElementById('resetButton');
let gameActive = true;
let currentPlayer = 'X';
const backendPort = 8082; // Or 8080, whichever your backend is running on

function updateFrontend(gameState) {
    console.log("Received gameState:", gameState);
    const board = gameState.board; // Assuming gameState.board is a 1D array of strings

    for (let i = 0; i < cells.length; i++) {
        const row = Math.floor(i / 3);
        const col = i % 3;
		cells[i].textContent = board[row][col].trim();
         // Access element by index and trim whitespace
    }

    turnDisplay.textContent = gameState.gameOver ? (gameState.winner === 'D' ? "It's a draw!" : `Player ${gameState.winner} wins!`) : `Player ${gameState.currentPlayer}'s turn`;
    winnerDisplay.textContent = gameState.gameOver ? (gameState.winner === 'D' ? "It's a draw!" : `Player ${gameState.winner} wins!`) : '';
    gameActive = !gameState.gameOver;
    currentPlayer = gameState.currentPlayer;
}

function makeMove(row, col) {
    console.log(`Attempting move at row: ${row}, col: ${col}`);
    if (!gameActive || cells[row * 3 + col].textContent !== '') {
        return;
    }

    fetch(`http://localhost:${backendPort}/api/game/move?row=${row}&col=${col}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
    })
    .then(response => response.json())
    .then(data => {
        updateFrontend(data);
    })
    .catch(error => console.error("Error making move:", error));
}

function getGameState() {
    fetch(`http://localhost:${backendPort}/api/game/state`)
        .then(response => response.json())
        .then(data => {
            updateFrontend(data);
        })
        .catch(error => console.error("Error getting state:", error));
}

function resetGame() {
    fetch(`http://localhost:${backendPort}/api/game/reset`, {
        method: 'POST'
    })
    .then(response => response.json())
    .then(data => {
        updateFrontend(data);
    })
    .catch(error => console.error("Error resetting game:", error));
}

cells.forEach((cell, index) => {
    const row = Math.floor(index / 3);
    const col = index % 3;
    cell.addEventListener('click', () => makeMove(row, col));
});

resetButton.addEventListener('click', resetGame);

getGameState();