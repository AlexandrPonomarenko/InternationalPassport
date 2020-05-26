package com.InternationalPassport.businessLayer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO schema = test_schema -- for tests
// TODO schema = dev_schema -- for work and tests
// TODO schema = prod_schema -- for deploy
// TODO schema = my_test_schema -- for custom tables

@Entity
@Table(name = "address", schema = "dev_schema", uniqueConstraints = {@UniqueConstraint(columnNames = "addressId") })
public class Address implements Serializable {
    @Id
    @SequenceGenerator(name = "addr_idaddr_seq", schema = "test_schema", sequenceName = "addr_idaddr_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addr_idaddr_seq")
    @Column(name = "addressId")
    private Integer id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "numberHome", nullable = false)
    private Integer numberHome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL)
    private List<Customer> customerList = new ArrayList<Customer>();

    public Address() {
    }

    public Address(String country, String city, String street, Integer numberHome) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.numberHome = numberHome;
    }

    public Integer getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumberHome() {
        return numberHome;
    }

    public void setNumberHome(Integer numberHome) {
        this.numberHome = numberHome;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customers) {
        this.customerList = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numberHome=" + numberHome +
                ", customerList=" + customerList.size() +
                '}';
    }
}
