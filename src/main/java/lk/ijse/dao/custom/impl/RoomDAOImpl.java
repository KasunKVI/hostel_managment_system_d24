package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.entity.Room;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean add(Room entity) throws SQLException, ClassNotFoundException, IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);

        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean isExist(String id) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT COUNT(*) FROM Room where key_money = 'Later'");
        int rowCount = ((Number) query.getSingleResult()).intValue();

        transaction.commit();
        session.close();

        return rowCount;
    }

    @Override
    public Room view(String id) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        System.out.println(id);
        Room room = session.get(Room.class,id);
        transaction.commit();
        session.close();
        return room;
    }

    @Override
    public boolean remove(String id) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public List<Room> getAll() throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public boolean update(Room entity) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        session.close();
        return true;
    }


    @Override
    public int RM0093Count() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT COUNT(*) FROM Room where type = 'RM-0093'");
        int rowCount = ((Number) query.getSingleResult()).intValue();

        transaction.commit();
        session.close();

        return rowCount;
    }

    @Override
    public int RM1324Count() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT COUNT(*) FROM Room where type = 'RM-1324'");
        int rowCount = ((Number) query.getSingleResult()).intValue();

        transaction.commit();
        session.close();

        return rowCount;
    }

    @Override
    public int RM5467Count() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT COUNT(*) FROM Room where type = 'RM-5467'");
        int rowCount = ((Number) query.getSingleResult()).intValue();

        transaction.commit();
        session.close();

        return rowCount;
    }

    @Override
    public int RM7896Count() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT COUNT(*) FROM Room where type = 'RM-7896'");
        int rowCount = ((Number) query.getSingleResult()).intValue();

        transaction.commit();
        session.close();

        return rowCount;
    }

    @Override
    public int getKeyMoneyPaidCount() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT COUNT(*) FROM Room where key_money = 'Paid'");
        int rowCount = ((Number) query.getSingleResult()).intValue();

        transaction.commit();
        session.close();

        return rowCount;
    }

    @Override
    public int getRoomResQty(String id) throws SQLException, ClassNotFoundException, IOException {

        System.out.println(id);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT qty FROM Room where room_type_id = :id");
        query.setParameter("id", id);
        Integer rowCount = (Integer) query.uniqueResult();

        transaction.commit();
        session.close();

        return (rowCount != null) ? rowCount : -1;
    }

    @Override
    public boolean removeRoom(Room room) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(room);
        transaction.commit();
        session.close();
        return true;
    }
}
