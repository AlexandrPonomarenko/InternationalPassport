package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.DAO.CustomerDAO;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private static final Logger loger = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public List<Customer> findByFirstName(String firstName) {
        List<Customer> customerList = null;
        try {
            customerList = customerDAO.findByFirstName(firstName);
            loger.debug("Service Customer findByFirstName -- " + customerList.size());
        } catch (DataAccessException e ) {
            loger.error("Was error from findByFirstName " + e.getMessage());
        }

        return customerList;
    }

    @Override
    public List<Customer> findByRole(String role) {
        List<Customer> customerList = null;
        try {
            customerList = customerDAO.findByRole(role);
            loger.debug("Service Customer findByRole -- " + customerList.size());
        } catch (DataAccessException e ) {
            loger.error("Was error from findByRole " + e.getMessage());
        }
         return customerList;
    }

    @Override
    public Customer findByLogin(String login) {
        Customer customer = null;
        try{
            customer = customerDAO.findByLogin(login);
            loger.debug("Service Customer findByLogin -- " + customer);
        } catch (DataAccessException e) {
            loger.error("Was error from findByLogin " + e.getMessage());
        }

        return customer;
    }

    @Override
    public Customer findByEmail(String email) {
        Customer customer = null;
        try {
            customer = customerDAO.findByEmail(email);
            loger.debug("Service Customer findByEmail -- " + customer);
        } catch (DataAccessException e){
            loger.error("Was error from findByLogin " + e.getMessage());
        }

        return customer;
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = null;
        try {
            customer = customerDAO.findById(id);
            loger.debug("Service Customer findById -- " + customer);
        } catch (DataAccessException e) {
            loger.error("Error was in findById " + e.getMessage());
        }

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
        List<Customer> customers = null;
        try {
            customers = customerDAO.findAll();
            loger.debug("Service Customer findAll -- " + customers.size());
        } catch (DataAccessException e) {
            loger.error("Error was in findAll " + e.getLocalizedMessage());
        }

        return customers;
    }

    @Override
    public void persist(Customer entity) {
        try {
            loger.debug("Service Customer persist -- " + entity);
            customerDAO.persist(entity);
        } catch (DataAccessException e) {
            loger.error("Error was in persist " + e.getMessage());
        }
    }

    @Override
    public void update(Customer entity) {
        try {
            loger.debug("Service Customer update -- " + entity);
            customerDAO.update(entity);
        } catch (DataAccessException e) {
            loger.error("Error was in update " + e.getMessage());
        }

    }

    @Override
    public void delete(Customer entity) {
        try {
            loger.debug("Service Customer delete -- " + entity);
            customerDAO.delete(entity);
        } catch (DataAccessException e) {
            loger.error("Error was in delete " + e.getMessage());
        }

    }
}
