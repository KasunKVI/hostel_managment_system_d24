package lk.ijse.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Reservation;
import lk.ijse.entity.Room;
import lk.ijse.util.DTOtoEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    private static int qty=0;

    @Override
    public boolean addNewReservation(ReservationDTO reservationDTO, String room_id,StudentDTO studentDTO, RoomDTO roomDTO ) throws SQLException, ClassNotFoundException, IOException {

        try {
            int qt = roomDAO.getRoomResQty(room_id);

            if (qt==2){
                qty = 0;
            } else if (qt==1){
                qty =2;
            } else {
                qty = 1;
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        boolean isRoomBook = false;
        boolean isStudentAdd = studentDAO.add(DTOtoEntity.getInstance().toStudentEntity(studentDTO));
        if (isStudentAdd) {
            if (qty == 0) {
                new Alert(Alert.AlertType.ERROR, "This Room Has Maximum Student Count").show();
            } else if (qty == 1) {
                isRoomBook = roomDAO.add(DTOtoEntity.getInstance().toRoomEntity(roomDTO));
            } else if (qty == 2) {
                isRoomBook = roomDAO.update(DTOtoEntity.getInstance().toRoomEntity(roomDTO));
            }
            Reservation reservation = DTOtoEntity.getInstance().toReservationEntity(reservationDTO);

            if (isRoomBook) {
                reservationDAO.add(reservation);
                return true;
            }
        }



        return false;
    }

    @Override
    public boolean isExistReserve(String id) throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.isExist(id);
    }

    @Override
    public int getReservationCount() throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.getCount();
    }

    @Override
    public int getThisMonthReservationCount() throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.getThisMonthReservationCount();
    }

    @Override
    public boolean removeStudentRes(String id) throws SQLException, ClassNotFoundException, IOException {


        boolean isUpdate=false;

        String room = reservationDAO.getRoomId(id);

        boolean isReservationRemove = reservationDAO.remove(id);


        if (isReservationRemove){

            boolean remove = studentDAO.remove(id);

            Room view = roomDAO.view(room);
            int qty = view.getQty();

            if (qty==2){
                view.setQty(1);
                 isUpdate= roomDAO.update(view);
            }else if (qty==1){
                isUpdate=roomDAO.remove(view.getRoom_type_id());
            }

            if (isUpdate) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getLatestResId() throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.getLatestResId();
    }

    @Override
    public List<String> getStudentId(String id) throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.getStudentId(id);
    }

    @Override
    public String getRoomId(String id) throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.getRoomId(id);
    }

    @Override
    public boolean addNewReservationWithRoom(ReservationDTO reservationDTO, RoomDTO roomDTO, String room_type_id) {

        try {
            int qt = roomDAO.getRoomResQty(room_type_id);

            if (qt==2){
                qty = 0;
            } else if (qt==1){
                qty =2;
            } else {
                qty = 1;
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        try {

            boolean b =false;

            if (qty ==0){
                new Alert(Alert.AlertType.ERROR, "This Room Has Maximum Student Count").show();
            }else if (qty==1){
                b = roomDAO.add(DTOtoEntity.getInstance().toRoomEntity(roomDTO));
            }else if (qty==2){
                b = roomDAO.add(DTOtoEntity.getInstance().toRoomEntity(roomDTO));
            }

            if (b){
                reservationDAO.add(DTOtoEntity.getInstance().toReservationEntity(reservationDTO));
                return true;
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public List<ReservationDTO> getKeyMoneyRemains() throws SQLException, ClassNotFoundException, IOException {

        List<Reservation> reservations = reservationDAO.getAll();

        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation reservation : reservations){
            reservationDTOS.add(DTOtoEntity.getInstance().toReservationDto(reservation));
        }
        return reservationDTOS;
    }

    @Override
    public int getKeyMoneyLt() throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.getKeyMoneyLT();
    }
}
