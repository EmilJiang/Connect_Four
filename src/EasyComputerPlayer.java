import java.awt.*;
import java.util.ArrayList;

public class EasyComputerPlayer extends Player{
    EasyComputerPlayer(Color color, String name) {
        super(color, name);
    }
    @Override
    boolean isComputer() {
        return true;
    }

    @Override
    public String getName() {
        return "easy";
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
    @Override
    void playMove(Game game) {
        ArrayList<Integer> a = get_valid_locations(game.getBoard());
        int num = a.size();
        int random = (int)(Math.random()*num);
        int column = a.get(random);
        game.setColumn(column);
        game.makeMove(column);
    }
}
