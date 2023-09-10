package lk.ijse.util.regEx;


public class Regex {

    public static String nameRegEx(){
        return "^[A-Z][a-z]{2,}(\\\\s+[A-Z][a-z]{2,})*$";
    }

    public static String emailRegEx(){
        return "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,})+$";
    }

    public static String contactRegEx(){
        return "^0\\d{9}$";
    }

    public static String customerIdRegEx(){
        return "^C\\d{3}$";
    }

    public static String employeeIdRegEx(){
     return "^E\\d{3}$";
    }

    public static String productIdRegEx(){
        return "^P\\d{3}$";
    }

    public static String valueRegEx(){
        return "^(?!0)\\d*$";
    }

    public static String quantity(){return "^(?!0)\\d+$";}
    public static String productLeafValue(){
        return "^(?!0)\\d{3,}$";
    }
    public static String otp(){
        return "[0-9]{5}";
    }

    public static String addressRegEx(){
        return "^([A-Z][a-zA-Z0-9]*)(,\\s*[A-Z][a-zA-Z]+)+$";
    }

    public static String routeRegEx(){
        return "\\b[A-Z][a-zA-Z]*\\b";

    }

    public static String teaValueTrp(){
        return "^(?!0)\\d{3,}$";
    }

    public static String teaValueSup(){
        return "^(?!0)\\d{1,}$";
    }

    public static String unitPrice(){
        return "^(?!0)\\d{2,}$";
    }

    public static String userName(){
        return "^A00[1-4]$";
    }

    public static String user(){return "^[A-Za-z][A-Za-z]{4,}$";
    }

    public static String password(){return "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$\n";}

//    public static String otp(){return  "^\\d{5}$\n";}
}
