import java.util.ArrayList;

public class EmployeeApp {
    private ArrayList<Employee> employeeList;
    private ArrayList<AttendanceRecord> attendanceList;

    public EmployeeApp() {
        employeeList = new ArrayList<Employee>();
        attendanceList = new ArrayList<AttendanceRecord>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        System.out.println("Employee added successfully.");
    }

    public Employee searchEmployee(int employeeId) {
        System.out.println("Searching for employee...");

        if (employeeList.size() > 0) {
            return employeeList.get(0);
        } else {
            return null;
        }
    }

    public void processPayroll() {
        System.out.println("Payroll is being processed.");
    }

    public void addAttendanceRecord(AttendanceRecord record) {
        attendanceList.add(record);
        System.out.println("Attendance record added successfully.");
    }
}