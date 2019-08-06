package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.DAO.PassportDAO;
import com.InternationalPassport.businessLayer.model.Passport;
import com.InternationalPassport.businessLayer.service.PassportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PassportService")
public class PassportServiceImpl implements PassportService {

    private static final Logger logger = LogManager.getLogger(PassportServiceImpl.class);

    @Autowired
    PassportDAO passportDAO;

    @Override
    public Passport findById(Integer id) {
        return passportDAO.findById(id);
    }

    @Override
    public Passport findByQuery(String query) {
        return null;
    }

    @Override
    public List<Passport> findAll() {
        return passportDAO.findAll();
    }

    @Override
    public void persist(Passport entity) {
        passportDAO.persist(entity);
    }

    @Override
    public void update(Passport entity) {
        passportDAO.update(entity);
    }

    @Override
    public void delete(Passport entity) {
        passportDAO.delete(entity);
    }
}
