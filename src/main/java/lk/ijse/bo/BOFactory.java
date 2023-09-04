package lk.ijse.bo;

import lk.ijse.bo.custom.impl.ReservationBOImpl;
import lk.ijse.bo.custom.impl.RoomBOImpl;
import lk.ijse.bo.custom.impl.StudentBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){
    }

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        STUDENT,ROOM,RESERVATION,USER
    }

    public SuperBO getBO(BOTypes types){

        switch (types){
            case RESERVATION:
                return new ReservationBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
