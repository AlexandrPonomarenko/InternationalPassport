package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.RoleDAO;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDAO {

    private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role findById(Integer id) {
        Role role = null;
        try {
//            role = (Role) entityManager.createQuery("SELECT r FROM Role r WHERE r.id=:id")
//                    .setParameter("id", id)
//                    .getSingleResult();

            List<Role> roles = entityManager.createQuery("SELECT r FROM Role r WHERE r.id=:id")
                    .setParameter("id", id)
                    .getResultList();
            if(roles.isEmpty()) {
                logger.debug("RETURN NULL");
                return role;
            }
            role = roles.get(0);
            logger.debug(role + " ROLE RoleDaoImpl " + role.getId());
        } catch (JDBCException e) {
            logger.debug(" NO RESULT EXEPTION" + e);
        }
        return role;
    }

    @Override
    public Role findByQuery(String query) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        List<Role> allRoles = entityManager.createQuery("FROM Role", Role.class)
                .getResultList();
        return allRoles;
    }

    @Override
    public void persist(Role entity) {
        entityManager.persist(entity);
        logger.debug("RoleDaoImpl Save -- " + entity);
    }

    @Override
    public void update(Role entity) {
        if(findById(entity.getId()).getId() == null) {
            entityManager.persist(entity);
            logger.debug("RoleDaoImpl Save  -- " + entity);
        } else {
            entityManager.merge(entity);
            logger.debug("RoleDaoImpl Update  -- " + entity);
        }
    }

    @Override
    public void delete(Role entity) {
        entityManager.remove(entity);
        logger.debug("RoleDaoImpl delete -- " + entity);
    }
}
