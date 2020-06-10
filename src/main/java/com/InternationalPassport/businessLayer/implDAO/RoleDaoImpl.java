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
public class RoleDaoImpl extends AbstractPersistenceProducer implements RoleDAO {

    private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    @Override
    public Role findById(Integer id) {
        Role role = null;
        try {
            List<Role> roles = getEntityManager().createQuery("SELECT r FROM Role r WHERE r.id=:id")
                    .setParameter("id", id)
                    .getResultList();
            if(roles.isEmpty()) {
                return role;
            }
            role = roles.get(0);

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
        List<Role> allRoles = getEntityManager().createQuery("FROM Role", Role.class)
                .getResultList();
        return allRoles;
    }

    @Override
    public void persist(Role entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public void update(Role entity) {
        if(findById(entity.getId()).getId() == null) {
            getEntityManager().persist(entity);
        } else {
            getEntityManager().merge(entity);
        }
    }

    @Override
    public void delete(Role entity) {
        getEntityManager().remove(entity);
    }
}
