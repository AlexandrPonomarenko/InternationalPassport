package com.InternationalPassport.security;

import com.InternationalPassport.businessLayer.implDAO.AbstractPersistenceProducer;
import com.InternationalPassport.businessLayer.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerSecurityRepImpl extends AbstractPersistenceProducer implements CustomerSecurityRep {
    public static final Logger logger = LogManager.getLogger(CustomerSecurityRepImpl.class);

    @Override
    public Customer findByLogin(String login) {
        logger.debug("BEFORE CALL BD " + login);
        Customer customer = (Customer) getEntityManager().createQuery("SELECT c FROM Customer AS c WHERE c.login LIKE :login")
                .setParameter("login", login)
                .getSingleResult();
        logger.debug(" findByLogin from -- CustomerSecurityRepImpl: " + customer.getLogin());
        customer.getRole().getRole();
        if (customer.getAddress() != null) {
            customer.getAddress().getCustomerList().size();
        }

        if (customer.getPassport() != null) {
            customer.getPassport().getId();
        }

        return customer;
    }
}
