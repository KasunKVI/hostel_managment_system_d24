package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainDashboardController {

    @FXML
    private AnchorPane content_pane;

    @FXML
    private Label lblDate;

    @FXML
    private AnchorPane full_back_pane;

    @FXML
    private Button btnRoom;

    @FXML
    private AnchorPane back_pane;

    Stage stage= new Stage();

    public void loadStudentPage(ActionEvent actionEvent) throws IOException {

        content_pane.getChildren().clear();
        content_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/student_dashboard_d24.fxml")));
    }

    public void loadHomePage(ActionEvent actionEvent) throws IOException {
        content_pane.getChildren().clear();
        content_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/copy_home_page_d24.fxml")));
    }

    public void loadPaymentPage(ActionEvent actionEvent) throws IOException {
        content_pane.getChildren().clear();
        content_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/payment_dashboard_d24.fxml")));
    }

    public void load_rooms_form(MouseEvent mouseEvent) throws IOException {

        stage= (Stage) btnRoom.getScene().getWindow();
        stage.close();

        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/room_form_d24.fxml"))));
        stage.centerOnScreen();
        stage.show();
    }


}
