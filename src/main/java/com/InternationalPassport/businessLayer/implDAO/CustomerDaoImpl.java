package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.CustomerDAO;
import com.InternationalPassport.businessLayer.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer findByFirstName(String firstName) {
        Customer customer = (Customer) entityManager.createQuery("SELECT c.name FROM Customer AS c WHERE c.name LIKE :name")
            .setParameter("name", firstName)
            .getSingleResult();
        return customer;
    }

    @Override
    public Customer findByLogin(String login) {
//        Customer customer = (Customer) entityManager.createQuery("SELECT name FROM Customer AS c WHERE c.name LIKE :name")
//                .setParameter("name", firstName)
//                .getSingleResult();
//        return customer;
        return  null;
    }

    @Override
    public Customer findByEmail(String email) {
        Customer customer = (Customer) entityManager.createQuery("SELECT c.email FROM Customer AS c WHERE c.email LIKE :email")
            .setParameter("email", email)
            .getSingleResult();
        return customer;
    }

    @Override
    public Customer findById(Integer id) {
        Customer customer = (Customer) entityManager.createQuery("SELECT c.id FROM Customer AS c WHERE c.id LIKE :id")
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
        List<Customer> allCustomer = entityManager.createQuery("FROM Customer", Customer.class)
            .getResultList();
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
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void delete(Customer entity) {
        entityManager.remove(entity);
    }
}
