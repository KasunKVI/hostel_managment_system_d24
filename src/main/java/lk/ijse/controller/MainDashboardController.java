package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.SuperBO;
import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;
import org.hibernate.type.descriptor.DateTimeUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    private Button btnReservation;
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
    @FXML
    private ComboBox<Integer> cmbRoomNo;
    @FXML
    private ComboBox<String> cmbRoomType;
    @FXML
    private ComboBox<String> cmbStudentID;
    @FXML
    private ToggleGroup keyMoneyTime;
    @FXML
    private RadioButton rbtnLater;
    @FXML
    private RadioButton rbtnNow;
    @FXML
    private TextField txtRoomQty;

    public static int qty = 0;


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
        number = 3;
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

        if (number==0) {
            loadTimeAndDate();

        }
        if (number==0||number==3) {
            updateProgressBar();
            initializePieChart();

            getReservationCount();
        }
        if (number==4){
            initializeComboBoxStudent();
            initializeComboBoxRoomTypes();
        }

    }

    private void initializeComboBoxRoomTypes() {

        cmbRoomType.getItems().addAll("RM_1324", "RM_5467", "RM_7896", "RM_0093");

    }

    private void initializeComboBoxStudent() {

        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<StudentDTO> studentDTOS = studentBO.getAllStudents();

            for (StudentDTO studentDTO : studentDTOS){
                obList.add(studentDTO.getStudent_id());
            }

            cmbStudentID.setItems(obList);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getReservationCount() {
        try {
            lbl_total_reservations.setText(String.valueOf(reservationBO.getReservationCount()));
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializePieChart(){

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
            student_capacity.setProgress((double) count /158);

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

    public void loadAddReservationForm(ActionEvent actionEvent) {

        number=4;

        content_pane.setDisable(true);


        try {
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/addReservation.fxml"))));
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        stage.setOnHidden(event1 -> {
            content_pane.setDisable(false);
        });

        stage.show();
    }

    public void addReservation(ActionEvent event) {

        try {
            StudentRegistrationController.res_id=reservationBO.getLatestResId();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        StudentRegistrationController.generateResId();
        RadioButton selectedRadioButton = (RadioButton)keyMoneyTime.getSelectedToggle();
        String room_type_id = cmbRoomType.getSelectionModel().getSelectedItem()+cmbRoomNo.getSelectionModel().getSelectedItem();
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoom_type_id(room_type_id);
        roomDTO.setKey_money(selectedRadioButton.getText());
        roomDTO.setType(cmbRoomType.getSelectionModel().getSelectedItem());


        try {
            int qt = roomBO.getRoomResQty(room_type_id);

            if (qt==2){
                qty = 0;
            } else if (qt==1){
                qty =2;
            } else {
                qty = 1;
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        roomDTO.setQty(qty);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudent_id(cmbStudentID.getSelectionModel().getSelectedItem());

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setRes_id(StudentRegistrationController.res_id);
        reservationDTO.setDate(LocalDate.now());
        reservationDTO.setStatus(selectedRadioButton.getText());
        reservationDTO.setRoom(roomDTO);
        reservationDTO.setStudent(studentDTO);

        boolean b1 = reservationBO.addNewReservationWithRoom(reservationDTO,roomDTO,room_type_id);

        if(b1){
            new Alert(Alert.AlertType.CONFIRMATION, "Reservation Add Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Something Error Please Try Again").show();
        }
    }

    public void loadRoomNoBox(ActionEvent actionEvent) {



        String type = cmbRoomType.getSelectionModel().getSelectedItem();

        System.out.println(type);
        int i = 0;

        if (type.equals("RM_1324")){

            i=35;

        }else if (type.equals("RM_5467")){

            i=20;

        }else if (type.equals("RM_7896")){

            i=14;

        }else if (type.equals("RM_0093")){

            i=10;

        }

        ObservableList<Integer> ids = FXCollections.observableArrayList();
        for (int j = 0; j<i; j++){
            ids.add(j+1);
        }
        cmbRoomNo.setItems(ids);
    }

    public void changePassword(MouseEvent mouseEvent) {

        content_pane.setDisable(true);


        try {
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/passwordResetForm.fxml"))));
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        stage.setOnHidden(event1 -> {
            content_pane.setDisable(false);
        });

        stage.show();

    }
}
