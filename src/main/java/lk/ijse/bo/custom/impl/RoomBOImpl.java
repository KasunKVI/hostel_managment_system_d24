package lk.ijse.bo.custom.impl;

import lk.ijse.bo.SuperBO;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.entity.Room;
import lk.ijse.util.DTOtoEntity;

import java.io.IOException;
import java.sql.SQLException;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    @Override
    public boolean addNewRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.add(DTOtoEntity.getInstance().toRoomEntity(roomDTO));
    }

    @Override
    public boolean isExistRoom(String id) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public int RM0093Count() throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.RM0093Count();
    }

    @Override
    public int RM1324Count() throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.RM1324Count();
    }

    @Override
    public int RM5467Count() throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.RM5467Count();
    }

    @Override
    public int RM7896Count() throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.RM7896Count();
    }

    @Override
    public int getKeyMoneyPendCount() throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.getCount();
    }

    @Override
    public int getKeyMoneyPaidCount() throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.getKeyMoneyPaidCount();
    }

    @Override
    public int getRoomResQty(String id) throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.getRoomResQty(id);
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.update(DTOtoEntity.getInstance().toRoomEntity(dto));
    }
}
