package com.InternationalPassport.security;

import com.InternationalPassport.businessLayer.DAO.CustomerDAO;
import com.InternationalPassport.businessLayer.model.Customer;

public interface CustomerSecurityRep {
    Customer findByLogin(String login);
}
