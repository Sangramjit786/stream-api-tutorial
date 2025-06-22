package net.javaguides.stream;

public class EmployeeDepartmentDTO {
    private String employeeName;
    private String departmentName;

    public EmployeeDepartmentDTO(String employeeName, String departmentName) {
        this.employeeName = employeeName;
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return employeeName + " - " + departmentName;
    }
}
