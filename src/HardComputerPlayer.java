import java.awt.*;
import java.util.ArrayList;


public class HardComputerPlayer extends Player{
    //rows is y and 6
    //column is x and 7

    private int originalDepth;
    HardComputerPlayer(Color color, String name) {
        super(color, name);
    }

    @Override
    boolean isComputer() {
        return true;
    }

    private boolean isTerminal(Game game, Board board){
        boolean isterminal = false;
        if(get_valid_locations(board).size() == 0 || board.checkForWinner(game.getPlayers()[1]) || board.checkForWinner(game.getPlayers()[0])){
            isterminal = true;
        }
        return isterminal;
    }
    private int[] minmax(Board board, Game game, int depth, int alpha, int beta, boolean maxplayer ){
        ArrayList<Integer> valid_locations = get_valid_locations(board);
        if(depth == 0 || isTerminal(game,board)){
            if(isTerminal(game,board)) {
                if (board.checkForWinner(game.getPlayers()[1])) {
                    return new int[]{5,10000000/((originalDepth-depth)*10)};

                } else if (board.checkForWinner(game.getPlayers()[0])) {
                    return new int[]{5,-10000000};
                } else {
                    return new int[]{5,0000000};
                }
            }
            else{
                return new int[]{0, score_horizontal(game, board, game.getPlayers()[1].getColor())};
            }
        }
        if(maxplayer){
            int score = Integer.MIN_VALUE;
            int column = valid_locations.get((int) (Math.random() * valid_locations.size()));
            for(int i = 0; i<valid_locations.size(); i++){
                //copy
                Board realBoard2 = new Board();
                for(int p =0; p<6; p++){
                    for(int j = 0; j<7; j++){
                        if(board.getBoardPos()[p][j] == null){
                            realBoard2.setBoardPos(p,j);
                        }
                        else{
                            realBoard2.setBoardPos(p,j,board.getBoardPos()[p][j].getOwner());
                        }

                    }
                }
                realBoard2.placePiece(valid_locations.get(i),game.getPlayers()[1]);
                int new_score = minmax(realBoard2,game,depth-1,alpha, beta, false)[1];
                if(new_score > score){
                    score = new_score;
                    column = valid_locations.get(i);
                }
                alpha = Math.max(score, alpha);
                if(alpha>=beta){
                    i = 100;
                }
            }
            int[] return1 = new int[]{column,score};
            return return1;
        }
        else{
            int score = Integer.MAX_VALUE;
            int column = valid_locations.get((int) (Math.random() * valid_locations.size()));

            for(int i = 0; i<valid_locations.size(); i++){
                //copy
                Board realBoard2 = new Board();
                for(int p =0; p<6; p++){
                    for(int j = 0; j<7; j++){
                        if(board.getBoardPos()[p][j] == null){
                            realBoard2.setBoardPos(p,j);
                        }
                        else{
                            realBoard2.setBoardPos(p,j,board.getBoardPos()[p][j].getOwner());
                        }

                    }
                }
                realBoard2.placePiece(valid_locations.get(i),game.getPlayers()[0]);
                int new_score = minmax(realBoard2,game,depth-1,alpha, beta,true)[1];
                if(new_score < score){
                    score = new_score;
                    column = valid_locations.get(i);
                }
                beta = Math.min(beta,score);
                if(alpha >= beta){
                    i = 100;
                }
            }
            int[] return1 = new int[]{column,score};
            return return1;
        }
    }
    private int eval_window(Piece[] hor, Color b){
        int score = 0;
        int nullCount = 0;
        int count = 0;
        int oppcount = 0;
        for(int h = 0; h<4; h++){
            if(hor[h] == null){
                nullCount ++;
            }
            else if(hor[h].getOwner().getColor() == b){
                count++;
            }
            else{
                oppcount++;
            }
        }
        if(count == 4){
            score += 100;
        }
        else if(count == 3 && nullCount ==1){
            score+=5;
        }
        else if(count == 2 && nullCount ==2){
            score+=2;
        }
        else if(oppcount == 3 && nullCount == 1){
            score-=4;
        }
        return score;
    }
    private ArrayList get_valid_locations(Board board){
        ArrayList<Integer> locations = new ArrayList<Integer>();
        for(int i =0; i<7; i++){
            if(board.getBoardPos()[0][i] == null){
                locations.add(i);
            }
        }
        return locations;
    }
    private int score_horizontal(Game game, Board board, Color b){
        int score = 0;
        int nullCount = 0;
        int count = 0;
        int oppCount = 0;
        //middle
        Piece[] center = new Piece[]{board.getBoardPos()[0][3],board.getBoardPos()[1][3],board.getBoardPos()[2][3],board.getBoardPos()[3][3],board.getBoardPos()[4][3],board.getBoardPos()[5][3]};
        for(int i = 0; i<center.length; i++){
            if(center[i] == null){
                nullCount++;
            }
            else if(center[i].getOwner().getColor() == b){
                count++;
            }
            else{
                oppCount++;
            }
        }
        score += count * 3;

        //horizontal
        for(int i =0; i<6; i++){
            Piece[] row_color = new Piece[]{board.getBoardPos()[i][0],board.getBoardPos()[i][1],board.getBoardPos()[i][2],board.getBoardPos()[i][3],board.getBoardPos()[i][4],board.getBoardPos()[i][5],board.getBoardPos()[i][6]};
            for(int j =0; j< 4; j++){
                Piece[] hor = new Piece[]{row_color[j],row_color[j+1],row_color[j+2],row_color[j+3]};
                score += eval_window(hor,b);
            }
        }

        //vertical
        for(int i =0; i<7; i++){
            Piece[] row_color = new Piece[]{board.getBoardPos()[0][i],board.getBoardPos()[1][i],board.getBoardPos()[2][i],board.getBoardPos()[3][i],board.getBoardPos()[4][i],board.getBoardPos()[5][i]};
            for(int j =0; j< 3; j++){
                Piece[] hor = new Piece[]{row_color[j],row_color[j+1],row_color[j+2],row_color[j+3]};
                score += eval_window(hor,b);
            }
        }

        //diagonal negative
        for(int i =0; i<3; i++){
            for(int j =0; j<4; j++){
                Piece[] hor = new Piece[4];
                for(int h = 0; h<4; h++){
                    hor[h] = board.getBoardPos()[i+h][j+h];
                }
                score += eval_window(hor,b);
            }
        }

        //diagonal positive
        for(int i =0; i<3; i++){
            for(int j =0; j<4; j++){
                Piece[] hor = new Piece[4];
                for(int h = 0; h<4; h++){
                    hor[h] = board.getBoardPos()[i+3-h][j+h];
                }
                score += eval_window(hor,b);
            }
        }
        return score;
    }
    @Override
    void playMove(Game game) {
//        int score = -1000;
//        int pos = 3;
//        int count = 0;
//        for(int i=0; i<7; i++){
//            Board realBoard = new Board();
//            try{
//                realBoard = (Board)game.getBoard().clone();}
//            catch (CloneNotSupportedException c){
//                System.out.println("hi");
//            }
//            if(get_valid_locations(game).contains(i)){
//                realBoard.placePiece(i,game.getCurrentPlayer());
//                if(score_horizontal(game,realBoard) >= score){
//                    score = score_horizontal(game,realBoard);
//                    pos = i;
//                    if(score_horizontal(game,realBoard) == 0){
//                        count++;
//                    }
//                }
//
//                realBoard.removePiece(i);
//            }
//        }
//        if((game.getMoveNum() <2 ) && score == 0){
//            pos = 3;
//        }
//        else if(score == 0 && game.getMoveNum()>=2 && count == 7){
//            System.out.println("hi");
//            pos = (int)(Math.random()*7);
//        }
//        System.out.println(count);
//        System.out.println(score);
//        System.out.println(game.getMoveNum());
//        System.out.println(pos);
        originalDepth = 9;
        int col = 0;
        int score = 0;
        if(game.getMoveNum() == 1){
            col = 3;
        }
        else {
            int[] result = minmax(game.getBoard(), game, originalDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
            col = result[0];
            score = result[1];
        }
        game.setColumn(col);
        game.makeMove(col);
    }
}
