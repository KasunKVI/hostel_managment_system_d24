package lk.ijse.bo.custom.impl;

import lk.ijse.bo.SuperBO;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;
import lk.ijse.util.DTOtoEntity;

import java.io.IOException;
import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean isExist(UserDTO userDTO) throws SQLException, ClassNotFoundException, IOException {
        User entity = new User();
        entity.setPassword(userDTO.getPassword());
        entity.setUser_name(userDTO.getUser_name());

        return userDAO.isExist(entity);
    }

    @Override
    public UserDTO getUser(String name) throws SQLException, ClassNotFoundException, IOException {
        return DTOtoEntity.getInstance().toUserDto(userDAO.getUser(name));
    }

    @Override
    public boolean updatePassword(UserDTO userDTO) throws SQLException, ClassNotFoundException, IOException {
        return userDAO.update(DTOtoEntity.getInstance().toUserEntity(userDTO));
    }
}
