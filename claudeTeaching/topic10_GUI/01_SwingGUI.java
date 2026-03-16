package topic10_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ============================================================================
 * TOPIC 10: GUI - Swing Framework
 * ============================================================================
 *
 * Swing is Java's GUI widget toolkit. It provides a rich set of components
 * for building desktop applications.
 *
 * KEY CONCEPTS:
 * 1. JFrame - Main window container
 * 2. JPanel - Container for organizing components
 * 3. Layout Managers - Arrange components (BorderLayout, FlowLayout, GridLayout)
 * 4. Event Handling - Respond to user actions
 * 5. Components - Buttons, Labels, TextFields, etc.
 *
 * SWING HIERARCHY:
 * Component
 * └── Container
 *     └── Window
 *         └── Frame (JFrame)
 *     └── Panel (JPanel)
 */

// ============================================================================
// BASIC SWING EXAMPLE
// ============================================================================
public class SwingBasics extends JFrame {

    public SwingBasics() {
        // Set up the frame
        setTitle("Swing Basics Demo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center on screen

        // Create components
        JLabel label = new JLabel("Welcome to Swing!");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton button = new JButton("Click Me!");
        button.addActionListener(e -> {
            label.setText("Button clicked!");
        });

        // Add components to frame
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        // Run on Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            new SwingBasics().setVisible(true);
        });
    }
}

// ============================================================================
// COMPREHENSIVE SWING DEMO
// ============================================================================
class ComprehensiveSwingDemo extends JFrame {

    private JTextField nameField;
    private JTextArea outputArea;
    private JComboBox<String> comboBox;
    private JCheckBox checkBox;
    private JRadioButton radio1, radio2;

    public ComprehensiveSwingDemo() {
        setTitle("Comprehensive Swing Demo");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create menu bar
        createMenuBar();

        // Create main panel with layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add form panel
        mainPanel.add(createFormPanel(), BorderLayout.NORTH);

        // Add output area
        mainPanel.add(createOutputPanel(), BorderLayout.CENTER);

        // Add button panel
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem exitItem = new JMenuItem("Exit");

        newItem.addActionListener(e -> showMessage("New file selected"));
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                "Swing Demo Application\nVersion 1.0",
                "About",
                JOptionPane.INFORMATION_MESSAGE);
        });
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("User Information"));

        // Name field
        panel.add(new JLabel("Name:"));
        nameField = new JTextField(20);
        panel.add(nameField);

        // Combo box
        panel.add(new JLabel("Favorite Color:"));
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        comboBox = new JComboBox<>(colors);
        panel.add(comboBox);

        // Checkbox
        checkBox = new JCheckBox("Subscribe to newsletter");
        panel.add(new JLabel(""));  // Empty label for spacing
        panel.add(checkBox);

        // Radio buttons
        panel.add(new JLabel("Experience:"));
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup group = new ButtonGroup();
        radio1 = new JRadioButton("Beginner");
        radio2 = new JRadioButton("Advanced");
        group.add(radio1);
        group.add(radio2);
        radio1.setSelected(true);
        radioPanel.add(radio1);
        radioPanel.add(radio2);
        panel.add(radioPanel);

        return panel;
    }

    private JScrollPane createOutputPanel() {
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));

        return scrollPane;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");
        JButton dialogButton = new JButton("Show Dialog");

        submitButton.addActionListener(e -> handleSubmit());
        clearButton.addActionListener(e -> handleClear());
        dialogButton.addActionListener(e -> showCustomDialog());

        panel.add(submitButton);
        panel.add(clearButton);
        panel.add(dialogButton);

        return panel;
    }

    private void handleSubmit() {
        String name = nameField.getText();
        if (name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a name!",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Submitted Information:\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Color: ").append(comboBox.getSelectedItem()).append("\n");
        sb.append("Newsletter: ").append(checkBox.isSelected() ? "Yes" : "No").append("\n");
        sb.append("Experience: ").append(radio1.isSelected() ? "Beginner" : "Advanced").append("\n");
        sb.append("------------------------\n");

        outputArea.append(sb.toString());
    }

    private void handleClear() {
        nameField.setText("");
        outputArea.setText("");
        comboBox.setSelectedIndex(0);
        checkBox.setSelected(false);
        radio1.setSelected(true);
    }

    private void showCustomDialog() {
        String input = JOptionPane.showInputDialog(this,
            "Enter your favorite programming language:",
            "Input Dialog",
            JOptionPane.QUESTION_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            showMessage("You entered: " + input);
        }
    }

    private void showMessage(String message) {
        outputArea.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ComprehensiveSwingDemo().setVisible(true);
        });
    }
}

// ============================================================================
// LAYOUT MANAGERS DEMO
// ============================================================================
class LayoutDemo extends JFrame {

    public LayoutDemo() {
        setTitle("Layout Managers Demo");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add different layout demos as tabs
        tabbedPane.addTab("FlowLayout", createFlowLayoutPanel());
        tabbedPane.addTab("BorderLayout", createBorderLayoutPanel());
        tabbedPane.addTab("GridLayout", createGridLayoutPanel());
        tabbedPane.addTab("BoxLayout", createBoxLayoutPanel());

        add(tabbedPane);
    }

    private JPanel createFlowLayoutPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("FlowLayout - Components flow left to right"));

        for (int i = 1; i <= 5; i++) {
            panel.add(new JButton("Button " + i));
        }

        return panel;
    }

    private JPanel createBorderLayoutPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("BorderLayout - NORTH, SOUTH, EAST, WEST, CENTER"));

        panel.add(new JButton("NORTH"), BorderLayout.NORTH);
        panel.add(new JButton("SOUTH"), BorderLayout.SOUTH);
        panel.add(new JButton("EAST"), BorderLayout.EAST);
        panel.add(new JButton("WEST"), BorderLayout.WEST);
        panel.add(new JButton("CENTER"), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createGridLayoutPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("GridLayout - 3x3 grid"));

        for (int i = 1; i <= 9; i++) {
            panel.add(new JButton("" + i));
        }

        return panel;
    }

    private JPanel createBoxLayoutPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("BoxLayout - Vertical arrangement"));

        for (int i = 1; i <= 5; i++) {
            JButton button = new JButton("Button " + i);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LayoutDemo().setVisible(true);
        });
    }
}

// ============================================================================
// EVENT HANDLING DEMO
// ============================================================================
class EventHandlingDemo extends JFrame {
    private JLabel statusLabel;

    public EventHandlingDemo() {
        setTitle("Event Handling Demo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        // Status label
        statusLabel = new JLabel("Event status will appear here", SwingConstants.CENTER);
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        panel.add(statusLabel, BorderLayout.SOUTH);

        // Center panel with interactive components
        JPanel centerPanel = new JPanel();

        // Button with ActionListener
        JButton button = new JButton("Click Me");
        button.addActionListener(e -> updateStatus("Button clicked!"));

        // Text field with multiple listeners
        JTextField textField = new JTextField(20);
        textField.addActionListener(e ->
            updateStatus("Text submitted: " + textField.getText()));

        // Mouse listener
        JPanel mousePanel = new JPanel();
        mousePanel.setPreferredSize(new Dimension(200, 100));
        mousePanel.setBackground(Color.CYAN);
        mousePanel.setBorder(BorderFactory.createTitledBorder("Mouse Area"));

        mousePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mousePanel.setBackground(Color.YELLOW);
                updateStatus("Mouse entered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mousePanel.setBackground(Color.CYAN);
                updateStatus("Mouse exited");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                updateStatus("Mouse clicked at (" + e.getX() + ", " + e.getY() + ")");
            }
        });

        // Key listener
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                updateStatus("Key pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
            }
        });

        centerPanel.add(new JLabel("Text Field:"));
        centerPanel.add(textField);
        centerPanel.add(button);
        centerPanel.add(mousePanel);

        panel.add(centerPanel, BorderLayout.CENTER);
        add(panel);
    }

    private void updateStatus(String message) {
        statusLabel.setText(message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EventHandlingDemo().setVisible(true);
        });
    }
}
