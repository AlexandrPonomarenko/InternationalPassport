package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.DAO.CustomerDAO;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CustomerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private static final Logger loger = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public List<Customer> findByFirstName(String firstName) {
        List<Customer> customerList = customerDAO.findByFirstName(firstName);
        loger.debug("Service Customer findByFirstName -- " + customerList.size());
        return customerList;
    }

    @Override
    public Customer findByLogin(String login) {
        Customer customer = customerDAO.findByLogin(login);
        loger.debug("Service Customer findByLogin -- " + customer);
        return customer;
    }

    @Override
    public Customer findByEmail(String email) {
        Customer customer = customerDAO.findByEmail(email);
        loger.debug("Service Customer findByEmail -- " + customer);
        return customer;
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = customerDAO.findById(id);
        loger.debug("Service Customer findById -- " + customer);
        return customer;
    }

    @Override
    public Customer findByQuery(String query) {
//        Customer customer = customerDAO.findByQuery(query);
//        loger.debug("Service Customer findByQuery -- " + customer);
//        return customer;
        return null;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = customerDAO.findAll();
        loger.debug("Service Customer findAll -- " + customers.size());
        return customers;
    }

    @Override
    public void persist(Customer entity) {
        loger.debug("Service Customer persist -- " + entity);
        customerDAO.persist(entity);
    }

    @Override
    public void update(Customer entity) {
        loger.debug("Service Customer update -- " + entity);
        customerDAO.update(entity);
    }

    @Override
    public void delete(Customer entity) {
        loger.debug("Service Customer delete -- " + entity);
        customerDAO.delete(entity);
    }
}
