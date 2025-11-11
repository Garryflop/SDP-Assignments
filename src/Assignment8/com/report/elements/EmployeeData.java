package Assignment8.com.report.elements;

import Assignment8.com.report.visitor.ReportVisitor;

//EmployeeData - Concrete Element representing employee information.

public class EmployeeData implements ReportElement {
    
    private final String employeeId;
    private final String fullName;
    private final String department;
    private final double salary;
    private final int yearsOfService;
    
    public EmployeeData(String employeeId, String fullName, String department, 
                       double salary, int yearsOfService) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
        this.yearsOfService = yearsOfService;
    }
    
    @Override
    public void accept(ReportVisitor visitor) {
        // Double dispatch: route to visitor's EmployeeData-specific method
        visitor.visitEmployeeData(this);
    }
    
    // Getters for visitor access
    public String getEmployeeId() {
        return employeeId;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public int getYearsOfService() {
        return yearsOfService;
    }
    
    @Override
    public String toString() {
        return String.format("EmployeeData{id='%s', name='%s', dept='%s', salary=$%.2f, years=%d}", 
                           employeeId, fullName, department, salary, yearsOfService);
    }
}
