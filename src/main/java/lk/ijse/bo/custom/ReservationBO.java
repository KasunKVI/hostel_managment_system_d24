package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ReservationBO extends SuperBO {

    public boolean addNewReservation(ReservationDTO reservationDTO, String room_id, StudentDTO studentDTO, RoomDTO roomDTO) throws SQLException, ClassNotFoundException, IOException;

    public boolean isExistReserve(String id) throws SQLException, ClassNotFoundException, IOException;

    public int getReservationCount() throws SQLException, ClassNotFoundException, IOException;

    public int getThisMonthReservationCount() throws SQLException, ClassNotFoundException,IOException;

    public boolean removeStudentRes(String id) throws SQLException, ClassNotFoundException, IOException;
    public String getLatestResId() throws SQLException, ClassNotFoundException, IOException;
    public List<String> getStudentId(String id) throws SQLException, ClassNotFoundException, IOException;
    public String getRoomId(String id) throws SQLException, ClassNotFoundException, IOException;

    boolean addNewReservationWithRoom(ReservationDTO reservationDTO, RoomDTO roomDTO, String room_type_id);

    public List<ReservationDTO> getKeyMoneyRemains()throws SQLException, ClassNotFoundException, IOException;
    public int getKeyMoneyLt()throws SQLException, ClassNotFoundException, IOException;
}
