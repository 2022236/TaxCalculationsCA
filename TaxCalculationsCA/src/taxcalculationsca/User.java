/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculationsca;
import java.util.Date;

/**
 *
 * @author lizam
 */


public class User extends TaxPayer{
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String position;
    private Date birthDate;
    private String email;
    
    
    private commonVariables.Type typeUser;
    private commonVariables.MaritalStatus userMaritalStatus;
    

    public User(String userName, String firstName,String lastName, String password, Date bithDate,String position,commonVariables.Type userType, String email,commonVariables.MaritalStatus userMaritalStatus, float GrossPayment) {
        super(GrossPayment);
        
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.birthDate  = bithDate;
        this.email = email;
        this.password = password;
        this.typeUser = userType;
        this.userMaritalStatus = userMaritalStatus;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public commonVariables.Type getTypeUser() {
        return typeUser;
    }

    public commonVariables.MaritalStatus getUserMaritalStatus() {
        return userMaritalStatus;
    }
    
    public float IcommingTaxCalculator() {
        return this._IcommingTaxCalculator(this.getPosition());
    }
    
    public float NetPaymentCalculator() {
        return this._NetPaymentCalculator(this.getPosition());
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBirthDate(Date bithDate) {
        this.birthDate = bithDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTypeUser(commonVariables.Type typeUser) {
        this.typeUser = typeUser;
    }

    public void setUserMaritalStatus(commonVariables.MaritalStatus userMaritalStatus) {
        this.userMaritalStatus = userMaritalStatus;
    }
    
    public String getAllData() {
        String typeUser = this.getTypeUser().equals(commonVariables.Type.Regular) ? "Regular" : "Admin";
        commonVariables.MaritalStatus maritalUser = this.getUserMaritalStatus();
        String maritalUserString;
        switch (maritalUser) {
            case Single:
                maritalUserString = "Single";
                break;
            case Married:
                maritalUserString = "Married";
                break;
            case widowed:
                maritalUserString = "Widowed";
                break;
            default:
                throw new AssertionError();
        }
        
        
        return "User Name: " + this.getUserName() + "\n" + 
               "First Name: " + this.getFirstName() + "\n" + 
               "Last Name: "  + this.getLastName() + "\n" + 
               "Position: " + this.getPosition() + "\n" + 
               "BirthDate: " + this.getBirthDate() + "\n" + 
               "Email: " + this.getEmail() + "\n" + 
               "Type: " + typeUser + "\n" + 
               "Marital Status: " + maritalUserString + "\n";
    }

    
}
