package com.InternationalPassport.businessLayer.model;

import antlr.LexerSharedInputState;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer", schema = "test_schema", uniqueConstraints = {@UniqueConstraint(columnNames = "customerId")})
public class Customer implements Serializable {
    @Id
    @SequenceGenerator(name = "cust_idcust_seq", sequenceName = "cust_idcust_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cust_idcust_seq")
    @Column(name = "customerId")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "name", nullable = false)
    private String patronymic;

    @Column(name = "name", nullable = false)
    private String lastName;

    @Column(name = "name", nullable = false)
    private Integer age;

    @Column(name = "name", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @Basic
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String repeatPassword;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Passport> passportList;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    public Customer () { }

    public Customer(String name, String patronymic, String lastName, Integer age, LocalDate birthDate, String email,
        String password, Role role) {
        this.name = name;
        this.patronymic = patronymic;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepitePassword() {
        return repeatPassword;
    }

    public void setRepitePassword(String repitePassword) {
        this.repeatPassword = repitePassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Passport> getPassportList() {
        return passportList;
    }

    public void setPassportList(Passport passportList) {
        this.passportList.add(passportList);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) &&
                birthDate.equals(customer.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, birthDate);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", role=" + role +
                ", passportList=" + passportList +
                ", address=" + address +
                '}';
    }
}
