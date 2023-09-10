package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {

    public boolean addNewStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException, IOException;
    public int getStudentCount() throws SQLException, ClassNotFoundException, IOException;
    public int getMAleStudentCount() throws SQLException, ClassNotFoundException, IOException;
    public int getFemaleStudentCount() throws SQLException, ClassNotFoundException, IOException;
    public StudentDTO viewStudent(String id) throws SQLException, ClassNotFoundException,IOException;
    public boolean removeStudent(String id) throws SQLException, ClassNotFoundException, IOException;
    public List<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException, IOException;
}
