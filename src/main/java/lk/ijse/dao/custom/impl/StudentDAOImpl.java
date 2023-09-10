package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Reservation;
import lk.ijse.entity.Student;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student entity) throws SQLException, ClassNotFoundException, IOException {

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

        NativeQuery query = session.createNativeQuery("SELECT COUNT(*) FROM Student");
        int rowCount = ((Number) query.getSingleResult()).intValue();

        transaction.commit();
        session.close();

        return rowCount;
    }

    @Override
    public Student view(String id) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Student student=session.get(Student.class,id);
        session.close();
        return student;
    }

    @Override
    public boolean remove(String id) throws SQLException, ClassNotFoundException, IOException {

//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//
//        Student student = new Student();
//        student.setStudent_id(id);
//
//        session.remove(student);
//
//        transaction.commit();
//        session.close();
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("delete from Student where student_id='"+id+"'", Reservation.class).executeUpdate();

        transaction.commit();
        session.close();
        return true;


    }

    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Student> list = session.createNativeQuery("SELECT * FROM Student", Student.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public boolean update(Student entity) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public int getMAleStudentCount() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT COUNT(*) FROM Student where gender = 'Male'");
        int rowCount = ((Number) query.getSingleResult()).intValue();

        transaction.commit();
        session.close();

        return rowCount;
    }

    @Override
    public int getFemaleStudentCount() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("SELECT COUNT(*) FROM Student where gender = 'Female'");
        int rowCount = ((Number) query.getSingleResult()).intValue();

        transaction.commit();
        session.close();

        return rowCount;
    }

}
