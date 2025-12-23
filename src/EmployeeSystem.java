import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeSystem implements EmployeeManager{

    ArrayList<Employee> employees = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void addEmployee() {

        System.out.println("Please enter the first name of the employee you want to add: ");
        String firstName = scanner.nextLine();

        System.out.println("Please enter the last name of the employee you want to add: ");
        String lastName = scanner.nextLine();

        System.out.println("Please enter the ID of the employee you want to add: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please enter the Department of the employee you want to add: ");
        String department = scanner.nextLine();

        System.out.println("Please enter the Salary of the employee you want to add: ");
        double salary = scanner.nextDouble();

        employees.add(new Employee(firstName, lastName, id, department, salary));
        System.out.println("New employee added successfully!");

    }

    @Override
    public void viewEmployee() {

        if (employees.isEmpty()) {
            System.out.println("No employee found!");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

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

    }

    @Override
    public void deleteEmployee() {

    }

    @Override
    public void saveToFile() {

    }

    @Override
    public void loadFromFile() {

    }
}
