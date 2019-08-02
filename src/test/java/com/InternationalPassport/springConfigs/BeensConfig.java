package com.InternationalPassport.springConfigs;

import com.InternationalPassport.businessLayer.implDAO.AddressDaoImpl;
import com.InternationalPassport.businessLayer.implDAO.CustomerDaoImpl;
import com.InternationalPassport.businessLayer.implDAO.PassportDaoImpl;
import com.InternationalPassport.businessLayer.implDAO.RoleDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.InternationalPassport.businessLayer.implDAO",
                                "com.InternationalPassport.businessLayer.model",
                                "com.InternationalPassport.businessLayer.service.serviceImpl"})
public class BeensConfig {

//    @Bean
//    public CustomerDaoImpl getImplDaoCustomer(){
//        return new CustomerDaoImpl();
//    }

    @Bean
    public RoleDaoImpl getRoleDaoImpl(){
        return new RoleDaoImpl();
    }

//    @Bean
//    public PassportDaoImpl getPassportDaoImpl(){
//        return new PassportDaoImpl();
//    }

    @Bean
    public AddressDaoImpl getAddressDaoImpl(){
        return new AddressDaoImpl();
    }
}
