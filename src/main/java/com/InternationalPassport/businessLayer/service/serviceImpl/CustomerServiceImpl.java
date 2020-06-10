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

    private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public List<Customer> findByFirstName(String firstName) {
        List<Customer> customerList = null;
        try {
            customerList = customerDAO.findByFirstName(firstName);
            logger.debug("Service Customer findByFirstName -- " + customerList.size());
        } catch (DataAccessException e ) {
            logger.error("Was error from findByFirstName " + e.getMessage());
        }

        return customerList;
    }

    @Override
    public List<Customer> findByRole(String role) {
        List<Customer> customerList = null;
        try {
            customerList = customerDAO.findByRole(role);
            logger.debug("Service Customer findByRole -- " + customerList.size());
        } catch (DataAccessException e ) {
            logger.error("Was error from findByRole " + e.getMessage());
        }
         return customerList;
    }

    @Override
    public Customer findByLogin(String login) {
        Customer customer = null;
        try{
            customer = customerDAO.findByLogin(login);
            logger.debug("Service Customer findByLogin -- " + customer);
        } catch (DataAccessException e) {
            logger.error("Was error from findByLogin " + e.getMessage());
        }

        return customer;
    }

    @Override
    public Customer findByEmail(String email) {
        Customer customer = null;
        try {
            customer = customerDAO.findByEmail(email);
            logger.debug("Service Customer findByEmail -- " + customer);
        } catch (DataAccessException e){
            logger.error("Was error from findByLogin " + e.getMessage());
        }

        return customer;
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = null;
        try {
            customer = customerDAO.findById(id);
            logger.debug("Service Customer findById -- " + customer);
        } catch (DataAccessException e) {
            logger.error("Error was in findById " + e.getMessage());
        }

        return customer;
    }

    @Override
    public Customer findByIdInitAll(Integer id) {
        Customer customer = null;
        try {
            customer = customerDAO.findById(id);
            logger.debug("Service Customer findById -- " + customer);
        } catch (DataAccessException e) {
            logger.error("Error was in findById " + e.getMessage());
        }

        initAllEntity(customer);

        return customer;
    }

    @Override
    public Customer findByLoginInitAll(String login) {
        Customer customer = null;
        try{
            customer = customerDAO.findByLogin(login);
            logger.debug("Service Customer findByLogin -- " + customer);
        } catch (DataAccessException e) {
            logger.error("Was error from findByLogin " + e.getMessage());
        }

        initAllEntity(customer);

        return customer;
    }

    @Override
    public Customer findByQuery(String query) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = null;
        try {
            customers = customerDAO.findAll();
            logger.debug("Service Customer findAll -- " + customers.size());
        } catch (DataAccessException e) {
            logger.error("Error was in findAll " + e.getLocalizedMessage());
        }

        return customers;
    }

    @Override
    public void persist(Customer entity) {
        try {
            logger.debug("Service Customer persist -- " + entity);
            customerDAO.persist(entity);
        } catch (DataAccessException e) {
            logger.error("Error was in persist " + e.getMessage());
        }
    }

    @Override
    public void update(Customer entity) {
        try {
            logger.debug("Service Customer update -- " + entity);
            customerDAO.update(entity);
        } catch (DataAccessException e) {
            logger.error("Error was in update " + e.getMessage());
        }

    }

    @Override
    public void delete(Customer entity) {
        try {
            logger.debug("Service Customer delete -- " + entity);
            customerDAO.delete(entity);
        } catch (DataAccessException e) {
            logger.error("Error was in delete " + e.getMessage());
        }

    }

    private void initAllEntity(Customer customer) {
        if (customer.getAddress() != null || customer.getPassport() != null) {
            customer.getAddress().getCustomerList().size();
        }
        if (customer.getPassport() != null) {
            customer.getPassport().getId();
        }

        if (customer.getPhotos() != null) {
            customer.getPhotos().size();
        }
    }
}
