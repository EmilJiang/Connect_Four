import java.awt.Color;

class HumanPlayer extends Player {

    private String name;
    private Color color;

    HumanPlayer(Color color, String name){
        super(color,name);
        this.name = name;
        this.color = color;
    }

    public boolean isComputer() {
        return false;
    }

    public String getName() {
        return name;
    }
    public Color getColor() {
        return color;
    }

    public void playMove(Game game) {}
}

