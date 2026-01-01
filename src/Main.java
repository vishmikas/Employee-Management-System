import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        EmployeeSystem system = new EmployeeSystem();
        system.loadFromFile();

        JFrame frame = new JFrame("Employee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));

        JLabel header = new JLabel("Employee Management System", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 26));
        header.setForeground(new Color(0, 102, 204));
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(header, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton addButton = new JButton("1. Add Employee");
        addButton.setFont(new Font("Arial", Font.PLAIN, 16));
        addButton.addActionListener(e -> system.addEmployee());

        JButton viewButton = new JButton("2. View Employees");
        viewButton.setFont(new Font("Arial", Font.PLAIN, 16));
        viewButton.addActionListener(e -> system.viewEmployee());

        JButton searchButton = new JButton("3. Search Employee");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 16));
        searchButton.addActionListener(e -> system.searchEmployee());

        JButton updateButton = new JButton("4. Update Employee");
        updateButton.setFont(new Font("Arial", Font.PLAIN, 16));
        updateButton.addActionListener(e -> system.updateEmployee());

        JButton deleteButton = new JButton("5. Delete Employee");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 16));
        deleteButton.addActionListener(e -> system.deleteEmployee());

        JButton exitButton = new JButton("6. Save and Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        exitButton.addActionListener(e -> {
            system.saveToFile();
            System.exit(0);
        });

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);

    }
}