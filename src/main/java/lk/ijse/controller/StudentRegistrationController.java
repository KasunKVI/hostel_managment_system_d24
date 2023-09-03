package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentRegistrationController {

    @FXML
    private Button btnPrc1;

    @FXML
    private AnchorPane back_pane;
    @FXML
    private AnchorPane back_pane1;

    Stage stage = new Stage();
    public void load_second_form(ActionEvent actionEvent) throws IOException {

        back_pane.getChildren().clear();
        back_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/student_add_form2_d24.fxml")));

    }

    public void load_student_form_3(ActionEvent actionEvent) throws IOException {

        back_pane.getChildren().clear();
        back_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/student_add_form3_d24.fxml")));
    }
    public void load_student_add_form(ActionEvent actionEvent) {

        back_pane1.setDisable(true);


        try {
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/student_add_form1_d24.fxml"))));
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        stage.setOnHidden(event1 -> {
            back_pane1.setDisable(false);
        });

        stage.show();
    }

    public void load_remove_student_form(ActionEvent actionEvent) {

        back_pane1.setDisable(true);


        try {
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/student_remove_form_d24.fxml"))));
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        stage.setOnHidden(event1 -> {
            back_pane1.setDisable(false);
        });

        stage.show();

    }
}
