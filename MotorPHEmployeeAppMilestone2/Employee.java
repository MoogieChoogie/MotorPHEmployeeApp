public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private double hourlyRate;

    public Employee(int employeeId, String firstName, String lastName, String position, double hourlyRate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.hourlyRate = hourlyRate;
    }

    public void displayEmployeeInfo() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Position: " + position);
        System.out.println("Hourly Rate: " + hourlyRate);
    }

    public void updateEmployeeInfo(String newPosition, double newHourlyRate) {
        position = newPosition;
        hourlyRate = newHourlyRate;
        System.out.println("Employee information updated.");
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}