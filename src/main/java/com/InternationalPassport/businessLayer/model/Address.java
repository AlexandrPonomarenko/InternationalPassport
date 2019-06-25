package com.InternationalPassport.businessLayer.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "address", schema = "test_schema", uniqueConstraints = {@UniqueConstraint(columnNames = "addressId") })
public class Address {
    @Id
    @SequenceGenerator(name = "addr_idaddr_seq", sequenceName = "addr_idaddr_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addr_idaddr_seq")
    @Column(name = "addressId")
    private Integer id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "country", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String street;

    @Column(name = "country", nullable = false)
    private Integer numberHome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    private List<Customer> customerList;

    public Address() {
    }

    public Address(Integer id, String country, String city, String street, Integer numberHome) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.numberHome = numberHome;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public long getNumberHome() {
        return numberHome;
    }

    public void setNumberHome(Integer numberHome) {
        this.numberHome = numberHome;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(Customer customer) {
        this.customerList.add(customer);
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
                ", customerList=" + customerList +
                '}';
    }
}
