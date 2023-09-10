package lk.ijse.util;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FontChanger {


    public static void setTextColorRed(TextField txt){

        txt.setStyle("-fx-text-fill: red; -fx-font-family: Nakula ; -fx-font-size: 16px; -fx-background-color:  #dff9fb; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: red");

        // txt.getStyleClass().add("my-text-field-red");

    }

    public static void setTextBlack(TextField txt){

        txt.setStyle("-fx-text-fill: #2d3436; -fx-font-family: Nakula ; -fx-font-size: 16px; -fx-background-radius: 10; -fx-background-color:  #dff9fb; -fx-border-radius: 10;  -fx-border-color: #636e72;");

        //txt.getStyleClass().add("my-text-field-black");

    }
    public static void setTextColorRedLogIn(TextField txt){

        txt.setStyle("-fx-text-fill: red; -fx-font-family: 'Liberation Serif' ; -fx-font-size: 19px; -fx-background-color:   #95a5a6;; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: red");

        // txt.getStyleClass().add("my-text-field-red");

    }

    public static void setTextBlackLogIn(TextField txt){

        txt.setStyle("-fx-text-fill: #f5f6fa; -fx-font-family: 'Liberation Serif' ; -fx-font-size: 19px; -fx-background-radius: 10; -fx-background-color:   #95a5a6; -fx-border-radius: 10;  -fx-border-color: #95a5a6;");

        //txt.getStyleClass().add("my-text-field-black");

    }

    public static void setTextColorRedLogInPs(PasswordField txt){

        txt.setStyle("-fx-text-fill: red; -fx-font-family: 'Liberation Serif' ; -fx-font-size: 19px; -fx-background-color:   #95a5a6;; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: red");

        // txt.getStyleClass().add("my-text-field-red");

    }

    public static void setTextBlackLogInPs(PasswordField txt){

        txt.setStyle("-fx-text-fill: #f5f6fa; -fx-font-family: 'Liberation Serif' ; -fx-font-size: 19px; -fx-background-radius: 10; -fx-background-color:   #95a5a6; -fx-border-radius: 10;  -fx-border-color: #95a5a6;");

        //txt.getStyleClass().add("my-text-field-black");

    }

    public static void setSearchBarRed(TextField txt){
        txt.setStyle("-fx-text-fill: red; -fx-font-family:'DejaVu Serif'; -fx-font-weight: bold; -fx-font-size: 26px; -fx-background-radius: 15px;");
    }
    public static void setSearchBarBlack(TextField txt){
        txt.setStyle("-fx-text-fill: black; -fx-font-family:'DejaVu Serif'; -fx-font-weight: bold; -fx-font-size: 26px; -fx-background-radius: 15px;");
    }

    public static void setTextColorRedLogIns(TextField txt){

        txt.setStyle("-fx-text-fill: red; -fx-font-family: 'DejaVu Serif' ; -fx-font-size: 13px; -fx-background-color:  #2d3436; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: red");

        // txt.getStyleClass().add("my-text-field-red");

    }

    public static void setTextBlackLogIns(TextField txt){

        txt.setStyle("-fx-text-fill: #f5f6fa; -fx-font-family: 'DejaVu Serif' ; -fx-font-size: 13px; -fx-background-radius: 10; -fx-background-color: #2d3436; -fx-border-radius: 10;  -fx-border-color: #95a5a6;");

        //txt.getStyleClass().add("my-text-field-black");

    }
}


