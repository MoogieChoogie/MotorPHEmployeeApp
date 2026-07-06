import java.io.*;
import java.util.ArrayList;

public class EmployeeDataHandler {
    private static final String FILE_NAME = "employees.csv";

    public static void createFileIfMissing() {
        File file = new File(FILE_NAME);

        try {
            if (file.createNewFile()) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
                    writer.println("EmployeeID,Name,Department,Position,Salary");
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

                if (data.length == 5) {
                    employees.add(data);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading employee file.");
        }

        return employees;
    }

    public static void addEmployee(String employeeId, String name, String department, String position, String salary) {
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
                writer.println(employeeId + "," + name + "," + department + "," + position + "," + salary);
            }

        } catch (IOException e) {
            System.out.println("Error writing employee record.");
        }
    }
}