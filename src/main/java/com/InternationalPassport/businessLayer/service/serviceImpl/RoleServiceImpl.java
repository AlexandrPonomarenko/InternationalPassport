package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.DAO.RoleDAO;
import com.InternationalPassport.businessLayer.model.Role;
import com.InternationalPassport.businessLayer.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("RoleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    @Autowired
    RoleDAO roleDAO;

    @Override
    public Role findById(Integer id) {
        Role role = roleDAO.findById(id);
        logger.debug("Service Role findById ---- " + role);
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
        List<Role> roles = roleDAO.findAll();
        logger.debug("Service Role findAll ---- " + roles);
        return roles;
    }

    @Override
    public void persist(Role entity) {
        logger.debug("Service Role persist ---- " + entity);
        roleDAO.persist(entity);
    }

    @Override
    public void update(Role entity) {
        logger.debug("Service Role update ---- " + entity);
        roleDAO.update(entity);
    }

    @Override
    public void delete(Role entity) {
        logger.debug("Service Role delete ---- " + entity);
        roleDAO.delete(entity);
    }
}
