package com.InternationalPassport.businessLayer.DAO;

import com.InternationalPassport.businessLayer.model.Passport;

import java.util.List;

public interface PassportDAO extends BaseDAO<Passport> {
    @Override
    Passport findById(Integer id);

    @Override
    Passport findByQuery(String query);

    @Override
    List<Passport> findAll();

    @Override
    void persist(Passport entity);

    @Override
    void update(Passport entity);

    @Override
    void delete(Passport entity);
}
