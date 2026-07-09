import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MotorPHEmployeeAppGUI {
    private JFrame frame;
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    private JTextField employeeIdField;
    private JTextField nameField;
    private JTextField departmentField;
    private JTextField positionField;
    private JTextField ratePerDayField;
    private JTextField daysWorkedField;
    private JTextField deductionsField;

    public MotorPHEmployeeAppGUI() {
        frame = new JFrame("MotorPH Employee App - Update and Delete Features");

        JLabel titleLabel = new JLabel("MotorPH Employee Record Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        String[] columnNames = {
                "Employee Number", "Name", "Department", "Position",
                "Rate", "Hours/Days Worked", "Deductions", "Gross Pay", "Net Pay"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        employeeTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(employeeTable);

        JLabel employeeIdLabel = new JLabel("Employee Number:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel departmentLabel = new JLabel("Department:");
        JLabel positionLabel = new JLabel("Position:");
        JLabel ratePerDayLabel = new JLabel("Rate:");
        JLabel daysWorkedLabel = new JLabel("Hours/Days Worked:");
        JLabel deductionsLabel = new JLabel("Deductions:");

        employeeIdField = new JTextField();
        nameField = new JTextField();
        departmentField = new JTextField();
        positionField = new JTextField();
        ratePerDayField = new JTextField();
        daysWorkedField = new JTextField();
        deductionsField = new JTextField();

        JButton addButton = new JButton("Add Employee");
        JButton updateButton = new JButton("Update Employee");
        JButton deleteButton = new JButton("Delete Employee");
        JButton clearButton = new JButton("Clear Fields");
        JButton refreshButton = new JButton("Refresh Table");
        JButton computeSalaryButton = new JButton("Compute Salaries");

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        formPanel.add(employeeIdLabel);
        formPanel.add(employeeIdField);

        formPanel.add(nameLabel);
        formPanel.add(nameField);

        formPanel.add(departmentLabel);
        formPanel.add(departmentField);

        formPanel.add(positionLabel);
        formPanel.add(positionField);

        formPanel.add(ratePerDayLabel);
        formPanel.add(ratePerDayField);

        formPanel.add(daysWorkedLabel);
        formPanel.add(daysWorkedField);

        formPanel.add(deductionsLabel);
        formPanel.add(deductionsField);

        formPanel.add(addButton);
        formPanel.add(clearButton);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(2, 2, 10, 10));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 15, 20));

        actionPanel.add(updateButton);
        actionPanel.add(deleteButton);
        actionPanel.add(refreshButton);
        actionPanel.add(computeSalaryButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(actionPanel, BorderLayout.SOUTH);

        frame.setLayout(new BorderLayout());
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addEmployeeRecord());
        updateButton.addActionListener(e -> updateEmployeeRecord());
        deleteButton.addActionListener(e -> deleteEmployeeRecord());
        clearButton.addActionListener(e -> clearFields());
        refreshButton.addActionListener(e -> loadTableData());
        computeSalaryButton.addActionListener(e -> computeSalaries());

        employeeTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                fillFieldsFromSelectedRow();
            }
        });

        EmployeeDataHandler.createFileIfMissing();
        loadTableData();

        frame.setSize(1000, 650);
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

    public void fillFieldsFromSelectedRow() {
        int selectedRow = employeeTable.getSelectedRow();

        if (selectedRow >= 0) {
            employeeIdField.setText(tableModel.getValueAt(selectedRow, 0).toString());
            nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            departmentField.setText(tableModel.getValueAt(selectedRow, 2).toString());
            positionField.setText(tableModel.getValueAt(selectedRow, 3).toString());
            ratePerDayField.setText(tableModel.getValueAt(selectedRow, 4).toString());
            daysWorkedField.setText(tableModel.getValueAt(selectedRow, 5).toString());
            deductionsField.setText(tableModel.getValueAt(selectedRow, 6).toString());

            employeeIdField.setEditable(false);
        }
    }

    public void addEmployeeRecord() {
        try {
            String employeeId = employeeIdField.getText().trim();
            String name = nameField.getText().trim();
            String department = departmentField.getText().trim();
            String position = positionField.getText().trim();
            String ratePerDayText = ratePerDayField.getText().trim();
            String daysWorkedText = daysWorkedField.getText().trim();
            String deductionsText = deductionsField.getText().trim();

            validateEmployeeData(employeeId, name, department, position, ratePerDayText, daysWorkedText,
                    deductionsText);

            if (employeeIdExists(employeeId)) {
                throw new IllegalArgumentException("Employee ID already exists.");
            }

            EmployeeDataHandler.addEmployee(
                    employeeId,
                    name,
                    department,
                    position,
                    ratePerDayText,
                    daysWorkedText,
                    deductionsText);

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
                    "Employee ID, Rate Per Day, Days Worked, and Deductions must be valid numbers.",
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

    public void updateEmployeeRecord() {
        try {
            String employeeId = employeeIdField.getText().trim();
            String name = nameField.getText().trim();
            String department = departmentField.getText().trim();
            String position = positionField.getText().trim();
            String ratePerDayText = ratePerDayField.getText().trim();
            String daysWorkedText = daysWorkedField.getText().trim();
            String deductionsText = deductionsField.getText().trim();

            validateEmployeeData(employeeId, name, department, position, ratePerDayText, daysWorkedText,
                    deductionsText);

            ArrayList<String[]> employees = EmployeeDataHandler.loadEmployees();
            HashMap<String, String[]> employeeMap = UpdateDeleteModule.createEmployeeMap(employees);

            if (!employeeMap.containsKey(employeeId)) {
                throw new IllegalArgumentException("Employee ID does not exist.");
            }

            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to update employee ID " + employeeId + "?",
                    "Confirm Update",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean updated = UpdateDeleteModule.updateRecord(
                        employees,
                        employeeId,
                        name,
                        department,
                        position,
                        ratePerDayText,
                        daysWorkedText,
                        deductionsText);

                if (updated) {
                    EmployeeDataHandler.saveChangesToFile(employees);
                    loadTableData();
                    clearFields();

                    JOptionPane.showMessageDialog(
                            frame,
                            "Employee record updated successfully.",
                            "Record Updated",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Employee ID, Rate Per Day, Days Worked, and Deductions must be valid numbers.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    e.getMessage(),
                    "Update Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteEmployeeRecord() {
        try {
            String employeeId = employeeIdField.getText().trim();

            if (employeeId.isEmpty()) {
                throw new IllegalArgumentException("Please select or enter the employee ID to delete.");
            }

            ArrayList<String[]> employees = EmployeeDataHandler.loadEmployees();
            HashMap<String, String[]> employeeMap = UpdateDeleteModule.createEmployeeMap(employees);

            if (!employeeMap.containsKey(employeeId)) {
                throw new IllegalArgumentException("Employee ID does not exist.");
            }

            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to delete employee ID " + employeeId + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = UpdateDeleteModule.deleteRecord(employees, employeeId);

                if (deleted) {
                    EmployeeDataHandler.saveChangesToFile(employees);
                    loadTableData();
                    clearFields();

                    JOptionPane.showMessageDialog(
                            frame,
                            "Employee record deleted successfully.",
                            "Record Deleted",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    e.getMessage(),
                    "Delete Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void computeSalaries() {
        try {
            int rowCount = tableModel.getRowCount();

            if (rowCount == 0) {
                throw new IllegalArgumentException("No employee records found.");
            }

            double[] ratePerDay = new double[rowCount];
            int[] daysWorked = new int[rowCount];
            double[] deductions = new double[rowCount];

            ArrayList<String[]> updatedEmployees = new ArrayList<String[]>();

            for (int i = 0; i < rowCount; i++) {
                String employeeId = tableModel.getValueAt(i, 0).toString();
                String name = tableModel.getValueAt(i, 1).toString();
                String department = tableModel.getValueAt(i, 2).toString();
                String position = tableModel.getValueAt(i, 3).toString();
                String rateText = tableModel.getValueAt(i, 4).toString();
                String daysText = tableModel.getValueAt(i, 5).toString();
                String deductionsText = tableModel.getValueAt(i, 6).toString();

                if (rateText.isEmpty() || daysText.isEmpty() || deductionsText.isEmpty()) {
                    throw new IllegalArgumentException("Missing salary data found for employee ID: " + employeeId);
                }

                ratePerDay[i] = Double.parseDouble(rateText);
                daysWorked[i] = Integer.parseInt(daysText);
                deductions[i] = Double.parseDouble(deductionsText);

                if (ratePerDay[i] <= 0) {
                    throw new IllegalArgumentException("Invalid rate per day for employee ID: " + employeeId);
                }

                if (daysWorked[i] <= 0) {
                    throw new IllegalArgumentException("Invalid days worked for employee ID: " + employeeId);
                }

                if (deductions[i] < 0) {
                    throw new IllegalArgumentException("Invalid deductions for employee ID: " + employeeId);
                }

                String[] employee = new String[9];
                employee[0] = employeeId;
                employee[1] = name;
                employee[2] = department;
                employee[3] = position;
                employee[4] = rateText;
                employee[5] = daysText;
                employee[6] = deductionsText;
                employee[7] = "0";
                employee[8] = "0";

                updatedEmployees.add(employee);
            }

            double[] grossPay = SalaryComputationModule.computeGrossPay(ratePerDay, daysWorked);
            double[] totalDeductions = SalaryComputationModule.computeDeductions(deductions);
            double[] netPay = SalaryComputationModule.computeNetPay(grossPay, totalDeductions);

            JTextArea output = new JTextArea();
            output.append("=== MotorPH Salary Computation Summary ===\n\n");

            for (int i = 0; i < rowCount; i++) {
                updatedEmployees.get(i)[7] = formatAmount(grossPay[i]);
                updatedEmployees.get(i)[8] = formatAmount(netPay[i]);

                output.append("Employee ID: " + updatedEmployees.get(i)[0] + "\n");
                output.append("Name: " + updatedEmployees.get(i)[1] + "\n");
                output.append("Gross Pay: " + formatAmount(grossPay[i]) + "\n");
                output.append("Deductions: " + formatAmount(totalDeductions[i]) + "\n");
                output.append("Net Pay: " + formatAmount(netPay[i]) + "\n\n");
            }

            double totalGrossPay = SalaryComputationModule.computeTotal(grossPay);
            double totalDeductionsAmount = SalaryComputationModule.computeTotal(totalDeductions);
            double totalNetPay = SalaryComputationModule.computeTotal(netPay);
            double averageNetPay = SalaryComputationModule.computeAverage(netPay);

            output.append("---------------------------------------\n");
            output.append("Total Gross Pay: " + formatAmount(totalGrossPay) + "\n");
            output.append("Total Deductions: " + formatAmount(totalDeductionsAmount) + "\n");
            output.append("Total Net Pay: " + formatAmount(totalNetPay) + "\n");
            output.append("Average Net Pay: " + formatAmount(averageNetPay) + "\n");

            output.setEditable(false);

            EmployeeDataHandler.saveChangesToFile(updatedEmployees);
            loadTableData();

            JOptionPane.showMessageDialog(
                    frame,
                    new JScrollPane(output),
                    "Salary Computation",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Invalid salary data found. Please check rate per day, days worked, and deductions.",
                    "Salary Error",
                    JOptionPane.ERROR_MESSAGE);

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    e.getMessage(),
                    "Salary Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void validateEmployeeData(String employeeId, String name, String department, String position,
            String ratePerDayText, String daysWorkedText, String deductionsText) {

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

        if (ratePerDayText.isEmpty()) {
            throw new IllegalArgumentException("Please enter the rate per day.");
        }

        if (daysWorkedText.isEmpty()) {
            throw new IllegalArgumentException("Please enter the days worked.");
        }

        if (deductionsText.isEmpty()) {
            throw new IllegalArgumentException("Please enter the deductions.");
        }

        int employeeNumber = Integer.parseInt(employeeId);
        double ratePerDay = Double.parseDouble(ratePerDayText);
        int daysWorked = Integer.parseInt(daysWorkedText);
        double deductions = Double.parseDouble(deductionsText);

        if (employeeNumber <= 0) {
            throw new IllegalArgumentException("Employee ID must be greater than zero.");
        }

        if (ratePerDay <= 0) {
            throw new IllegalArgumentException("Rate per day must be greater than zero.");
        }

        if (daysWorked <= 0) {
            throw new IllegalArgumentException("Days worked must be greater than zero.");
        }

        if (deductions < 0) {
            throw new IllegalArgumentException("Deductions cannot be negative.");
        }
    }

    public boolean employeeIdExists(String employeeId) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String existingId = tableModel.getValueAt(i, 0).toString();

            if (existingId.equals(employeeId)) {
                return true;
            }
        }

        return false;
    }

    public String formatAmount(double amount) {
        return String.format("%.2f", amount);
    }

    public void clearFields() {
        employeeIdField.setText("");
        nameField.setText("");
        departmentField.setText("");
        positionField.setText("");
        ratePerDayField.setText("");
        daysWorkedField.setText("");
        deductionsField.setText("");

        employeeIdField.setEditable(true);
        employeeTable.clearSelection();
    }
}