package com.InternationalPassport.businessLayer.service;

import com.InternationalPassport.businessLayer.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findByFirstName(String firstName);
    Customer findByLogin(String login);
    Customer findByLoginInitAll(String login);
    Customer findByEmail(String email);
    Customer findById(Integer id);
    Customer findByIdInitAll(Integer id);
    List<Customer> findByRole(String role);
    Customer findByQuery(String query);
    List<Customer> findAll();
    void persist(Customer entity);
    void update(Customer entity);
    void delete(Customer entity);
}
