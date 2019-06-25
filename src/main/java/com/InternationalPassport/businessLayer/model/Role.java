package com.InternationalPassport.businessLayer.model;

import javax.persistence.*;
import java.security.PrivateKey;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "role" , schema = "test_schema", uniqueConstraints = {@UniqueConstraint(columnNames = "roleId")})
public class Role {
    @Id
    @SequenceGenerator(name = "r_idr_seq", sequenceName = "r_idr_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "r_idr_seq")
    @Column(name = "roleId")
    private Integer id;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<Customer> customers;

    public Role() { }

    public Role( String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setCustomers(Customer customer) {
//        this.customers = customers;
        customers.add(customer);
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
                ", customers=" + customers +
                '}';
    }
}
