import java.awt.Color;

abstract class Player {
    private Color color;
    private String name;
    Player(Color color, String name) {
        this.color = color;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public Color getColor() {
        return color;
    }
    abstract boolean isComputer();
    abstract void playMove(Game game);
}

