package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.DAO.CustomerDAO;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public List<Customer> findByFirstName(String firstName) {
        return customerDAO.findByFirstName(firstName);
    }

    @Override
    public Customer findByLogin(String login) {
        return customerDAO.findByLogin(login);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerDAO.findByEmail(email);
    }

    @Override
    public Customer findById(Integer id) {
        return customerDAO.findById(id);
    }

    @Override
    public Customer findByQuery(String query) {
        return customerDAO.findByQuery(query);
    }

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public void persist(Customer entity) {
        customerDAO.persist(entity);
    }

    @Override
    public void update(Customer entity) {
        customerDAO.update(entity);
    }

    @Override
    public void delete(Customer entity) {
        customerDAO.delete(entity);
    }
}
