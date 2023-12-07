package taxcalculationsca;

public class TaxPayer {
    private String name;
    private double income;
    private double taxPaid;

    
    public TaxPayer(String name, double income, double taxPaid1) {
        this.name = name;
        this.income = income;
        this.taxPaid = 0; // Initial tax paid could be set to 0
    }

    public String getName() {
        return name;
    }

    public double getIncome() {
        return income;
    }

    public double getTaxPaid() {
        return taxPaid;
    }

    
}
