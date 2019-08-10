package com.InternationalPassport.businessLayer.service;

import com.InternationalPassport.businessLayer.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findByFirstName(String firstName);
    Customer findByLogin(String login);
    Customer findByEmail(String email);
    Customer findById(Integer id);
    Customer findByQuery(String query);
    List<Customer> findAll();
    void persist(Customer entity);
    void update(Customer entity);
    void delete(Customer entity);
}
