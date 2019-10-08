package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.PassportDAO;
import com.InternationalPassport.businessLayer.model.Passport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PassportDaoImpl extends AbstractPersistenceProducer implements PassportDAO {

    private static final Logger logger = LogManager.getLogger(PassportDaoImpl.class);

//    @Autowired
//    private EntityManager entityManager;

    @Override
    public Passport findById(Integer id) {
        Passport passport = null;
        try {
            passport = (Passport) getEntityManager().createQuery("SELECT r FROM Passport r WHERE r.id=:id")
                    .setParameter("id", id)
                    .getSingleResult();
            logger.debug("Passport  findById --- " + passport.toString());
        } catch (JDBCException e) {
            logger.error("Passport  findById  error--- " + e.getMessage());
        }
        return passport;
    }

    @Override
    public Passport findByQuery(String query) {
        return null;
    }

    @Override
    public List<Passport> findAll() {
        List<Passport> allPassports = getEntityManager().createQuery("FROM Passport", Passport.class)
                .getResultList();
        logger.debug("Passport  findAll --- " + allPassports.size());
        return allPassports;
    }

    @Override
    public void persist(Passport entity) {
        getEntityManager().persist(entity);
        logger.debug("Passport  persist --- " + entity.toString());
    }

    @Override
    public void update(Passport entity) {
        if(findById(entity.getId()).getId() == null) {
            getEntityManager().persist(entity);
            logger.debug("Passport  update SAVE --- " + entity);
        } else {
            getEntityManager().merge(entity);
            logger.debug("Passport  update / MERGE --- " + entity);
        }
    }

    @Override
    public void delete(Passport entity) {
        getEntityManager().remove(entity);
        logger.debug("Passport  delete --- " + entity);
    }
}
