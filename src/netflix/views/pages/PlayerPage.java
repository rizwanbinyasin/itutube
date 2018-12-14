package netflix.views.pages;

import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import netflix.Main;
import netflix.models.Playable;
import netflix.models.media.Media;
import netflix.views.components.ActionButton;
import netflix.views.pages.content.FrontPage;

/**
 * Mock page for displaying a media.
 * Just displays the Media's color, for testing.
 */
public class PlayerPage extends StackPane {

    /**
     * @param media The media to play
     */
    public PlayerPage(Media media) {
        Text text = new Text();
        text.setText("Now Playing: " + media.getName());
        text.getStyleClass().add("playing-text");

        VBox textContainer = new VBox();
        if (!(media instanceof Playable)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error playing the media");
            alert.setContentText("The was an error playing the media. The media is not playable.");
            alert.showAndWait();
            return;
        } else {
            Playable playable = (Playable) media;
            textContainer.setStyle("-fx-background-color: " + playable.getMediaContent() + ";");
        }
        textContainer.getStyleClass().add("playing-text-container");
        textContainer.getChildren().add(text);

        ActionButton goBackButton = new ActionButton(
                "Go Back",
                "#deaa00",
                e -> Main.setPage(new FrontPage())
        );

        VBox content = new VBox(20);
        content.getChildren().add(textContainer);
        content.getChildren().add(goBackButton);

        this.getChildren().add(content);
    }
}