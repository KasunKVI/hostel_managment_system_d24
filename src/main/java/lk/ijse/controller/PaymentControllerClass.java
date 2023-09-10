package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RoomBO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PaymentControllerClass implements Initializable {

    @FXML
    private Button btnKeyMoney;
    @FXML
    private AnchorPane back_pane1;
    @FXML
    private BarChart<?, ?> chart_payment;
    @FXML
    private Label lbl_paid_month;
    @FXML
    private Label lbl_unpaid_month;

    private static int number = 0;

    private Stage stage = new Stage();


    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    public void load_keymoney_page(ActionEvent actionEvent) {

        number=1;

        back_pane1.setDisable(true);


        try {
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/key_money_pay_form_d24.fxml"))));
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setOnHidden(event1 -> {
            back_pane1.setDisable(false);
        });

        stage.show();
    }

    public void load_monthly_payment_page(ActionEvent actionEvent) {

        number=2;

        back_pane1.setDisable(true);


        try {
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/monthly_payment_add_form_d24.fxml"))));
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setOnHidden(event1 -> {
            back_pane1.setDisable(false);
        });

        stage.show();

    }

    private void loadKeyMoneyPayCounts() {

        try {
            lbl_unpaid_month.setText(String.valueOf(roomBO.getKeyMoneyPendCount()));
            lbl_paid_month.setText(String.valueOf(roomBO.getKeyMoneyPaidCount()));
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (number==0) {
            loadKeyMoneyPayCounts();
        }
    }
}
