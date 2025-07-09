package Core_Java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Employee1 {
    private int id;
    private String name;
    private String department;

    public Employee1(int id, String name, String department) {
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
}


public class EmployeeJDBC {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/jdbc";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "4321";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public void addEmployee(Employee1 employee) {
        String sql = "INSERT INTO employees (id, name, department) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employee.getId());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getDepartment());
            pstmt.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee1> getAllEmployees() {
        List<Employee1> employees = new ArrayList<>();
        String sql = "SELECT id, name, department FROM employees";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                Employee1 employee = new Employee1(id, name, department);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Employee1 getEmployeeById(int id) {
        String sql = "SELECT name, department FROM employees WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String department = rs.getString("department");
                return new Employee1(id, name, department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateEmployee(Employee1 employee) {
        String sql = "UPDATE employees SET name = ?, department = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getDepartment());
            pstmt.setInt(3, employee.getId());
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Employee with ID " + employee.getId() + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee with ID " + id + " deleted.");
            } else {
                System.out.println("Employee with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EmployeeJDBC employeeJDBC = new EmployeeJDBC();

        employeeJDBC.addEmployee(new Employee1(1, "Alice", "HR"));
        employeeJDBC.addEmployee(new Employee1(2, "Bob", "IT"));

        List<Employee1> allEmployees = employeeJDBC.getAllEmployees();
        System.out.println("All Employees:");
        allEmployees.forEach(System.out::println);

        int searchId = 1;
        System.out.println("\nEmployee with ID " + searchId + ":");
        Employee1 foundEmployee = employeeJDBC.getEmployeeById(searchId);
        if (foundEmployee != null) {
            System.out.println(foundEmployee);
        } else {
            System.out.println("Employee not found.");
        }

        Employee1 employeeToUpdate = new Employee1(2, "Robert", "Finance");
        employeeJDBC.updateEmployee(employeeToUpdate);
        System.out.println("\nUpdated Employee with ID " + employeeToUpdate.getId() + ":");
        allEmployees = employeeJDBC.getAllEmployees();
        allEmployees.forEach(System.out::println);

        int deleteId = 1;
        employeeJDBC.deleteEmployee(deleteId);
        System.out.println("\nAfter deleting Employee with ID " + deleteId + ":");
        allEmployees = employeeJDBC.getAllEmployees();
        allEmployees.forEach(System.out::println);
    }
}
