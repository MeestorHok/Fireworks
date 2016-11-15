import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Canvas extends JComponent {
    public Canvas() {
        super();
        setPreferredSize(new Dimension(850, 500));
    }

    @Override
    public void paintComponent(Graphics g) {

    }
}
