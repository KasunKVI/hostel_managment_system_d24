package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {

    public int getMAleStudentCount() throws SQLException, ClassNotFoundException, IOException;
    public int getFemaleStudentCount() throws SQLException, ClassNotFoundException, IOException;

}
