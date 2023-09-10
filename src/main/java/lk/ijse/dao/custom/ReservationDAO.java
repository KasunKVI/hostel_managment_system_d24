package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Reservation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ReservationDAO extends CrudDAO<Reservation> {

    public int getThisMonthReservationCount() throws SQLException, ClassNotFoundException, IOException;

    public String getLatestResId() throws SQLException, ClassNotFoundException, IOException;

    public List<String> getStudentId(String id) throws SQLException, ClassNotFoundException, IOException;
    public String getRoomId(String id) throws SQLException, ClassNotFoundException, IOException;

    public int getKeyMoneyLT() throws SQLException, ClassNotFoundException, IOException;

}
