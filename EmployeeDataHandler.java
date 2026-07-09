import java.io.*;
import java.util.ArrayList;

public class EmployeeDataHandler {
    private static final String FILE_NAME = "Employees.csv";

    public static void createFileIfMissing() {
        File file = new File(FILE_NAME);

        try {
            if (file.createNewFile()) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
                    writer.println(
                            "EmployeeID,Name,Department,Position,RatePerDay,DaysWorked,Deductions,GrossPay,NetPay");
                }
            }
        } catch (IOException e) {
            System.out.println("Error creating employee file.");
        }
    }

    public static ArrayList<String[]> loadEmployees() {
        ArrayList<String[]> employees = new ArrayList<String[]>();

        createFileIfMissing();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] data = line.split(",");

                if (data.length == 9) {
                    employees.add(data);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading employee file.");
        }

        return employees;
    }

    public static void addEmployee(String employeeId, String name, String department, String position,
            String ratePerDay, String daysWorked, String deductions) {
        createFileIfMissing();

        try {
            File file = new File(FILE_NAME);

            if (file.length() > 0) {
                try (RandomAccessFile randomFile = new RandomAccessFile(file, "r")) {
                    randomFile.seek(file.length() - 1);
                    int lastByte = randomFile.read();

                    if (lastByte != '\n') {
                        try (PrintWriter newLineWriter = new PrintWriter(new FileWriter(FILE_NAME, true))) {
                            newLineWriter.println();
                        }
                    }
                }
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
                writer.println(employeeId + "," + name + "," + department + "," + position + ","
                        + ratePerDay + "," + daysWorked + "," + deductions + ",0,0");
            }

        } catch (IOException e) {
            System.out.println("Error writing employee record.");
        }
    }

    public static void saveChangesToFile(ArrayList<String[]> employees) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println(
                    "Employee Number,Name,Department,Position,Rate,Hours/Days Worked,Deductions,GrossPay,NetPay");

            for (String[] employee : employees) {
                writer.println(employee[0] + "," + employee[1] + "," + employee[2] + "," + employee[3] + ","
                        + employee[4] + "," + employee[5] + "," + employee[6] + "," + employee[7] + "," + employee[8]);
            }

        } catch (IOException e) {
            System.out.println("Error saving employee records.");
        }
    }

    public static void saveEmployees(ArrayList<String[]> employees) {
        saveChangesToFile(employees);
    }
}