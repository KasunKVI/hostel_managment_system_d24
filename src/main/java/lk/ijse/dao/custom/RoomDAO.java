package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Room;

import java.io.IOException;
import java.sql.SQLException;

public interface RoomDAO extends CrudDAO<Room> {

    public int RM0093Count() throws SQLException, ClassNotFoundException, IOException;
    public int RM1324Count() throws SQLException, ClassNotFoundException, IOException;
    public int RM5467Count() throws SQLException, ClassNotFoundException, IOException;
    public int RM7896Count() throws SQLException, ClassNotFoundException, IOException;
    public int getKeyMoneyPaidCount() throws SQLException, ClassNotFoundException, IOException;
    public int getRoomResQty(String id) throws SQLException, ClassNotFoundException, IOException;
    public boolean removeRoom(Room room) throws SQLException,ClassNotFoundException,IOException;
}
