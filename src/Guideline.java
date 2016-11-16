import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Guideline {
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    private int angle;
    private int length;
    private Color color;

    public Guideline(int x, int y, int angle, int length, Color color) {
        this.x1 = x;
        this.y1 = y;
        this.angle = angle;
        this.length = length;
        this.color = color;

        updateEndpoints();
    }

    public void draw(Graphics g) {
        g.setColor(color);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(x1, y1, x2, y2);
    }

    public void updateEndpoints() {
        int xOffset = (int) Math.round(Math.cos(Math.toRadians(angle)) * length);
        int yOffset = (int) Math.round(Math.sin(Math.toRadians(angle)) * length);

        x2 = x1 + xOffset;
        y2 = y1 - yOffset;
    }

    public void setX(int x) {
        x1 = x;
        updateEndpoints();
    }

    public void setAngle(int angle) {
        this.angle = angle;
        updateEndpoints();
    }

    public void setLength(int length) {
        this.length = length;
        updateEndpoints();
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
