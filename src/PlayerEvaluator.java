import java.awt.*;

public class PlayerEvaluator {
    private int numRuns;
    private int maxTurns;
    private int minTurns = 999;
    private int totalTurns;
    PlayerEvaluator(Player firstPlayer, Player secondPlayer,int runs){
        numRuns = runs;
        int firstplayer = 0;
        int secondplayer = 0;
        for(int i = 0; i<runs; i++){
            Game game = new Game(firstPlayer,secondPlayer);
            GameScreen gs = new GameScreen(game,Color.RED,Color.YELLOW);
            if(game.getWinner().getColor() == Color.RED){
                firstplayer++;
            }
            else{
                secondplayer++;
            }
        }
        System.out.println("number wins for firstplayer " + firstplayer);
        System.out.println("number wins for second " + secondplayer);
    }
    public static void main(String args[]){
        new PlayerEvaluator(new MediumComputerPlayer(Color.RED,"medium"), new HardComputerPlayer(Color.YELLOW,"hard"),100);
    }
}