import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class FireworksContainer extends JPanel implements ActionListener {

    private FireworkController[] fireworks = new FireworkController[]{};
    private JPanel fireworksContainer;

    private JButton addButton;
    private JButton launchAllButton;

    public FireworksContainer() {
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

        launchAllButton = new JButton("Launch All");
        launchAllButton.addActionListener(this);
        controlsPanel.add(launchAllButton, BorderLayout.EAST);

        add(controlsPanel);
    }

    private void addContainer() {
        fireworksContainer = new JPanel();
        fireworksContainer.setLayout(new BoxLayout(fireworksContainer, BoxLayout.Y_AXIS));

        add(fireworksContainer);
    }

    private void addFirework() {
        FireworkController newFirework = new FireworkController(this, fireworks.length);

        FireworkController[] newFireworks = new FireworkController[fireworks.length + 1];

        for (int i = 0; i < fireworks.length; i++) {
            newFireworks[i] = fireworks[i];
        }
        newFireworks[fireworks.length] = newFirework;

        fireworks = newFireworks;

        fireworksContainer.add(newFirework);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.pack();
    }

    void removeFirework(JPanel fw, int id) {
        FireworkController[] newFireworks = new FireworkController[fireworks.length - 1];

        for (int i = 0; i < fireworks.length; i++) {
            if (i < id) {
                newFireworks[i] = fireworks[i];
            } else if (i > id) {
                newFireworks[i - 1] = fireworks[i];
            }
        }

        fireworks = newFireworks;

        fireworksContainer.remove(fw);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.pack();
    }

    private void launchAll() {
        for (FireworkController fw : fireworks) {
            fw.launch();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source == addButton) {
            addFirework();
        } else if (source == launchAllButton) {
            launchAll();
        }
    }
}
