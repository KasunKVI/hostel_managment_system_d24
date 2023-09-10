package lk.ijse.bo.custom.impl;

import lk.ijse.bo.SuperBO;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Reservation;
import lk.ijse.entity.Student;
import lk.ijse.util.DTOtoEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public boolean addNewStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.add(DTOtoEntity.getInstance().toStudentEntity(studentDTO));
    }

    @Override
    public int getStudentCount() throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.getCount();
    }

    @Override
    public int getMAleStudentCount() throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.getMAleStudentCount();
    }

    @Override
    public int getFemaleStudentCount() throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.getFemaleStudentCount();
    }

    @Override
    public StudentDTO viewStudent(String id) throws SQLException, ClassNotFoundException, IOException {        return DTOtoEntity.getInstance().toStudentDto(studentDAO.view(id));
    }

    @Override
    public boolean removeStudent(String id) throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.remove(id);
    }

    @Override
    public List<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException, IOException {
        List<Student> students = studentDAO.getAll();

        List<StudentDTO> studentDTOS = new ArrayList<>();

        for (Student student:students){
            studentDTOS.add(DTOtoEntity.getInstance().toStudentDto(student));
        }
        return studentDTOS;
    }
}
