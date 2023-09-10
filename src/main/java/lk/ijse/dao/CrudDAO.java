package lk.ijse.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO{

    public boolean add(T entity) throws SQLException, ClassNotFoundException, IOException;

    public boolean isExist(String id) throws SQLException, ClassNotFoundException, IOException;

    public int getCount() throws SQLException, ClassNotFoundException, IOException;

    public T  view(String id) throws  SQLException, ClassNotFoundException,IOException;

    public boolean remove(String id) throws SQLException, ClassNotFoundException, IOException;

    public List<T> getAll() throws SQLException, ClassNotFoundException, IOException;

    public boolean update(T entity) throws SQLException, ClassNotFoundException, IOException;

}
