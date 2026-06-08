public class Payroll {
    private double grossPay;
    private double totalDeductions;
    private double netPay;

    public Payroll() {
        grossPay = 0;
        totalDeductions = 0;
        netPay = 0;
    }

    public double calculateGrossPay(double hoursWorked, double hourlyRate) {
        grossPay = hoursWorked * hourlyRate;
        return grossPay;
    }

    public double calculateNetPay(Deduction deduction) {
        totalDeductions = deduction.getTotalDeductions();
        netPay = grossPay - totalDeductions;
        return netPay;
    }

    public void displayPayrollSummary() {
        System.out.println("Gross Pay: " + grossPay);
        System.out.println("Total Deductions: " + totalDeductions);
        System.out.println("Net Pay: " + netPay);
    }
}