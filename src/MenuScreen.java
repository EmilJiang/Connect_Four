import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScreen extends JPanel implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton onePlayer;
    MenuScreen(){
        frame = new JFrame("Connect Four");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(null);

        onePlayer = new JButton("One Player");
        onePlayer.setOpaque(true);
        onePlayer.setBorderPainted(true);
        onePlayer.setContentAreaFilled(false);
        onePlayer.setFont(new Font("Arial", Font.PLAIN, 25));
        onePlayer.setForeground(Color.GREEN);
        onePlayer.setBackground(Color.BLACK);
        onePlayer.setBounds(400,300,200,200);
        onePlayer.addActionListener(this);
        onePlayer.setActionCommand("onePlayer");
        onePlayer.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        
        this.setBackground(new Color(213, 233, 221));
        this.setSize(1200, 800);
        this.setLayout(null);
        this.add(onePlayer);

        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    public static void main(String args[]){
        new MenuScreen();
    }
}
