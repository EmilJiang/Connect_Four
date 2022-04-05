import java.awt.*;

public class MediumComputerPlayer extends Player{
    MediumComputerPlayer(Color color, String name) {
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
