package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public interface UserBO extends SuperBO {

    public boolean isExist(UserDTO userDTO) throws SQLException, ClassNotFoundException, IOException;

    public UserDTO getUser(String name) throws SQLException, ClassNotFoundException, IOException;

    public boolean updatePassword(UserDTO userDTO) throws SQLException,ClassNotFoundException,IOException;
}
