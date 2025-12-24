import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EmployeeSystem system = new EmployeeSystem();
        system.loadFromFile();

        while (true) {
            System.out.println("*************************************");
            System.out.println("Welcome to Employee Management System");
            System.out.println("************************************");
            System.out.println();
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Save and Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> system.addEmployee();
                case 2 -> system.viewEmployee();
                case 3 -> system.searchEmployee();
                case 4 -> system.updateEmployee();
                case 5 -> system.deleteEmployee();
                case 6 -> {
                    system.saveToFile();
                    System.out.println("Good Bye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }

    }
}