package com.InternationalPassport.businessLayer.model;

import antlr.LexerSharedInputState;
import com.InternationalPassport.validation.CustomerAgeConstrain;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

// TODO schema = test_schema -- for tests
// TODO schema = dev_schema -- for work and tests
// TODO schema = prod_schema -- for deploy
// TODO schema = my_test_schema -- for custom tables

@Entity
@Table(name = "customer", schema = "dev_schema", uniqueConstraints = {@UniqueConstraint(columnNames = "customerId")})
public class Customer implements Serializable {
    @Id
    @SequenceGenerator(name = "cust_idcust_seq", schema = "test_schema", sequenceName = "cust_idcust_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cust_idcust_seq")
    @Column(name = "customerId")
    private Integer id;

    @NotNull
    @Size(min = 3, max = 30, message = "{customer.name.Size}")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 3, max = 30, message = "{customer.patronymic.Size}")
    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @NotNull
    @Size(min = 3, max = 30, message = "{customer.lastName.Size}")
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @NotNull
//    @CustomerAgeConstrain
    @Column(name = "age", nullable = false)
    private Integer age;

    @NotNull
//    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthData", nullable = false)
    private LocalDate birthDate;


    @NotNull
    @Email
//    @Basic
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(min = 5, max = 30, message = "{customer.login.Size}")
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotNull
    @Size(min = 8, max = 256, message = "{customer.password.Size}")
    @Column(name = "password", nullable = false)
    private String password;

//    @NotNull
//    @Size(min = 8, max = 256, message = "{customer.repeatPassword.Size}")
    @Transient
    private String repeatPassword;

    @Column(name = "status", nullable = false)
    private boolean statusAccount = true;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "passport")
    private Passport passport;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address")
    private Address address;

    public Customer () { }

    public Customer(String name, String patronymic, String lastName, Integer age, LocalDate birthDate, String email,
                    String login, String password) {
        this.name = name;
        this.patronymic = patronymic;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public Customer(String name, String patronymic, String lastName, Integer age, LocalDate birthDate, String email,
        String login, String password, String repeatPassword, Role role) {
        this.name = name;
        this.patronymic = patronymic;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.email = email;
        this.login = login;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.role = role;
    }

    public Integer getId() {
        return id;
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

    public Integer getAge() {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public boolean isStatusAccount() {
        return statusAccount;
    }

    public void setStatusAccount(boolean statusAccount) {
        this.statusAccount = statusAccount;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Passport getPassport() {
        return this.passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
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
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) &&
                email.equals(customer.email) &&
                login.equals(customer.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, login);
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
                ", login='" + login + '\'' +
                ", statusAccount='" + statusAccount + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", role=" + role.getId() +
                '}';
    }

    public String toStringLogin() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", statusAccount='" + statusAccount + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }
}
