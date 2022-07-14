import javax.swing.*;
import java.awt.*;

public class GUI {
    private static JFrame frame;
    public static Color background;
    public static Color text;
    public static Color button;
    public static Color board;
    private JPanel menuPanel;
    public static String theme;
    GUI(){
        background  =new Color(88, 102, 139);
        text = new Color(189, 234, 238);
        button = new Color(235, 244, 246);
        board = new Color(0x76B4BD);
        theme = "WATER BEARER";
        frame = new JFrame("Connect Four");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.menuPanel = new MenuScreen();
        frame.setLayout(null);
        frame.add(menuPanel);
        frame.setSize(1200, 800);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void setTheme(String theme) {
        GUI.theme = theme;
    }

    public static String getTheme() {
        return theme;
    }

    public static void switchPane(JPanel currentPanel, JPanel newPanel){
        frame.remove(currentPanel);
        frame.add(newPanel);
        newPanel.updateUI();
    }
    public static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        GUI gui = new GUI();
    }

    public static void setBackground(Color background) {
        GUI.background = background;
    }

    public static void setBoard(Color board) {
        GUI.board = board;
    }

    public static void setButton(Color button) {
        GUI.button = button;
    }

    public static void setText(Color text) {
        GUI.text = text;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runGUI();
            }
        });
    }
}
