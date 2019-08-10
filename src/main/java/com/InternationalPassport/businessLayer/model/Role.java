package com.InternationalPassport.businessLayer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "role" , schema = "test_schema", uniqueConstraints = {@UniqueConstraint(columnNames = "roleId")})
public class Role implements Serializable {
    @Id
    @SequenceGenerator(name = "r_idr_seq", schema = "test_schema", sequenceName = "r_idr_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "r_idr_seq")
    @Column(name = "roleId")
    private Integer id;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<Customer> customers = new ArrayList<Customer>();

    public Role() { }

    public Role(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", customers=" + customers.size() +
                '}';
    }
}
