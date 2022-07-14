import java.awt.Color;

class Board implements Cloneable{
    private Piece[][] board = new Piece[6][7];
    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }

// 	[rows][columns]
    /* * * * * * *
     * * * * * * *
     * * * * * * *
     * * * * * * *
     * * * * * * *
     * * * * * * */

    public Piece[][] getBoardPos() {
        return board;
    }
    public void setBoardPos(int y, int x, Player player){
        board[y][x] = new Piece(player);
    }
    public void setBoardPos(int y, int x){
        board[y][x] = null;
    }
    public boolean isOccupied(int x, int y) {
        if(board[y][x] != null) {
            return true;
        }
        return false;
    }
    public boolean placePiece(int spot, Player player) {
        // made boolean so you can check if it actually placed a piece down
        for (int a = 5; a > -1; a--) {

            if (isOccupied(spot,a)) {
            } else {
                board[a][spot] = new Piece(player);
                return true;
            }
        }
        return false;
    }
    public void removePiece(int spot) {
        boolean cont = true;
        for (int a = 0; a < 7; a++) {
            if (isOccupied(spot,a)) {
                board[a][spot] = null;
                a = 100;
            }
        }
    }
    public boolean checkForWinner(Player player) {
        for (int y = 5; y > -1; y--) {
            for (int x = 6; x > -1; x--) {
                // checks horizontally  (GOING RIGHT)
                if (x < 4) {
                    if (board[y][x] != null
                            && board[y][x + 1] != null
                            && board[y][x + 2] != null
                            && board[y][x + 3] != null) {
                        if (board[y][x].getOwner() == player
                                && board[y][x + 1].getOwner() == player
                                && board[y][x + 2].getOwner() == player
                                && board[y][x + 3].getOwner() == player) {
                            return true;
                        }
                    }

                }
                // checks vertically  (GOING DOWN)
                if (y < 3) {
                    if (board[y][x] != null
                            && board[y + 1][x] != null
                            && board[y + 2][x] != null
                            && board[y + 3][x] != null) {
                        if (board[y][x].getOwner() == player
                                && board[y + 1][x].getOwner() == player
                                && board[y + 2][x].getOwner() == player
                                && board[y + 3][x].getOwner() == player) {
                            return true;
                        }
                    }
                }
                // checks diagonally  (GOING DOWN AND RIGHT)
                if (y < 3 && x < 4) {
                    if (board[y][x] != null
                            && board[y + 1][x + 1] != null
                            && board[y + 2][x + 2] != null
                            && board[y + 3][x + 3] != null) {
                        if (board[y][x].getOwner() == player
                                && board[y + 1][x + 1].getOwner() == player
                                && board[y + 2][x + 2].getOwner() == player
                                && board[y + 3][x + 3].getOwner() == player) {
                            return true;
                        }
                    }
                }
                // check diagonally up and left
                if (y > 2 && x < 4) {
                    if (board[y][x] != null
                            && board[y - 1][x + 1] != null
                            && board[y - 2][x + 2] != null
                            && board[y - 3][x + 3] != null) {
                        if (board[y][x].getOwner() == player
                                && board[y - 1][x + 1].getOwner() == player
                                && board[y - 2][x + 2].getOwner() == player
                                && board[y - 3][x + 3].getOwner() == player) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public void printBoard() {
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 7; x++) {
                if (board[y][x] == null) {
                    System.out.print("* ");
                } else
                if (board[y][x].getOwner().getColor() == Color.RED) {
                    System.out.print("R ");
                } else
                if (board[y][x].getOwner().getColor() == Color.YELLOW) {
                    System.out.print("Y ");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }

}

