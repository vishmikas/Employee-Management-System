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
