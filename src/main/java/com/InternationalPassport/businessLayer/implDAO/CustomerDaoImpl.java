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
public class CustomerDaoImpl extends AbstractPersistenceProducer implements CustomerDAO {

    private static final Logger logger = LogManager.getLogger(CustomerDaoImpl.class);


    @Override
    public List<Customer> findByFirstName(String firstName) {
        List<Customer> customers;
        customers = getEntityManager().createQuery("SELECT c FROM Customer c WHERE c.name = :firstName")
            .setParameter("firstName", firstName).getResultList();
        return customers;
    }

    @Override
    public List<Customer> findByRole(String role) {
        List<Customer> customers = getEntityManager()
                .createQuery("SELECT c FROM Customer c, Role r WHERE  r.role = :role AND c.id = r.id")
                .setParameter("role", role).getResultList();
        return customers;
    }

    @Override
    public Customer findByLogin(String login) {
        Customer customer = (Customer) getEntityManager().createQuery("SELECT c FROM Customer AS c WHERE c.login LIKE :login")
                .setParameter("login", login)
                .getSingleResult();
        return customer;
    }

    @Override
    public Customer findByEmail(String email) {
        Customer customer = (Customer) getEntityManager().createQuery("SELECT c FROM Customer AS c WHERE c.email LIKE :email")
            .setParameter("email", email)
            .getSingleResult();
        return customer;
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = (Customer) getEntityManager().createQuery("SELECT c FROM Customer AS c WHERE c.id =:id")
                .setParameter("id", id)
                .getSingleResult();
        return customer;
    }

    @Override
    public Customer findByQuery(String query) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> allCustomer = getEntityManager().createQuery("FROM Customer", Customer.class)
            .getResultList();
        return allCustomer;
    }

    @Override
    public void persist(Customer entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public void update(Customer entity) {
        if(entity != null) {
            getEntityManager().merge(entity);
        }
    }

    @Override
    public void delete(Customer entity) {
        getEntityManager().remove(entity);
    }
}
