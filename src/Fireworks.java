import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

public class Fireworks extends JFrame {

    private Fireworks() {
        setTitle("Fireworks Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        addElements();
        pack();
    }

    private void addElements() {
        add(new Canvas(), BorderLayout.CENTER);
        add(new FireworksContainer(), BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        Fireworks fireworks = new Fireworks();
        fireworks.setVisible(true);
    }
}
