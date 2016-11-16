import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class FireworksContainer extends JPanel implements ActionListener {

    private FireworkController[] fireworks = new FireworkController[]{};
    public Canvas canvas;
    private ScrollPanel fireworksContainer;

    private JButton addButton;
    private JButton launchAllButton;
    private JButton clearButton;

    public FireworksContainer(Canvas canvas) {
        this.canvas = canvas;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new MatteBorder(10, 10, 10, 10, new Color(223, 223, 223)));
        addControls();
        addContainer();
    }

    private void addControls() {
        JPanel controlsPanel = new JPanel();
        controlsPanel.setBackground(Color.WHITE);
        controlsPanel.setLayout(new BorderLayout());
        controlsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        addButton = new JButton("+");
        addButton.addActionListener(this);
        controlsPanel.add(addButton, BorderLayout.WEST);

        Box b = Box.createHorizontalBox();

        clearButton = new JButton("Clear Canvas");
        clearButton.addActionListener(this);

        b.add(clearButton);
        b.add(Box.createRigidArea(new Dimension(5, 0)));

        launchAllButton = new JButton("Launch All");
        launchAllButton.addActionListener(this);

        b.add(launchAllButton);
        controlsPanel.add(b, BorderLayout.EAST);

        add(controlsPanel);
    }

    private void addContainer() {
        fireworksContainer = new ScrollPanel();

        JScrollPane scroller = new JScrollPane(fireworksContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setBorder(new MatteBorder(10, 0, 0, 0, new Color(223, 223, 223)));
        scroller.getViewport().setBackground(new Color(223, 223, 223));

        add(scroller);
    }

    private void addFirework() {
        // First, create a new firework controller
        FireworkController newFirework = new FireworkController(fireworks.length, canvas);

        // Then add it to the list of all
        FireworkController[] newFireworks = new FireworkController[fireworks.length + 1];

        for (int i = 0; i < fireworks.length; i++) {
            newFireworks[i] = fireworks[i];
        }
        newFireworks[fireworks.length] = newFirework;

        fireworks = newFireworks;

        // Then add it to the UI
        fireworksContainer.add(newFirework);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.pack();
    }

    void removeFirework(JPanel fw, int id) {
        // First, remove it from the list of all
        FireworkController[] newFireworks = new FireworkController[fireworks.length - 1];

        for (int i = 0; i < fireworks.length; i++) {
            if (i < id) {
                newFireworks[i] = fireworks[i];
            } else if (i > id) {
                newFireworks[i - 1] = fireworks[i];
            }
        }

        fireworks = newFireworks;

        // Update the guidelines as well
        HashMap<Integer, Guideline> newGuides = new HashMap<Integer, Guideline>();

        // Renumber (assign new IDs to) all the fireworks
        int i = 0;
        for (FireworkController fwc : fireworks) {
            newGuides.put(i, canvas.guides.get(fwc.id));
            fwc.setId(i);
            i++;
        }

        canvas.guides = newGuides;
        canvas.repaint();

        // Then remove it from UI
        fireworksContainer.remove(fw);
        fireworksContainer.repaint();

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.pack();
    }

    private void launchAll() {
        clearCanvas();

        for (FireworkController fw : fireworks) {
            fw.launch(false);
        }

        canvas.repaint();
    }

    private void clearCanvas() {
        canvas.fireworks = new ArrayList<Firework>(); // reset fireworks
        canvas.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source == addButton) {
            addFirework();
        } else if (source == launchAllButton) {
            launchAll();
        } else if (source == clearButton) {
            clearCanvas();
        }
    }

    // Inner class to allow implementation of Scrollable
    private class ScrollPanel extends JPanel implements Scrollable {
        ScrollPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }

        @Override
        public Dimension getPreferredScrollableViewportSize() {
            int minWidth = (int) Math.min(getPreferredSize().getWidth(), 800);
            int minHeight = (int) Math.min(getPreferredSize().getHeight(), 320);
            return new Dimension(minWidth, minHeight);
        }

        @Override
        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
            return 0;
        }

        @Override
        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
            return 0;
        }

        @Override
        public boolean getScrollableTracksViewportWidth() {
            return false;
        }

        @Override
        public boolean getScrollableTracksViewportHeight() {
            return false;
        }
    }
}
