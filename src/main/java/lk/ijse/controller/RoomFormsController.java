package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dto.StudentDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RoomFormsController implements Initializable {

    @FXML
    private AnchorPane content_pane;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private AnchorPane main_full_pain;
    @FXML
    private Label RM_00931;
    @FXML
    private Label RM_009310;
    @FXML
    private Label RM_00932;
    @FXML
    private Label RM_00933;
    @FXML
    private Label RM_00934;
    @FXML
    private Label RM_00935;
    @FXML
    private Label RM_00936;
    @FXML
    private Label RM_00937;
    @FXML
    private Label RM_00938;
    @FXML
    private Label RM_00939;
    @FXML
    private Label RM_78961;
    @FXML
    private Label RM_789610;
    @FXML
    private Label RM_789611;
    @FXML
    private Label RM_789612;
    @FXML
    private Label RM_789613;
    @FXML
    private Label RM_789614;
    @FXML
    private Label RM_78962;
    @FXML
    private Label RM_78963;
    @FXML
    private Label RM_78964;
    @FXML
    private Label RM_78965;
    @FXML
    private Label RM_78966;
    @FXML
    private Label RM_78967;
    @FXML
    private Label RM_78968;
    @FXML
    private Label RM_78969;
    @FXML
    private Label RM_54671;
    @FXML
    private Label RM_546710;
    @FXML
    private Label RM_546711;
    @FXML
    private Label RM_546712;
    @FXML
    private Label RM_546713;
    @FXML
    private Label RM_546714;
    @FXML
    private Label RM_546715;
    @FXML
    private Label RM_546716;
    @FXML
    private Label RM_546717;
    @FXML
    private Label RM_546718;
    @FXML
    private Label RM_546719;
    @FXML
    private Label RM_54672;
    @FXML
    private Label RM_546720;
    @FXML
    private Label RM_54673;
    @FXML
    private Label RM_54674;
    @FXML
    private Label RM_54675;
    @FXML
    private Label RM_54676;
    @FXML
    private Label RM_54677;
    @FXML
    private Label RM_54678;
    @FXML
    private Label RM_54679;
    @FXML
    private Label RM_13241;
    @FXML
    private Label RM_132410;
    @FXML
    private Label RM_132411;
    @FXML
    private Label RM_132412;
    @FXML
    private Label RM_132413;
    @FXML
    private Label RM_132414;
    @FXML
    private Label RM_132415;
    @FXML
    private Label RM_132416;
    @FXML
    private Label RM_132417;
    @FXML
    private Label RM_132418;
    @FXML
    private Label RM_132419;
    @FXML
    private Label RM_13242;
    @FXML
    private Label RM_132420;
    @FXML
    private Label RM_132421;
    @FXML
    private Label RM_132422;
    @FXML
    private Label RM_132423;
    @FXML
    private Label RM_132424;
    @FXML
    private Label RM_132425;
    @FXML
    private Label RM_132426;
    @FXML
    private Label RM_132427;
    @FXML
    private Label RM_132428;
    @FXML
    private Label RM_132429;
    @FXML
    private Label RM_13243;
    @FXML
    private Label RM_132430;
    @FXML
    private Label RM_132431;
    @FXML
    private Label RM_132432;
    @FXML
    private Label RM_132433;
    @FXML
    private Label RM_132434;
    @FXML
    private Label RM_132435;
    @FXML
    private Label RM_13244;
    @FXML
    private Label RM_13245;
    @FXML
    private Label RM_13246;
    @FXML
    private Label RM_13247;
    @FXML
    private Label RM_13248;
    @FXML
    private Label RM_13249;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblContact;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblGender;
    @FXML
    private Label lblRoomNo;
    @FXML
    private Label lblRoomType;
    @FXML
    private Label lblStudentID;
    @FXML
    private Label lblStudentName;
    @FXML
    private Label lblUniversity;
    @FXML
    private Button btnOk;
    @FXML
    private ImageView imglogOut;
    private static String roomId ="";


    private static int number = 0;

    Stage stage = new Stage();

    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void show_room_detail(MouseEvent mouseEvent) {

        // Get the clicked label
        Label clickedLabel = (Label) mouseEvent.getSource();
        // Get the ID of the clicked label
        roomId = clickedLabel.getId();


        number=5;

        try {
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/student_detail_by_room_d24.fxml"))));
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.show();
    }

    public void load1324(ActionEvent actionEvent) throws IOException {
        number=1;
        content_pane.getChildren().clear();
        content_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/RM-1324_rooms_d24.fxml")));

    }

    public void loadHomePage(ActionEvent actionEvent) throws IOException {
        main_full_pain.getChildren().clear();
        main_full_pain.getChildren().add(FXMLLoader.load(getClass().getResource("/view/main_dashboard_d24.fxml")));
    }

    public void load5467(ActionEvent actionEvent) throws IOException {
        number=2;
        content_pane.getChildren().clear();
        content_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/RM-5467_rooms_d24.fxml")));

    }

    public void load7896(ActionEvent actionEvent) throws IOException {
        number=3;
        content_pane.getChildren().clear();
        content_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/RM-7896_rooms_d24.fxml")));

    }

    public void load0093(ActionEvent actionEvent) throws IOException {
        number=4;
        content_pane.getChildren().clear();
        content_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/RM-0093_rooms_d24.fxml")));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (number==0) {
            loadTimeAndDate();
            setRoomAvailability1324();
        }
        if(number==4){
            setRoomAvailability0093();
        }
        if (number==3){
            setRoomAvailability7896();
        }
        if (number==2){
            setRoomAvailability5467();
        }
        if (number==1){
            setRoomAvailability1324();
        }
        if (number==5){
            setStudentDetail();
        }
    }

    private void setStudentDetail() {

        try {

            List<String> s_id = reservationBO.getStudentId(roomId);

            int count = s_id.size();
            System.out.println(count);

            if (s_id.size()==2){

                ButtonType manage = new ButtonType("Student1", ButtonBar.ButtonData.YES);
                ButtonType bill = new ButtonType("Student2", ButtonBar.ButtonData.YES);

                Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "This room has two students. Select the student you want to see", manage, bill).showAndWait();

                if (result.orElse(null)==bill) {
                    StudentDTO studentDTO = studentBO.viewStudent(s_id.get(0));

                    //Get the first part (first 7 characters)
                    String roomType = roomId.substring(0, 7);

                    // Get the second part (remaining characters)
                    String roomNo = roomId.substring(7);

                    lblStudentID.setText(studentDTO.getStudent_id());
                    lblStudentName.setText(studentDTO.getName());
                    lblAddress.setText(studentDTO.getAddress());
                    lblContact.setText(studentDTO.getContact_no());
                    lblGender.setText(studentDTO.getGender());
                    lblEmail.setText(studentDTO.getEmail());
                    lblUniversity.setText(studentDTO.getUniversity());
                    lblRoomType.setText(roomType);
                    lblRoomNo.setText(roomNo);

                }else{


                    StudentDTO studentDTO = studentBO.viewStudent(s_id.get(1));

                    //Get the first part (first 7 characters)
                    String roomType = roomId.substring(0, 7);

                    // Get the second part (remaining characters)
                    String roomNo = roomId.substring(7);

                    lblStudentID.setText(studentDTO.getStudent_id());
                    lblStudentName.setText(studentDTO.getName());
                    lblAddress.setText(studentDTO.getAddress());
                    lblContact.setText(studentDTO.getContact_no());
                    lblGender.setText(studentDTO.getGender());
                    lblEmail.setText(studentDTO.getEmail());
                    lblUniversity.setText(studentDTO.getUniversity());
                    lblRoomType.setText(roomType);
                    lblRoomNo.setText(roomNo);
                }
            }else {
                StudentDTO studentDTO = studentBO.viewStudent(s_id.get(0));

                //Get the first part (first 7 characters)
                String roomType = roomId.substring(0, 7);

                // Get the second part (remaining characters)
                String roomNo = roomId.substring(7);

                lblStudentID.setText(studentDTO.getStudent_id());
                lblStudentName.setText(studentDTO.getName());
                lblAddress.setText(studentDTO.getAddress());
                lblContact.setText(studentDTO.getContact_no());
                lblGender.setText(studentDTO.getGender());
                lblEmail.setText(studentDTO.getEmail());
                lblUniversity.setText(studentDTO.getUniversity());
                lblRoomType.setText(roomType);
                lblRoomNo.setText(roomNo);
            }


        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void setRoomAvailability1324() {
        for (int i = 1; i <= 35; i++) {
            try {

                int qty = roomBO.getRoomResQty("RM_1324" + i);

                Label label = getLabelForRoom1324("RM_1324" + i);

                if (qty == 1) {
                    label.setDisable(false);
                    label.setStyle("-fx-background-color: yellow; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-border-color: black; -fx-text-fill: black");
                } else if (qty == 2) {
                    label.setDisable(false);
                    label.setStyle("-fx-background-color: red; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-border-color: white");
                }
            } catch (SQLException | ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setRoomAvailability5467() {
        for (int i = 1; i <= 20; i++) {
            try {

                int qty = roomBO.getRoomResQty("RM_5467" + i);

                Label label = getLabelForRoom5467("RM_5467" + i);

                if (qty == 1) {
                    label.setDisable(false);
                    label.setStyle("-fx-background-color: yellow; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-border-color: black; -fx-text-fill: black");
                } else if (qty == 2) {
                    label.setDisable(false);
                    label.setStyle("-fx-background-color: red; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-border-color: white");
                }
            } catch (SQLException | ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }



    private void setRoomAvailability7896() {
        for (int i = 1; i <= 14; i++) {
            try {

                int qty = roomBO.getRoomResQty("RM_7896" + i);

                Label label = getLabelForRoom7896("RM_7896" + i);

                if (qty == 1) {
                    label.setDisable(false);
                    label.setStyle("-fx-background-color: yellow; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-border-color: black; -fx-text-fill: black");
                } else if (qty == 2) {
                    label.setDisable(false);
                    label.setStyle("-fx-background-color: red; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-border-color: white");
                }
            } catch (SQLException | ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void setRoomAvailability0093() {

        for (int i = 1; i <= 10; i++) {

            try {

                int qty = roomBO.getRoomResQty("RM_0093" + i);

                Label label = getLabelForRoom0093("RM_0093" + i);

                if (qty == 1) {
                    label.setDisable(false);
                    label.setStyle("-fx-background-color: yellow; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-border-color: black; -fx-text-fill: black");
                } else if (qty == 2) {
                    label.setDisable(false);
                    label.setStyle("-fx-background-color: red; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-border-color: white");
                }

            } catch (SQLException | ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }

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
    private Label getLabelForRoom0093(String roomNumber) {


        switch (roomNumber) {
            case "RM_00931":
                return RM_00931;
            case "RM_00932":
                return RM_00932;
            case "RM_00933":
                return RM_00933;
            case "RM_00934":
                return RM_00934;
            case "RM_00935":
                return RM_00935;
            case "RM_00936":
                return RM_00936;
            case "RM_00937":
                return RM_00937;
            case "RM_00938":
                return RM_00938;
            case "RM_00939":
                return RM_00939;
            case "RM_009310":
                return RM_009310;
            default:
                throw new IllegalArgumentException("Invalid room number: " + roomNumber);
        }
    }
    private Label getLabelForRoom7896(String roomNumber) {

        switch (roomNumber) {
            case "RM_78961":
                return RM_78961;
            case "RM_78962":
                return RM_78962;
            case "RM_78963":
                return RM_78963;
            case "RM_78964":
                return RM_78964;
            case "RM_78965":
                return RM_78965;
            case "RM_78966":
                return RM_78966;
            case "RM_78967":
                return RM_78967;
            case "RM_78968":
                return RM_78968;
            case "RM_78969":
                return RM_78969;
            case "RM_789610":
                return RM_789610;
            case "RM_789611":
                return RM_789611;
            case "RM_789612":
                return RM_789612;
            case "RM_789613":
                return RM_789613;
            case "RM_789614":
                return RM_789614;
            default:
                throw new IllegalArgumentException("Invalid room number: " + roomNumber);
        }
    }

    private Label getLabelForRoom5467(String roomNumber) {
        switch (roomNumber) {
            case "RM_54671":
                return RM_54671;
            case "RM_54672":
                return RM_54672;
            case "RM_54673":
                return RM_54673;
            case "RM_54674":
                return RM_54674;
            case "RM_54675":
                return RM_54675;
            case "RM_54676":
                return RM_54676;
            case "RM_54677":
                return RM_54677;
            case "RM_54678":
                return RM_54678;
            case "RM_54679":
                return RM_54679;
            case "RM_546710":
                return RM_546710;
            case "RM_546711":
                return RM_546711;
            case "RM_546712":
                return RM_546712;
            case "RM_546713":
                return RM_546713;
            case "RM_546714":
                return RM_546714;
            case "RM_546715":
                return RM_546715;
            case "RM_546716":
                return RM_546716;
            case "RM_546717":
                return RM_546717;
            case "RM_546718":
                return RM_546718;
            case "RM_546719":
                return RM_546719;
            case "RM_546720":
                return RM_546720;
            default:
                throw new IllegalArgumentException("Invalid room number: " + roomNumber);
        }
    }
    private Label getLabelForRoom1324(String roomNumber) {
        switch (roomNumber) {
            case "RM_13241":
                return RM_13241;
            case "RM_13242":
                return RM_13242;
            case "RM_13243":
                return RM_13243;
            case "RM_13244":
                return RM_13244;
            case "RM_13245":
                return RM_13245;
            case "RM_13246":
                return RM_13246;
            case "RM_13247":
                return RM_13247;
            case "RM_13248":
                return RM_13248;
            case "RM_13249":
                return RM_13249;
            case "RM_132410":
                return RM_132410;
            case "RM_132411":
                return RM_132411;
            case "RM_132412":
                return RM_132412;
            case "RM_132413":
                return RM_132413;
            case "RM_132414":
                return RM_132414;
            case "RM_132415":
                return RM_132415;
            case "RM_132416":
                return RM_132416;
            case "RM_132417":
                return RM_132417;
            case "RM_132418":
                return RM_132418;
            case "RM_132419":
                return RM_132419;
            case "RM_132420":
                return RM_132420;
            case "RM_132421":
                return RM_132421;
            case "RM_132422":
                return RM_132422;
            case "RM_132423":
                return RM_132423;
            case "RM_132424":
                return RM_132424;
            case "RM_132425":
                return RM_132425;
            case "RM_132426":
                return RM_132426;
            case "RM_132427":
                return RM_132427;
            case "RM_132428":
                return RM_132428;
            case "RM_132429":
                return RM_132429;
            case "RM_132430":
                return RM_132430;
            case "RM_132431":
                return RM_132431;
            case "RM_132432":
                return RM_132432;
            case "RM_132433":
                return RM_132433;
            case "RM_132434":
                return RM_132434;
            case "RM_132435":
                return RM_132435;
            default:
                throw new IllegalArgumentException("Invalid room number: " + roomNumber);
        }
    }

    public void okBtnOnAction(ActionEvent actionEvent) {

        stage = (Stage) btnOk.getScene().getWindow();
        stage.close();

    }

    public void loadMainDashboard(MouseEvent mouseEvent) throws IOException {

        stage = (Stage) imglogOut.getScene().getWindow();
        stage.close();

        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_d24.fxml"))));
        stage.centerOnScreen();
        stage.show();
    }
}
