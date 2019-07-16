package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.PassportDAO;
import com.InternationalPassport.businessLayer.model.Passport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PassportDaoImpl implements PassportDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Passport findById(Integer id) {
        Passport passport = (Passport) entityManager.createQuery("SELECT r.id FROM Passport r WHERE r.id LIKE :id")
                .setParameter("id", id)
                .getSingleResult();
        return passport;
    }

    @Override
    public Passport findByQuery(String query) {
        return null;
    }

    @Override
    public List<Passport> findAll() {
        List<Passport> allPassports = entityManager.createQuery("FROM Role", Passport.class)
                .getResultList();
        return allPassports;
    }

    @Override
    public void persist(Passport entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Passport entity) {
        if(findById(entity.getId()).getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void delete(Passport entity) {
        entityManager.remove(entity);
    }
}
