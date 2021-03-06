package itutube.views.components;

import itutube.models.ImageButtonInfo;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;

/**
 * Button with an image and a title
 */
public class ImageButton extends Button {

    /**
     * @param info Info object for the image button.
     */
    public ImageButton(ImageButtonInfo info) {
        super();
        // TODO: This line should be done in CSS
        this.setPrefSize(200, 250);
        this.setContentDisplay(ContentDisplay.TOP);
        this.getStyleClass().add("image-button");
        this.setOnAction(info.getEventHandler());
        this.setText(info.getText());
        if (info.getImage() != null) {
            ImageView imageView = new ImageView(info.getImage());
            double imgWidth = this.getPrefWidth();
            double imgHeight = this.getPrefHeight() - 60;
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(imgWidth);
            imageView.setFitHeight(imgHeight);
            this.setGraphic(imageView);
            this.setGraphicTextGap(15);
        }
    }
}
