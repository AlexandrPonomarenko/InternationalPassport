package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.DAO.RoleDAO;
import com.InternationalPassport.businessLayer.model.Role;
import com.InternationalPassport.businessLayer.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    @Autowired
    RoleDAO roleDAO;

    @Override
    public Role findById(Integer id) {
        return roleDAO.findById(id);
    }

    @Override
    public Role findByQuery(String query) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public void persist(Role entity) {
        roleDAO.persist(entity);
    }

    @Override
    public void update(Role entity) {
        roleDAO.update(entity);
    }

    @Override
    public void delete(Role entity) {
        roleDAO.delete(entity);
    }
}
