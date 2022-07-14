import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class GameScreen extends JPanel implements ActionListener, MouseMotionListener, MouseListener {
    private JFrame frame;
    private JPanel panel;
    private Game game;
    private int panelNumber;
    private Timer timer=new Timer(1, this);
    private RightEnd rightEnd;
    private LeftEnd leftEnd;
    private Board board;
    private boolean placePiece;
    private int[][][] posList;
    private int column;
    public boolean gameOver;
    private Color player1;
    private Color player2;
    private float y1;
    private JButton undo;
    private JButton menu;
    private JButton playAgain;
    private int nowRow;
    private boolean tie;
    private int prevColumn;
    private ArrayList<Integer> poss;
    private boolean active;
    GameScreen(Game game, Color player1, Color player2){
        this.active = true;
        this.prevColumn =-1;
        this.player1 = player1;
        this.player2 = player2;
        this.tie = false;
        this.y1 = 100;
        this.column = -1;
        this.nowRow = 0;
        gameOver = false;
        this.poss = new ArrayList<Integer>();
        this.posList = new int[6][7][2];
        for(int y = 0 ;y<6; y++){
            for (int x =0; x<7; x++){
                posList[y][x][0] = 205+(x*115);
                posList[y][x][1] = 115+(y*100);
            }
        }
        this.game = game;
        this.board = new Board();
        this.placePiece = false;
        panelNumber = 0;
        rightEnd = new RightEnd(120,600,100,875,100);
        leftEnd = new LeftEnd(120,600,100,300,100);


        JPanel jp1 = new JPanel();
        jp1.addMouseMotionListener(this);
        jp1.addMouseListener(this);
        jp1.setLocation(200, 100);
        jp1.setSize(115,600);

        JPanel jp2 = new JPanel();
        jp2.addMouseMotionListener(this);
        jp2.addMouseListener(this);
        jp2.setLocation(315, 100);
        jp2.setSize(115,600);

        JPanel jp3 = new JPanel();
        jp3.addMouseMotionListener(this);
        jp3.addMouseListener(this);
        jp3.setLocation(430, 100);
        jp3.setSize(115,600);

        JPanel jp4 = new JPanel();
        jp4.addMouseMotionListener(this);
        jp4.addMouseListener(this);
        jp4.setLocation(545, 100);
        jp4.setSize(115,600);

        JPanel jp5 = new JPanel();
        jp5.addMouseMotionListener(this);
        jp5.addMouseListener(this);
        jp5.setLocation(660, 600);
        jp5.setSize(115,86);

        JPanel jp6 = new JPanel();
        jp6.addMouseListener(this);
        jp6.addMouseMotionListener(this);
        jp6.setLocation(775, 100);
        jp6.setSize(115,600);

        JPanel jp7 = new JPanel();
        jp7.addMouseListener(this);
        jp7.addMouseMotionListener(this);
        jp7.setLocation(890, 100);
        jp7.setSize(115,600);

        menu = new JButton("menu");
        menu.setOpaque(true);
        menu.setBorderPainted(true);
        menu.setContentAreaFilled(true);
        menu.setBorder(new LineBorder(GUI.text));
        menu.setFont(new Font("Helvetica", Font.PLAIN, 50));
        menu.setForeground(GUI.background);
        menu.setBackground(GUI.button);
        menu.setBounds(25,10,200,75);
        menu.addActionListener(this);
        menu.setActionCommand("menu");
        menu.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu.setBackground(new Color((int)(GUI.button.getRed()*.9),(int)(GUI.button.getGreen()*.9),(int)(GUI.button.getBlue()*.9)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                menu.setBackground(GUI.button);
            }
        });

        undo = new JButton("undo");
        undo.setOpaque(true);
        undo.setBorderPainted(true);
        undo.setContentAreaFilled(true);
        undo.setBorder(new LineBorder(GUI.text));
        undo.setFont(new Font("Helvetica", Font.PLAIN, 50));
        undo.setForeground(GUI.background);
        undo.setBackground(GUI.button);
        undo.setBounds(975,10,200,75);
        undo.addActionListener(this);
        undo.setActionCommand("undo");
        undo.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        undo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                undo.setBackground(new Color((int)(GUI.button.getRed()*.9),(int)(GUI.button.getGreen()*.9),(int)(GUI.button.getBlue()*.9)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                undo.setBackground(GUI.button);
            }
        });

        playAgain = new JButton("play again");
        playAgain.setOpaque(true);
        playAgain.setBorderPainted(true);
        playAgain.setContentAreaFilled(true);
        playAgain.setBorder(new LineBorder(GUI.text));
        playAgain.setFont(new Font("Helvetica", Font.PLAIN, 25));
        playAgain.setForeground(GUI.background);
        playAgain.setBackground(GUI.button);
        playAgain.setBounds(975,10,200,75);
        playAgain.addActionListener(this);
        playAgain.setActionCommand("playAgain");
        playAgain.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        playAgain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playAgain.setBackground(new Color((int)(GUI.button.getRed()*.9),(int)(GUI.button.getGreen()*.9),(int)(GUI.button.getBlue()*.9)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                playAgain.setBackground(GUI.button);
            }
        });
        playAgain.setVisible(false);

        this.add(playAgain);
        this.add(undo);
        this.add(menu);
        this.setBackground(GUI.background);
        this.setSize(1200, 800);
        this.setLayout(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==timer){
            if(gameOver || tie){
                undo.setVisible(false);
                playAgain.setVisible(true);
                timer.stop();
            }
            if(game.checkForWinner(game.getCurrentPlayer()) != null){
                gameOver = true;
            }
            if(game.getMoveNum() == 43 && game.checkForWinner(game.getCurrentPlayer()) == null){
                tie = true;
            }
            if(!gameOver && game.getCurrentPlayer().isComputer()){
                panelNumber = 0;
                this.repaint();
                active = false;
                if(game.getMoveNum() != 1){
                    if(game.getPlayers()[1].getName() == "hard"){
                        try {
                            this.repaint();
                            Thread. sleep(0);
                        } catch (InterruptedException g) {
                            throw new RuntimeException(g);
                        }
                    }
                    else{
                        try {
                            panelNumber = 0;
                            this.repaint();
                            Thread. sleep(1000);
                            panelNumber = 0;
                        } catch (InterruptedException g) {
                            throw new RuntimeException(g);
                        }
                    }

                }
                placePiece = true;
                game.getCurrentPlayer().playMove(game);
            }
            else{
                active = true;
            }
            this.repaint();
        }
        if(e.getActionCommand() == "menu"){
            GUI.switchPane(this, new MenuScreen());
        }
        if(e.getActionCommand() == "playAgain"){
            GUI.switchPane(this,new GameScreen(new Game(game.getPlayers()[0],game.getPlayers()[1]),player1, player2));
        }

        //undo

        if(game.getMoveNum() != 1 && e.getActionCommand() == "undo" && game.getPlayers()[1].isComputer() && !game.getCurrentPlayer().isComputer()){
            game.takeBackMove(poss.get(poss.size()-1));
            poss.remove(poss.size()-1);
            game.takeBackMove(poss.get(poss.size()-1));
            poss.remove(poss.size()-1);
        }
        else if(game.getMoveNum() != 1 && e.getActionCommand() == "undo"){
            game.takeBackMove(poss.get(poss.size()-1));
            poss.remove(poss.size()-1);
        }

    }
    public class RightEnd extends Path2D.Float {

        public RightEnd(float width, float height, float radius,int x,int y) {
            moveTo(0+x, 0+y);
            lineTo((width - radius)+x, 0+y);
            curveTo(width+x, 0+y, width+x, 0+y, width+x, radius+y);
            lineTo(width+x, (height - radius)+y);
            curveTo(width+x, height+y, width+x, height+y, (width - radius)+x, height+y);
            lineTo(0+x, height+y);
            closePath();
        }
    }
    public class LeftEnd extends Path2D.Float {
        public LeftEnd(float width, float height, float radius, int x, int y) {
            moveTo(0+x,0+y);
            lineTo(((width - radius)*-1)+x, 0+y);
            curveTo((width*-1)+x, 0+y, (width*-1)+x, 0+y, (width*-1)+x, radius+y);
            lineTo((width*-1)+x, height - radius+y);
            curveTo((width*-1)+x, height+y, (width*-1)+x, height+y, ((width - radius)*-1) + x, height+y);
            lineTo(0+x, height+y);
            closePath();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        if(panelNumber == 1 && !game.getCurrentPlayer().isComputer()){
            g.setColor(new Color((int)(GUI.board.getRed()*.8),(int)(GUI.board.getGreen()*.8),(int)(GUI.board.getBlue()*.8)));
            graphics.fill(leftEnd);
            g.setColor(GUI.board);
            g.fillRect(300,100,575,600);
            graphics.fill(rightEnd);
        }
        else if(panelNumber == 2 && !game.getCurrentPlayer().isComputer()){
            //shaded region
            g.setColor(new Color((int)(GUI.board.getRed()*.8),(int)(GUI.board.getGreen()*.8),(int)(GUI.board.getBlue()*.8)));
            g.fillRect(300,100,115,600);
            //unshaded region
            g.setColor(GUI.board);
            g.fillRect(415,100,480,600);
            graphics.fill(leftEnd);
            graphics.fill(rightEnd);
        }
        else if(panelNumber == 3 && !game.getCurrentPlayer().isComputer()){
            //shaded region
            g.setColor(new Color((int)(GUI.board.getRed()*.8),(int)(GUI.board.getGreen()*.8),(int)(GUI.board.getBlue()*.8)));
            g.fillRect(415,100,115,600);
            //unshaded region
            g.setColor(GUI.board);
            g.fillRect(300,100,115,600);
            g.fillRect(530,100,400,600);
            graphics.fill(leftEnd);
            graphics.fill(rightEnd);
        }
        else if(panelNumber == 4 && !game.getCurrentPlayer().isComputer()){
            //shaded region
            g.setColor(new Color((int)(GUI.board.getRed()*.8),(int)(GUI.board.getGreen()*.8),(int)(GUI.board.getBlue()*.8)));
            g.fillRect(530,100,115,600);
            //unshaded region
            g.setColor(GUI.board);
            g.fillRect(300,100,230,600);
            g.fillRect(645,100,280,600);
            graphics.fill(leftEnd);
            graphics.fill(rightEnd);
        }
        else if(panelNumber == 5 && !game.getCurrentPlayer().isComputer()){
            //shaded region
            g.setColor(new Color((int)(GUI.board.getRed()*.8),(int)(GUI.board.getGreen()*.8),(int)(GUI.board.getBlue()*.8)));
            g.fillRect(645,100,115,600);
            //unshaded region
            g.setColor(GUI.board);
            g.fillRect(300,100,345,600);
            g.fillRect(760,100,150,600);
            graphics.fill(leftEnd);
            graphics.fill(rightEnd);
        }
        else if(panelNumber == 6 && !game.getCurrentPlayer().isComputer()){
            //shaded region
            g.setColor(new Color((int)(GUI.board.getRed()*.8),(int)(GUI.board.getGreen()*.8),(int)(GUI.board.getBlue()*.8)));
            g.fillRect(760,100,115,600);
            //unshaded region
            g.setColor(GUI.board);
            g.fillRect(300,100,460,600);
            g.fillRect(875,100,50,600);
            graphics.fill(leftEnd);
            graphics.fill(rightEnd);
        }
        else if(panelNumber == 7 && !game.getCurrentPlayer().isComputer()){
            //shaded region
            g.setColor(new Color((int)(GUI.board.getRed()*.8),(int)(GUI.board.getGreen()*.8),(int)(GUI.board.getBlue()*.8)));
            graphics.fill(rightEnd);
            //unshaded region
            g.setColor(GUI.board);
            g.fillRect(300,100,575,600);
            graphics.fill(leftEnd);
        }
        else{
            g.setColor(GUI.board);
            graphics.fill(leftEnd);
            g.fillRect(300,100,575,600);
            graphics.fill(rightEnd);
        }
        if(placePiece && !gameOver){
            placePiece = false;
            if(game.getCurrentPlayer().isComputer()){
                poss.add(game.getColumn());
            }
            else{
                poss.add(column);
            }
            if(game.checkForWinner(game.getCurrentPlayer()) != null){
                gameOver = true;
            }
            game.endTurn();
            if(!game.getCurrentPlayer().isComputer()){
                active = true;
            }
        }
        if(gameOver){
            panelNumber = 0;
            g.setColor(GUI.button);
            FontMetrics metrics = g.getFontMetrics(new Font("Arial", Font.BOLD, 50));
            g.setFont(new Font("Arial", Font.BOLD, 50));

            g.drawString(game.getWinner().getName() + " won", (1200 - metrics.stringWidth(game.getWinner().getName() + " won")) / 2,50);
        }
        else if(tie){
            panelNumber = 0;
            g.setColor(GUI.button);
            FontMetrics metrics = g.getFontMetrics(new Font("Arial", Font.BOLD, 50));
            g.setFont(new Font("Arial", Font.BOLD, 50));

            g.drawString("TIE", (1200 - metrics.stringWidth("TIE")) / 2,50);
        }
        else{
            g.setColor(Color.WHITE);
            FontMetrics metrics = g.getFontMetrics(new Font("Arial", Font.BOLD, 40));
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString(game.getCurrentPlayer().getName() + " turn", (1200 - metrics.stringWidth(game.getCurrentPlayer().getName() + " turn")) / 2,50);
            if(game.getMoveNum()!=1 && !game.getCurrentPlayer().isComputer() && game.getPlayers()[1].isComputer()){
                g.drawString(game.getNotCurrentPlayer().getName() + " placed in column " + (game.getColumn()+1) , (1200 - metrics.stringWidth(game.getNotCurrentPlayer().getName() + " placed in column " + (game.getColumn()+1))) / 2,750);
            }
        }

//        if(placePiece && !gameOver){
//            int num = -1;
//            for(int i = 5; i>-1; i--){
//                if(game.getBoard().getBoardPos()[i][column] == null){
//                    num = i;
//                }
//            }
//            num = 5-num;
//            if(nowRow < num){
//                System.out.println("hi");
//                if (game.getCurrentPlayer().getColor() == Color.RED) {
//                    g.setColor(player1);
//                }
//                else{
//                    g.setColor(player2);
//                }
//                for (int y = 0; y < 6; y++) {
//                    for (int x = 0; x < 7; x++) {
//                        if (game.getBoard().getBoardPos()[y][x] == null && y != nowRow && x != column) {
//                            g.setColor(Color.WHITE);
//                            graphics.fillOval(posList[y][x][0], posList[y][x][1], 75, 75);
//                        }
//                        else if(game.getBoard().getBoardPos()[y][x] == null && y == nowRow && x == column)
//                        else if (game.getBoard().getBoardPos()[y][x].getOwner().getColor() == Color.RED) {
//                            g.setColor(player1);
//                            graphics.fillOval(posList[y][x][0], posList[y][x][1], 75, 75);
//                        } else if (game.getBoard().getBoardPos()[y][x].getOwner().getColor() == Color.YELLOW) {
//                            g.setColor(player2);
//                            graphics.fillOval(posList[y][x][0], posList[y][x][1], 75, 75);
//                        }
//                    }
//                }
//                g.fillOval(posList[nowRow][column][0], posList[nowRow][column][1], 75, 75);
//                nowRow+=1;
//                try {
//                    Thread. sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            else{
//                y1 = 0;
//                placePiece = false;
//                nowRow = 5;
//                if(game.checkForWinner(game.getCurrentPlayer()) != null){
//                    gameOver = true;
//                    System.out.println(game.getCurrentPlayer().getName() + "won");
//                }
//                game.endTurn();
//            }
//        }
//        else{
//            for (int y = 0; y < 6; y++) {
//                for (int x = 0; x < 7; x++) {
//                    if (game.getBoard().getBoardPos()[y][x] == null) {
//                        g.setColor(Color.WHITE);
//                        graphics.fillOval(posList[y][x][0], posList[y][x][1], 75, 75);
//                    } else if (game.getBoard().getBoardPos()[y][x].getOwner().getColor() == Color.RED) {
//                        g.setColor(player1);
//                        graphics.fillOval(posList[y][x][0], posList[y][x][1], 75, 75);
//                    } else if (game.getBoard().getBoardPos()[y][x].getOwner().getColor() == Color.YELLOW) {
//                        g.setColor(player2);
//                        graphics.fillOval(posList[y][x][0], posList[y][x][1], 75, 75);
//                    }
//                }
//            }
//        }
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 7; x++) {
                if (game.getBoard().getBoardPos()[y][x] == null) {
                    g.setColor(Color.WHITE);
                    graphics.fillOval(posList[y][x][0], posList[y][x][1], 75, 75);
                } else if (game.getBoard().getBoardPos()[y][x].getOwner().getColor() == Color.RED) {
                    g.setColor(player1);
                    graphics.fillOval(posList[y][x][0], posList[y][x][1], 75, 75);
                } else if (game.getBoard().getBoardPos()[y][x].getOwner().getColor() == Color.YELLOW) {
                    g.setColor(player2);
                    graphics.fillOval(posList[y][x][0], posList[y][x][1], 75, 75);
                }
            }
        }
        g.setColor(GUI.button);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        int widthp0 = g.getFontMetrics().stringWidth(game.getPlayers()[0].getName());
        int widthp1 = g.getFontMetrics().stringWidth(game.getPlayers()[1].getName());
        g.drawString(game.getPlayers()[0].getName(),25,750);
        g.drawString(game.getPlayers()[1].getName(),(1175-widthp1),750);
        g.setColor(player1);
        g.fillOval(widthp0+25,710,50,50);
        g.setColor(player2);
        g.fillOval(1125-widthp1,710,50,50);
    }
    public void setColumn(int column){
        this.column = column;
    }
    public static void main(String args[]){
        JFrame frame = new JFrame("Connect Four");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(null);
        frame.add(new GameScreen(new Game(new MediumComputerPlayer(Color.red,"random"), new HardComputerPlayer(Color.yellow, "hard")), Color.red,  Color.YELLOW));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);


    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(e.getY() > 100 && e.getY()<705 && !game.getCurrentPlayer().isComputer()){
            if(e.getX()>=180 && e.getX()< 305){
                panelNumber = 1;
            }
            else if(e.getX()>=305 && e.getX()< 415){
                panelNumber = 2;
            }
            else if(e.getX()>=415 && e.getX()< 530){
                panelNumber = 3;
            }
            else if(e.getX()>=530 && e.getX()< 645){
                panelNumber = 4;
            }
            else if(e.getX()>=645 && e.getX()< 760){
                panelNumber = 5;
            }
            else if(e.getX()>=760 && e.getX()< 875){
                panelNumber = 6;
            }
            else if(e.getX()>=875 && e.getX()< 1000){
                panelNumber = 7;
            }
            else{
                panelNumber = 0;
            }
        }
        else{
            panelNumber = 0;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!game.getCurrentPlayer().isComputer() && active){
            active = false;
            if(panelNumber!=0){
                if(game.makeMove(panelNumber-1)){
                    placePiece = true;
                    column = panelNumber - 1;
                }
                else{
                    active = true;
                    placePiece = false;
                }
            }
        }
        else{
            panelNumber = 0;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

