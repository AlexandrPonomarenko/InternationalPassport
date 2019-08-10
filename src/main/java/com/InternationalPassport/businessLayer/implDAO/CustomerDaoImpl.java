package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.CustomerDAO;
import com.InternationalPassport.businessLayer.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDAO {

    private static final Logger logger = LogManager.getLogger(CustomerDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findByFirstName(String firstName) {
        List<Customer> customers = new ArrayList<Customer>();
        try {
            customers = entityManager.createQuery("SELECT c FROM Customer c WHERE c.name = :firstName")
                .setParameter("firstName", firstName).getResultList();
            logger.debug(customers + " findByFirstName" + customers.getClass());
        } catch (JDBCException e ) {
            logger.error("error from findByFirstName", e);
        }
        return customers;
    }

    @Override
    public Customer findByLogin(String login) {
        Customer customer = (Customer) entityManager.createQuery("SELECT c FROM Customer AS c WHERE c.login LIKE :login")
                .setParameter("login", login)
                .getSingleResult();
        logger.debug(customer + " findByLogin: " + customer.getLogin());
        return customer;
    }

    @Override
    public Customer findByEmail(String email) {
        Customer customer = (Customer) entityManager.createQuery("SELECT c FROM Customer AS c WHERE c.email LIKE :email")
            .setParameter("email", email)
            .getSingleResult();
        logger.debug(customer + " findByEmail:  " + customer.getEmail());
        return customer;
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = (Customer) entityManager.createQuery("SELECT c FROM Customer AS c WHERE c.id =:id")
                .setParameter("id", id)
                .getSingleResult();
        logger.debug(customer + " findById: " + customer.getId());
        return customer;
    }

    @Override
    public Customer findByQuery(String query) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> allCustomer = entityManager.createQuery("FROM Customer", Customer.class)
            .getResultList();
        logger.debug(allCustomer + " findAll: -" + allCustomer.size());
        return allCustomer;
    }

    @Override
    public void persist(Customer entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Customer entity) {
        if(findById(entity.getId()).getId() == null) {
            entityManager.persist(entity);
            logger.debug(" update : - " + entity);
        } else {
            entityManager.merge(entity);
            logger.debug(" update merge : - " + entity);
        }
    }

    @Override
    public void delete(Customer entity) {
        entityManager.remove(entity);
        logger.debug(" delete: - " + entity);
    }
}
