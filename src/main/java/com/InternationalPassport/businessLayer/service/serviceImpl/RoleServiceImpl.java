package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.DAO.RoleDAO;
import com.InternationalPassport.businessLayer.model.Role;
import com.InternationalPassport.businessLayer.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    @Autowired
    RoleDAO roleDAO;

    @Override
    public Role findById(Integer id) {
        Role role = null;
        try {
            role = roleDAO.findById(id);
            logger.debug("Service Role findById ---- " + role);
        } catch (DataAccessException e) {
            logger.error("Error Service Role findById ---- " + e);
        }

        return role;
    }

    @Override
    public Role findByQuery(String query) {
//        Role role = roleDAO.findById(id);
//        logger.debug("Service Role findByQuery ---- " + role);
        return null;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = null;
        try {
            roles = roleDAO.findAll();
            logger.debug("Service Role findAll ---- " + roles);
        } catch (DataAccessException e ) {
            logger.error("Error Service Role findAll ---- " + e);
        }

        return roles;
    }

    @Override
    public void persist(Role entity) {
        try {
            logger.debug("Service Role persist ---- " + entity);
            roleDAO.persist(entity);
        } catch (DataAccessException e ) {
            logger.error("Error Service Role persist ---- " + e);
        }

    }

    @Override
    public void update(Role entity) {
        try {
            logger.debug("Service Role update ---- " + entity);
            roleDAO.update(entity);
        } catch (DataAccessException e ) {
            logger.error("Error Service Role update ---- " + e);
        }


    }

    @Override
    public void delete(Role entity) {
        try {
            roleDAO.delete(entity);
            logger.debug("Service Role delete ---- " + entity);
        } catch (DataAccessException e) {
            logger.error("Error Service Role delete ---- " + e);
        }
    }
}
