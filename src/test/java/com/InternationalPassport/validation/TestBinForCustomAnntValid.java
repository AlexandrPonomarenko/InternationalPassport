package com.InternationalPassport.validation;

public class TestBinForCustomAnntValid {
    @CustomerAgeConstrain
    private Integer age;
    private String name;
    private String password;
    private String city;

    public TestBinForCustomAnntValid() {

    }

    public TestBinForCustomAnntValid(Integer age, String name, String password, String city) {
        this.age = age;
        this.name = name;
        this.password = password;
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "TestBinForCustomAnntValid{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
