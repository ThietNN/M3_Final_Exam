package DAO;

import java.util.List;

public interface IDAO<T>{
    List<T> findAll();
    T findById(int id);
    void save(T t );
    boolean delete (int id);
    boolean update (T t );
    T findByName (String name);
}
