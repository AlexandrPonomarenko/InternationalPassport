package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.AddressDAO;
import com.InternationalPassport.businessLayer.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AddressDaoImpl implements AddressDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Address findById(Integer id) {
        Address address = (Address) entityManager.createQuery("SELECT a.id FROM Address a WHERE a.id LIKE :id")
            .setParameter("id", id)
            .getSingleResult();
    }

    @Override
    public Address findByQuery(String query) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        List<Address> allAddreses = entityManager.createQuery("FROM Address", Address.class)
                .getResultList();
        return allAddreses;
    }

    @Override
    public void persist(Address entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Address entity) {
        if(findById(entity.getId()).getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void delete(Address entity) {
        entityManager.remove(entity);
    }
}
