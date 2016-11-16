import java.awt.Color;
import java.awt.Graphics;

public class Firework {
    private int startingX;
    private int startingY;
    private int startingAngle;
    private double initialVelocity;

    private int x;
    private int y;
    private int angle;
    private double velocity;

    private double delay;
    private String type;
    private Color color;

    private double time = 0;
    private int currentX, centerX;
    private int currentY, centerY;

    public Firework(int startingX, int startingY, int angle, int velocity, String type, double delay, Color color) {
        this.startingX = startingX;
        this.startingY = startingY;
        this.startingAngle = angle;
        this.initialVelocity = velocity / 3.0; // used to throttle input

        this.x = this.startingX;
        this.y = this.startingY;
        this.angle = this.startingAngle;
        this.velocity = this.initialVelocity;

        this.type = type;
        this.delay = delay;
        this.color = color;
    }

    // Draw the path of the
    public void launch(Graphics g) {
        // time it takes complete one parabola
        double t = (velocity * velocity) / (2 * 9.81);

        while (time < t / delay) {
            g.setColor(Color.DARK_GRAY);
            g.fillOval(x, y, 3, 3);
            nextX();
            nextY();
            time += 0.05;
        }

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
            case "Heart":
                heart(g);
                break;
            case "Fountain":
                fountain(g);
                break;
            case "Weighted":
                weighted(g);
                break;
            case "Bubbles":
                bubbles(g);
                break;
            default:
                break;
        }
    }

    // Parabolic equations for projectile motion
    private void nextX() {
        currentX = x;
        x += velocity * Math.cos(Math.toRadians(angle)) * time;
    }
    private void nextY() {
        currentY = y;
        y -= velocity * Math.sin(Math.toRadians(angle)) * time - (0.5 * 9.81 * time * time);
    }

    // resets to the location of the detonation
    private void reset() {
        // Reset x, y to the point of explosion
        // Reset time to 0
        x = centerX;
        y = centerY;
        time = 0;
    }

    // resets to square one
    private void finish() {
        x = startingX;
        y = startingY;
        angle = startingAngle;
        velocity = initialVelocity;
    }

    // Prepare detonation
    private void initialize(double v, int a) {
        currentX = centerX = x;
        currentY = centerY = y;
        angle = a; // angle to shoot projectiles
        velocity = v; // velocity of the projectiles
        time = 0; // reset timer
    }

    // Draw the actual explosion
    private void draw(int lastAngle, boolean useLines, double timeLoop,
                     double timeIncrement, int angleIncrement,
                     int beadSize, Graphics g) {

        // rotate through angles around explosion point
        while (angle < lastAngle) {
            while(time < timeLoop) {
                g.setColor(color);
                nextX();
                nextY();

                if (!(type.equals("Echo") || type.equals("Bubbles"))) {
                    if (useLines)
                        g.drawLine(currentX, currentY, x, y);
                    else
                        g.drawOval(x, y, beadSize, beadSize);
                }

                time += timeIncrement;
            }

            if (type.equals("Echo") || type.equals("Bubbles"))
                bloom(velocity, angle, g);

            reset();
            angle += angleIncrement;
        }

        finish();
    }

    // Detonates projectiles
    private void bloom(double oldVelocity, int oldAngle, Graphics g) {
        boolean linedType = type.equals("Echo");

        int currentX, tempX = x;
        int currentY, tempY = y;
        angle = 0;
        velocity = (linedType) ? 8 : 12;
        time = 0;

        while (this.angle < 360) {
            while(time < ((linedType) ? 1.45 : 1.5)) {
                g.setColor(color);

                currentX = x;
                currentY = y;
                nextX();
                nextY();

                if (linedType)
                    g.drawLine(currentX, currentY, x, y);
                else
                    g.drawOval(x, y, 3, 3);

                time += 0.1;
            }

            x = tempX;
            y = tempY;

            angle += (linedType) ? 30 : 10;
            time = 0;
        }

        // instance variables are used due to the call to nextX(), nextY()
        angle = oldAngle;
        velocity = oldVelocity;
    }

    // Simple circular explosion
    private void circle(Graphics g) {
        initialize(15, 0);
        draw(360, false, 1.3, 0.05, 5, 4, g);
    }

    private void echo(Graphics g) {
        initialize(10,0);
        draw(360, true, 1.8, 0.1, 36, 0, g);
    }

    private void heart(Graphics g) {}

    private void fountain(Graphics g) {
        initialize(10, 50);
        draw(130, true, 2.2, 0.05, 5, 0, g);
    }

    private void weighted(Graphics g) {
        initialize(14, 0);
        draw(360, true, 2, 0.1, 5, 0, g);
    }

    private void bubbles(Graphics g) {
        initialize(10, 0);
        draw(360, false, 1.5, 0.1, 60, 3, g);
    }
}
