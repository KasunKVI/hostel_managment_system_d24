package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.SuperBO;
import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.bo.custom.StudentBO;
import org.hibernate.type.descriptor.DateTimeUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainDashboardController implements Initializable {

    @FXML
    private AnchorPane content_pane;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private AnchorPane full_back_pane;
    @FXML
    private Button btnRoom;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private AnchorPane back_pane;
    @FXML
    private Label lbl_total_Income;
    @FXML
    private Label lbl_total_reservations;
    @FXML
    private PieChart rooms_received_with_types;
    @FXML
    private ProgressBar student_capacity;

    Stage stage= new Stage();
    private static int number = 0;


    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);

    public void loadStudentPage(ActionEvent actionEvent) throws IOException {
        number = 1;
        content_pane.getChildren().clear();
        content_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/student_dashboard_d24.fxml")));


    }

    public void loadHomePage(ActionEvent actionEvent) throws IOException {
        number = 0;
        content_pane.getChildren().clear();
        content_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/copy_home_page_d24.fxml")));
    }

    public void loadPaymentPage(ActionEvent actionEvent) throws IOException {
        number=2;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (number==1) {


        } else if (number==0) {
            updateProgressBar();
            initializePieChart();
            loadTimeAndDate();
            getReservationCount();
        }

    }

    private void getReservationCount() {
        try {
            lbl_total_reservations.setText(String.valueOf(reservationBO.getReservationCount()));
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializePieChart() {

        try {
            rooms_received_with_types.getData().add(new PieChart.Data("RM-0093",roomBO.RM0093Count()));
            rooms_received_with_types.getData().add(new PieChart.Data("RM-1324", roomBO.RM1324Count()));
            rooms_received_with_types.getData().add(new PieChart.Data("RM-7896",roomBO.RM7896Count()));
            rooms_received_with_types.getData().add(new PieChart.Data("RM-5467", roomBO.RM5467Count()));
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateProgressBar() {

        try {

            int count = studentBO.getStudentCount();
            student_capacity.setProgress((double) count /175);

        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadTimeAndDate() {

        if (number==0) {

            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            lblDate.setText(now.format(formatter));

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), event -> {
                        LocalTime currentTime = LocalTime.now();
                        String formattedTime = currentTime.format(timeFormatter);
                        lblTime.setText(formattedTime);
                    })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }

    }
}
