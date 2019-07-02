package com.InternationalPassport.businessLayer.DAO;

import java.util.List;

public interface BaseDAO <T> {
    T findById(Integer id);
    T findByQuery(String query);
    List<T> findAll();
    void persist(T entity);
    void update(T entity);
    void delete(T entity);


}
