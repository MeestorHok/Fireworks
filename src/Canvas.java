import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

public class Canvas extends JComponent {
    public ArrayList<Firework> fireworks;
    public HashMap<Integer, Guideline> guides;

    public Canvas() {
        super();

        setPreferredSize(new Dimension(850, 500));
        fireworks = new ArrayList<Firework>();
        guides = new HashMap<Integer, Guideline>();
    }

    @Override
    public void paintComponent(Graphics g) {
        for(Guideline gl : guides.values()) {
            gl.draw(g);
        }
        for(Firework fw : fireworks) {
            fw.launch(g);
        }
    }
}
