package com.InternationalPassport.businessLayer.service;

import com.InternationalPassport.businessLayer.model.Address;

import java.util.List;

public interface AddressService {
    Address findById(Integer id);
    Address findByQuery(String query);
    List<Address> findAll();
    void persist(Address entity);
    void update(Address entity);
    void delete(Address entity);
}
