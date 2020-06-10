package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.DAO.AddressDAO;
import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.service.AddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

    @Autowired
    AddressDAO addressDAO;

    @Override
    public Address findById(Integer id) {
        Address address = null;
        try {
            address = addressDAO.findById(id);
        } catch (DataAccessException e) {
            logger.error("Address error from findById " + e);
        }

        return address;
    }

    @Override
    public Address findByQuery(String query) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = null;
        try {
            addresses = addressDAO.findAll();
        } catch (DataAccessException e ) {
            logger.error("Error was in  Address findAll --- " + e);
        }
        return addresses;
    }

    @Override
    public void persist(Address entity) {
        try {
            addressDAO.persist(entity);
        }catch (DataAccessException e ) {
            logger.error("Error was service Address persist --- " + e);
        }
    }

    @Override
    public void update(Address entity) {
         try {
             addressDAO.update(entity);
         }catch (DataAccessException e ) {
             logger.error("Service Address update --- " + e);
         }
    }

    @Override
    public void delete(Address entity) {
        try {
            addressDAO.delete(entity);
        } catch (DataAccessException e ) {
            logger.error("Service Address delete --- " + e);
        }

    }
}
