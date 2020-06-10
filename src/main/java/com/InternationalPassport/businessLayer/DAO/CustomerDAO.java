package com.InternationalPassport.businessLayer.DAO;

import com.InternationalPassport.businessLayer.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDAO extends BaseDAO<Customer> {
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByRole(String role);
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
