import java.awt.*;

public class EasyComputerPlayer extends Player{
    EasyComputerPlayer(Color color, String name) {
        super(color, name);
    }
    @Override
    boolean isComputer() {
        return true;
    }

    @Override
    void playMove(Game game) {
        game.makeMove((int)(Math.random()*7));
    }
}
