import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class MenuScreen extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton onePlayer;
    private JButton twoPlayer;
    private JButton setting;
    private JButton quit;
    private Timer timer=new Timer(10, this);
    MenuScreen(){


        onePlayer = new JButton("One Player");
        onePlayer.setOpaque(true);
        onePlayer.setBorderPainted(true);
        onePlayer.setContentAreaFilled(true);
        onePlayer.setBorder(new LineBorder(GUI.text));
        onePlayer.setFont(new Font("Helvetica", Font.PLAIN, 50));
        onePlayer.setForeground(GUI.background);
        onePlayer.setBackground(GUI.button);
        onePlayer.setBounds(400,200,400,150);
        onePlayer.addActionListener(this);
        onePlayer.setActionCommand("onePlayer");
        onePlayer.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        onePlayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                onePlayer.setBackground(new Color((int)(GUI.button.getRed()*.9),(int)(GUI.button.getGreen()*.9),(int)(GUI.button.getBlue()*.9)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                onePlayer.setBackground(GUI.button);
            }
        });

        twoPlayer = new JButton("Two Player");
        twoPlayer.setOpaque(true);
        twoPlayer.setBorderPainted(true);
        twoPlayer.setContentAreaFilled(true);
        twoPlayer.setBorder(new LineBorder(GUI.text));
        twoPlayer.setFont(new Font("Helvetica", Font.PLAIN, 50));
        twoPlayer.setForeground(GUI.background);
        twoPlayer.setBackground(GUI.button);
        twoPlayer.setBounds(400,400,400,150);
        twoPlayer.addActionListener(this);
        twoPlayer.setActionCommand("twoPlayer");
        twoPlayer.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        twoPlayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                twoPlayer.setBackground(new Color((int)(GUI.button.getRed()*.9),(int)(GUI.button.getGreen()*.9),(int)(GUI.button.getBlue()*.9)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                twoPlayer.setBackground(GUI.button);
            }
        });

        setting = new JButton("Customize");
        setting.setOpaque(true);
        setting.setBorderPainted(true);
        setting.setContentAreaFilled(true);
        setting.setBorder(new LineBorder(GUI.text));
        setting.setFont(new Font("Helvetica", Font.PLAIN, 50));
        setting.setForeground(GUI.background);
        setting.setBackground(GUI.button);
        setting.setBounds(400,600,400,150);
        setting.addActionListener(this);
        setting.setActionCommand("setting");
        setting.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        setting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setting.setBackground(new Color((int)(GUI.button.getRed()*.9),(int)(GUI.button.getGreen()*.9),(int)(GUI.button.getBlue()*.9)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setting.setBackground(GUI.button);
            }
        });

        quit = new JButton("Quit");
        quit.setOpaque(true);
        quit.setBorderPainted(true);
        quit.setContentAreaFilled(true);
        quit.setBorder(new LineBorder(GUI.text));
        quit.setFont(new Font("Helvetica", Font.PLAIN, 50));
        quit.setForeground(GUI.background);
        quit.setBackground(GUI.button);
        quit.setBounds(50,675,200,75);
        quit.addActionListener(this);
        quit.setActionCommand("quit");
        quit.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        quit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                quit.setBackground(new Color((int)(GUI.button.getRed()*.9),(int)(GUI.button.getGreen()*.9),(int)(GUI.button.getBlue()*.9)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                quit.setBackground(GUI.button);
            }
        });

        //adding to the panel
        this.add(setting);
        this.add(quit);
        this.setBackground(GUI.background);
        this.setSize(1200, 800);
        this.setLayout(null);
        this.add(onePlayer);
        this.add(twoPlayer);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==timer){
            this.repaint();
        }
        if(Objects.equals(e.getActionCommand(), "onePlayer")){
            GUI.switchPane(this, new NameScreen(1));
        }
        else if(Objects.equals(e.getActionCommand(), "twoPlayer")){
            GUI.switchPane(this, new NameScreen(2));
        }
        else if(Objects.equals(e.getActionCommand(), "setting")){
            GUI.switchPane(this, new Customize(GUI.getTheme()));
        }
        if(e.getActionCommand() == "quit"){
            System.exit(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.setColor(GUI.text);
        g.drawString("CONNECT - FOUR",150,100);
    }

    public static void main(String args[]){
        JFrame frame = new JFrame("Connect Four");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(null);
        frame.add(new MenuScreen());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(e.getXOnScreen() >= 400 && e.getXOnScreen() <= 600){
            if(e.getYOnScreen() >= 200 && e.getYOnScreen()<=350){
                onePlayer.setBackground(new Color(147, 213, 172));
            }
            else if(e.getYOnScreen() >= 400 && e.getYOnScreen()<=550){
                twoPlayer.setBackground(new Color(147, 213, 172));
            }
            else{
                onePlayer.setBackground(new Color(213, 233, 221));
                twoPlayer.setBackground(new Color(213, 233, 221));
            }
        }
        else{
            onePlayer.setBackground(new Color(213, 233, 221));
            twoPlayer.setBackground(new Color(213, 233, 221));
        }
    }
}
