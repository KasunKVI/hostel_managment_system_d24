package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.Room;
import lk.ijse.entity.Student;
import lk.ijse.entity.User;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    @Override
    public boolean isExist(User entity) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT password, user_name FROM User WHERE password = :id AND user_name = :name");
        query.setParameter("id", entity.getPassword());
        query.setParameter("name", entity.getUser_name());

        // Execute the query and get the result as a List<Object[]>
        List<Object[]> resultList = query.list();

        transaction.commit();
        session.close();

        // Check if the result list is not empty
        if (!resultList.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public User getUser(String name) throws SQLException, ClassNotFoundException, IOException {


        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class,name);
        transaction.commit();
        session.close();
        if (user!=null){
            return user;
        }else {
            return new User();
        }
    }

    @Override
    public boolean add(User entity) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public boolean isExist(String id) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException, IOException {
        return 0;
    }

    @Override
    public User view(String id) throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public boolean remove(String id) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }
}
