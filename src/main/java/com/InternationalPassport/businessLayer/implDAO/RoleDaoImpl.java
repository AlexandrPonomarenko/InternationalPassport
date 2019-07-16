package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.RoleDAO;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role findById(Integer id) {
        Role role = (Role) entityManager.createQuery("SELECT r.id FROM Role r WHERE r.id LIKE :id")
            .setParameter("id", id)
            .getSingleResult();
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
    }

    @Override
    public void update(Role entity) {
        if(findById(entity.getId()).getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void delete(Role entity) {
        entityManager.remove(entity);
    }
}
