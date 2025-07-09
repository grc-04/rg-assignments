package Core_Java;

import java.util.ArrayList;
import java.util.List;

class Employee {
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

public class EmployeeCRUD {
    private List<Employee> employees;

    public EmployeeCRUD() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee getEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public void updateEmployee(int id, String name, String department) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setName(name);
                employee.setDepartment(department);
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    public void deleteEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employees.remove(employee);
                System.out.println("Employee with ID " + id + " deleted.");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    public void printAllEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public static void main(String[] args) {
        EmployeeCRUD employeeCRUD = new EmployeeCRUD();

        employeeCRUD.addEmployee(new Employee(1, "Alice", "HR"));
        employeeCRUD.addEmployee(new Employee(2, "Bob", "IT"));

        List<Employee> allEmployees = employeeCRUD.getAllEmployees();
        System.out.println("All Employees:");
        employeeCRUD.printAllEmployees();

        int searchId = 1;
        System.out.println("\nEmployee with ID " + searchId + ":");
        Employee foundEmployee = employeeCRUD.getEmployeeById(searchId);
        if (foundEmployee != null) {
            System.out.println(foundEmployee);
        } else {
            System.out.println("Employee not found.");
        }

        int updateId = 2;
        employeeCRUD.updateEmployee(updateId, "Robert", "Finance");
        System.out.println("\nUpdated Employee with ID " + updateId + ":");
        employeeCRUD.printAllEmployees();

        int deleteId = 1;
        employeeCRUD.deleteEmployee(deleteId);
        System.out.println("\nAfter deleting Employee with ID " + deleteId + ":");
        employeeCRUD.printAllEmployees();
    }
}
