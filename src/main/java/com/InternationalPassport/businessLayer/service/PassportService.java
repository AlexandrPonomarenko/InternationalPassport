package com.InternationalPassport.businessLayer.service;

import com.InternationalPassport.businessLayer.model.Passport;

import java.util.List;

public interface PassportService {
    Passport findById(Integer id);
    Passport findByQuery(String query);
    List<Passport> findAll();
    void persist(Passport entity);
    void update(Passport entity);
    void delete(Passport entity);
}
