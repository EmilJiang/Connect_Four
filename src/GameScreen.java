import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;

public class GameScreen extends JPanel implements ActionListener, MouseMotionListener, MouseListener {
    private JFrame frame;
    private JPanel panel;
    private Game game;
    private int panelNumber;
    private Timer timer=new Timer(10, this);
    private RightEnd rightEnd;
    private LeftEnd leftEnd;
    private Board board;
    GameScreen(Game game){

        this.game = game;
        this.board = new Board();

        panelNumber = 0;
        rightEnd = new RightEnd(120,600,100,875,100);
        leftEnd = new LeftEnd(120,600,100,300,100);

        frame = new JFrame("Connect Four");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(null);

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

        this.setBackground(new Color(0x8D9D94));
        this.setSize(1200, 800);
        this.setLayout(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        frame.add(this);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==timer){
            this.repaint();
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
        if(panelNumber == 1){
            g.setColor(new Color(0x138013));
            graphics.fill(leftEnd);
            g.setColor(new Color(0x28BE28));
            g.fillRect(300,100,575,600);
            graphics.fill(rightEnd);
        }
        else if(panelNumber == 2){
            //shaded region
            g.setColor(new Color(0x138013));
            g.fillRect(300,100,115,600);
            //unshaded region
            g.setColor(new Color(0x28BE28));
            g.fillRect(415,100,575,600);
            graphics.fill(leftEnd);
            graphics.fill(rightEnd);
        }
        else if(panelNumber == 3){
            //shaded region
            g.setColor(new Color(0x138013));
            g.fillRect(415,100,115,600);
            //unshaded region
            g.setColor(new Color(0x28BE28));
            g.fillRect(300,100,115,600);
            g.fillRect(530,100,400,600);
            graphics.fill(leftEnd);
            graphics.fill(rightEnd);
        }
        else if(panelNumber == 4){
            //shaded region
            g.setColor(new Color(0x138013));
            g.fillRect(530,100,115,600);
            //unshaded region
            g.setColor(new Color(0x28BE28));
            g.fillRect(300,100,230,600);
            g.fillRect(645,100,300,600);
            graphics.fill(leftEnd);
            graphics.fill(rightEnd);
        }
        else if(panelNumber == 5){
            //shaded region
            g.setColor(new Color(0x138013));
            g.fillRect(645,100,115,600);
            //unshaded region
            g.setColor(new Color(0x28BE28));
            g.fillRect(300,100,345,600);
            g.fillRect(760,100,200,600);
            graphics.fill(leftEnd);
            graphics.fill(rightEnd);
        }
        else if(panelNumber == 6){
            //shaded region
            g.setColor(new Color(0x138013));
            g.fillRect(760,100,115,600);
            //unshaded region
            g.setColor(new Color(0x28BE28));
            g.fillRect(300,100,460,600);
            g.fillRect(875,100,100,600);
            graphics.fill(leftEnd);
            graphics.fill(rightEnd);
        }
        else if(panelNumber == 7){
            //shaded region
            g.setColor(new Color(0x138013));
            graphics.fill(rightEnd);
            //unshaded region
            g.setColor(new Color(0x28BE28));
            g.fillRect(300,100,575,600);
            graphics.fill(leftEnd);
        }
        else{
            g.setColor(new Color(0x28BE28));
            graphics.fill(leftEnd);
            g.fillRect(300,100,575,600);
            graphics.fill(rightEnd);
        }
        g.setColor(Color.white);
            for(int i = 0; i<7; i++){
                for(int j = 0; j<6; j++){
                    graphics.fillOval(205+(i*115), 115+(j*100), 75, 75);
                }
            }
    }

    public static void main(String args[]){
        new GameScreen(new Game(new HumanPlayer(Color.red,"bob"), new HumanPlayer(Color.yellow,"frank")));
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(e.getY() > 100 && e.getY()<600){

            if(e.getX()>=200 && e.getX()< 315){
                panelNumber = 1;
            }
            else if(e.getX()>=315 && e.getX()< 430){
                panelNumber = 2;
            }
            else if(e.getX()>=430 && e.getX()< 545){
                panelNumber = 3;
            }
            else if(e.getX()>=545 && e.getX()< 660){
                panelNumber = 4;
            }
            else if(e.getX()>=660 && e.getX()< 775){
                panelNumber = 5;
            }
            else if(e.getX()>=775 && e.getX()< 890){
                panelNumber = 6;
            }
            else if(e.getX()>=890 && e.getX()< 1000){
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
        if(panelNumber!=0){
            game.makeMove(panelNumber);
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

