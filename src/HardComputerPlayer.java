import java.awt.*;

public class HardComputerPlayer extends Player{
    HardComputerPlayer(Color color, String name) {
        super(color, name);
    }

    @Override
    boolean isComputer() {
        return false;
    }

    @Override
    void playMove(Game game) {

    }
}
