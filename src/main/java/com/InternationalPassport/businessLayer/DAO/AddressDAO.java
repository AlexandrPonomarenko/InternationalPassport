package com.InternationalPassport.businessLayer.DAO;

import com.InternationalPassport.businessLayer.model.Address;

import java.util.List;

public interface AddressDAO extends BaseDAO<Address> {
    @Override
    Address findById(Integer id);

    @Override
    Address findByQuery(String query);

    @Override
    List<Address> findAll();

    @Override
    void persist(Address entity);

    @Override
    void update(Address entity);

    @Override
    void delete(Address entity);
}
