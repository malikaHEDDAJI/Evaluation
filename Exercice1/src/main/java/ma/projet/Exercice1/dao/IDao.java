package ma.projet.Exercice1.dao;

import java.util.List;

public interface IDao<T> {
    T save(T t);
    T update(T t);
    void delete(T t);
    T findById(int id);
    List<T> findAll();
}
