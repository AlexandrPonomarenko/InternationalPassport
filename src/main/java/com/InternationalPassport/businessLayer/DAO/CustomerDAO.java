package com.InternationalPassport.businessLayer.DAO;

import com.InternationalPassport.businessLayer.model.Customer;

import java.util.List;

public interface CustomerDAO extends BaseDAO<Customer>{
//    Customer findById(int id);
//    void persist(Customer c);
//    List<Customer> findAllCustimers();
//    void update(Customer customer);
//    void delete(Customer customer);


    Customer findByFirstName(String firstName);
    Customer findByLogin(String login);
    Customer findByEmail(String email);

    @Override
    Customer findById(Integer id);

    @Override
    Customer findByQuery(String query);

    @Override
    List<Customer> findAll();

    @Override
    void persist(Customer entity);

    @Override
    void update(Customer entity);

    @Override
    void delete(Customer entity);

}
