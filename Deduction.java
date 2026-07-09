public class Deduction {
    private double sss;
    private double philHealth;
    private double pagIbig;
    private double withholdingTax;

    public Deduction(double sss, double philHealth, double pagIbig, double withholdingTax) {
        this.sss = sss;
        this.philHealth = philHealth;
        this.pagIbig = pagIbig;
        this.withholdingTax = withholdingTax;
    }

    public double calculateSSS() {
        return sss;
    }

    public double calculatePhilHealth() {
        return philHealth;
    }

    public double calculatePagIbig() {
        return pagIbig;
    }

    public double calculateTax() {
        return withholdingTax;
    }

    public double getTotalDeductions() {
        return sss + philHealth + pagIbig + withholdingTax;
    }
}