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

        System.out.println("Search employee");
        System.out.println("1. By ID");
        System.out.println("2. By Name");
        System.out.println("Enter your option(1/2): ");

        int option = 0;
        int id = 0;

        try{
            option = scanner.nextInt();
            scanner.nextLine();
        }
        catch(Exception e) {
            System.out.println("Invalid input!");
        }

        if (option == 1) {
            System.out.println("Please enter the ID of the employee you want to search: ");

            try{
                id = scanner.nextInt();
            }
            catch (Exception e){
                System.out.println("Invalid ID");
            }

            for (Employee employee : employees) {
                if (employee.getId() == id) {
                    System.out.println("Employee found!");
                    System.out.println(employee.toString());
                    return;
                }
                else {
                    System.out.println("There  is no such employee with that ID");
                }
            }
        }
        else if (option == 2) {
            System.out.println("Please enter the first name or last name of the employee you want to search: ");
            String name = scanner.nextLine().toLowerCase();

            for (Employee employee : employees) {
                if (employee.getFirstName().toLowerCase().equals(name) || employee.getLastName().toLowerCase().equals(name)) {
                    System.out.println("Employee found!");
                    System.out.println(employee.toString());
                    return;
                }
                else {
                    System.out.println("There  is no such employee with that name");
                }
            }
        }
        else {
            System.out.println("Invalid input!");
        }
    }

    @Override
    public void updateEmployee() {

        System.out.println("Enter the ID of the employee you want to update: ");
        int id = 0;

        try{
            id = scanner.nextInt();
        }
        catch(Exception e) {
            System.out.println("Invalid ID");
            return;
        }

        for (Employee employee : employees) {
            if (employee.getId() == id) {
                System.out.println("Employee found!");
                System.out.println("What component do you want to update?");
                System.out.println("1. First name");
                System.out.println("2. Last name");
                System.out.println("3. Department");
                System.out.println("4. Salary");
                System.out.println("Enter your option number: ");

                int option = 0;

                try{
                    option =scanner.nextInt();
                    scanner.nextLine();
                }
                catch (Exception e){
                    System.out.println("Invalid input!");
                    return;
                }

                switch (option) {
                    case 1 :
                        System.out.println("Please enter the first name of the employee you want to update: ");
                        employee.setFirstName(scanner.nextLine());
                        System.out.println("First name updated successfully!");
                        break;

                    case 2 :
                        System.out.println("Please enter the last name of the employee you want to update: ");
                        employee.setLastName(scanner.nextLine());
                        System.out.println("Last name updated successfully!");
                        break;

                    case 3 :
                        System.out.println("Please enter the Department of the employee you want to update: ");
                        employee.setDepartment(scanner.nextLine());
                        System.out.println("Department updated successfully!");
                        break;

                    case 4 :
                        System.out.println("Please enter the Salary of the employee you want to update: ");
                        employee.setSalary(scanner.nextDouble());
                        System.out.println("Salary updated successfully!");
                        break;
                }
            }
        }
        System.out.println("Updated employee successfully!");
        return;
    }

    @Override
    public void deleteEmployee() {

        Iterator<Employee> iterator = employees.iterator();

        System.out.println("Enter the ID of the employee you want to delete: ");

        int id;

        try{
            id = scanner.nextInt();
        }
        catch(Exception e) {
            System.out.println("Invalid input!");
            scanner.nextLine();
            return;
        }

        while (iterator.hasNext()) {

            Employee employeeToDelete = iterator.next();

            if (employeeToDelete.getId() == id) {
                iterator.remove();
                System.out.println("Employee deleted successfully!");
                return;
            }
        }
        System.out.println("There  is no such employee with that ID");

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
            System.out.println("File written successfully!");
        }
        catch (FileNotFoundException e){
            System.out.println("File not found!");
        }
        catch (IOException e) {
            System.out.println("Error writing to file!");
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
            System.out.println("File loaded successfully!");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        catch (IOException e) {
            System.out.println("Error writing to file!");
        }

    }
}
