import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MotorPHEmployeeAppGUI {
    private JFrame frame;
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    private JTextField employeeIdField;
    private JTextField nameField;
    private JTextField departmentField;
    private JTextField positionField;
    private JTextField salaryField;

    public MotorPHEmployeeAppGUI() {
        frame = new JFrame("MotorPH Employee App - Employee Record Management");

        JLabel titleLabel = new JLabel("MotorPH Employee Records");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        String[] columnNames = { "Employee ID", "Name", "Department", "Position", "Salary" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        employeeTable = new JTable(tableModel);

        JScrollPane tableScrollPane = new JScrollPane(employeeTable);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel departmentLabel = new JLabel("Department:");
        JLabel positionLabel = new JLabel("Position:");
        JLabel salaryLabel = new JLabel("Salary:");

        employeeIdField = new JTextField();
        nameField = new JTextField();
        departmentField = new JTextField();
        positionField = new JTextField();
        salaryField = new JTextField();

        JButton addButton = new JButton("Add Employee");
        JButton clearButton = new JButton("Clear Fields");
        JButton refreshButton = new JButton("Refresh Table");

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        formPanel.add(employeeIdLabel);
        formPanel.add(employeeIdField);

        formPanel.add(nameLabel);
        formPanel.add(nameField);

        formPanel.add(departmentLabel);
        formPanel.add(departmentField);

        formPanel.add(positionLabel);
        formPanel.add(positionField);

        formPanel.add(salaryLabel);
        formPanel.add(salaryField);

        formPanel.add(addButton);
        formPanel.add(clearButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(refreshButton, BorderLayout.SOUTH);

        frame.setLayout(new BorderLayout());
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addEmployeeRecord());
        clearButton.addActionListener(e -> clearFields());
        refreshButton.addActionListener(e -> loadTableData());

        EmployeeDataHandler.createFileIfMissing();
        loadTableData();

        frame.setSize(750, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void loadTableData() {
        tableModel.setRowCount(0);

        ArrayList<String[]> employees = EmployeeDataHandler.loadEmployees();

        for (String[] employee : employees) {
            tableModel.addRow(employee);
        }
    }

    public void addEmployeeRecord() {
        try {
            String employeeId = employeeIdField.getText().trim();
            String name = nameField.getText().trim();
            String department = departmentField.getText().trim();
            String position = positionField.getText().trim();
            String salaryText = salaryField.getText().trim();

            if (employeeId.isEmpty()) {
                throw new IllegalArgumentException("Please enter the employee ID.");
            }

            if (name.isEmpty()) {
                throw new IllegalArgumentException("Please enter the employee name.");
            }

            if (department.isEmpty()) {
                throw new IllegalArgumentException("Please enter the department.");
            }

            if (position.isEmpty()) {
                throw new IllegalArgumentException("Please enter the position.");
            }

            if (salaryText.isEmpty()) {
                throw new IllegalArgumentException("Please enter the salary.");
            }

            int employeeNumber = Integer.parseInt(employeeId);
            double salary = Double.parseDouble(salaryText);

            if (employeeNumber <= 0) {
                throw new IllegalArgumentException("Employee ID must be greater than zero.");
            }

            if (salary <= 0) {
                throw new IllegalArgumentException("Salary must be greater than zero.");
            }

            EmployeeDataHandler.addEmployee(employeeId, name, department, position, salaryText);

            loadTableData();
            clearFields();

            JOptionPane.showMessageDialog(
                    frame,
                    "Employee record added successfully.",
                    "Record Added",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Employee ID and Salary must be valid numbers.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    e.getMessage(),
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearFields() {
        employeeIdField.setText("");
        nameField.setText("");
        departmentField.setText("");
        positionField.setText("");
        salaryField.setText("");
    }
}