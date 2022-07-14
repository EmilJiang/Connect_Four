class Game {

    private Player[] players;
    private Board board;
    private int gameState;
    private int moveNumber;
    private Player winner;
    private int column;
    Game (Player player1, Player player2) {
// 		get players from menu
        moveNumber = 1;
        players = new Player[] {player1, player2};
        gameState = (int) ( Math.random() * 2 + 1);
        this.board = new Board();
// 		gameState lets the game know who's turn it is.
    }
    public boolean makeMove(int spot) {
        boolean hey = board.placePiece(spot,players[gameState - 1]);
        if (hey) {
            return true;
        }
        else {
            return false;
        }
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }


    public void takeBackMove(int spot) {
        board.removePiece(spot);
        endTurn();
        moveNumber--;
        moveNumber--;

    }
    public void endTurn() {
        if (gameState == 1) {
            gameState = 2;
        }
        else if (gameState == 2) {
            gameState = 1;
        }
        moveNumber++;
    }
    public Board getBoard() {
        return board;
    }
    public Player checkForWinner(Player player) {
        if (board.checkForWinner(player)) {
            winner = player;
            return player;
        } else {
            return null;
        }
    }

    public Player getWinner(){
        if(winner != null){
            return winner;
        }
        return null;
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
    public Player getNotCurrentPlayer(){
        if(getCurrentPlayer() == getPlayers()[0]){
            return getPlayers()[1];
        }
        return getPlayers()[0];
    }
}

