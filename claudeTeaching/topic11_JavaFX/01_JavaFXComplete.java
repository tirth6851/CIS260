package topic11_JavaFX;

/**
 * ============================================================================
 * TOPIC 11: JAVAFX - Complete Guide
 * ============================================================================
 *
 * JavaFX is Java's modern GUI framework, replacing Swing as the standard
 * for desktop application development.
 *
 * KEY CONCEPTS:
 * 1. Application - Entry point for JavaFX apps
 * 2. Stage - Top-level container (window)
 * 3. Scene - Container for scene graph
 * 4. Scene Graph - Hierarchy of nodes (UI components)
 * 5. FXML - XML-based UI layout (separate design from code)
 * 6. CSS Styling - Style applications with CSS
 * 7. Event Handling - Handle user interactions
 * 8. Observable Collections - Data binding
 *
 * JAVAFX HIERARCHY:
 * javafx.application.Application
 * javafx.stage.Stage
 * javafx.scene.Scene
 * javafx.scene.Node
 * ├── Control (Button, Label, TextField, etc.)
 * ├── Pane (layout containers)
 * └── Shape (graphics)
 *
 * NOTE: To run JavaFX, you need JavaFX library in your classpath.
 * Modern Java (11+) requires adding JavaFX as a dependency.
 */

// ============================================================================
// BASIC JAVAFX APPLICATION
// ============================================================================
/*
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BasicJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        Label label = new Label("Welcome to JavaFX!");
        Button button = new Button("Click Me!");

        // Set up event handling
        button.setOnAction(e -> {
            label.setText("Button clicked!");
        });

        // Create layout container
        VBox root = new VBox(10);  // 10px spacing
        root.getChildren().addAll(label, button);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Create scene
        Scene scene = new Scene(root, 300, 200);

        // Configure stage
        primaryStage.setTitle("Basic JavaFX App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/

// ============================================================================
// COMPREHENSIVE JAVAFX DEMO (Commented for compilation without JavaFX)
// ============================================================================
/*
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ComprehensiveJavaFX extends Application {

    private Label statusLabel;
    private TextArea outputArea;
    private TextField nameField;
    private ComboBox<String> comboBox;
    private CheckBox checkBox;
    private RadioButton radio1, radio2;
    private ListView<String> listView;
    private ProgressBar progressBar;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Comprehensive JavaFX Demo");

        // Create menu bar
        MenuBar menuBar = createMenuBar();

        // Create main layout
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(createMainContent());
        root.setBottom(createStatusBar());

        // Create scene with CSS styling
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        // File menu
        Menu fileMenu = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> System.exit(0));
        fileMenu.getItems().addAll(newItem, new SeparatorMenuItem(), exitItem);

        // Edit menu
        Menu editMenu = new Menu("Edit");
        MenuItem cutItem = new MenuItem("Cut");
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");
        editMenu.getItems().addAll(cutItem, copyItem, pasteItem);

        // Help menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(e -> showAboutDialog());
        helpMenu.getItems().add(aboutItem);

        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
        return menuBar;
    }

    private SplitPane createMainContent() {
        SplitPane splitPane = new SplitPane();

        // Left side - Form
        splitPane.getItems().add(createFormPanel());

        // Right side - Output
        splitPane.getItems().add(createOutputPanel());

        splitPane.setDividerPositions(0.4);
        return splitPane;
    }

    private VBox createFormPanel() {
        VBox formPanel = new VBox(10);
        formPanel.setPadding(new Insets(10));
        formPanel.setStyle("-fx-background-color: #f0f0f0;");

        // Title
        Label title = new Label("User Information");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Name field
        HBox nameBox = new HBox(10);
        nameBox.setAlignment(Pos.CENTER_LEFT);
        nameBox.getChildren().addAll(new Label("Name:"), nameField = new TextField());

        // Combo box
        HBox comboBox = new HBox(10);
        comboBox.setAlignment(Pos.CENTER_LEFT);
        this.comboBox = new ComboBox<>(FXCollections.observableArrayList(
            "Red", "Green", "Blue", "Yellow", "Purple"
        ));
        this.comboBox.setValue("Blue");
        comboBox.getChildren().addAll(new Label("Color:"), this.comboBox);

        // Check box
        checkBox = new CheckBox("Subscribe to newsletter");

        // Radio buttons
        HBox radioBox = new HBox(10);
        ToggleGroup group = new ToggleGroup();
        radio1 = new RadioButton("Beginner");
        radio2 = new RadioButton("Advanced");
        radio1.setToggleGroup(group);
        radio2.setToggleGroup(group);
        radio1.setSelected(true);
        radioBox.getChildren().addAll(new Label("Level:"), radio1, radio2);

        // List view
        Label listLabel = new Label("Interests:");
        listView = new ListView<>(FXCollections.observableArrayList(
            "Programming", "Design", "Music", "Sports", "Reading"
        ));
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setPrefHeight(100);

        // Progress bar
        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(Double.MAX_VALUE);

        // Buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        Button submitBtn = new Button("Submit");
        Button clearBtn = new Button("Clear");
        Button loadBtn = new Button("Load Data");

        submitBtn.setOnAction(e -> handleSubmit());
        clearBtn.setOnAction(e -> handleClear());
        loadBtn.setOnAction(e -> simulateLoading());

        buttonBox.getChildren().addAll(submitBtn, clearBtn, loadBtn);

        // Add all to form
        formPanel.getChildren().addAll(
            title, nameBox, comboBox, checkBox, radioBox,
            listLabel, listView, progressBar, buttonBox
        );

        return formPanel;
    }

    private VBox createOutputPanel() {
        VBox outputPanel = new VBox(10);
        outputPanel.setPadding(new Insets(10));

        Label title = new Label("Output");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        VBox.setVgrow(outputArea, Priority.ALWAYS);

        outputPanel.getChildren().addAll(title, outputArea);
        return outputPanel;
    }

    private HBox createStatusBar() {
        HBox statusBar = new HBox();
        statusBar.setPadding(new Insets(5, 10, 5, 10));
        statusBar.setStyle("-fx-background-color: #e0e0e0;");

        statusLabel = new Label("Ready");
        HBox.setHgrow(statusLabel, Priority.ALWAYS);

        statusBar.getChildren().add(statusLabel);
        return statusBar;
    }

    private void handleSubmit() {
        String name = nameField.getText();
        if (name.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a name!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Submitted Information:\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Color: ").append(comboBox.getValue()).append("\n");
        sb.append("Newsletter: ").append(checkBox.isSelected() ? "Yes" : "No").append("\n");
        sb.append("Level: ").append(radio1.isSelected() ? "Beginner" : "Advanced").append("\n");
        sb.append("Interests: ").append(listView.getSelectionModel().getSelectedItems()).append("\n");
        sb.append("------------------------\n");

        outputArea.appendText(sb.toString());
        updateStatus("Form submitted successfully");
    }

    private void handleClear() {
        nameField.clear();
        outputArea.clear();
        comboBox.setValue("Blue");
        checkBox.setSelected(false);
        radio1.setSelected(true);
        listView.getSelectionModel().clearSelection();
        progressBar.setProgress(0);
        updateStatus("Form cleared");
    }

    private void simulateLoading() {
        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                final int progress = i;
                javafx.application.Platform.runLater(() -> {
                    progressBar.setProgress(progress / 100.0);
                });
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            javafx.application.Platform.runLater(() -> {
                updateStatus("Loading complete!");
            });
        }).start();
    }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("JavaFX Demo Application");
        alert.setContentText("Version 1.0\nBuilt with JavaFX");
        alert.showAndWait();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateStatus(String message) {
        statusLabel.setText(message);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/

// ============================================================================
// JAVAFX NOTES AND CONCEPTS
// ============================================================================
class JavaFXNotes {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║     JAVAFX NOTES AND CONCEPTS                                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");

        System.out.println("--- Core Concepts ---\n");

        System.out.println("1. Application Lifecycle:");
        System.out.println("   • init() - Initialize resources");
        System.out.println("   • start(Stage) - Main entry point, create UI");
        System.out.println("   • stop() - Clean up resources");
        System.out.println("   • launch() - Static method to start application\n");

        System.out.println("2. Stage (Window):");
        System.out.println("   • Top-level container");
        System.out.println("   • Methods: setTitle(), setScene(), show(), close()");
        System.out.println("   • Can have multiple stages (windows)\n");

        System.out.println("3. Scene:");
        System.out.println("   • Container for scene graph");
        System.out.println("   • Constructor: Scene(rootNode, width, height)");
        System.out.println("   • Can switch scenes on same stage\n");

        System.out.println("4. Layout Containers:");
        System.out.println("   • BorderPane - Top, bottom, left, right, center");
        System.out.println("   • VBox/HBox - Vertical/horizontal arrangement");
        System.out.println("   • GridPane - Grid-based layout");
        System.out.println("   • StackPane - Overlapping components");
        System.out.println("   • FlowPane - Flows like text");
        System.out.println("   • AnchorPane - Anchor to edges\n");

        System.out.println("5. Common Controls:");
        System.out.println("   • Label - Display text");
        System.out.println("   • Button - Clickable action");
        System.out.println("   • TextField - Single-line text input");
        System.out.println("   • TextArea - Multi-line text");
        System.out.println("   • ComboBox - Dropdown selection");
        System.out.println("   • ListView - Scrollable list");
        System.out.println("   • CheckBox - Binary selection");
        System.out.println("   • RadioButton - Grouped selection");
        System.out.println("   • Slider - Numeric range");
        System.out.println("   • ProgressBar - Show progress");
        System.out.println("   • TableView - Tabular data\n");

        System.out.println("6. Event Handling:");
        System.out.println("   • Lambda: button.setOnAction(e -> handle())");
        System.out.println("   • Method reference: button.setOnAction(this::handle)");
        System.out.println("   • Anonymous class: new EventHandler<ActionEvent>() {...}");
        System.out.println("   • FXML + Controller class\n");

        System.out.println("7. Data Binding:");
        System.out.println("   • Bind property to another property");
        System.out.println("   • Automatic synchronization");
        System.out.println("   • Example: label.textProperty().bind(field.textProperty())\n");

        System.out.println("8. Observable Collections:");
        System.out.println("   • ObservableList - List with change notifications");
        System.out.println("   • FXCollections.observableArrayList()");
        System.out.println("   • Automatically updates UI when modified\n");

        System.out.println("9. CSS Styling:");
        System.out.println("   • Similar to web CSS");
        System.out.println("   • scene.getStylesheets().add('file.css')");
        System.out.println("   • node.setStyle('-fx-background-color: red;')\n");

        System.out.println("10. FXML:");
        System.out.println("    • XML-based UI declaration");
        System.out.println("    • Separates design from logic");
        System.out.println("    • Scene Builder - Visual editor");
        System.out.println("    • Controller class handles events\n");

        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║  JAVAFX vs SWING:                                                ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  JavaFX:                                                         ║");
        System.out.println("║    • Modern, hardware-accelerated graphics                       ║");
        System.out.println("║    • CSS styling support                                         ║");
        System.out.println("║    • FXML for declarative UI                                     ║");
        System.out.println("║    • Better separation of concerns                             ║");
        System.out.println("║    • Mobile and embedded support                                 ║");
        System.out.println("║                                                                  ║");
        System.out.println("║  Swing:                                                          ║");
        System.out.println("║    • Mature, stable API                                          ║");
        System.out.println("║    • Part of standard Java (pre-Java 11)                         ║");
        System.out.println("║    • More third-party components                                 ║");
        System.out.println("║    • Simpler for basic applications                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝");
    }
}
