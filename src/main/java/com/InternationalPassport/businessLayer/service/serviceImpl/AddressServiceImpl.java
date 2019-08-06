package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.DAO.AddressDAO;
import com.InternationalPassport.businessLayer.model.Address;
import com.InternationalPassport.businessLayer.service.AddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AddressService")
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

    @Autowired
    AddressDAO addressDAO;

    @Override
    public Address findById(Integer id) {
        return addressDAO.findById(id);
    }

    @Override
    public Address findByQuery(String query) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        return addressDAO.findAll();
    }

    @Override
    public void persist(Address entity) {
        addressDAO.persist(entity);
    }

    @Override
    public void update(Address entity) {
        addressDAO.update(entity);
    }

    @Override
    public void delete(Address entity) {
        addressDAO.delete(entity);
    }
}
