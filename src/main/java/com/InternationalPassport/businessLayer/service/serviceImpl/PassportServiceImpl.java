package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.DAO.PassportDAO;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Passport;
import com.InternationalPassport.businessLayer.service.PassportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("passportService")
@Transactional
public class PassportServiceImpl implements PassportService {

    private static final Logger logger = LogManager.getLogger(PassportServiceImpl.class);

    @Autowired
    PassportDAO passportDAO;

    @Override
    public Passport findById(Integer id) {
        Passport passport = passportDAO.findById(id);
        logger.debug("Service Passport findById --- " + passport);
        return passport;
    }

    @Override
    public Passport findByQuery(String query) {
//        logger.debug("Service Passport findById --- " + passport);
        return null;
    }

    @Override
    public List<Passport> findAll() {
        List<Passport> passports = passportDAO.findAll();
        logger.debug("Service Passport findAll --- " + passports);
        return passports;
    }

    @Override
    public void persist(Passport entity) {
        logger.debug("Service Passport persist --- " + entity);
        passportDAO.persist(entity);
    }

    @Override
    public void update(Passport entity) {
        logger.debug("Service Passport update --- " + entity);
        passportDAO.update(entity);
    }

    @Override
    public void delete(Passport entity) {
        logger.debug("Service Passport delete --- " + entity);
        passportDAO.delete(entity);
    }
}
