class Piece {
    private Player player;
    Piece (Player player) {
        this.player = player;
    }

    public Player getOwner() {
        return player;
    }
}
