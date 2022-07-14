import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChooser extends JPanel implements ChangeListener {
    private JColorChooser tccPlayerOne;
    private JColorChooser tccPlayerTwo;
    public Color newColor;
    private JPanel previewPanel;
    private JPanel f;
    public ColorChooser(Color colorPlayerOne){

        JDialog d = new JDialog();
        newColor = colorPlayerOne;
        tccPlayerOne = new JColorChooser(colorPlayerOne);
        tccPlayerOne.getSelectionModel().addChangeListener(this);
        AbstractColorChooserPanel[] Panels = tccPlayerOne.getChooserPanels();
        for (AbstractColorChooserPanel panel : Panels) {
            if (! "RGB".equals(panel.getDisplayName()))
                tccPlayerOne.removeChooserPanel(panel);
        }

        f = new JPanel();
        f.setBackground(colorPlayerOne);
        JLabel a = new JLabel("         ");
        a.setFont(new Font("Helvetica", Font.PLAIN, 50));
        f.add(a);
        tccPlayerOne.setPreviewPanel(f);
        ActionListener okListener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                tccPlayerOne.setVisible(false);
            }
        };
        ActionListener cancelListener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                tccPlayerOne.setVisible(false);
                newColor = colorPlayerOne;

            }
        };
        d = tccPlayerOne.createDialog(null,"",true,tccPlayerOne,okListener,cancelListener);
        d.setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        newColor = tccPlayerOne.getColor();
        f.setBackground(newColor);
    }

    public Color getNewColor() {
        return newColor;
    }

    public static void main(String args[]){
        new ColorChooser(Color.RED);
    }
}
