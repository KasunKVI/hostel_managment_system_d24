package lk.ijse.controller;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;
import lk.ijse.util.FontChanger;
import lk.ijse.util.regEx.Regex;

import java.io.IOException;
import java.sql.SQLException;

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
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;
    @FXML
    private CheckBox showPass;
    @FXML
    private TextField txtVisiblePass;

    @FXML
    private ImageView imgCorrect;
    private boolean passwordVisible = false;

    Stage primaryStage = new Stage();

    final String username = "kkhettiarachchi10@gmail.com";
    final String password = "pivcvbeomsbvfzra";
    String smtpHost = "smtp.gmail.com";
    int smtpPort = 587;

    private static String user_name="";
    private static String pass1="";
    private static String pass2="";
    private static String email="";
    private static int OTP=0;

    private static  int num = 0;
    UserDTO user = new UserDTO();

    public boolean isUserExist = false;
    public boolean condition = true;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void show_forget_password_page(MouseEvent event) throws IOException {

//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();

        log_in_page.setDisable(true);


        try {
            primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/forget_password_page_d24.fxml"))));
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

        primaryStage.show();

        // Add a listener to detect when the forget_password page is closed
        primaryStage.setOnHidden(event1 -> {
            log_in_page.setDisable(false); // Re-enable the login page
        });



    }

    @FXML
    void go_to_next_page(ActionEvent event) {

        num=0;
        if (headLabel.getText().equals("Please input your user name here")) {

           user_name = txtInput.getText();

            try {
                user = userBO.getUser(user_name);

                if (user.getUser_name()==null){
                    isUserExist=false;
                    new Alert(Alert.AlertType.ERROR, "User not exist").show();

                } else if (!condition) {
                    new Alert(Alert.AlertType.ERROR, "User name format not correct").show();
                } else {
                    isUserExist = true;

                    btn2.setText("Back");
                    headLabel.setText("Please input your email here");
                    txtInput.setText("");
                    txtInput.setPromptText("Email");
                    btn2.setStyle("-fx-background-color: #7f8fa6");
                    num = 1;
                }
            } catch (SQLException | ClassNotFoundException | IOException e) {

            }



        } else if ((headLabel.getText().equals("Please input your email here"))) {

           num=1;

            if (txtInput.getText().equals(user.getEmail())&&condition) {

                email=txtInput.getText();

                btn2.setText("Back");
                headLabel.setText("Please input the OTP send to your email");
                txtInput.setText("");
                txtInput.setPromptText("OTP");
                btn2.setStyle("-fx-background-color: #7f8fa6");



               OTP = generateOTP();

                String htmlMessage = "<html><body><p style='font-weight: bold; color: #0070c0; font-size: 16px'>Hello User,</p><br>"
                        + "<p>We understand that you're having trouble accessing your D24 Hostel Management System account. No worries, we're here to assist you! To get you back into your account, we've generated a one-time password (OTP).</p><br>"
                        + "<p style='font-weight: bold; color: #ff0000; font-size: 20px'>Your OTP is: " + OTP + "</p><br>"
                        + "<p>Please use this OTP to reset your password. If you didn't initiate this password reset request, please contact your supervisor or the system administrator immediately.</p><br>"
                        + "<p>We're here to support you, so feel free to reach out if you have any questions or need further assistance.</p><br><br>"
                        + "<p style='font-weight: bold;'>Best regards,</p>"
                        + "<p>D24 Hostel Management System Support Team</p><br>"
                        + "<p style='color: #777;'>Note: This OTP is confidential. Do not share it with anyone.</p></body></html>";


                sentEmail(email,"Verification OTP For Forget Your Password",htmlMessage);

                num=2;

            }else {
                new Alert(Alert.AlertType.ERROR, "Enter The Email Correctly").show();
            }


        } else if (headLabel.getText().equals("Please input the OTP send to your email")) {



            if (OTP==Integer.parseInt(txtInput.getText())&&condition){

                headLabel.setText("Please input your new password here");
                txtInput.setText("");
                txtInput.setPromptText("New Password");
                txtInput1.setVisible(true);
                btnNext.setText("Done");
                btnNext.setStyle("-fx-background-color: #4cd137;\n " + "-fx-text-fill: #2f3640;");
                num=3;
            }else {
                new Alert(Alert.AlertType.ERROR, "OTP Was Incorrect. Please Try Again").show();
            }



        }else{

            pass1=txtInput.getText();
            pass2=txtInput1.getText();


            if (pass1.equals(pass2) && condition){

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
            }else {
                new Alert(Alert.AlertType.ERROR, "Passwords Not Match. Please Try Again").show();
            }


        }

    }


    public void close_or_back(ActionEvent actionEvent) {

        if(headLabel.getText().equals("Please input your new password here")){

            btn2.setText("Back");
            headLabel.setText("Please input the OTP send to your email");
            txtInput.setPromptText("OTP");
            btn2.setStyle("-fx-background-color: #7f8fa6");
            btnNext.setStyle("-fx-background-color:  #00a8ff");
            txtInput1.setVisible(false);


        } else if (headLabel.getText().equals("Please input the OTP send to your email")) {
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

    public void login_to_the_system(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {


        UserDTO userDTO = new UserDTO();
        userDTO.setUser_name(txtUserName.getText());

        if (passwordVisible){
            userDTO.setPassword(txtVisiblePass.getText());
        }else {
            userDTO.setPassword(txtPassword.getText());
        }
        boolean isCorrect = userBO.isExist(userDTO);

        if (isCorrect && condition) {
            Stage stage = new Stage();
            primaryStage = (Stage) btn_logIn.getScene().getWindow();
            primaryStage.close();

            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/main_dashboard_d24.fxml"))));
            stage.centerOnScreen();
            stage.show();


        }else {
            new Alert(Alert.AlertType.ERROR, "Input Valid Details").show();
        }
    }

    public void showPassword(ActionEvent actionEvent) {

        if (showPass.isSelected()) {
            // If the checkbox is selected, show the text in a regular TextField
            passwordVisible = true;
            txtVisiblePass.setText(txtPassword.getText());
            txtVisiblePass.setManaged(true);
            txtVisiblePass.setVisible(true);
            txtPassword.setManaged(false);
            txtPassword.setVisible(false);
        } else {
            // If the checkbox is not selected, switch back to PasswordField
            passwordVisible = false;
            txtPassword.setText(txtVisiblePass.getText());
            txtPassword.setManaged(true);
            txtPassword.setVisible(true);
            txtVisiblePass.setManaged(false);
            txtVisiblePass.setVisible(false);
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

    public int generateOTP() {
        Random rand = new Random();
        int min = 10000; // Minimum 5-digit number
        int max = 99999; // Maximum 5-digit number
        return rand.nextInt((max - min) + 1) + min;
    }

    public void checkUserNameValidity(KeyEvent keyEvent) {

        if (num==0){
            if (!txtInput.getText().matches(Regex.user())){
                condition=false;
                FontChanger.setTextColorRed(txtInput);
            }else {
                condition=true;
                FontChanger.setTextBlack(txtInput);
            }
        }else if (num==1){
            if (!txtInput.getText().matches(Regex.emailRegEx())){
                condition=false;
                FontChanger.setTextColorRed(txtInput);
            }else {
                condition=true;
                FontChanger.setTextBlack(txtInput);
            }
        }else if (num==2){
            if (!txtInput.getText().matches(Regex.otp())){
                condition=false;
                FontChanger.setTextColorRed(txtInput);
            }else {
                condition=true;
                FontChanger.setTextBlack(txtInput);
            }
        }else if (num==3){
            if (!txtInput.getText().matches(Regex.password())){
                condition=false;
                FontChanger.setTextColorRed(txtInput);
            }else {
                condition=true;
                FontChanger.setTextBlack(txtInput);
            }
        }



    }

    public void checkPasswordValidity(KeyEvent keyEvent) {

        if (!txtInput1.getText().matches(Regex.password())){
            condition=false;
            FontChanger.setTextColorRed(txtInput1);
        }else {
            condition=true;
            FontChanger.setTextBlack(txtInput1);
        }

    }

    public void validateUserName(KeyEvent keyEvent) {

        if (!txtUserName.getText().matches(Regex.user())){
            condition=false;
            FontChanger.setTextColorRedLogIn(txtUserName);
        }else {
            condition=true;
            FontChanger.setTextBlackLogIn(txtUserName);
        }
    }

    public void validatePassword(KeyEvent keyEvent) {

//        if (!txtPassword.getText().matches(Regex.password())){
//            condition=false;
//            FontChanger.setTextColorRedLogInPs(txtPassword);
//        }else {
//            condition=true;
//            FontChanger.setTextBlackLogInPs(txtPassword);
//        }

    }

    public void enterPass(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void enteredPass(ActionEvent actionEvent) {
        try {
            login_to_the_system(actionEvent);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkPassValid(KeyEvent keyEvent) {

        if (!txtVisiblePass.getText().matches(Regex.password())){
            condition=false;
            FontChanger.setTextColorRedLogIn(txtVisiblePass);
        }else {
            condition=true;
            FontChanger.setTextBlackLogIn(txtVisiblePass);
        }
    }
}
