package net.javaguides.stream;

import java.time.LocalDate;

public class Employee {

    Integer id;
    String firstName;
    String lastName;
    String email;
    String departmentName;
    Integer salary;
    char gender;
    Integer departmentId;
    LocalDate date;
    String designation;

//    public Employee(Integer id, String firstName, String departmentName, Integer salary) {
//
//    }


    public Employee(String firstName, String departmentName, Integer salary) {
        this.firstName = firstName;
        this.departmentName = departmentName;
        this.salary = salary;
    }

    public Employee(Integer id, String firstName, String departmentName, Integer salary, String designation) {
        this.id = id;
        this.firstName = firstName;
        this.departmentName = departmentName;
        this.salary = salary;
        this.designation = designation;
    }

    public Employee(Integer id, String firstName, String lastName, String email, String departmentName, Integer salary, char gender, LocalDate date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentName = departmentName;
        this.salary = salary;
        this.gender = gender;
        this.date = date;
    }

    public Employee(Integer id, String firstName, String lastName, String email, String departmentName, Integer salary, char gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentName = departmentName;
        this.salary = salary;
        this.gender = gender;
    }

    public Employee(Integer id, String firstName, String lastName, String email, String departmentName, Integer salary) {
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

    public Employee(String firstName, Integer salary) {
        this.firstName = firstName;
        this.salary = salary;
    }

    public Employee(Integer id, String firstName, Integer salary, String departmentName) {
        this.id = id;
        this.firstName = firstName;
        this.departmentName = departmentName;
        this.salary = salary;
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
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
