import javax.swing.*;

public class GUI {
    private static JFrame frame;
    private JPanel menuPanel;
    GUI(){
        frame = new JFrame("Connect Four");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.menuPanel = new MenuScreen();

        frame.add(menuPanel);
        frame.setLayout(null);
        frame.setSize(1200, 800);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    public static void switchPane(JPanel currentPanel, JPanel newPanel){
        frame.remove(currentPanel);
        frame.add(newPanel);
        newPanel.updateUI();
        frame.pack();
    }
    public static void main(String args[]){
        new GUI();
    }
}
