package com.InternationalPassport.businessLayer.model;

import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

// TODO schema = test_schema -- for tests
// TODO schema = dev_schema -- for work and tests
// TODO schema = prod_schema -- for deploy
// TODO schema = my_test_schema -- for custom tables

@Entity
@Table(name = "passport", schema = "dev_schema",  uniqueConstraints = {@UniqueConstraint(columnNames = "passId")})
public class Passport implements Serializable {
    @Id
    @SequenceGenerator(name = "pass_idpass_seq", schema = "test_schema", sequenceName = "pass_idpass_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pass_idpass_seq")
    @Column(name = "passId")
    private Integer id;

    @Column(name = "seria", nullable = false)
    private String seria;

    @Column(name = "type", nullable = false)
    private String type;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    private Customer customer;

    public Passport() { }

    public Passport(String seria, String type) {
        this.seria = seria;
        this.type = type;
    }

    public Passport(String seria, String type, Customer customer) {
        this.seria = seria;
        this.type = type;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passport)) return false;
        Passport passport = (Passport) o;
        return id.equals(passport.id) &&
                seria.equals(passport.seria) &&
                customer.equals(passport.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seria, customer);
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", seria='" + seria + '\'' +
                ", type='" + type + '\'' +
                ", customer=" + customer +
                '}';
    }
}
