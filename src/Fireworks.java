import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Fireworks extends JFrame {
    private Canvas canvas;

    private Fireworks() {
        setTitle("Fireworks Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        addElements();
        pack();
    }

    private void addElements() {
        canvas = new Canvas();
        add(canvas, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Fireworks fireworks = new Fireworks();
        fireworks.setVisible(true);
    }
}
