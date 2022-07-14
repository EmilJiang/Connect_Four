import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;

public class Customize extends JPanel implements ActionListener{

    private JFrame frame;
    private Timer timer=new Timer(10, this);
    private RightEnd rightEnd;
    private LeftEnd leftEnd;
    private int[][][] posList;
    private JButton menu;
    private JComboBox dropdown;
    Customize(String theme){
        this.posList = new int[6][7][2];
        for(int y = 0 ;y<6; y++){
            for (int x =0; x<7; x++){
                posList[y][x][0] = 205+(x*115);
                posList[y][x][1] = 115+(y*100);
            }
        }
        rightEnd = new Customize.RightEnd(120,600,100,875,100);
        leftEnd = new Customize.LeftEnd(120,600,100,300,100);
        menu = new JButton("menu");
        menu.setOpaque(true);
        menu.setBorderPainted(true);
        menu.setContentAreaFilled(true);
        menu.setBorder(new LineBorder(GUI.text));
        menu.setFont(new Font("Helvetica", Font.PLAIN, 50));
        menu.setForeground(GUI.background);
        menu.setBackground(GUI.button);
        menu.setBounds(25,700,200,75);
        menu.addActionListener(this);
        menu.setActionCommand("menu");
        menu.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");


        //dropdown
        String[] a = new String[11];
        String[] b = new String[]{"WATER BEARER", "BEACH", "PURPLE SKYLINE","MARINE BLUES","SHADES IN STONE","HOT FLAME","WET JUNGLE","GRAYSCALE","COLOR EXPLOSION", "VIBRANT SUNRISE", "SUNLIT BUBBLES"};
        a[0] = theme;
        int num = 1;
        for(int i = 0; i<b.length; i++){
            if(theme != b[i]){
                a[num] = b[i];
                num++;
            }
        }

        dropdown = new JComboBox(a);
        dropdown.setBounds(500,700,200,50);

        this.setLayout(null);
        this.add(dropdown);
        this.setBackground(GUI.background);
        this.setSize(1200, 800);
        this.add(menu);
        timer.start();
    }
    public static void main(String[] args){
        JFrame frame = new JFrame("Connect Four");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(null);
        frame.add(new Customize("WATER BEARER"));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

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
        g.setColor(GUI.board);
        graphics.fill(leftEnd);
        g.fillRect(300,100,575,600);
        graphics.fill(rightEnd);
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 7; x++) {
                g.setColor(Color.WHITE);
                graphics.fillOval(posList[y][x][0], posList[y][x][1], 75, 75);
            }
        }
        menu.setBorder(new LineBorder(GUI.text));
        menu.setForeground(GUI.background);
        menu.setBackground(GUI.button);
        this.setBackground(GUI.background);
        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.setColor(GUI.text);
        g.drawString("CONNECT - FOUR",150,100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == timer){
            this.repaint();
            if(dropdown.getSelectedItem() == "WATER BEARER"){
                GUI.setBoard(new Color(0x76B4BD));
                GUI.setBackground(new Color(88, 102, 139));
                GUI.setText(new Color(189, 234, 238));
                GUI.setButton( new Color(235, 244, 246));
                GUI.setTheme("WATER BEARER");
            }
            else if(dropdown.getSelectedItem() == "BEACH"){
                GUI.setBoard(new Color(255, 204, 92));
                GUI.setBackground(new Color(255, 111, 105));
                GUI.setText(new Color(255, 204, 92));
                GUI.setButton(new Color(255, 238, 173));
                GUI.setTheme("BEACH");
            }
            else if(dropdown.getSelectedItem() == "PURPLE SKYLINE"){
                GUI.setBoard(new Color(136, 116, 163));
                GUI.setBackground(new Color(61, 30, 109));
                GUI.setText(new Color(136, 116, 163));
                GUI.setButton(new Color(228, 220, 241));
                GUI.setTheme("PURPLE SKYLINE");
            }
            else if(dropdown.getSelectedItem() == "MARINE BLUES"){
                GUI.setBoard(new Color(104, 176, 171));
                GUI.setBackground(new Color(74, 124, 89));
                GUI.setText(new Color(143, 192, 169));
                GUI.setButton(new Color(250, 243, 221));
                GUI.setTheme("MARINE BLUES");
            }
            else if(dropdown.getSelectedItem() == "SHADES IN STONE"){
                GUI.setBoard(new Color(166, 128, 140));
                GUI.setBackground(new Color(86, 82, 100));
                GUI.setText(new Color(112, 102, 119));
                GUI.setButton(new Color(204, 183, 174));
                GUI.setTheme("SHADES IN STONE");
            }
            else if(dropdown.getSelectedItem() == "HOT FLAME"){
                GUI.setBoard(new Color(254, 103, 110));
                GUI.setBackground(new Color(199, 56, 102));
                GUI.setText(new Color(253, 143, 82));
                GUI.setButton(new Color(255, 189, 113));
                GUI.setTheme("HOT FLAME");
            }
            else if(dropdown.getSelectedItem() == "WET JUNGLE"){
                GUI.setBoard(new Color(91, 168, 160));
                GUI.setBackground(new Color(59, 82, 132));
                GUI.setText(new Color(203, 229, 78));
                GUI.setButton(new Color(148, 180, 71));
                GUI.setTheme("WET JUNGLE");
            }
            else if(dropdown.getSelectedItem() == "GRAYSCALE"){
                GUI.setBoard(new Color(182, 182, 182));
                GUI.setBackground(new Color(155, 155, 155));
                GUI.setText(new Color(209, 209, 209));
                GUI.setButton(new Color(231, 231, 231));
                GUI.setTheme("GRAYSCALE");
            }
            else if(dropdown.getSelectedItem() == "COLOR EXPLOSION"){
                GUI.setBoard(new Color(250, 137, 123));
                GUI.setBackground(new Color(134, 227, 206));
                GUI.setText(new Color(208, 230, 165));
                GUI.setButton(new Color(255, 221, 148));
                GUI.setTheme("COLOR EXPLOSION");
            }
            else if(dropdown.getSelectedItem() == "VIBRANT SUNRISE"){
                GUI.setBoard(new Color(223, 130, 95));
                GUI.setBackground(new Color(77, 68, 111));
                GUI.setText(new Color(223, 177, 91));
                GUI.setButton(new Color(248, 149, 111));
                GUI.setTheme("VIBRANT SUNRISE");
            }
            else if(dropdown.getSelectedItem() == "SUNLIT BUBBLES"){
                GUI.setBoard(new Color(127, 172, 214));
                GUI.setBackground(new Color(51, 83, 158));
                GUI.setText(new Color(191, 184, 218));
                GUI.setButton(new Color(234, 183, 212));
                GUI.setTheme("SUNLIT BUBBLES");
            }
        }
        if(e.getActionCommand() == "menu"){
            timer.stop();
            GUI.switchPane(this,new MenuScreen());
        }
    }
}
