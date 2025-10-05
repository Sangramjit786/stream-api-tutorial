package net.javaguides.stream;

import java.time.LocalDate;

public class Employee {

    Integer id;
    String firstName;
    String lastName;
    String email;
    String departmentName;
    Double salary;
    char gender;
    Integer departmentId;
    LocalDate date;
    String designation;
    Integer age;
    String city;

    public Employee(String departmentName, Double salary, String city) {
        this.departmentName = departmentName;
        this.salary = salary;
        this.city = city;
    }

    public Employee(Integer age, String departmentName, String firstName) {
        this.age = age;
        this.departmentName = departmentName;
        this.firstName = firstName;
    }

    public Employee(String firstName, String departmentName, Double salary) {
        this.firstName = firstName;
        this.departmentName = departmentName;
        this.salary = salary;
    }

    public Employee(Integer id, String firstName, String departmentName, Double salary, String designation) {
        this.id = id;
        this.firstName = firstName;
        this.departmentName = departmentName;
        this.salary = salary;
        this.designation = designation;
    }

    public Employee(Integer id, String firstName, String lastName, String email, String departmentName, Double salary, char gender, LocalDate date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentName = departmentName;
        this.salary = salary;
        this.gender = gender;
        this.date = date;
    }

    public Employee(Integer id, String firstName, String lastName, String email, String departmentName, Double salary, char gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentName = departmentName;
        this.salary = salary;
        this.gender = gender;
    }

    public Employee(Integer id, String firstName, String lastName, String email, String departmentName, Double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentName = departmentName;
        this.salary = salary;
    }

    public Employee(Integer id, String firstName, String departmentName, Integer departmentId) {
        this.id = id;
        this.firstName = firstName;
        this.departmentName = departmentName;
        this.departmentId = departmentId;
    }

    public Employee(String firstName, Double salary) {
        this.firstName = firstName;
        this.salary = salary;
    }

    public Employee(Integer id, String firstName, Double salary, String departmentName) {
        this.id = id;
        this.firstName = firstName;
        this.departmentName = departmentName;
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
