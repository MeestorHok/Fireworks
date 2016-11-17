import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

public class Simulator extends JFrame {
    public Canvas canvas;

    private Simulator() {
        setTitle("Simulator Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        addElements();
        pack();
    }

    private void addElements() {
        canvas = new Canvas();

        add(canvas, BorderLayout.CENTER);
        add(new FireworksContainer(canvas), BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new Simulator().setVisible(true);
    }
}
