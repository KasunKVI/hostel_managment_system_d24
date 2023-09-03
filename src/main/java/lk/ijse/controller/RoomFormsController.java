package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RoomFormsController {

    @FXML
    private AnchorPane content_pane;

    @FXML
    private Label lblDate;

    @FXML
    private AnchorPane main_full_pain;

    public void show_room_detail(MouseEvent mouseEvent) {
    }

    public void load1324(ActionEvent actionEvent) throws IOException {
        content_pane.getChildren().clear();
        content_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/RM-1324_rooms_d24.fxml")));
    }

    public void loadHomePage(ActionEvent actionEvent) throws IOException {
        main_full_pain.getChildren().clear();
        main_full_pain.getChildren().add(FXMLLoader.load(getClass().getResource("/view/main_dashboard_d24.fxml")));
    }

    public void load5467(ActionEvent actionEvent) throws IOException {
        content_pane.getChildren().clear();
        content_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/RM-5467_rooms_d24.fxml")));
    }

    public void load7896(ActionEvent actionEvent) throws IOException {
        content_pane.getChildren().clear();
        content_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/RM-7896_rooms_d24.fxml")));
    }

    public void load0093(ActionEvent actionEvent) throws IOException {
        content_pane.getChildren().clear();
        content_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/RM-0093_rooms_d24.fxml")));
    }
}
