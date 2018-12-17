package itutube.views.pages;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import itutube.Database;
import itutube.Main;
import itutube.helpers.Images;
import itutube.models.ImageButtonInfo;
import itutube.models.User;
import itutube.views.components.CreateUserDialog;
import itutube.views.components.ImageButtonGrid;

import java.util.List;

import static itutube.helpers.ImageButtonInfoHelper.usersToImageButtonInfos;

/**
 * Page for selecting which user to use
 */
public class UserSelectPage extends ScrollPane {

    public UserSelectPage() {
        // User list
        List<ImageButtonInfo> imageButtonInfos = usersToImageButtonInfos(Database.getUsers());
        imageButtonInfos.add(new ImageButtonInfo("Add User", Images.getAddUserImage(), e -> {
            User newUser = CreateUserDialog.display();
            if (!newUser.getName().equals("") && newUser.getType() != null) {
                Database.addUser(newUser);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Can't create user");
                alert.setContentText("The user couldn't be added. Please try again with the correct info.");
                alert.showAndWait();
            }
            Main.setPage(new UserSelectPage());
        }));
        Parent usersView = new ImageButtonGrid("Select User", imageButtonInfos);
        usersView.getStyleClass().add("center");
        // Wrapper
        VBox wrapper = new VBox();
        wrapper.getStyleClass().add("user-select-wrapper");
        wrapper.getStyleClass().add("center");
        wrapper.getChildren().add(usersView);
        wrapper.setFillWidth(true);

        this.setFitToWidth(true);
        this.setContent(wrapper);
    }
}