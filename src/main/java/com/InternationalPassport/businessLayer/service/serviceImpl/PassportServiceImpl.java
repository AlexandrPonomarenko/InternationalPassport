package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.DAO.PassportDAO;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Passport;
import com.InternationalPassport.businessLayer.service.PassportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        Passport passport = null;
        try {
            passport = passportDAO.findById(id);
            logger.debug("Service Passport findById --- " + passport);
        }catch (DataAccessException e ) {
            logger.error("Passport  findById  error--- " + e.getMessage());
        }

        return passport;
    }

    @Override
    public Passport findByQuery(String query) {
//        logger.debug("Service Passport findById --- " + passport);
        return null;
    }

    @Override
    public List<Passport> findAll() {
        List<Passport> passports = null;
        try {
             passports = passportDAO.findAll();
            logger.debug("Service Passport findAll --- " + passports);
        } catch (DataAccessException e ) {
            logger.error("Error Service Passport findAll --- " + e);
        }
        return passports;
    }

    @Override
    public void persist(Passport entity) {
        try {
            logger.debug("Service Passport persist --- " + entity);
            passportDAO.persist(entity);
        } catch (DataAccessException e ) {
            logger.error("Error Service Passport persist --- " + e);
        }

    }

    @Override
    public void update(Passport entity) {
        try {
            logger.debug("Service Passport update --- " + entity);
            passportDAO.update(entity);
        } catch (DataAccessException e ) {
            logger.error( " Error Service Passport update --- " + e);
        }

    }

    @Override
    public void delete(Passport entity) {
        try {
            logger.debug("Service Passport delete --- " + entity);
            passportDAO.delete(entity);
        } catch (DataAccessException e ) {
            logger.debug("Error Service Passport delete --- " + e);
        }


    }
}
