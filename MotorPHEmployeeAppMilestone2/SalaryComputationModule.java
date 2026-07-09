public class SalaryComputationModule {

    public static double[] computeGrossPay(double[] ratePerDay, int[] daysWorked) {
        double[] grossPay = new double[ratePerDay.length];

        for (int i = 0; i < ratePerDay.length; i++) {
            grossPay[i] = ratePerDay[i] * daysWorked[i];
        }

        return grossPay;
    }

    public static double[] computeDeductions(double[] deductions) {
        double[] totalDeductions = new double[deductions.length];

        for (int i = 0; i < deductions.length; i++) {
            totalDeductions[i] = deductions[i];
        }

        return totalDeductions;
    }

    public static double[] computeNetPay(double[] grossPay, double[] deductions) {
        double[] netPay = new double[grossPay.length];

        for (int i = 0; i < grossPay.length; i++) {
            netPay[i] = grossPay[i] - deductions[i];
        }

        return netPay;
    }

    public static double computeTotal(double[] values) {
        double total = 0;

        for (int i = 0; i < values.length; i++) {
            total += values[i];
        }

        return total;
    }

    public static double computeAverage(double[] values) {
        if (values.length == 0) {
            return 0;
        }

        return computeTotal(values) / values.length;
    }
}