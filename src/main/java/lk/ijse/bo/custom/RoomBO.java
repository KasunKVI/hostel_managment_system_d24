package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.RoomDTO;

import java.io.IOException;
import java.sql.SQLException;

public interface RoomBO extends SuperBO {

    public boolean addNewRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException, IOException;
    public boolean isExistRoom(String id) throws SQLException, ClassNotFoundException, IOException;
    public int RM0093Count() throws SQLException, ClassNotFoundException, IOException;
    public int RM1324Count() throws SQLException, ClassNotFoundException, IOException;
    public int RM5467Count() throws SQLException, ClassNotFoundException, IOException;
    public int RM7896Count() throws SQLException, ClassNotFoundException, IOException;
    public int getKeyMoneyPendCount() throws SQLException, ClassNotFoundException, IOException;
    public int getKeyMoneyPaidCount() throws SQLException, ClassNotFoundException, IOException;
    public int getRoomResQty(String id) throws SQLException, ClassNotFoundException, IOException;
    public boolean updateRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException, IOException;
}
