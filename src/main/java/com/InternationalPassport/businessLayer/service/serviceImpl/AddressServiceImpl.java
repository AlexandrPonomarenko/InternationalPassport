package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.DAO.AddressDAO;
import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.service.AddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("AddressService")
@Transactional
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

    @Autowired
    AddressDAO addressDAO;

    @Override
    public Address findById(Integer id) {
        Address address = addressDAO.findById(id);
        logger.debug("Service Address findById --- " + address);
        return address;
    }

    @Override
    public Address findByQuery(String query) {
//        Address address = addressDAO.findById(id);
//        logger.debug("Service Address findById --- " + address);
        return null;
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = addressDAO.findAll();
        logger.debug("Service Address findAll --- " + addresses);
        return addresses;
    }

    @Override
    public void persist(Address entity) {
        logger.debug("Service Address persist --- " + entity);
        addressDAO.persist(entity);
    }

    @Override
    public void update(Address entity) {
        logger.debug("Service Address update --- " + entity);
        addressDAO.update(entity);
    }

    @Override
    public void delete(Address entity) {
        logger.debug("Service Address delete --- " + entity);
        addressDAO.delete(entity);
    }
}
