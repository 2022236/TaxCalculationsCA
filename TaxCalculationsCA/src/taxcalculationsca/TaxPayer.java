package taxcalculationsca;

public class TaxPayer {
    private final float GrossPayment;
    private float NetPayment;
    private float USC;
    private float PRSCI;
    private float IcommingTax;
    
    private int totalWeekPerYear;
    private float weekPayment;
    private float basicTax;
    
    private final float personalCredit;
    private final float employeeCredit;
    
    
    public TaxPayer(float GrossPayment) {
        this.GrossPayment = GrossPayment;
        this.personalCredit = 1775;
        this.employeeCredit = 1775;
        this.totalWeekPerYear = 52;
        this.weekPayment = this.GrossPayment/this.totalWeekPerYear;
        this.basicTax = (float) 0.2;
 
    }
    
    protected float _IcommingTaxCalculator(String position ) {
        float credits = this.personalCredit;
        
        
        if(!position.equals("")){
            credits += this.employeeCredit;
        }
        
        this.IcommingTax = (this.GrossPayment * this.basicTax) - credits;
        
        return this.IcommingTax;
    }
    
    public float USCTaxCalculator() {
        float totalTax = 0;
        float leftValue = this.GrossPayment;
        
        if(this.GrossPayment > 13000) {
            leftValue -= 12012;
            totalTax += 12012 * 0.005;
        }
        
        if(leftValue >= 10908) {
            leftValue -= 10908;
            totalTax += 10908 * 0.02;
        }
        
        if(leftValue >= 47124) {
            leftValue -= 47124;
            totalTax += 47124 * 0.045;
        }
        
        if(leftValue > 0) {
            totalTax += leftValue * 0.08;
        }
        
        this.USC = totalTax;
        
        return this.USC;
    }
    
    public float PRSCI ( ){
        float taxPRSIcredit = 0;
        float taxPRSIdiscount = 0;
        
            if(this.weekPayment <= 424) {
                
                taxPRSIcredit += (this.GrossPayment - 352.01) / 6;
                
                if(this.weekPayment >= 352.01 && this.weekPayment <= 424) {
                    if(taxPRSIcredit > 12) {
                        taxPRSIcredit = 12;
                    }
                }
                
            }
            else if(this.weekPayment > 424){
                taxPRSIcredit = 12;
            }
        
        
        if(taxPRSIcredit > 0) {
            taxPRSIdiscount = (float) ((0.04 * this.weekPayment) - taxPRSIcredit);
        } else if(this.weekPayment <= 352) {
            taxPRSIdiscount = (float) 0.0;
        }
        
        this.PRSCI = taxPRSIdiscount * this.totalWeekPerYear;
        
        return this.PRSCI;
    }
    
    protected float _NetPaymentCalculator(String position) {
        float totalCredit  = this.personalCredit;
        
        if(!position.equals(new String(""))) {
            totalCredit += this.employeeCredit;
        }
        
        this.NetPayment = this.GrossPayment - (this.USC + this.PRSCI + this.IcommingTax) + totalCredit;
        
        return this.NetPayment;
    }
}
