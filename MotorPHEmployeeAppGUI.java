import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MotorPHEmployeeAppGUI {
    private JFrame frame;
    private JTextField employeeNumberField;
    private JTextField employeeNameField;
    private JTextField payCoverageField;

    public MotorPHEmployeeAppGUI() {
        frame = new JFrame("MotorPH Employee App");

        JLabel titleLabel = new JLabel("MotorPH Employee App");
        JLabel employeeNumberLabel = new JLabel("Employee Number:");
        JLabel employeeNameLabel = new JLabel("Employee Name:");
        JLabel payCoverageLabel = new JLabel("Pay Coverage:");

        employeeNumberField = new JTextField();
        employeeNameField = new JTextField();
        payCoverageField = new JTextField();

        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        panel.add(employeeNumberLabel);
        panel.add(employeeNumberField);

        panel.add(employeeNameLabel);
        panel.add(employeeNameField);

        panel.add(payCoverageLabel);
        panel.add(payCoverageField);

        panel.add(submitButton);
        panel.add(clearButton);

        frame.setLayout(new BorderLayout());
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitEmployeeInfo();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void submitEmployeeInfo() {
        try {
            String employeeNumberText = employeeNumberField.getText().trim();
            String employeeName = employeeNameField.getText().trim();
            String payCoverageText = payCoverageField.getText().trim();

            if (employeeNumberText.isEmpty()) {
                throw new IllegalArgumentException("Please enter the employee number.");
            }

            if (employeeName.isEmpty()) {
                throw new IllegalArgumentException("Please enter the employee name.");
            }

            if (payCoverageText.isEmpty()) {
                throw new IllegalArgumentException("Please enter the pay coverage.");
            }

            int employeeNumber = Integer.parseInt(employeeNumberText);
            double payCoverage = Double.parseDouble(payCoverageText);

            if (employeeNumber <= 0) {
                throw new IllegalArgumentException("Employee number must be greater than zero.");
            }

            if (payCoverage <= 0) {
                throw new IllegalArgumentException("Pay coverage must be greater than zero.");
            }

            JOptionPane.showMessageDialog(
                    frame,
                    "Employee information submitted successfully!\n\n" +
                            "Employee Number: " + employeeNumber + "\n" +
                            "Employee Name: " + employeeName + "\n" +
                            "Pay Coverage: " + payCoverage,
                    "Submission Successful",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Employee Number and Pay Coverage must be valid numbers.",
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
        employeeNumberField.setText("");
        employeeNameField.setText("");
        payCoverageField.setText("");

        JOptionPane.showMessageDialog(
                frame,
                "Input fields cleared.",
                "Clear",
                JOptionPane.INFORMATION_MESSAGE);
    }
}