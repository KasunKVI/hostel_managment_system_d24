package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.util.FontChanger;
import lk.ijse.util.regEx.Regex;
import lk.ijse.util.tm.KeyMoneyRemainTM;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class StudentRegistrationController implements Initializable {

    public Button btnremove;
    @FXML
    private Button btnPrc1;
    @FXML
    private AnchorPane back_pane;
    @FXML
    private AnchorPane back_pane1;
    @FXML
    private Button btn_emergency;
    @FXML
    private Button btn_key_money_list;
    @FXML
    private Label lbl_female_count;
    @FXML
    private Label lbl_male_count;
    @FXML
    private Label lbl_student_count_key_money;
    @FXML
    private Label lbl_students_this_month;
    @FXML
    private Label lbl_total_students;
    @FXML
    private VBox new_students_box;
    @FXML
    private RadioButton rbtnFemale;
    @FXML
    private RadioButton rbtnMale;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtContactNumber;
    @FXML
    private TextField txtEmailAddress;
    @FXML
    private TextField txtStudentName;
    @FXML
    private ComboBox<String> cmbRoomType;
    @FXML
    private ComboBox<String> cmbDuration;
    @FXML
    private ToggleGroup keyMoneyTime;
    @FXML
    private RadioButton rbtnLater;
    @FXML
    private RadioButton rbtnNow;
    @FXML
    private TextField txtDuration;
    @FXML
    private TextField txtS_Id;
    @FXML
    private TextField txtUniContact;
    @FXML
    private TextField txtUniName;
    @FXML
    private ToggleGroup Gendergroup;
    @FXML
    private TextField txtRoomNo;
    @FXML
    private TextField txtQty;
    @FXML
    private Button btnAdd;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblContact;
    @FXML
    private Label lblGender;
    @FXML
    private Label lblKeyMoney;
    @FXML
    private Label lblRoomNo;
    @FXML
    private Label lblRoomType;
    @FXML
    private Label lblSName;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblS_id;
    @FXML
    private Label lblUniName;
    @FXML
    private Label lblResId;
    @FXML
    private DatePicker dtpDob;
    @FXML
    private TextField txtSidSearch;
    @FXML
    private Label lblSAddressS;
    @FXML
    private Label lblSContactS;
    @FXML
    private Label lblSEmailS;
    @FXML
    private Label lblSNameS;
    @FXML
    private Label lblRoomId;
    @FXML
    private ComboBox<Integer> cmbRoomNo;
    @FXML
    private Button btnClose1;
    @FXML
    private Button btnBack1;
    @FXML
    private TableColumn<?, ?> clmResId;

    @FXML
    private TableColumn<?, ?> clmRoomId;

    @FXML
    private TableColumn<?, ?> clmStudentId;

    @FXML
    private TableView<KeyMoneyRemainTM> tblKeyMoney;


    public static String name="";
    public static String gender="";
    public static String contact="";
    public static String email="";
    public static String address="";
    public static String s_id="";
    public static String uni="";
    public static String uni_contact="";
    public static String duration="";
    public static String keyMoney="";
    public static String room_type="";
    public static String res_id="R001";
    public static String room_type_id="";
    public static Integer  roomNo=0;
    public static int qty = 0;
    public static LocalDate dob=LocalDate.of(2000, 1, 1);;

    private static int num = 0;

    final String username = "kkhettiarachchi10@gmail.com";
    final String password = "pivcvbeomsbvfzra";
    String smtpHost = "smtp.gmail.com";
    int smtpPort = 587;
    public boolean condition = true;

    Stage stage = new Stage();

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);

    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    public void load_second_form(ActionEvent actionEvent) throws IOException {

        num=2;

        RadioButton selectedRadioButton = (RadioButton)Gendergroup.getSelectedToggle();

        name = txtStudentName.getText();
        contact = txtContactNumber.getText();
        gender = selectedRadioButton.getText();
        email = txtEmailAddress.getText();
        address = txtAddress.getText();
        dob = dtpDob.getValue();

        back_pane.getChildren().clear();
        back_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/student_add_form2_d24.fxml")));

    }

    public void load_student_form_3(ActionEvent actionEvent) throws IOException {



        RadioButton selectedRadioButton = (RadioButton)keyMoneyTime.getSelectedToggle();

        s_id = txtS_Id.getText();
        uni = txtUniName.getText();
        uni_contact = txtUniContact.getText();
        room_type = cmbRoomType.getSelectionModel().getSelectedItem();
        duration = txtDuration.getText()+" : "+cmbDuration.getSelectionModel().getSelectedItem();
        keyMoney = selectedRadioButton.getText();
        room_type = cmbRoomType.getSelectionModel().getSelectedItem();
        roomNo = cmbRoomNo.getSelectionModel().getSelectedItem();
        room_type_id = room_type+roomNo;

        try {
            int qt = roomBO.getRoomResQty(room_type_id);

            if (qt==2){
                qty = 0;
            } else if (qt==1){
                qty =2;
            } else {
                qty = 1;
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        num=3;

        back_pane.getChildren().clear();
        back_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/student_add_form3_d24.fxml")));


    }
    public void load_student_add_form(ActionEvent actionEvent) {

        num = 1;

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

        num=4;

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

    public void show_key_money_remain_list(ActionEvent actionEvent) {

        num=5;

        back_pane1.setDisable(true);


        try {
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/keyMoneyRemainStudents.fxml"))));
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        stage.setOnHidden(event1 -> {
            back_pane1.setDisable(false);
        });

        stage.show();

    }

    public void load_emergency_alert(ActionEvent actionEvent) {
    }

    public void addStudentToDatabase(ActionEvent event) {

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudent_id(s_id);
        studentDTO.setName(name);
        studentDTO.setAddress(address);
        studentDTO.setDob(dob);
        studentDTO.setContact_no(contact);
        studentDTO.setGender(gender);
        studentDTO.setUniversity(uni);
        studentDTO.setEmail(email);

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoom_type_id(room_type_id);
        roomDTO.setKey_money(keyMoney);
        roomDTO.setQty(qty);
        roomDTO.setType(room_type);

        try {

            res_id = reservationBO.getLatestResId();
                generateResId();


            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setRes_id(res_id);
            reservationDTO.setDate(LocalDate.now());
            reservationDTO.setStatus("for : " + duration);
            reservationDTO.setRoom(roomDTO);
            reservationDTO.setStudent(studentDTO);

            studentDTO.getReservations().add(reservationDTO);
            roomDTO.getReservations().add(reservationDTO);


            boolean isReservationAdd = reservationBO.addNewReservation(reservationDTO,room_type_id,studentDTO,roomDTO);

                    if(isReservationAdd){

                        String htmlMessage = "<html><body><p style='font-weight: bold; color: #0070c0; font-size: 16px'>Hello "+name+",</p><br>"
                                + "<p>Thank You for choosing d24 hostel. You are warmly welcome to the hostel</p><br>"
                                + "<p style='font-weight: bold; color: #b2bec3; font-size: 16px'>Your Room Type is : " + room_type + "<br><br> Your Room No is : "+roomNo+"</p><br>"
                                + "<p>We're here to support you, so feel free to reach out if you have any questions or need further assistance.</p><br><br>"
                                + "<p style='font-weight: bold;'>Best regards,</p>"
                                + "<p>D24 Hostel Management System Support Team</p><br>"
                                + "</body></html>";


                        sentEmail(email,"Room Reservation in D24 Hostel",htmlMessage);

                        new Alert(Alert.AlertType.CONFIRMATION, "Reservation Add Successfully").show();

                        stage = (Stage) btnAdd.getScene().getWindow();
                        stage.close();

                    }else {
                        new Alert(Alert.AlertType.ERROR, "Something Error Please Try Again").show();
                    }

        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void backToSecondPage(ActionEvent event) {
        num=3;
        back_pane.getChildren().clear();
        try {
            back_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/student_add_form2_d24.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (num==1) {
        }
        if (num==2){
            initializeComboBoxes();
        }
        if (num==3){
            loadFormDetails();
        }
        if (num==0){
            loadStudentCount();
            loadThisMonthStudentCount();
            loadKeyMoneyPends();
            loadGendersCounts();
        }
        if (num==5){
            setCellValueFactory();
            loadTableData();
        }

    }

    private void loadTableData() {

        try {
            ObservableList<KeyMoneyRemainTM> obList = FXCollections.observableArrayList();
            List<ReservationDTO> reservationDTOS = reservationBO.getKeyMoneyRemains();

            for(ReservationDTO reservationDTO : reservationDTOS) {
                obList.add(new KeyMoneyRemainTM(
                        reservationDTO.getRes_id(),
                        reservationDTO.getRoom().getRoom_type_id(),
                        reservationDTO.getStudent().getStudent_id()

                ));
            }
            tblKeyMoney.setItems(obList);

        } catch (SQLException e) {

            e.printStackTrace();

            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeRoomNO() {

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

    private void loadGendersCounts() {

        try {
            lbl_male_count.setText(String.valueOf(studentBO.getMAleStudentCount()));
            lbl_female_count.setText(String.valueOf(studentBO.getFemaleStudentCount()));
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadKeyMoneyPends() {
        try {
            lbl_student_count_key_money.setText(String.valueOf(reservationBO.getKeyMoneyLt()));
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadThisMonthStudentCount() {

        try {
            lbl_students_this_month.setText(String.valueOf(reservationBO.getThisMonthReservationCount()));
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadStudentCount() {

        try {
            lbl_total_students.setText(String.valueOf(studentBO.getStudentCount()));
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadFormDetails() {

        lblSName.setText(name);
        lblS_id.setText(s_id);
        lblAddress.setText(address);
        lblGender.setText(gender);
        lblEmail.setText(email);
        lblContact.setText(contact);
        lblUniName.setText(uni);
        lblRoomType.setText(room_type);
        lblRoomNo.setText("15");
        lblKeyMoney.setText(keyMoney);
        System.out.println(name);
        System.out.println(s_id);
        System.out.println(address);

    }

    private void initializeComboBoxes() {

        cmbRoomType.getItems().addAll("RM_0093", "RM_1324", "RM_5467", "RM_7896");
        cmbDuration.getItems().addAll("Year","Month");
    }

    public static void generateResId() {

        String numericPart = res_id.substring(1); // Get "001"

        // Parse the numeric part as an integer, increment it, and format it back to a three-digit string
        int numericValue = Integer.parseInt(numericPart); // Convert "001" to 1

        System.out.println(numericPart);
        numericValue++; // Increment by one
        String incrementedNumericPart = String.format("%03d", numericValue); // Format as "002"

        // Combine the incremented numeric part with the "R" prefix
        res_id = "R" + incrementedNumericPart;

    }

    public void loadStudentDetails(ActionEvent actionEvent) {

        s_id = txtSidSearch.getText();
        try {

            StudentDTO studentDTO = studentBO.viewStudent(s_id);
            lblSNameS.setText(studentDTO.getName());
            lblSAddressS.setText(studentDTO.getAddress());
            lblSContactS.setText(studentDTO.getContact_no());
            lblSEmailS.setText(studentDTO.getEmail());
            lblRoomId.setText(reservationBO.getRoomId(s_id));

        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeStudent(ActionEvent actionEvent) {

        try {

           boolean isRemoved = reservationBO.removeStudentRes(s_id);

           if (isRemoved){
               new Alert(Alert.AlertType.CONFIRMATION, "Student Removed Successfully").show();
               stage = (Stage) btnremove.getScene().getWindow();
               stage.close();

           }else {
               new Alert(Alert.AlertType.ERROR, "Something Error Please Try Again").show();
           }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sentEmail(String email, String subject, String msg){

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setContent(msg ,"text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed to send email.");
        }

    }

    public void loadRoomNo(ActionEvent actionEvent) {
        initializeRoomNO();
    }

    @FXML
    public void CheckStudentAddress(KeyEvent event) {

        if (!txtAddress.getText().matches(Regex.addressRegEx())){
            condition=false;
            FontChanger.setTextColorRedLogIns(txtAddress);
        }else {
            condition=true;
            FontChanger.setTextBlackLogIns(txtAddress);
        }

    }

    @FXML
    public void checkStudentContact(KeyEvent event) {
        if (!txtContactNumber.getText().matches(Regex.contactRegEx())){
            condition=false;
            FontChanger.setTextColorRedLogIns(txtContactNumber);
        }else {
            condition=true;
            FontChanger.setTextBlackLogIns(txtContactNumber);
        }

    }

    @FXML
    public void checkStudentEmail(KeyEvent event) {
        if (!txtEmailAddress.getText().matches(Regex.emailRegEx())){
            condition=false;
            FontChanger.setTextColorRedLogIns(txtEmailAddress);
        }else {
            condition=true;
            FontChanger.setTextBlackLogIns(txtEmailAddress);
        }

    }

    @FXML
    public void checkStudentName(KeyEvent event) {
        if (!txtStudentName.getText().matches(Regex.nameRegEx())){
            condition=false;
            FontChanger.setTextColorRedLogIns(txtStudentName);
        }else {
            condition=true;
            FontChanger.setTextBlackLogIns(txtStudentName);
        }

    }

    public void closeStudentForm1(ActionEvent actionEvent) {

        stage = (Stage) btnClose1.getScene().getWindow();
        stage.close();

    }

    public void checkUniName(KeyEvent keyEvent) {
        if (!txtUniName.getText().matches(Regex.nameRegEx())){
            condition=false;
            FontChanger.setTextColorRedLogIns(txtUniName);
        }else {
            condition=true;
            FontChanger.setTextBlackLogIns(txtUniName);
        }
    }

    public void checkUniContact(KeyEvent keyEvent) {
        if (!txtUniContact.getText().matches(Regex.contactRegEx())){
            condition=false;
            FontChanger.setTextColorRedLogIns(txtUniContact);
        }else {
            condition=true;
            FontChanger.setTextBlackLogIns(txtUniContact);
        }
    }

    public void checkDuration(KeyEvent keyEvent) {
        if (!txtDuration.getText().matches(Regex.quantity())){
            condition=false;
            FontChanger.setTextColorRedLogIns(txtDuration);
        }else {
            condition=true;
            FontChanger.setTextBlackLogIns(txtDuration);
        }
    }

    public void backToFirstPage(ActionEvent actionEvent) throws IOException {

        num=1;
        back_pane.getChildren().clear();
        back_pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/student_add_form1_d24.fxml")));


    }
    private void setCellValueFactory() {

        clmResId.setCellValueFactory(new PropertyValueFactory<>("Reservation_id"));
        clmRoomId.setCellValueFactory(new PropertyValueFactory<>("Room_type_id"));
        clmStudentId.setCellValueFactory(new PropertyValueFactory<>("Student_id"));

    }
}
