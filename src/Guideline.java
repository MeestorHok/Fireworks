import java.awt.Color;
import java.awt.Graphics;

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
        g.drawLine(x1, y1, x2, y2);
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
