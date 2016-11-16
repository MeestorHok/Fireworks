import java.awt.Color;
import java.awt.Graphics;

public class Firework {
    private int startingX;
    private int angle;
    private int velocity;
    private String type;
    private double delay;
    private Color color;

    public Firework(int positionX, int angle, int velocity, String type, double delay, Color color) {
        this.startingX = positionX;
        this.angle = angle;
        this.velocity = velocity;
        this.type = type;
        this.delay = delay;
        this.color = color;
    }

    // Draw the path of the
    public void launch(Graphics g) {
        g.setColor(color);

        // TODO: draw path using angle, initial position, velocity, and delay
        g.drawLine(startingX, velocity, angle, (int) delay);

        // Create explosion
        explode(g);
    }

    // Decide which explosion to make
    private void explode(Graphics g) {
        switch(type) {
            case "Circle":
                circle(g);
                break;
            case "Echo":
                echo(g);
                break;
            case "Cross":
                cross(g);
                break;
            case "Fountain":
                fountain(g);
                break;
            case "Weighted":
                weighted(g);
                break;
            case "Disk":
                disk(g);
                break;
            default:
                break;
        }
    }

    private void circle(Graphics g) {}

    private void echo(Graphics g) {}

    private void cross(Graphics g) {}

    private void fountain(Graphics g) {}

    private void weighted(Graphics g) {}

    private void disk(Graphics g) {}
}
