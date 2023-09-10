package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {

    public boolean isExist(User entity) throws SQLException, ClassNotFoundException, IOException;

    public User getUser(String name) throws SQLException, ClassNotFoundException, IOException;
}
