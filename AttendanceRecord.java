public class AttendanceRecord {
    private String date;
    private String timeIn;
    private String timeOut;
    private double hoursWorked;

    public AttendanceRecord(String date, String timeIn, String timeOut, double hoursWorked) {
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.hoursWorked = hoursWorked;
    }

    public void recordTimeIn() {
    System.out.println("Date: " + date);
    System.out.println("Time in recorded: " + timeIn);
}

    public void recordTimeOut() {
        System.out.println("Time out recorded: " + timeOut);
    }
    public double calculateHoursWorked() {
        System.out.println("Hours worked: " + hoursWorked);
        return hoursWorked;
    }
}
