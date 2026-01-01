import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class EmployeeSystem implements EmployeeManager{

    ArrayList<Employee> employees = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void addEmployee() {

        String firstName = JOptionPane.showInputDialog("Enter the first name: ");
        if (firstName == null || firstName.equals("")) {
            return;
        }
        String lastName = JOptionPane.showInputDialog("Enter the last name: ");
        if (lastName == null || lastName.equals("")) {
            return;
        }
        String idStr = JOptionPane.showInputDialog("Enter the employee ID: ");
        if (idStr == null || idStr.equals("")) {
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid employee ID");
            return;
        }
        String department = JOptionPane.showInputDialog("Enter the department name: ");
        if (department == null || department.equals("")) {
            return;
        }
        String salaryStr = JOptionPane.showInputDialog("Enter the salary: ");
        if (salaryStr == null || salaryStr.equals("")) {
            return;
        }
        double salary = 0;
        try {
            salary = Double.parseDouble(salaryStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid salary");
        }

        employees.add(new Employee(firstName, lastName, id, department, salary));
    }

    @Override
    public void viewEmployee() {

        if (employees.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There is no employee with this ID");
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (Employee employee : employees) {
            builder.append(employee.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, builder.toString());
    }

    @Override
    public void searchEmployee() {

        String optionStr = JOptionPane.showInputDialog("Search Employee\n 1. By ID\n 2. By Name\n Enter your option(1/2): ");
        if (optionStr == null || optionStr.equals("")) {
            return;
        }
        int option = 0;
        try {
            option = Integer.parseInt(optionStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Input");
            return;
        }

        if (option == 1) {
            String idStr = JOptionPane.showInputDialog("Enter the employee ID: ");
            if (idStr == null || idStr.equals("")) {
                return;
            }
            int id;
            try {
                id = Integer.parseInt(idStr);
            }  catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid Input");
                return;
            }
            for(Employee employee : employees) {
                if (employee.getId() == id) {
                    JOptionPane.showMessageDialog(null, "Employee found!\n" + employee.toString());
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Employee not found!");
        }
        else if (option == 2) {
            String name = JOptionPane.showInputDialog("Please enter the first name or last name of the employee you want to search:");
            if (name == null) return;
            name = name.toLowerCase();
            for (Employee employee : employees) {
                if (employee.getFirstName().toLowerCase().equals(name) || employee.getLastName().toLowerCase().equals(name)) {
                    JOptionPane.showMessageDialog(null, "Employee found!\n" + employee.toString());
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "There is no such employee with that name");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid input!");
        }
    }

    @Override
    public void updateEmployee() {

        String idStr = JOptionPane.showInputDialog("Enter the ID of the employee you want to update:");
        if (idStr == null) return;
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID");
            return;
        }
        Employee found = null;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                found = employee;
                break;
            }
        }
        if (found == null) {
            JOptionPane.showMessageDialog(null, "Employee not found!");
            return;
        }
        String optionStr = JOptionPane.showInputDialog("Employee found!\nWhat component do you want to update?\n1. First name\n2. Last name\n3. Department\n4. Salary\nEnter your option number:");
        if (optionStr == null) return;
        int option;
        try {
            option = Integer.parseInt(optionStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input!");
            return;
        }
        switch (option) {
            case 1:
                String firstName = JOptionPane.showInputDialog("Please enter the first name of the employee you want to update:");
                if (firstName != null) {
                    found.setFirstName(firstName);
                    JOptionPane.showMessageDialog(null, "First name updated successfully!");
                }
                break;
            case 2:
                String lastName = JOptionPane.showInputDialog("Please enter the last name of the employee you want to update:");
                if (lastName != null) {
                    found.setLastName(lastName);
                    JOptionPane.showMessageDialog(null, "Last name updated successfully!");
                }
                break;
            case 3:
                String department = JOptionPane.showInputDialog("Please enter the Department of the employee you want to update:");
                if (department != null) {
                    found.setDepartment(department);
                    JOptionPane.showMessageDialog(null, "Department updated successfully!");
                }
                break;
            case 4:
                String salaryStr = JOptionPane.showInputDialog("Please enter the Salary of the employee you want to update:");
                if (salaryStr != null) {
                    try {
                        double salary = Double.parseDouble(salaryStr);
                        found.setSalary(salary);
                        JOptionPane.showMessageDialog(null, "Salary updated successfully!");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid Salary!");
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid option!");
        }
        JOptionPane.showMessageDialog(null, "Updated employee successfully!");
    }

    @Override
    public void deleteEmployee() {

        String idStr = JOptionPane.showInputDialog("Enter the ID of the employee you want to delete:");
        if (idStr == null) return;
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input!");
            return;
        }
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employeeToDelete = iterator.next();
            if (employeeToDelete.getId() == id) {
                iterator.remove();
                JOptionPane.showMessageDialog(null, "Employee deleted successfully!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "There is no such employee with that ID");

    }

    @Override
    public void saveToFile() {

        String filePath = "employee.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Employee employee : employees) {
                writer.write(
                        employee.getFirstName() + "," +
                        employee.getLastName() + "," +
                        employee.getId() + "," +
                        employee.getDepartment() + "," +
                        employee.getSalary() + ","
                );
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "File written successfully!");
        }
        catch (FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "File not found!");
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file!");
        }

    }

    @Override
    public void loadFromFile() {

        employees.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("employee.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                String firstName = data[0];
                String lastName = data[1];
                int id = Integer.parseInt(data[2]);
                String department = data[3];
                double salary = Double.parseDouble(data[4]);

                employees.add(new Employee(firstName, lastName, id, department, salary));
            }
            JOptionPane.showMessageDialog(null, "File loaded successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file!");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Error parsing file data!");
        }
    }
}
