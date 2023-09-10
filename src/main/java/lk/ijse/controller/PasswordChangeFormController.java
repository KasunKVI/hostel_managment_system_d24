package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDTO;
import lk.ijse.util.FontChanger;
import lk.ijse.util.regEx.Regex;

import java.io.IOException;
import java.sql.SQLException;

public class PasswordChangeFormController {

    @FXML
    private AnchorPane back_pane;

    @FXML
    private Button btnClose1;

    @FXML
    private Button btnReset;

    @FXML
    private TextField txtNewPass;

    @FXML
    private TextField txtNewPass1;

    @FXML
    private TextField txtOldPass;
    boolean condition = true;

    Stage stage = new Stage();
    UserDTO userDTO = new UserDTO();
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    public void isPasswordCorrect(ActionEvent actionEvent) {

        try {
             userDTO = userBO.getUser("admin");
            if (txtOldPass.getText().equals(userDTO.getPassword())){
                txtNewPass.requestFocus();
            }else {
                condition=false;
                new Alert(Alert.AlertType.ERROR, "Password Entered Incorrect. Please Try Again").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkPassOld(KeyEvent keyEvent) {

       // isPasswordCorrect( new ActionEvent());

    }

    public void checkPassNew(KeyEvent keyEvent) {

//        if (!txtNewPass.getText().matches(Regex.password())){
//            condition =false;
//            FontChanger.setTextColorRedLogIns(txtNewPass);
//        }else {
//            condition =true;
//            FontChanger.setTextBlackLogIns(txtNewPass);
//        }

    }

    public void addPass2focus(ActionEvent actionEvent) {
        txtNewPass1.requestFocus();
    }

    public void donePass(ActionEvent actionEvent) {
        resetPassword(actionEvent);
    }

    public void checkPassNew1(KeyEvent keyEvent) {

//        if (!txtNewPass1.getText().matches(Regex.password())){
//            condition =false;
//            FontChanger.setTextColorRedLogIns(txtNewPass1);
//        }else {
//            condition =true;
//            FontChanger.setTextBlackLogIns(txtNewPass1);
//        }

    }

    public void resetPassword(ActionEvent actionEvent) {



        if (condition && txtNewPass.getText().equals(txtNewPass1.getText())){

            System.out.println(userDTO.getUser_name());

            userDTO.setPassword(txtNewPass1.getText());

            try {
                boolean updatePass = userBO.updatePassword(userDTO);

                if (updatePass){
                    new Alert(Alert.AlertType.CONFIRMATION, "Password Changed Successfully").show();
                    stage = (Stage) btnReset.getScene().getWindow();
                    stage.close();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Something Error Please Try Again").show();
                }
            } catch (SQLException | ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Please enter valid details").show();
        }

    }

    public void closePasswordForm(ActionEvent actionEvent) {

       stage = (Stage) btnReset.getScene().getWindow();
       stage.close();

    }
}
