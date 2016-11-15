import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FireworkController extends JPanel implements ChangeListener, ActionListener {
    private int id;
    private FireworksContainer container;

    private static final Dimension PREF_SIZE = new Dimension(200, 150);
    private static final int MAX_POSITION = 500;
    private static final int MAX_ANGLE = 90;
    private static final int MAX_VELOCITY = 100;
    private static final String[] TYPES = { "Circle", "Echo", "Cross", "Fountain", "Weighted", "Disc" };

    private JSlider angleSlider;
    private JSlider posSlider;
    private JSlider velSlider;
    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;
    private JPanel colorBox;
    private JSlider delaySlider;
    private JComboBox<String> typeComboBox;
    private JButton launchButton;
    private JButton removeButton;

    private int positionX = 0;
    private int angle = MAX_ANGLE / 2;
    private int velocity = MAX_VELOCITY / 2;
    private int red = 200;
    private int green = 50;
    private int blue = 255;
    private double delay = 1.0;
    private String type = TYPES[0];

    public FireworkController(FireworksContainer container, int id) {
        this.id = id;
        this.container = container;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.WHITE);
        setBorder(new MatteBorder(10, 0, 0, 0, new Color(223, 223, 223)));

        addPositionControls();
        addColorControls();
        addMiscControls();
        addLaunchButton();
    }

    private void addPositionControls() {
        JPanel positionPanel = new JPanel();
        positionPanel.setBackground(Color.WHITE);
        positionPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        positionPanel.setPreferredSize(PREF_SIZE);
        positionPanel.setLayout(new BoxLayout(positionPanel, BoxLayout.Y_AXIS));

        JLabel posLabel = new JLabel("Position");
        posSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_POSITION, positionX);
        posSlider.addChangeListener(this);
        posSlider.setBackground(Color.WHITE);

        positionPanel.add(leftAlign(posLabel));
        positionPanel.add(posSlider);
        positionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel angleLabel = new JLabel("Angle");
        angleSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_ANGLE, angle);
        angleSlider.addChangeListener(this);
        angleSlider.setBackground(Color.WHITE);

        positionPanel.add(leftAlign(angleLabel));
        positionPanel.add(angleSlider);
        positionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel velLabel = new JLabel("Velocity");
        velSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_VELOCITY, velocity);
        velSlider.addChangeListener(this);
        velSlider.setBackground(Color.WHITE);

        positionPanel.add(leftAlign(velLabel));
        positionPanel.add(velSlider);

        add(positionPanel);

        add(Box.createVerticalStrut(10));
        add(new JSeparator(JSeparator.VERTICAL));
        add(Box.createVerticalStrut(10));
    }

    private void addColorControls() {
        JPanel colorPanel = new JPanel();
        colorPanel.setBackground(Color.WHITE);
        colorPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        colorPanel.setPreferredSize(PREF_SIZE);
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));

        JLabel colorLabel = new JLabel("Color");
        colorPanel.add(leftAlign(colorLabel));

        JPanel colorControlPanel = new JPanel();
        colorControlPanel.setBackground(Color.WHITE);
        colorControlPanel.setLayout(new BoxLayout(colorControlPanel, BoxLayout.X_AXIS));

        redSlider = new JSlider(JSlider.VERTICAL, 0, 255, red);
        redSlider.addChangeListener(this);
        redSlider.setBackground(Color.WHITE);
        JLabel redLabel = new JLabel("Red");

        colorControlPanel.add(redSlider);
        colorControlPanel.add(bottomAlign(redLabel));
        colorControlPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        greenSlider = new JSlider(JSlider.VERTICAL, 0, 255, green);
        greenSlider.addChangeListener(this);
        greenSlider.setBackground(Color.WHITE);
        JLabel greenLabel = new JLabel("Green");

        colorControlPanel.add(greenSlider);
        colorControlPanel.add(bottomAlign(greenLabel));
        colorControlPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        blueSlider = new JSlider(JSlider.VERTICAL, 0, 255, blue);
        blueSlider.addChangeListener(this);
        blueSlider.setBackground(Color.WHITE);
        JLabel blueLabel = new JLabel("Blue");

        colorControlPanel.add(blueSlider);
        colorControlPanel.add(bottomAlign(blueLabel));

        colorPanel.add(colorControlPanel);

        colorBox = new JPanel();
        colorBox.setBorder(new MatteBorder(10, 0, 0, 0, new Color(red, green, blue)));
        colorBox.setBackground(new Color(red, green, blue));

        colorPanel.add(colorBox);

        add(colorPanel);

        add(Box.createVerticalStrut(10));
        add(new JSeparator(JSeparator.VERTICAL));
        add(Box.createVerticalStrut(10));
    }

    private void addMiscControls() {
        JPanel miscPanel = new JPanel();
        miscPanel.setBackground(Color.WHITE);
        miscPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        miscPanel.setPreferredSize(PREF_SIZE);
        miscPanel.setLayout(new BoxLayout(miscPanel, BoxLayout.Y_AXIS));

        JLabel delayLabel = new JLabel("Delay");
        delaySlider = new JSlider(JSlider.HORIZONTAL, 0, 15, (int) (delay * 5));
        delaySlider.addChangeListener(this);
        delaySlider.setBackground(Color.WHITE);

        miscPanel.add(leftAlign(delayLabel));
        miscPanel.add(delaySlider);
        miscPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel typeLabel = new JLabel("Type");
        typeComboBox = new JComboBox<>(TYPES);
        typeComboBox.setActionCommand("type");
        typeComboBox.setSelectedIndex(0);
        typeComboBox.addActionListener(this);
        typeComboBox.setBackground(Color.WHITE);
        typeComboBox.setMaximumSize(new Dimension(200, 25));

        miscPanel.add(leftAlign(typeLabel));
        miscPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        miscPanel.add(typeComboBox);

        add(miscPanel);

        add(new JSeparator(JSeparator.VERTICAL));
    }

    private void addLaunchButton() {
        JPanel launchPanel = new JPanel();
        launchPanel.setBackground(Color.WHITE);
        launchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        launchPanel.setPreferredSize(PREF_SIZE);
        launchPanel.setLayout(new BoxLayout(launchPanel, BoxLayout.Y_AXIS));

        JLabel fireworkLabel = new JLabel("Firework " + (id + 1));
        fireworkLabel.setFont(new Font(fireworkLabel.getFont().getName(), Font.PLAIN, 24));

        launchPanel.add(fireworkLabel);
        launchPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        launchButton = new JButton("Launch");
        launchButton.setActionCommand("launch");
        launchButton.addActionListener(this);

        launchPanel.add(launchButton);
        launchPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        removeButton = new JButton("Delete");
        removeButton.setActionCommand("delete");
        removeButton.addActionListener(this);

        launchPanel.add(removeButton);

        add(launchPanel);
    }

    // Helper function to left align elements
    private Box leftAlign (JLabel label) {
        Box b = Box.createHorizontalBox();
        b.add(Box.createRigidArea(new Dimension(5, 0)));
        b.add(label);
        b.add(Box.createHorizontalGlue());
        return b;
    }

    // Helper function to bottom align elements
    private Box bottomAlign (JLabel label) {
        Box b = Box.createVerticalBox();
        b.add(Box.createVerticalGlue());
        b.add(label);
        b.add(Box.createRigidArea(new Dimension(0, 5)));
        return b;
    }

    void launch() {
        System.out.println(id + " launched!");
    }

    private void delete() {
        container.removeFirework(this, id);
    }

    private void updateColor() {
        colorBox.setBorder(new MatteBorder(10, 0, 0, 0, new Color(red, green, blue)));
        colorBox.setBackground(new Color(red, green, blue));
        colorBox.repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();

        if (source == posSlider) {
            positionX = source.getValue();
        } else if (source == angleSlider) {
            angle = source.getValue();
        } else if (source == velSlider) {
            velocity = source.getValue();
        } else if (source == redSlider) {
            red = source.getValue();
            updateColor();
        } else if (source == greenSlider) {
            green = source.getValue();
            updateColor();
        } else if (source == blueSlider) {
            blue = source.getValue();
            updateColor();
        } else if (source == delaySlider) {
            delay = source.getValue() / 5.0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("type")) {
            type = (String) typeComboBox.getSelectedItem();
        } else if (e.getActionCommand().equals("launch")) {
            launch();
        } else if (e.getActionCommand().equals("delete")) {
            delete();
        }
    }
}
