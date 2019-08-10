package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.AddressDAO;
import com.InternationalPassport.businessLayer.model.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressDaoImpl implements AddressDAO {

    private static final Logger logger = LogManager.getLogger(AddressDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public Address findById(Integer id) {
        Address address = null;
        try {
            List<Address> addresses = entityManager.createQuery("SELECT a FROM Address a WHERE a.id =:id")
                    .setParameter("id", id)
                    .getResultList();
            if (addresses.isEmpty()) {
                logger.debug("RETURN NULL  ");
                return address;
            }
            address = addresses.get(0);
        }catch (JDBCException e ) {
            logger.debug("GER EXPT -- " + e);
        }
//        Address address = (Address) entityManager.createQuery("SELECT a FROM Address a WHERE a.id =:id")
//            .setParameter("id", id)
//            .getSingleResult();
        return address;
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
