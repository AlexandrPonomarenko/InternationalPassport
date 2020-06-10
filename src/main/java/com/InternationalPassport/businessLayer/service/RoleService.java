package com.InternationalPassport.businessLayer.service;

import com.InternationalPassport.businessLayer.model.Role;

import java.util.List;

public interface RoleService {
    Role findById(Integer id);
    Role findByQuery(String query);
    List<Role> findAll();
    void persist(Role entity);
    void update(Role entity);
    void delete(Role entity);
}
