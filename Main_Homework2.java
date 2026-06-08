public class Main_Homework2 {
    public static void main(String[] args) {

        Employee employee = new Employee(1001, "Christian", "Narte", "IT Staff", 150.00);
        AttendanceRecord attendance = new AttendanceRecord("2026-06-08", "8:00 AM", "5:00 PM", 8);
        Deduction deduction = new Deduction(200, 150, 100, 250);
        Payroll payroll = new Payroll();

        EmployeeApp app = new EmployeeApp();
        app.addEmployee(employee);
        app.addAttendanceRecord(attendance);

        System.out.println();

        employee.displayEmployeeInfo();

        System.out.println();

        attendance.recordTimeIn();
        attendance.recordTimeOut();
        double hoursWorked = attendance.calculateHoursWorked();

        System.out.println();

        payroll.calculateGrossPay(hoursWorked, employee.getHourlyRate());
        payroll.calculateNetPay(deduction);

        payroll.displayPayrollSummary();

        System.out.println();

        app.processPayroll();
    }
}
