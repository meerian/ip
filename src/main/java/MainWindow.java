import java.util.Objects;
import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Button listButton;

    @FXML
    private ListView<String> taskList;

    private Duke duke;

    private final Image userImage = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.jpg")));
    private final Image dukeImage = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.jpeg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.startScreen();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    @FXML
    private void displayList() {
        taskList.getItems().clear();
        String list = duke.displayList();
        taskList.getItems().addAll(list);
    }

    private void startScreen() {
        dialogContainer.getChildren().add(DialogBox.getStartScreen(dukeImage));
    }
}
