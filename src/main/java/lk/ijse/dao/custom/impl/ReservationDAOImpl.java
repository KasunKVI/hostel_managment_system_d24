package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.entity.Reservation;
import lk.ijse.entity.Student;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean add(Reservation entity) throws SQLException, ClassNotFoundException, IOException {
            
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean isExist(String id) throws SQLException, ClassNotFoundException, IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reservation reservation = session.find(Reservation.class, id);
        transaction.commit();
        session.close();

        return reservation != null;

    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException, IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT COUNT(*) FROM Reservation");
        int rowCount = ((Number) query.getSingleResult()).intValue();

        transaction.commit();
        session.close();

        return rowCount;

    }

    @Override
    public Reservation view(String id) throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public boolean remove(String id) throws SQLException, ClassNotFoundException, IOException {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//
//        Student student = new Student();
//        student.setStudent_id(id);
//        Reservation reservation = new Reservation();
//
//        reservation.setStudent(student);
//        session.remove(reservation);
//
//        transaction.commit();
//        session.close();
//        return true;
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("delete from Reservation where student_student_id='"+id+"'",Reservation.class).executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Reservation> getAll() throws SQLException, ClassNotFoundException, IOException {


        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<Reservation> query = session.createQuery("FROM Reservation WHERE status = 'Later'", Reservation.class);
        List<Reservation> reservations = query.getResultList();

        transaction.commit();
        session.close();

        return reservations;
    }

    @Override
    public boolean update(Reservation entity) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public int getThisMonthReservationCount() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // Get the current month and year
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        NativeQuery query = session.createNativeQuery("SELECT COUNT(*) FROM Reservation " +
                "WHERE MONTH(date) = :currentMonth  " +
                "AND YEAR(date) = :currentYear");

        query.setParameter("currentMonth", currentMonth);
        query.setParameter("currentYear", currentYear);

        int rowCount = ((Number) query.getSingleResult()).intValue();

        transaction.commit();
        session.close();

        return rowCount;
    }

    @Override
    public String getLatestResId() throws SQLException, ClassNotFoundException, IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<String> query = session.createNativeQuery("SELECT res_id FROM Reservation " +
                "ORDER BY res_id DESC LIMIT 1", String.class);

        String latestReservationId = query.uniqueResult();

        transaction.commit();
        session.close();

        return Objects.requireNonNullElse(latestReservationId, "R001");
    }

    @Override
    public List<String> getStudentId(String id) throws SQLException, ClassNotFoundException, IOException {

        List<String> studentIds = new ArrayList<>();

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT student_student_id FROM Reservation where room_room_type_id = :id");
        query.setParameter("id", id);
        List<Object> results = query.list();
        for (Object result : results) {
            if (result != null) {
                studentIds.add(result.toString());
            }
        }

        transaction.commit();
        session.close();

        return studentIds;
    }

    @Override
    public String getRoomId(String id) throws SQLException, ClassNotFoundException, IOException {

        System.out.println(id);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT room_room_type_id FROM Reservation WHERE student_student_id = :id");
        query.setParameter("id", id);
        List<Object> results = query.list();
        for (Object result : results) {
            if (result != null) {
               return result.toString();
            }
        }
       //String roomID = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        return null;
    }

    @Override
    public int getKeyMoneyLT() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<Long> query = session.createQuery("SELECT COUNT(r) FROM Reservation r WHERE r.status = 'Later'", Long.class);
        Long rowCount = query.getSingleResult();
        int count = rowCount.intValue(); // Convert the Long result to an int


        transaction.commit();
        session.close();

        return count;
    }

}
