package com.InternationalPassport.businessLayer.DAO;

import com.InternationalPassport.businessLayer.model.Role;

import java.util.List;

public interface RoleDAO extends BaseDAO<Role> {

//    Role findByRoleNameAndId(String name, Integer id)

    @Override
    Role findById(Integer id);

    @Override
    Role findByQuery(String query);

    @Override
    List<Role> findAll();

    @Override
    void persist(Role entity);

    @Override
    void update(Role entity);

    @Override
    void delete(Role entity);
}
