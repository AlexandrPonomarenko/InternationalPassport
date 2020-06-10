package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.AddressDAO;
import com.InternationalPassport.businessLayer.model.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressDaoImpl extends AbstractPersistenceProducer implements AddressDAO {

    private static final Logger logger = LogManager.getLogger(AddressDaoImpl.class);


    @Override
    public Address findById(Integer id) {
        Address address = null;
        try {
             address = (Address) getEntityManager().createQuery("SELECT a FROM Address a WHERE a.id =:id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e ) {
        }

        return address;
    }

    @Override
    public Address findByQuery(String query) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        List<Address> allAddreses = getEntityManager().createQuery("FROM Address", Address.class)
                .getResultList();
        return allAddreses;
    }

    @Override
    public void persist(Address entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public void update(Address entity) {
        if(findById(entity.getId()) == null) {
            getEntityManager().persist(entity);
        } else {
            getEntityManager().merge(entity);
        }
    }

    @Override
    public void delete(Address entity) {
        getEntityManager().remove(entity);
    }
}
