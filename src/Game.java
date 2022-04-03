class Game {

    private Player[] players;
    private Board board;
    private int gameState;
    private int moveNumber;

    Game (Player player1, Player player2) {
// 		get players from menu
        players = new Player[] {player1, player2};
        gameState = (int) Math.random() * 2;
        gameState++;
        this.board = new Board();
// 		gameState lets the game know who's turn it is.
    }
    public void makeMove(int spot) {
        board.placePiece(spot,players[gameState - 1]);
        moveNumber++;
    }
    public void takeBackMove(int spot) {
        board.removePiece(spot);
        moveNumber--;
    }
    public void endTurn() {
        if (gameState == 1) {
            gameState = 2;
        } else
        if (gameState == 2) {
            gameState = 1;
        }
    }
    public Board getBoard() {
        return board;
    }
    public Player checkForWinner(Player player) {
        if (board.checkForWinner(player)) {
            return player;
        } else {
            return null;
        }
    }
    public int getMoveNum() {
        return moveNumber;
    }

    public int getGameState() {
        return gameState;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players[gameState - 1];
    }
}

