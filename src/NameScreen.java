import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class NameScreen extends JPanel implements ActionListener, ChangeListener {

    private int oneOrTwo;
    private JFrame frame;
    private JButton cont;
    private JButton back;
    private JButton playerOneButton;
    private JButton playerTwoButton;
    private JTextField playerOneName;
    private JTextField playerTwoName;
    private JColorChooser tccPlayerOne;
    private JColorChooser tccPlayerTwo;
    private ColorChooser cc;
    private JComboBox dropdown;
    private Color playerOneColor;
    private Color playerTwoColor;
    NameScreen(int oneOrTwo){

        this.oneOrTwo = oneOrTwo;



        playerOneColor = Color.RED;
        playerTwoColor = Color.YELLOW;

        //text
        playerOneName = new JTextField();
        playerOneName.setBounds(325,300,500,50);
        playerOneName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {

                if(!(Character.isLetter(evt.getKeyChar()))){
                    evt.consume();
                }
            }
        });
        playerOneName.setDocument(new JTextFieldLimit(10));
        playerTwoName = new JTextField();
        playerTwoName.setBounds(325,400,500,50);
        playerTwoName.setDocument(new JTextFieldLimit(9));
        playerTwoName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {

                if(!(Character.isLetter(evt.getKeyChar()))){
                    evt.consume();
                }
            }
        });

        //dropdown
        String[] a = new String[]{"EASY", "MEDIUM", "HARD"};
        dropdown = new JComboBox(a);
        dropdown.setBounds(325,400,500,50);
        //button
        cont = new JButton("√");
        cont.setBounds(700,500,150,50);
        cont.setOpaque(true);
        cont.setBorderPainted(true);
        cont.setContentAreaFilled(true);
        cont.setBorder(new LineBorder(GUI.background));
        cont.setFont(new Font("Helvetica", Font.PLAIN, 50));
        cont.setForeground(GUI.background);
        cont.setBackground(GUI.button);

        cont.addActionListener(this);
        cont.setActionCommand("cont");
        cont.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        cont.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cont.setBackground(new Color((int)(GUI.button.getRed()*.9),(int)(GUI.button.getGreen()*.9),(int)(GUI.button.getBlue()*.9)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                cont.setBackground(GUI.button);
            }
        });

        back = new JButton("X");
        back.setOpaque(true);
        back.setBorderPainted(true);
        back.setContentAreaFilled(true);
        back.setBorder(new LineBorder(GUI.background));
        back.setFont(new Font("Helvetica", Font.PLAIN, 50));
        back.setForeground(GUI.background);
        back.setBackground(GUI.button);
        back.setBounds(350,500,150,50);
        back.addActionListener(this);
        back.setActionCommand("back");
        back.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                back.setBackground(new Color((int)(GUI.button.getRed()*.9),(int)(GUI.button.getGreen()*.9),(int)(GUI.button.getBlue()*.9)));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                back.setBackground(GUI.button);
            }
        });


        playerOneButton = new JButton();
        playerOneButton.setOpaque(true);
        playerOneButton.setBorderPainted(true);
        playerOneButton.setContentAreaFilled(true);
        playerOneButton.setBorder(new LineBorder(Color.RED));
        playerOneButton.setForeground(Color.RED);;
        playerOneButton.setBackground(Color.RED);
        playerOneButton.setBounds(825,300,50,50);
        playerOneButton.addActionListener(this);
        playerOneButton.setActionCommand("playerOneButton");
        playerOneButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");



        playerTwoButton = new JButton();
        playerTwoButton.setOpaque(true);
        playerTwoButton.setBorderPainted(true);
        playerTwoButton.setContentAreaFilled(true);
        playerTwoButton.setBorder(new LineBorder(Color.YELLOW));
        playerTwoButton.setForeground(Color.YELLOW);;
        playerTwoButton.setBackground(Color.YELLOW);
        playerTwoButton.setBounds(825,400,50,50);
        playerTwoButton.addActionListener(this);
        playerTwoButton.setActionCommand("playerTwoButton");
        playerTwoButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");



        //panel setup

        this.setBackground(GUI.background);
        this.setSize(1200, 800);
        this.setLayout(null);
        if(oneOrTwo == 2){
            this.add(playerTwoName);
        }
        else{
            this.add(dropdown);
        }
        this.add(playerOneName);
        this.add(cont);
        this.add(back);
        this.add(playerOneButton);
        this.add(playerTwoButton);

    }
    public class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
    @Override
    public void stateChanged(ChangeEvent e) {

    }

    private static class RoundedBorder implements Border {

        private int radius;


        RoundedBorder(int radius) {
            this.radius = radius;
        }


        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }


        public boolean isBorderOpaque() {
            return true;
        }


        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "cont"){
            if(oneOrTwo == 1){
                if (dropdown.getSelectedItem() == "EASY") {
                    GUI.switchPane(this,new GameScreen(new Game(new HumanPlayer(Color.RED,playerOneName.getText()),new EasyComputerPlayer(Color.YELLOW,"easy")), playerOneColor, playerTwoColor));
                }
                else if(dropdown.getSelectedItem() == "MEDIUM"){
                    GUI.switchPane(this,new GameScreen(new Game(new HumanPlayer(Color.RED,playerOneName.getText()),new MediumComputerPlayer(Color.YELLOW,"medium")), playerOneColor, playerTwoColor));
                }
                else if(dropdown.getSelectedItem() == "HARD"){
                    GUI.switchPane(this,new GameScreen(new Game(new HumanPlayer(Color.RED,playerOneName.getText()),new HardComputerPlayer(Color.YELLOW,"hard")),playerOneColor, playerTwoColor));
                }
            }
            else{
                GUI.switchPane(this,new GameScreen(new Game(new HumanPlayer(Color.RED,playerOneName.getText()),new HumanPlayer(Color.YELLOW,playerTwoName.getText())), playerOneColor, playerTwoColor));
            }
        }
        else if(e.getActionCommand() == "back"){
            GUI.switchPane(this, new MenuScreen());
        }
        else if(e.getActionCommand() == "playerOneButton"){
            playerOneColor = new ColorChooser(playerOneButton.getBackground()).getNewColor();
            playerOneButton.setBackground(playerOneColor);
            playerOneButton.setBorderPainted(false);
        }
        else if(e.getActionCommand() == "playerTwoButton"){
            playerTwoColor = new ColorChooser(playerTwoButton.getBackground()).getNewColor();
            playerTwoButton.setBackground(playerTwoColor);
            playerTwoButton.setBorderPainted(false);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.setColor(GUI.text);
        g.drawString("CONNECT - FOUR",150,100);
        g.fillRect(300,200,600,400);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 25));
        g.drawString("Player Name", 325,275);
        if(oneOrTwo == 2){
            g.drawString("Player Name", 325,385);
        }
    }

    public static void main(String args[]){
        JFrame frame = new JFrame("Connect Four");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(null);
        frame.add(new NameScreen(1));

        frame.setVisible(true);
        frame.setResizable(false);
    }
}
