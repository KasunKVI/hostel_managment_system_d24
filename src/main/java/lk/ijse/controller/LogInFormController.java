package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LogInFormController {


    @FXML
    private Button btn_logIn;
    @FXML
    private AnchorPane log_in_page;

    @FXML
    private AnchorPane forget_password_page;

    @FXML
    private Button btn2;

    @FXML
    private Button btnNext;

    @FXML
    private Label headLabel;

    @FXML
    private TextField txtInput;

    @FXML
    private TextField txtInput1;

    @FXML
    private ImageView imgCorrect;

    Stage primaryStage = new Stage();

    @FXML
    void show_forget_password_page(MouseEvent event) throws IOException {

//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();

        log_in_page.setDisable(true);


        try {
            primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/forget_password_page_d24.fxml"))));
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Add a listener to detect when the forget_password page is closed
        primaryStage.setOnHidden(event1 -> {
            log_in_page.setDisable(false); // Re-enable the login page
        });

        primaryStage.show();

    }

    @FXML
    void go_to_next_page(ActionEvent event) {

        if (headLabel.getText().equals("Please input your user name here")) {

            btn2.setText("Back");
            headLabel.setText("Please input your email here");
            txtInput.setPromptText("Email");
            btn2.setStyle("-fx-background-color: #7f8fa6");

        } else if (headLabel.getText().equals("Please input your email here")) {

            headLabel.setText("Please input your new password here");
            txtInput.setPromptText("New Password");
            txtInput1.setVisible(true);
            btnNext.setText("Done");
            btnNext.setStyle("-fx-background-color: #4cd137;\n " + "-fx-text-fill: #2f3640;");

        }else{

            btnNext.setText("");
            btnNext.setVisible(false);
            Image image = new Image("icons8-correct.gif");
            imgCorrect.setImage(image);


            Duration delay = Duration.seconds(0.8);
            KeyFrame keyFrame = new KeyFrame(delay, events -> {

                primaryStage = (Stage) btnNext.getScene().getWindow();
                primaryStage.close();
            });
            Timeline timeline = new Timeline(keyFrame);
            timeline.play();
        }

    }


    public void close_or_back(ActionEvent actionEvent) {

        if(headLabel.getText().equals("Please input your new password here")){
            btn2.setText("Back");
            headLabel.setText("Please input your email here");
            txtInput.setPromptText("Email");
            btn2.setStyle("-fx-background-color: #7f8fa6");
            btnNext.setStyle("-fx-background-color:  #00a8ff");
            txtInput1.setVisible(false);

        } else if (headLabel.getText().equals("Please input your email here")) {

            btn2.setText("Close");
            headLabel.setText("Please input your user name here");
            txtInput.setPromptText("User Name");
            btn2.setStyle("-fx-background-color: #e84118");

        }else {
            primaryStage = (Stage) btnNext.getScene().getWindow();
            primaryStage.close();
        }
    }

    public void login_to_the_system(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        primaryStage = (Stage) btn_logIn.getScene().getWindow();
        primaryStage.close();

        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_d24.fxml"))));
        stage.centerOnScreen();
        stage.show();
    }
}
